package com.example.administrator.myapplication.charts;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.example.administrator.myapplication.R;


/**
 * Created by wudashan on 2015/11/19 0019.
 */
public abstract class AbstractChartView<T extends ChartData> extends RefreshLoadingView implements View.OnClickListener{

//    private static final int LONG_PRESS = 1;
    public static final DashPathEffect gridDashPathEffect = new DashPathEffect(new float[]{5.0f, 5.0f}, 1.0f);
    public int avgPriceLineColor;
    public int axisColor;
    private Canvas canvas;
    protected T chartData;
    private String code;
    public int downColor;
//    private float downX;
//    private float downY;
    public float dpUnit;
    public int fontColor;
    public float fontSize;
//    private boolean fullscreen = false;
    public int gridColor;
//    private boolean hasLongPress = false;
//    public float highLowMaxWidth;
//    public float highLowMinWidth;
//    public float highLowWidth;
    public int indicatorColor;
//    public int klineDownColor;
//    public int klineUpColor;
//    public int klineVolumeDownColor;
//    public int klineVolumeUpColor;
    public float labelSpan;
    public float loadingFontSize;
    private Vibrator vibrator;

//    private  final Handler longPressHandler = new Handler(){
//        @Override
//        public void handleMessage(Message paramAnonymousMessage) {
//            if (paramAnonymousMessage.what == 1 && paramAnonymousMessage.obj instanceof MotionEvent){
//                AbstractChartView.this.vibrator.vibrate(50);
//                AbstractChartView.this.onCursor(canvas, chartData, (MotionEvent) paramAnonymousMessage.obj);
//
//            }
//        }
//    };

//    private int longPressTimeOut;
//    private ScaleGestureDetector mScaleDetector;
    private int mTouchSlopSquare;
//    public int ma10LineColor;
//    public int ma30LineColor;
//    public int ma5LineColor;
    public float marginHorizontal;
    public float marginVertical;
    private String market;
//    public float openCloseMinHeight;
//    public float openCloseMinWidth;
//    public float openCloaseWidth;
    private Paint paint;
    public int priceAreaColor;
    public int priceLineColor;
    public float span;
    public int upColor;
//    private boolean zoomMode = false;




