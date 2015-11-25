#ChartView
一个股价实时显示图控件。
如下图，某日的上证指数的数据：
![image](https://github.com/wudashan/ChartView/blob/master/app/src/main/resultPicture/Image.png)

##如何使用
* 初始化控件
```
VolleyUtils.init(this);
//第二个参数代表沪深股市，第三个参数代表 0 + 股票代码
mTimeTodayChartView = new TimeTodayChartView(this, "HS", "0000001");
```
* 更新数据
```
mTimeTodayChartView.refreshChart();
```
* Demo
```
具体使用可以看MainActivity.java如何使用。
```

##代码分析
* 控件流程图
![image](https://github.com/wudashan/ChartView/blob/master/app/src/main/resultPicture/Image2.png)
* 控件类图
![image](https://github.com/wudashan/ChartView/blob/master/app/src/main/resultPicture/Image3.png)
