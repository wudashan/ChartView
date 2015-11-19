package com.example.administrator.myapplication.charts.time;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.view.MotionEvent;

import com.example.administrator.myapplication.charts.AbstractChartView;

/**
 * Created by wudashan on 2015/11/18 0018.
 */
public class TimeTodayChartView extends AbstractChartView<TimeTodayChartData>{

    private Path avgPricePath = new Path();
//    private CharView chartView;



    @Override
    protected void onCursor(Canvas paramCanvas, TimeTodayChartData paramT, MotionEvent paramMotionEvent) {

    }

    public TimeTodayChartView(Context context) {
        super(context);
    }

    public void redrawChart(){

    }

}
