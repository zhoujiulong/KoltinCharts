package com.example.lib

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.annotation.RequiresApi
import java.math.BigDecimal

/**
 * 折线图
 */
class LineChartView : View {

    var mData: ArrayList<ChartDataBean> = arrayListOf()
        set(value) {
            field = value
            setMeasureData()
            mAni.cancel()
            mAni.start()
        }

    private val mPaint by lazy {
        val paint = Paint()
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true
        paint.strokeWidth = 1F
        paint
    }

    //绘制虚线
    private val mEffectPaint by lazy {
        val paint = Paint()
        val pathEffect = DashPathEffect(floatArrayOf(8F, 8F), 1F)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 1F
        paint.color = Color.parseColor("#B4BBC6")
        paint.isAntiAlias = true
        paint.pathEffect = pathEffect
        paint
    }

    //绘制折线
    private val mPath by lazy { Path() }

    //动画加载进度
    private var mAniProgress: Float = 0F
    private val mAni by lazy {
        val ani = ValueAnimator.ofFloat(0F, 1F)
        ani.duration = 1380
        ani.startDelay = 100
        ani.repeatCount = 0
        ani.interpolator = AccelerateDecelerateInterpolator()
        ani.addUpdateListener {
            val value = it.animatedValue
            if (value is Float) {
                mAniProgress = value
                postInvalidate()
            }
        }
        ani
    }

    private var mFormContentWidth: Float = 0F
    private var mFormContentHeight: Float = 0F

    private var mYTextSize: Float = 20F//Y方向文字大小
    private var mYTextMaxWidth: Float = 120F//Y方向文字最大宽度
    private var mYTextRightMargin: Float = 20F//Y方向文字右侧离表内容的距离
    private var mYTextColor: Int = Color.parseColor("#121822")//Y方向文字颜色

    private var mXTextSize: Float = mYTextSize//X方向文字大小
    private var mXTextTopMargin: Float = mYTextRightMargin//X方向文字顶部离表的距离
    private var mXTextColor: Int = Color.parseColor("#121822")//X方向文字的颜色

    private var mLineTextSize: Float = mYTextSize//条形顶部描述文字的大小
    private var mLineTextBottomMargin = 12F//条形顶部描述文字底部离条形的距离
    private var mLineTextColor: Int = Color.parseColor("#1B4073")//条形顶部文字颜色

    private var mLineColor: Int = Color.parseColor("#000000")//表格折线的颜色
    private var mContentBgColor: Int = Color.parseColor("#88ABCDEF")//表格内容背景颜色
    private var mDotRadius: Float = 4F//折线中点的半径
    private var mDotColor: Int = Color.parseColor("#000000")//折线圆点颜色
    private var mFormLineColor: Int = Color.parseColor("#B4BBC6")//表格线的颜色

    private var mYSpace: Int = 6//y 方向有多少等分
    private var mFormYSpace: Float = 20F //y 方向每等分的高度
    private var mFormXSpace: Float = 20F//X方向每等分的宽度

