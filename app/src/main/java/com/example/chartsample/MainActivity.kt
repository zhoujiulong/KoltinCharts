package com.example.chartsample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        barChart.setOnClickListener {
            startActivity(Intent(this, BarChartActivity::class.java))
        }

        lineChart.setOnClickListener {
            startActivity(Intent(this, LineChartActivity::class.java))
        }
    }

}



























