package com.example.administrator.myapplication.charts.time;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.View;

import com.example.administrator.myapplication.charts.StringHandler;

/**
 * Created by wudashan on 2015/11/26 0026.
 */
public class TimeTodayCursorView extends View {
    private static final String TAG = "TimeTodayCursorView";
    private TimeTodayChartView chartView;
    private TimeChartCursor cursor;
    private Paint paint = new Paint();
    private Path path = new Path();

    public TimeTodayCursorView(TimeTodayChartView paramTimeTodayChartView){
        super(paramTimeTodayChartView.getContext());
        this.chartView = paramTimeTodayChartView;
    }

    private void drawPercentLabel(Canvas paramCanvas, TimeChartCursor paramTimeChartCursor){
        float f1 = this.chartView.right + this.chartView.labelSpan;
        float f2 = paramTimeChartCursor.priceY - this.chartView.fontSize / 2.0f;
        float f3 = this.chartView.width - this.chartView.marginHorizontal;
        float f4 = f2 + this.chartView.fontSize;
        this.paint.setColor(this.chartView.indicatorColor);
        this.paint.setStyle(Paint.Style.FILL);
        paramCanvas.drawRect(f1, f2, f3, f4, this.paint);
        this.paint.setColor(-1);
        this.paint.setTextAlign(Paint.Align.RIGHT);
        paramCanvas.drawText(StringHandler.formatPercent(paramTimeChartCursor.percentCursor, 2, false, false), f3 - this.chartView.labelSpan, f4 - this.chartView.labelSpan, this.paint);
    }

    private void drawPriceLabel(Canvas paramCanvas, TimeChartCursor paramTimeChartCursor){
        float f1 = this.chartView.marginHorizontal;
        float f2 = paramTimeChartCursor.priceY - this.chartView.fontSize / 2.0f;
        float f3 = this.chartView.left - this.chartView.labelSpan;
        float f4 = f2 + this.chartView.fontSize;
        String str = this.chartView.formatPrice(paramTimeChartCursor.price);
        float f5 = (int) (0.5 + this.paint.measureText(str));
        if (f3 - f5 < f1){
            f1 = 0.0f;
            f3 = f5 + 2.0f * this.chartView.labelSpan;
        }
        this.paint.setColor(this.chartView.indicatorColor);
        this.paint.setStyle(Paint.Style.FILL);
        paramCanvas.drawRect(f1, f2, f3, f4, this.paint);
        this.paint.setColor(-1);
        this.paint.setTextAlign(Paint.Align.RIGHT);
        paramCanvas.drawText(str, f3 - this.chartView.labelSpan, f4 - this.chartView.labelSpan, this.paint);
    }

    private void drawVolumeLabel(Canvas paramCanvas, TimeChartCursor paramTimeChartCursor){
        float f1 = this.chartView.marginHorizontal;
        float f2 = paramTimeChartCursor.volumeY - this.chartView.fontSize / 2.0f;
        float f3 = this.chartView.left - this.chartView.labelSpan;
        float f4 = f2 + this.chartView.fontSize;
        String str = this.chartView.formatVolume(paramTimeChartCursor.volume);
        float f5 = (int) (0.5 + this.paint.measureText(str));
        if (f3 - f5 < f1){
            f1 = 0.0f;
            f3 = f5 + 2.0f * this.chartView.labelSpan;
        }
        this.paint.setColor(this.chartView.indicatorColor);
        this.paint.setStyle(Paint.Style.FILL);
        paramCanvas.drawRect(f1, f2, f3, f4, this.paint);
        this.paint.setColor(-1);
        this.paint.setTextAlign(Paint.Align.RIGHT);
        paramCanvas.drawText(this.chartView.formatVolume(paramTimeChartCursor.volume), f3 - this.chartView.labelSpan, f4 - this.chartView.labelSpan, this.paint);

    }

    private void drawTimeLabel(Canvas canvas, TimeChartCursor cursor) {
        float f1 = this.paint.measureText(cursor.time) / 2.0f + this.chartView.labelSpan;
        float f2 = cursor.x - f1;
        float f3 = Math.max(this.chartView.priceAreaTop - this.chartView.fontSize - this.chartView.labelSpan, 0.0f);
        float f4 = f1 + cursor.x;
        float f5 = f3 + this.chartView.fontSize;
        if (f2 < this.chartView.left){
            f2 = this.chartView.left;
            f4 = this.chartView.left + f1 * 2.0f;
        }
        if (f4 > this.chartView.right){
            f2 = this.chartView.right - f1 * 2.0f;
            f4 = this.chartView.right;
        }
        this.paint.setColor(this.chartView.indicatorColor);
        this.paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(f2, f3, f4, f5, this.paint);
        this.paint.setColor(-1);
        this.paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(cursor.time, f4 - this.chartView.labelSpan, f5 - this.chartView.labelSpan, this.paint);
    }


    public void setCursor(TimeChartCursor cursor) {
        this.cursor = cursor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (this.cursor != null){
            Log.d(TAG, "onDraw");
            canvas.drawColor(0);
            this.paint.setAntiAlias(true);
            this.paint.setColor(this.chartView.indicatorColor);
            this.paint.setTextSize(this.chartView.fontSize);
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setStrokeWidth(this.chartView.dpUnit);
            this.path.reset();
            this.path.moveTo(this.cursor.x, this.chartView.priceAreaTop);
            this.path.lineTo(this.cursor.x, this.chartView.priceAreaBottom);
            this.path.moveTo(this.cursor.x, this.chartView.volumeAreaTop);
            this.path.lineTo(this.cursor.x, this.chartView.volumeAreaBottom);
            this.path.moveTo(this.chartView.left, this.cursor.priceY);
            this.path.lineTo(this.chartView.right, this.cursor.priceY);
            canvas.drawPath(this.path, this.paint);
            this.paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(this.cursor.x, this.cursor.priceY, 4.0f * this.chartView.dpUnit, this.paint);
            canvas.drawCircle(this.cursor.x, this.cursor.avePriceY, 4.0f * this.chartView.dpUnit, this.paint);
            canvas.drawCircle(this.cursor.x, this.cursor.volumeY, 4.0f * this.chartView.dpUnit, this.paint);
            drawPriceLabel(canvas, this.cursor);
            drawPercentLabel(canvas, this.cursor);
            drawVolumeLabel(canvas, this.cursor);
            drawTimeLabel(canvas, this.cursor);
        }
    }

}