    //y 方向每等分数值
    private var mYSpaceSize: BigDecimal = BigDecimal(0)
    private var mFormTopEmpty: Float = 20F

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        init(attrs)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
    ) : super(
        context, attrs, defStyleAttr, defStyleRes
    ) {
        init(attrs)
    }

    override fun onDetachedFromWindow() {
        mAni.cancel()
        super.onDetachedFromWindow()
    }

    private fun init(attrs: AttributeSet?) {
        attrs?.apply {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.LineChartView)
            mYTextSize = ta.getDimension(R.styleable.LineChartView_YTextSize, mYTextSize)
            mYTextRightMargin =
                ta.getDimension(R.styleable.LineChartView_YTextRightMargin, mYTextRightMargin)
            mYTextColor = ta.getColor(R.styleable.LineChartView_YTextColor, mYTextColor)
            mXTextSize = ta.getDimension(R.styleable.LineChartView_XTextSize, mXTextSize)
            mXTextTopMargin =
                ta.getDimension(R.styleable.LineChartView_XTextTopMargin, mXTextTopMargin)
            mXTextColor = ta.getColor(R.styleable.LineChartView_XTextColor, mXTextColor)
            mLineTextSize = ta.getDimension(R.styleable.LineChartView_LineTextSize, mLineTextSize)
            mLineTextBottomMargin = ta.getDimension(
                R.styleable.LineChartView_LineTextBottomMargin, mLineTextBottomMargin
            )
            mLineTextColor = ta.getColor(R.styleable.LineChartView_LineTextColor, mLineTextColor)
            mLineColor = ta.getColor(R.styleable.LineChartView_LineColor, mLineColor)
            mContentBgColor = ta.getColor(R.styleable.LineChartView_ContentBgColor, mContentBgColor)
            mDotRadius = ta.getDimension(R.styleable.LineChartView_DotRadius, mDotRadius)
            mDotColor = ta.getColor(R.styleable.LineChartView_DotColor, mDotColor)
            mFormLineColor = ta.getColor(R.styleable.LineChartView_FormLineColor, mFormLineColor)
            ta.recycle()
        }
        mAni.start()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        setMeasureData()
    }

    private fun setMeasureData() {
        initYSpaceSize()
        mYTextMaxWidth = getStringWidth("${getMaxNum().toInt()}", mYTextSize)
        mFormContentWidth =
            measuredWidth - (paddingLeft + paddingRight) - (mYTextMaxWidth + mYTextRightMargin)
        mFormXSpace = mFormContentWidth / mData.size.toFloat()
        mFormContentHeight =
            measuredHeight - (paddingTop + paddingBottom) - (mXTextSize + mXTextTopMargin) - mFormTopEmpty
        mFormYSpace = mFormContentHeight / mYSpace
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (mData.size == 0) return

        drawLefYText(canvas)
        drawFormLine(canvas)
        drawFormContent(canvas)
    }

    /**
     * 绘制 y 方向文字
     */
    private fun drawLefYText(canvas: Canvas) {
        mPaint.color = mYTextColor
        mPaint.textSize = mYTextSize
        val tempX = paddingLeft + mYTextMaxWidth
        val tempY = measuredHeight - paddingBottom - (mXTextSize + mXTextTopMargin)
        var x: Float
        var y: Float
        var str: String
        for (i in 0..mYSpace) {
            str = "${(mYSpaceSize * BigDecimal(i)).toInt()}"
            x = tempX - getStringWidth(str, mYTextSize)
            y = tempY - i * mFormYSpace + mYTextSize / 2
            canvas.drawText(str, x, y, mPaint)
        }
    }

    /**
     * 绘制表格的线
     */
    private fun drawFormLine(canvas: Canvas) {
        mPaint.color = mFormLineColor
        val x = paddingLeft + mYTextMaxWidth + mYTextRightMargin
        var y = measuredHeight - paddingBottom - (mXTextSize + mXTextTopMargin)

        canvas.drawLine(x, y, x, paddingTop.toFloat(), mPaint)
        canvas.drawLine(x, y, (measuredWidth - paddingRight).toFloat(), y, mPaint)

        for (i in 1..mYSpace) {
            y -= mFormYSpace
            mPath.reset()
            mPath.moveTo(x, y)
            mPath.lineTo((measuredWidth - paddingRight).toFloat(), y)
            canvas.drawPath(mPath, mEffectPaint)
        }
    }

    /**
     * 绘制表中的条形以及条形对应的内容
     */
    private fun drawFormContent(canvas: Canvas) {
        if (mData.size == 0) return

        val bottomTextY = (measuredHeight - paddingBottom).toFloat()
        val formContentBottom = bottomTextY - mXTextSize - mXTextTopMargin
        val formContentStartX = paddingLeft + mYTextMaxWidth + mYTextRightMargin

        //折线的点
        val lineDotList = mutableListOf<Dot>()
        //底部文字点
        val bottomStrDotList = mutableListOf<Dot>()
        var tempX: Float
        var tempHeight: Float
        for (i in 0 until mData.size) {
            tempX = formContentStartX + mFormXSpace * (i + 1)
            tempHeight = (mData[i].data * mFormYSpace.toBigDecimal() / mYSpaceSize).toFloat()
            lineDotList.add(Dot(tempX, formContentBottom - tempHeight))
            bottomStrDotList.add(Dot(tempX, bottomTextY))
        }

        //绘制底部文字
        var str: String
        bottomStrDotList.withIndex().forEach {
            //绘制底部文字
            mPaint.textSize = mXTextSize
            mPaint.color = mXTextColor
            str = mData[it.index].type
            canvas.drawText(
                str, it.value.x - getStringWidth(str, mXTextSize) / 2, bottomTextY, mPaint
            )
        }

        //按动画进度绘制表格
        if (mAniProgress < 1) {
            canvas.save()
            val endX = formContentStartX + mFormContentWidth * mAniProgress
            canvas.clipRect(0, 0, endX.toInt(), measuredHeight)
        }

        //绘制折线
        mPath.reset()
        mPath.moveTo(formContentStartX, formContentBottom)
        var previousX = formContentStartX
        var previousY = formContentBottom
        mPaint.color = mLineColor
        lineDotList.forEach {
            mPath.lineTo(it.x, it.y)
            canvas.drawLine(previousX, previousY, it.x, it.y, mPaint)
            previousX = it.x
            previousY = it.y
        }
        if (lineDotList.size > 0) mPath.lineTo(
            lineDotList[lineDotList.size - 1].x, formContentBottom
        )
        mPath.close()
        mPaint.color = mContentBgColor
        canvas.drawPath(mPath, mPaint)

        //绘制折线中的圆点和圆点上的文字
        mPaint.textSize = mLineTextSize
        lineDotList.withIndex().forEach {
            mPaint.color = mDotColor
            canvas.drawCircle(it.value.x, it.value.y, mDotRadius, mPaint)
            mPaint.color = mLineTextColor
            str = mData[it.index].data.toString()
            canvas.drawText(
                str, it.value.x - getStringWidth(str, mLineTextSize) / 2,
                it.value.y - mLineTextBottomMargin, mPaint
            )
        }

        if (mAniProgress < 1) canvas.restore()
    }

    private data class Dot(
        val x: Float,
        val y: Float
    )

    /**
     * 获取 y 方向最大值
     */
    private fun getMaxNum(): BigDecimal {
        var max = BigDecimal(0)
        if (mData.size == 0) return max
        mData.forEach {
            if (max < it.data) max = it.data
        }
        return max
    }

    /**
     * 获取文本的宽度
     */
    private fun getStringWidth(string: String, textSize: Float): Float {
        mPaint.textSize = textSize
        return mPaint.measureText(string)
    }

    /**
     * 设置 Y 方向的每等分的数值
     */
    private fun initYSpaceSize() {
        var max = getMaxNum()
        var temp = BigDecimal(1)

        val temp2 = BigDecimal(50)
        val temp3 = BigDecimal(10)
        while (max >= temp2) {
            max /= temp3
            temp *= temp3
        }

        when {
            max >= BigDecimal(25) -> temp *= BigDecimal(5)
            max >= BigDecimal(15) -> temp *= BigDecimal(3)
            max >= BigDecimal(8) -> temp *= BigDecimal(2)
        }

        mYSpaceSize = temp
        mYSpace = (getMaxNum() / mYSpaceSize).toInt()
        if (max % mYSpaceSize > BigDecimal(0)) mYSpace += 1
    }
}










































