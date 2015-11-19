package com.example.administrator.myapplication.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;


/**
 * Created by wudashan on 2015/11/19 0019.
 */
public abstract class AbstractChartView<T extends ChartData> extends RefreshLoadingView implements View.OnClickListener{

    private static final int LONG_PRESS = 1;
    public static final DashPathEffect gridDashPathEffect = new DashPathEffect(new float[]{5.0f, 5.0f}, 1.0f);
    public int avgPriceLineColor;
    public int axisColor;
    private Canvas canvas;
    protected T chartData;
//    protected ChartView chartView;
    private String code;
    public int downColor;
    private float downX;
    private float downY;
    public float dpUnit;
    public float fontSize;
    private boolean fullscreen = false;
    public int gridColor;
    private boolean hasLongPress = false;
    public float highLowMaxWidth;
    public float highLowMinWidth;
    public float highLowWidth;
    public int indicatorColor;
    public int klineDownColor;
    public int klineUpColor;
    public int klineVolumeDownColor;
    public int klineVolumeUpColor;
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

    private int longPressTimeOut;
    private ScaleGestureDetector mScaleDetector;
    private int mTouchSlopSquare;
    public int ma10LineColor;
    public int ma30LineColor;
    public int ma5LineColor;
    public float marginHorizontal;
    public float marginVertical;
    private String market;
    public float openCloseMinHeight;
    public float openCloseMinWidth;
    public float openCloaseWidth;
    private Paint paint;
    public int priceAreaColor;
    public int priceLineColor;
    public float span;
    public int upColor;
    private boolean zoomMode = false;

    protected abstract void onCursor(Canvas paramCanvas, T paramT, MotionEvent paramMotionEvent);


    public AbstractChartView(Context paramContext) {
        super(paramContext);
    }

    @Override
    public void onClick(View v) {

    }
}
