package com.example.chartsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lib.ChartDataBean
import kotlinx.android.synthetic.main.activity_bar_chart.*
import java.math.BigDecimal

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
        val list = arrayListOf<ChartDataBean>()
        list.add(ChartDataBean("微信", BigDecimal(52000)))
        list.add(ChartDataBean("支付宝", BigDecimal(39000)))
        list.add(ChartDataBean("余额", BigDecimal(28800)))
        list.add(ChartDataBean("现金", BigDecimal(20000)))
        list.add(ChartDataBean("标记收款", BigDecimal(500)))
        barChartView.mData = list
    }
}