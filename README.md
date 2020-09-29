### 先上效果图，如果无法查看图片请到 https://www.jianshu.com/p/5cdf7d54d4aa 查看展示效果
<img src="https://github.com/zhoujiulong/KoltinCharts/tree/master/pic/bar_chart.gif" width="23%"/><img src="https://github.com/zhoujiulong/KoltinCharts/tree/master/pic/line_chart.gif" width="23%"/><img src="https://github.com/zhoujiulong/KoltinCharts/tree/master/pic/curve_chart.gif" width="23%"/><img src="https://github.com/zhoujiulong/KoltinCharts/tree/master/pic/pie_chart.gif" width="23%"/>
### 添加项目依赖
    1、在项目根路径下的 build.gralde 中添加
    allprojects {
      repositories {
          google()
          jcenter()
          maven { url 'https://jitpack.io' }
      }
    }
    2、在项目中添加下面依赖
    implementation 'com.github.zhoujiulong:KoltinCharts:1.0.3'
### 条形图使用
    <com.zhoujiulong.charts.BarChartView
        android:id="@+id/barChartView"
        android:layout_width="match_parent"
        android:layout_height="300dp"
        android:layout_centerInParent="true"
        android:padding="30dp"
        app:BarColor="#9884FF"
        app:BarRadius="6dp"
        app:BarTopTextBottomMargin="2dp"
        app:BarTopTextColor="#CE0E2D"
        app:BarTopTextSize="6dp"
        app:FormLineColor="#9B9B9B"
        app:XTextColor="#000000"
        app:XTextSize="8dp"
        app:XTextTopMargin="4dp"
        app:YTextColor="#000000"
        app:YTextRightMargin="4dp"
        app:YTextSize="8dp" />
        
        val list = arrayListOf<ChartBean>()
        list.add(ChartBean("微信", BigDecimal(52000)))
        list.add(ChartBean("支付宝", BigDecimal(39000)))
        list.add(ChartBean("余额", BigDecimal(28800)))
        list.add(ChartBean("现金", BigDecimal(20000)))
        list.add(ChartBean("标记收款", BigDecimal(500)))
        barChartView.mData = list
       
### 折线图使用
    <com.zhoujiulong.charts.LineChartView
        android:id="@+id/lineChartView"
        android:layout_width="match_parent"
        android:layout_height="300dp"
        android:layout_centerInParent="true"
        android:padding="30dp"
        app:DotColor="#CE0E2D"
        app:DotRadius="2dp"
        app:FormLineColor="#9B9B9B"
        app:LineColor="#CE0E2D"
        app:LineTextBottomMargin="4dp"
        app:LineTextColor="#CE0E2D"
        app:LineTextSize="6dp"
        app:XTextColor="#000000"
        app:XTextSize="8dp"
        app:XTextTopMargin="4dp"
        app:YTextColor="#000000"
        app:YTextRightMargin="4dp"
        app:YTextSize="8dp" />
        
        val list = arrayListOf<ChartBean>()
        list.add(ChartBean("9/1", BigDecimal(30000)))
        list.add(ChartBean("9/2", BigDecimal(21000)))
        list.add(ChartBean("9/3", BigDecimal(42800)))
        list.add(ChartBean("9/4", BigDecimal(39000)))
        list.add(ChartBean("9/5", BigDecimal(63000)))
        list.add(ChartBean("9/6", BigDecimal(38000)))
        lineChartView.mData = list
        
### 曲线图使用
    <com.zhoujiulong.charts.CurveChartView
        android:id="@+id/curveChartView"
        android:layout_width="match_parent"
        android:layout_height="300dp"
        android:layout_centerInParent="true"
        android:padding="30dp"
        app:DotColor="#CE0E2D"
        app:DotRadius="2dp"
        app:FormLineColor="#9B9B9B"
        app:LineColor="#CE0E2D"
        app:LineTextBottomMargin="4dp"
        app:LineTextColor="#CE0E2D"
        app:LineTextSize="6dp"
        app:XTextColor="#000000"
        app:XTextSize="8dp"
        app:XTextTopMargin="4dp"
        app:YTextColor="#000000"
        app:YTextRightMargin="4dp"
        app:YTextSize="8dp" />
        
        val list = arrayListOf<ChartBean>()
        list.add(ChartBean("9/1", BigDecimal(40000)))
        list.add(ChartBean("9/2", BigDecimal(35000)))
        list.add(ChartBean("9/3", BigDecimal(56800)))
        list.add(ChartBean("9/4", BigDecimal(45000)))
        list.add(ChartBean("9/5", BigDecimal(38000)))
        list.add(ChartBean("9/6", BigDecimal(52000)))
        curveChartView.mData = list
        
### 饼状图使用
    <com.zhoujiulong.charts.PieChartView
        android:id="@+id/pieChartView"
        android:layout_width="match_parent"
        android:layout_height="300dp"
        android:layout_centerInParent="true"
        android:padding="45dp"
        app:PieRadius="100dp"
        app:PieTextColor="#000000"
        app:PieTextSize="8dp"
        app:TextSize="10dp"
        app:TextTopMargin="6dp" />
        
        val list = arrayListOf<PieChartBean>()
        list.add(PieChartBean("微信收款", BigDecimal(30), Color.BLUE))
        list.add(PieChartBean("支付宝收款", BigDecimal(22), Color.GREEN))
        list.add(PieChartBean("余额收款", BigDecimal(21), Color.LTGRAY))
        list.add(PieChartBean("现金收款", BigDecimal(15), Color.CYAN))
        list.add(PieChartBean("标记收款", BigDecimal(7), Color.MAGENTA))
        list.add(PieChartBean("其它收款", BigDecimal(5), Color.DKGRAY))
        pieChartView.mData = list
