package com.zhoujiulong.chartsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhoujiulong.charts.ChartBean
import kotlinx.android.synthetic.main.activity_bar_chart.*
import java.math.BigDecimal

/**
 * 条形图
 */
class BarChartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_chart)

        resetData.setOnClickListener {
            setChartData()
        }

        setChartData()
    }

    private fun setChartData() {
        val list = arrayListOf<ChartBean>()
        list.add(ChartBean("微信", BigDecimal(52000)))
        list.add(ChartBean("支付宝", BigDecimal(39000)))
        list.add(ChartBean("余额", BigDecimal(28800)))
        list.add(ChartBean("现金", BigDecimal(20000)))
        list.add(ChartBean("标记收款", BigDecimal(500)))
        barChartView.mData = list
    }
}