    public AbstractChartView(Context paramContext, String paramString1, String paramString2) {
        super(paramContext);
        this.market = paramString1;
        this.code = paramString2;
        Resources localResources = paramContext.getResources();
        this.dpUnit = localResources.getDimension(R.dimen.dpUnit);
        this.marginVertical = localResources.getDimension(R.dimen.chart_margin_vertical);
        this.marginHorizontal = localResources.getDimension(R.dimen.chart_margin_horizontal);
        this.span = localResources.getDimension(R.dimen.chart_span);
        this.upColor = localResources.getColor(R.color.chart_red_color);
        this.downColor = localResources.getColor(R.color.chart_green_color);
        this.fontColor = localResources.getColor(R.color.chart_font_color);
        this.fontSize = localResources.getDimension(R.dimen.chart_font_size);
        this.loadingFontSize = localResources.getDimension(R.dimen.chart_loading_font_size);
        this.axisColor = localResources.getColor(R.color.chart_axis_color);
        this.gridColor = localResources.getColor(R.color.chart_grid_color);
        this.indicatorColor = localResources.getColor(R.color.chart_indicator_color);
        this.priceAreaColor = localResources.getColor(R.color.chart_time_price_area_color);
        this.priceLineColor = localResources.getColor(R.color.chart_time_price_line_color);
        this.avgPriceLineColor = localResources.getColor(R.color.chart_time_avg_price_line_color);
        this.labelSpan = 3 * this.dpUnit;
        setOnClickListener(this);
//        this.mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleGestureDetector.OnScaleGestureListener() {
//            @Override
//            public boolean onScale(ScaleGestureDetector detector) {
//                if (chartData != null && detector.getScaleFactor() > 0.01){
//                    onZoom(canvas, chartData, detector.getScaleFactor());
//                    return true;
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onScaleBegin(ScaleGestureDetector detector) {
//                return true;
//            }
//
//            @Override
//            public void onScaleEnd(ScaleGestureDetector detector) {
//            }
//        });
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.vibrator = (Vibrator) paramContext.getSystemService(Context.VIBRATOR_SERVICE);
        ViewConfiguration localViewConfiguration = ViewConfiguration.get(paramContext);
//        this.longPressTimeOut = 400;
        this.mTouchSlopSquare = localViewConfiguration.getScaledTouchSlop() * localViewConfiguration.getScaledTouchSlop();

    }

    public boolean destroy(){
        if (this.chartData != null){
            this.chartData.destroy();
            this.chartData = null;
            return true;
        }
        return false;
    }

    public void drawNoData(){
        this.canvas.drawColor(-1);
        this.paint.setColor(this.fontColor);
        this.paint.setTextSize(this.loadingFontSize);
        this.paint.setTextAlign(Paint.Align.CENTER);
        this.canvas.drawText("暂无数据显示", getWidth() / 2, getHeight() / 2, this.paint);
    }

    private void drawLoading(){
        this.canvas.drawColor(-1);
        this.paint.setColor(this.fontColor);
        this.paint.setTextSize(this.loadingFontSize);
        this.paint.setTextAlign(Paint.Align.CENTER);
        this.canvas.drawText("正在努力加载行情图...", getWidth() / 2, getHeight() / 2, this.paint);
    }

//    private boolean hasMoved(MotionEvent paramMotionEvent){
//        int i = (int) (paramMotionEvent.getX() - this.downX);
//        int j = (int) (paramMotionEvent.getY() - this.downY);
//        return i * i + j * j > this.mTouchSlopSquare;
//    }


    @Override
    protected void onDraw(Canvas paramCanvas) {
        this.canvas = paramCanvas;
        paramCanvas.drawColor(-1);
        if (this.chartData == null){
            drawLoading();
            this.chartData = loadChartData(this.chartData, this.market, this.code);
            return;
        }
        if (this.chartData.noData()){
            drawNoData();
            return;
        }
        if (this.chartData.isEmpty()) {
            drawLoading();
            return;
        }
        initViewData(paramCanvas, this.chartData);
        drawChart(paramCanvas, this.chartData);
//        super.onDraw(canvas);
    }

    @Override
    public void onClick(View v) {
    }

    public void redrawChart(){
        postInvalidate();
        getLoadingViewListener().hiddenAutoLoading(this);
    }

    public void refreshChart(){
        getLoadingViewListener().showAutoLoading(this);
        if (destroy()){
            this.chartData = loadChartData(this.chartData, this.market, this.code);
            return;
        }
        redrawChart();
    }

    public String formatPrice(float paramFloat){
        return ApiStock.getPriceFormatted(paramFloat);
    }

    public String formatVolume(float paramFloat){
        String str = StringHandler.formatDouble(paramFloat, 2, false, false);
        if (str.length() > 5){
            str = StringHandler.formatDouble(paramFloat, 0, false, false);
        }
        return str;
    }



    protected abstract void drawChart(Canvas paramCanvas, T paramT);

    protected abstract void onZoom(Canvas paramCanvas, T paramT, float paramFloat);

    protected abstract void initViewData(Canvas paramCanvas, T paramTimeTodayChartData);

    protected abstract T loadChartData(T paramT, String paramString1, String paramString2);

    protected abstract void onCursor(Canvas paramCanvas, T paramT, MotionEvent paramMotionEvent);

    protected abstract void onMove(Canvas paramCanvas, T paramT, MotionEvent paramMotionEvent);

    protected abstract void onRemoveCursor(Canvas paramCanvas, T paramT, MotionEvent paramMotionEvent);
}
