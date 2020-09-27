package com.example.chartsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lib.ChartBean
import kotlinx.android.synthetic.main.activity_line_chart.*
import java.math.BigDecimal

/**
 * 折线图
 */
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
        val list = arrayListOf<ChartBean>()
        list.add(ChartBean("9/1", BigDecimal(30000)))
        list.add(ChartBean("9/2", BigDecimal(21000)))
        list.add(ChartBean("9/3", BigDecimal(42800)))
        list.add(ChartBean("9/4", BigDecimal(39000)))
        list.add(ChartBean("9/5", BigDecimal(63000)))
        list.add(ChartBean("9/6", BigDecimal(38000)))
        lineChartView.mData = list
    }
}