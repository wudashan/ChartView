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

    @Override
    protected void onMove(Canvas paramCanvas, TimeTodayChartData paramT, MotionEvent paramMotionEvent) {

    }

    @Override
    protected void onRemoveCursor(Canvas paramCanvas, TimeTodayChartData paramT, MotionEvent paramMotionEvent) {

    }

    public TimeTodayChartView(Context context, String paramString1, String paramString2) {
        super(context, paramString1, paramString2);
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setTextSize(this.fontSize);
    }

    @Override
    protected void onZoom(Canvas paramCanvas, TimeTodayChartData paramT, float paramFloat) {

    }

    @Override
    protected void initViewData(Canvas paramCanvas, TimeTodayChartData paramT) {

    }

    @Override
    protected TimeTodayChartData loadChartData(TimeTodayChartData paramT, String paramString1, String paramString2) {
        return null;
    }


    public void drawOutline(Canvas paramCanvas, TimeTodayChartData paramTimeTodayChartData, Paint paramPaint){
        paramPaint.setColor(this.axisColor);
        paramPaint.setStyle(Paint.Style.STROKE);
        paramPaint.setStrokeWidth(this.dpUnit);
        float f1 = (this.right - this.left) / (paramTimeTodayChartData.getCount() - 1);
        float f2 = (this.priceAreaBottom - this.priceAreaTop) / 4.0f;
        float f3 = (this.volumeAreaBottom - this.volumeAreaTop) / 3.0f;
        paramCanvas.drawRect(this.left, this.priceAreaTop, this.right, this.priceAreaBottom, paramPaint);
        paramCanvas.drawRect(this.left, this.volumeAreaTop, this.right, this.volumeAreaBottom, paramPaint);
        paramCanvas.drawLine(this.left, this.priceAreaTop + 2.0f * f2, this.right, this.priceAreaTop + 2.0f * f2, paramPaint);
        paramPaint.setColor(this.fontColor);
        paramPaint.setTextSize(this.fontSize);
        paramPaint.setStyle(Paint.Style.FILL);
        float f4 = this.priceAreaBottom + this.fontSize;
        this.paint.reset();
        int[] arrayOfInt = paramTimeTodayChartData.getTimeLabelKeys();
        String[] arrayOfString = paramTimeTodayChartData.getTimeLabelValues();

        for (int i = 0, j = arrayOfInt.length; i < j; i++){
            if (i == 0){
                paramPaint.setTextAlign(Paint.Align.LEFT);
            }
            if (i == j - 1){
                paramPaint.setTextAlign(Paint.Align.RIGHT);
            }
            float f6 = this.left + f1 * (arrayOfInt[i] - 1);
            paramPaint.setTextAlign(Paint.Align.CENTER);
            this.path.moveTo(f6, this.priceAreaTop);
            this.path.lineTo(f6, this.priceAreaBottom);
            this.path.moveTo(f6, this.volumeAreaTop);
            this.path.lineTo(f6, this.volumeAreaBottom);
            paramCanvas.drawText(arrayOfString[i], f6, f4, paramPaint);
        }
        this.path.moveTo(this.left, f2 + this.priceAreaTop);
        this.path.lineTo(this.right, f2 + this.priceAreaTop);
        this.path.moveTo(this.left, this.priceAreaTop + 3.0f * f2);
        this.path.lineTo(this.right, this.priceAreaTop + 3.0f * f2);
        this.path.moveTo(this.left, f3 + this.volumeAreaTop);
        this.path.lineTo(this.right, f3 + this.volumeAreaTop);
        this.path.moveTo(this.left, this.volumeAreaTop + 2.0f * f3);
        this.path.lineTo(this.right, this.volumeAreaTop + 2.0f * f3);
        paramPaint.setColor(this.gridColor);
        paramPaint.setStyle(Paint.Style.STROKE);
        paramPaint.setStrokeWidth(this.dpUnit);
        paramPaint.setPathEffect(gridDashPathEffect);
        paramCanvas.drawPath(this.path, paramPaint);

        paramPaint.setPathEffect(null);
        float f5 = this.fontSize / 2.0f - 2.0f * dpUnit;
        paramPaint.setTextSize(this.fontSize);
        paramPaint.setStyle(Paint.Style.FILL);
        paramPaint.setTextAlign(Paint.Align.RIGHT);
        paramPaint.setColor(this.upColor);
        paramCanvas.drawText(this.priceLabels[0], this.left - this.span, f5 + this.priceAreaTop, paramPaint);
        paramCanvas.drawText(this.priceLabels[1], this.left - this.span, f2 + f5 + this.priceAreaTop, paramPaint);
        paramPaint.setColor(this.fontColor);
        paramCanvas.drawText(this.priceLabels[2], this.left - this.span, f5 + this.priceAreaTop + 2.0f * f2, paramPaint);
        paramPaint.setColor(this.downColor);
        paramCanvas.drawText(this.priceLabels[3], this.left - this.span, f5 + this.priceAreaTop + 3.0f * f2, paramPaint);
        paramCanvas.drawText(this.priceLabels[4], this.left - this.span, f5 + this.priceAreaBottom, paramPaint);
        paramPaint.setTextAlign(Paint.Align.LEFT);
        paramPaint.setColor(this.upColor);
        paramCanvas.drawText(this.pricePercentLabels[0], this.right + this.span, f5 + this.priceAreaTop, paramPaint);
        paramCanvas.drawText(this.pricePercentLabels[1], this.right + this.span, f2 + f5 + this.priceAreaTop, paramPaint);
        paramPaint.setColor(this.fontColor);
        paramCanvas.drawText(this.pricePercentLabels[2], this.right + this.span, f5 + this.priceAreaTop + 2.0f * f2, paramPaint);
        paramPaint.setColor(this.downColor);
        paramCanvas.drawText(this.pricePercentLabels[3], this.right + this.span, f5 + this.priceAreaTop + 3.0f * f2, paramPaint);
        paramCanvas.drawText(this.pricePercentLabels[4], this.right + this.span, f5 + this.priceAreaBottom, paramPaint);
        paramPaint.setTextAlign(Paint.Align.RIGHT);
        paramPaint.setColor(this.fontColor);
        paramCanvas.drawText(this.volumeLabels[0], this.left - this.span, f5 + this.priceAreaTop, paramPaint);
        paramCanvas.drawText(this.volumeLabels[1], this.left - this.span, f3 + f5 + this.volumeAreaTop, paramPaint);
        paramCanvas.drawText(this.volumeLabels[2], this.left - this.span, f5 + this.volumeAreaTop + 2.0f * f3, paramPaint);
        paramCanvas.drawText(this.volumeLabels[3], this.left - this.span, f5 + this.volumeAreaBottom, paramPaint);


    }

    public void redrawChart(){

    }

    @Override
    protected void drawChart(Canvas paramCanvas, TimeTodayChartData paramT) {

    }

}
