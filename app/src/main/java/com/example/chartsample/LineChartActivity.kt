package com.example.chartsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lib.ChartDataBean
import kotlinx.android.synthetic.main.activity_line_chart.*
import java.math.BigDecimal

class LineChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line_chart)

        resetData.setOnClickListener {
            setChartData()
        }
        setChartData()
    }

    private fun setChartData() {
        val list = arrayListOf<ChartDataBean>()
        list.add(ChartDataBean("9/1", BigDecimal(10000)))
        list.add(ChartDataBean("9/2", BigDecimal(20000)))
        list.add(ChartDataBean("9/3", BigDecimal(28800)))
        list.add(ChartDataBean("9/4", BigDecimal(39000)))
        list.add(ChartDataBean("9/5", BigDecimal(52000)))
        list.add(ChartDataBean("9/6", BigDecimal(60000)))
        lineChartView.mData = list
    }
}