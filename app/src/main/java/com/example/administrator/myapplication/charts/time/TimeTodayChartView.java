package com.example.administrator.myapplication.charts.time;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;

import com.example.administrator.myapplication.charts.AbstractChartView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wudashan on 2015/11/18 0018.
 */
public class TimeTodayChartView extends AbstractChartView<TimeTodayChartData>{

    private Path avgPricePath = new Path();
//    private CharView chartView;
    private TimeChartCursor cursor;
    public float height;
    private List<TimeChartCursor> items = new ArrayList<>();
    public float left;
    private float lowerLimit;
    private float maxX;
    private Paint paint;
    private Path path = new Path();
    public float priceAreaBottom;
    private Path priceAreaPath = new Path();
    public float priceAreaTop;
    private String[] priceLabels = new String[5];
    private Path pricePath = new Path();
    private String[] pricePercentLabels = new String[5];
    public float right;
    private float upperLimit;
    public float volumeAreaBottom;
    public float volumeAreaTop;
    private String[] volumeLabels = new String[4];
    public float width;




    @Override
    protected void onCursor(Canvas paramCanvas, TimeTodayChartData paramT, MotionEvent paramMotionEvent) {

    }

    public TimeTodayChartView(Context context, String paramString1, String paramString2) {
        super(context, paramString1, paramString2);
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setTextSize(this.fontSize);
    }

    private void drwaOutline(Canvas paramCanvas, TimeTodayChartData paramTimeTodayChartData, Paint paramPaint){

    }

    public void redrawChart(){

    }

}
