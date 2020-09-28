### 先上效果图
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
    implementation 'com.github.zhoujiulong:KoltinCharts:1.0.1'
### 条形图使用
    <com.example.lib.BarChartView
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
       
### 折线图使用
    <com.example.lib.LineChartView
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
### 曲线图使用
    <com.example.lib.CurveChartView
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
### 饼状图使用
    <com.example.lib.PieChartView
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
