package com.example.administrator.myapplication.charts.time;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;

import com.example.administrator.myapplication.charts.AbstractChartView;
import com.example.administrator.myapplication.charts.StringHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wudashan on 2015/11/18 0018.
 */
public class TimeTodayChartView extends AbstractChartView<TimeTodayChartData> {

    private static final String TAG = "TimeTodayChartView";
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

        float yestclose = paramTimeTodayChartData.getYestclose();
        float priceMax = paramTimeTodayChartData.getHigh();
        float priceMin = paramTimeTodayChartData.getLow();
        float volumeMax = paramTimeTodayChartData.getMaxVolume();
        String volumeUnit = paramTimeTodayChartData.getVolumeUnit();
        float differenceMin = priceMin - yestclose;
        float differenceMax = priceMax - yestclose;
        float absMin = Math.abs(differenceMin);
        float absMax = Math.abs(differenceMax);
        if (absMin < absMax) {
            absMin = absMax;
        }
        float difference = absMin * 1.02f;
        this.upperLimit = yestclose + difference;
        this.lowerLimit = yestclose - difference;
        this.priceLabels[0] = formatPrice(this.upperLimit);
        this.priceLabels[1] = formatPrice(this.upperLimit - difference / 2.0f);
        this.priceLabels[2] = formatPrice(yestclose);
        this.priceLabels[3] = formatPrice(this.lowerLimit + difference / 2.0f);
        this.priceLabels[4] = formatPrice(this.lowerLimit);

//        Log.d(TAG, difference + " " + yestclose);
        String totalPercent = StringHandler.formatPercent(difference / yestclose, 2, false, false);
        String halfPercent = StringHandler.formatPercent(0.5 * (difference / yestclose), 2, false, false);
        this.pricePercentLabels[0] = "+" + totalPercent;
//        Log.d(TAG, pricePercentLabels[0]);
        this.pricePercentLabels[1] = "+" + halfPercent;
        this.pricePercentLabels[2] = "0";
        this.pricePercentLabels[3] = "-" + halfPercent;
        this.pricePercentLabels[4] = "-" + totalPercent;

        float volume = volumeMax / 3.0f;
        this.volumeLabels[0] = formatVolume(3.0f * volume);
        this.volumeLabels[1] = formatVolume(2.0f * volume);
        this.volumeLabels[2] = formatVolume(volume);
        this.volumeLabels[3] = volumeUnit;

        float leftBoundary = Math.max(Math.max(this.paint.measureText(formatPrice(this.upperLimit)), this.paint.measureText(formatVolume(volumeMax))), this.paint.measureText(volumeUnit));
        float rightBoundary = this.marginHorizontal + this.span + this.paint.measureText(this.pricePercentLabels[0]);
        this.left = leftBoundary + this.marginHorizontal + this.span;
        this.right = this.width - rightBoundary - this.dpUnit;
        float centerFontArea = this.fontSize + 3.0f * this.span;
        this.priceAreaTop = this.marginVertical + this.fontSize / 2.0f;
        this.volumeAreaBottom = this.height - this.marginVertical - this.fontSize / 2.0f;
        this.priceAreaBottom = 0.75f * (this.volumeAreaBottom - this.priceAreaTop - centerFontArea) + this.priceAreaTop;
        this.volumeAreaTop = centerFontArea + this.priceAreaBottom;
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
        int i = paramTimeTodayChartData.getCount() - 1;
        float f = (right - left) / (float) i;
        float f1 = f / 5.0f;
        float f2 = (priceAreaBottom - priceAreaTop) / (upperLimit - lowerLimit);
        float f3 = (this.volumeAreaBottom - this.volumeAreaTop) / paramTimeTodayChartData.getMaxVolume();
        boolean flag;
        float f4;
        float f5;
        float f6;
        String as[];
        float af[];
        float af1[];
        float af2[];
        int j;
        int k;
        if (upperLimit == lowerLimit) {
            flag = true;
        } else {
            flag = false;
        }
        f4 = priceAreaBottom;
        if (flag) {
            f4 -= (priceAreaBottom - priceAreaTop) / 2.0f;
        }
        pricePath.reset();
        priceAreaPath.reset();
        avgPricePath.reset();
        items.clear();
        f5 = 0.0f;
        f6 = 0.0f;
        as = paramTimeTodayChartData.getTimes();
        af = paramTimeTodayChartData.getPrices();
        af1 = paramTimeTodayChartData.getAvgPrices();
        af2 = paramTimeTodayChartData.getVolumes();
        j = 0;
        k = af.length;
        while (j < k) {
            String s = as[j];
            float f7 = af[j];
            float f8 = af1[j];
            float f9 = af2[j];
            f5 = left + f * (float) j;
            float f10;
            float f11;
            int l;
            float f12;
            float f13;
            if (flag) {
                f10 = f4;
                f11 = f4;
            } else {
                f10 = priceAreaBottom - f2 * (f7 - lowerLimit);
                f11 = priceAreaBottom - f2 * (f8 - lowerLimit);
            }
            if (j == 0) {
                pricePath.moveTo(f5, f10);
                avgPricePath.moveTo(f5, f11);
//                Log.d(TAG, f5 + " " + f10);
            } else {
                pricePath.lineTo(f5, f10);
                avgPricePath.lineTo(f5, f11);
//                Log.d(TAG, f5 + " " + f10);
            }
            if (j == 0) {
                if (f7 >= paramTimeTodayChartData.getYestclose()) {
                    l = upColor;
                } else {
                    l = downColor;
                }
            } else {
                if (f7 >= f6) {
                    l = upColor;
                } else {
                    l = downColor;
                }
            }
            f12 = volumeAreaBottom - f9 * f3;
            f13 = (f7 - paramTimeTodayChartData.getYestclose()) / paramTimeTodayChartData.getYestclose();
            items.add(new TimeChartCursor(f5, f10, f11, f12, f8, f7, s, f13, f13, f9, paramTimeTodayChartData.getTouchVolumeForHs(f9)));
            paramPaint.setColor(l);
            paramPaint.setStyle(Paint.Style.FILL);
            if (j == 0) {
                paramCanvas.drawRect(left, f12, f5 + f1, volumeAreaBottom, paramPaint);
            } else if (j == i) {
                paramCanvas.drawRect(f5 - f1, f12, right, volumeAreaBottom, paramPaint);
            } else {
                paramCanvas.drawRect(f5 - f1, f12, f5 + f1, volumeAreaBottom, paramPaint);
            }
            f6 = f7;
            j++;
        }
        maxX = f5;
        priceAreaPath.addPath(pricePath);
        priceAreaPath.lineTo(maxX, priceAreaBottom);
        priceAreaPath.lineTo(left, priceAreaBottom);
        priceAreaPath.close();
        paramPaint.setColor(priceAreaColor);
        paramPaint.setStyle(Paint.Style.FILL);
        paramCanvas.drawPath(priceAreaPath, paramPaint);

        paramPaint.setColor(priceLineColor);
        paramPaint.setStyle(Paint.Style.STROKE);
        paramPaint.setStrokeWidth(2.0f * dpUnit);
        paramCanvas.drawPath(pricePath, paramPaint);

        if (paramTimeTodayChartData.getHigh() != paramTimeTodayChartData.getLow()) {
            paramPaint.setColor(avgPriceLineColor);
            paramPaint.setStrokeWidth(2.0f * dpUnit);
            paramCanvas.drawPath(avgPricePath, paramPaint);
        }


    }

}
