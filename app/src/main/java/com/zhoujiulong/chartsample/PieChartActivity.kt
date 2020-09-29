package com.zhoujiulong.chartsample

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhoujiulong.charts.PieChartBean
import kotlinx.android.synthetic.main.activity_pie_chart.*
import java.math.BigDecimal

/**
 * 饼装图
 */
class PieChartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_chart)

        resetData.setOnClickListener {
            setChartData()
        }

        setChartData()
    }

    private fun setChartData() {
        val list = arrayListOf<PieChartBean>()
        list.add(PieChartBean("微信收款", BigDecimal(30), Color.BLUE))
        list.add(PieChartBean("支付宝收款", BigDecimal(22), Color.GREEN))
        list.add(PieChartBean("余额收款", BigDecimal(21), Color.LTGRAY))
        list.add(PieChartBean("现金收款", BigDecimal(15), Color.CYAN))
        list.add(PieChartBean("标记收款", BigDecimal(7), Color.MAGENTA))
        list.add(PieChartBean("其它收款", BigDecimal(5), Color.DKGRAY))
        pieChartView.mData = list
    }
}































