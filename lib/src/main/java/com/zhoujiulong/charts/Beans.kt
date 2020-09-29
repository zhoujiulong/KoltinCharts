package com.zhoujiulong.charts

import androidx.annotation.ColorInt
import java.math.BigDecimal

data class ChartBean(
    val type: String,//类别
    val data: BigDecimal//数值大小
)

data class PieChartBean(
    val type: String,//类别
    val data: BigDecimal,//数值大小
    @ColorInt val color: Int//颜色
)