package com.example.administrator.myapplication.charts.time;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;

import com.example.administrator.myapplication.charts.AbstractChartView;
import com.example.administrator.myapplication.charts.StringHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wudashan on 2015/11/18 0018.
 */
public class TimeTodayChartView extends AbstractChartView<TimeTodayChartData> {

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

    public TimeTodayChartView(Context context, String paramString1, String paramString2) {
        super(context, paramString1, paramString2);
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setTextSize(this.fontSize);
    }


    public void drawOutline(Canvas paramCanvas, TimeTodayChartData paramTimeTodayChartData, Paint paramPaint) {

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

        for (int i = 0, j = arrayOfInt.length; i < j; i++) {
            if (i == 0) {
                paramPaint.setTextAlign(Paint.Align.LEFT);
            }
            if (i == j - 1) {
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
        paramCanvas.drawText(this.volumeLabels[0], this.left - this.span, f5 + this.volumeAreaTop, paramPaint);
        paramCanvas.drawText(this.volumeLabels[1], this.left - this.span, f3 + f5 + this.volumeAreaTop, paramPaint);
        paramCanvas.drawText(this.volumeLabels[2], this.left - this.span, f5 + this.volumeAreaTop + 2.0f * f3, paramPaint);
        paramCanvas.drawText(this.volumeLabels[3], this.left - this.span, f5 + this.volumeAreaBottom, paramPaint);


    }

    @Override
    protected void onCursor(Canvas paramCanvas, TimeTodayChartData paramT, MotionEvent paramMotionEvent) {

    }

    @Override
    protected void onMove(Canvas paramCanvas, TimeTodayChartData paramT, MotionEvent paramMotionEvent) {

    }

    @Override
    protected void onRemoveCursor(Canvas paramCanvas, TimeTodayChartData paramT, MotionEvent paramMotionEvent) {

    }


    @Override
    protected void onZoom(Canvas paramCanvas, TimeTodayChartData paramT, float paramFloat) {

    }

    @Override
    protected void initViewData(Canvas paramCanvas, TimeTodayChartData paramTimeTodayChartData) {
        this.width = getWidth();
        this.height = getHeight();
        float f1 = paramTimeTodayChartData.getYestclose();
        float f2 = paramTimeTodayChartData.getHigh();
        float f3 = paramTimeTodayChartData.getLow();
        float f4 = paramTimeTodayChartData.getMaxVolume();
        float f5 = 0.0f;
        float f6 = 0.0f;
        String str1 = paramTimeTodayChartData.getVolumeUnit();
        if (f3 < 0.0f) {
            f5 = f3 - f1;
        }
        if (f2 < 0.0f) {
            f6 = f2 - f1;
        }
        float f7 = Math.abs(f5);
        float f8 = Math.abs(f6);
        float f9 = f7 * 1.02f;
        this.upperLimit = f1 + f9;
        this.lowerLimit = f1 - f9;
        this.priceLabels[0] = formatPrice(this.upperLimit);
        this.priceLabels[1] = formatPrice(this.upperLimit - f9 / 2.0f);
        this.priceLabels[2] = formatPrice(f1);
        this.priceLabels[3] = formatPrice(this.lowerLimit + f9 / 2.0f);
        this.priceLabels[4] = formatPrice(this.lowerLimit);
        String str2 = StringHandler.formatPercent(f9 / f1, 2, false, false);
        String str3 = StringHandler.formatPercent(0.5 * (f9 / f1), 2, false, false);
        this.pricePercentLabels[0] = "+" + str2;
        this.pricePercentLabels[1] = "+" + str3;
        this.pricePercentLabels[2] = "0";
        this.pricePercentLabels[3] = "-" + str3;
        this.pricePercentLabels[4] = "-" + str2;
        float f10 = f4 / 3.0f;
        this.volumeLabels[0] = formatVolume(3.0f * f10);
        this.volumeLabels[1] = formatVolume(2.0f * f10);
        this.volumeLabels[2] = formatVolume(f10);
        this.volumeLabels[3] = str1;
        float f11 = Math.max(Math.max(this.paint.measureText(formatPrice(this.upperLimit)), this.paint.measureText(formatVolume(f4))), this.paint.measureText(str1));
        float f12 = this.marginHorizontal + this.span + this.paint.measureText(this.pricePercentLabels[0]);
        this.left = f11 + this.marginHorizontal + this.span;
        this.right = this.width - f12 - this.dpUnit;
        float f13 = this.fontSize + 3.0f * this.span;
        this.priceAreaTop = this.marginVertical + this.fontSize / 2.0f;
        this.volumeAreaBottom = this.height - this.marginVertical - this.fontSize / 2.0f;
        this.priceAreaBottom = 0.75f * (this.volumeAreaBottom - this.priceAreaTop - f13) + this.priceAreaTop;
        this.volumeAreaTop = f13 + this.priceAreaBottom;
    }

    @Override
    protected TimeTodayChartData loadChartData(TimeTodayChartData paramTimeTodayChartData, String paramString1, String paramString2) {
        if (paramTimeTodayChartData == null) {
            paramTimeTodayChartData = new TimeTodayChartData(this, paramString1, paramString2);
        }
        return paramTimeTodayChartData;
    }


    @Override
    protected void drawChart(Canvas paramCanvas, TimeTodayChartData paramTimeTodayChartData) {
        drawPriceAndVolume(paramCanvas, paramTimeTodayChartData, this.paint);
        drawOutline(paramCanvas, paramTimeTodayChartData, this.paint);
    }

    private void drawPriceAndVolume(Canvas paramCanvas, TimeTodayChartData paramTimeTodayChartData, Paint paramPaint) {

    }

}
