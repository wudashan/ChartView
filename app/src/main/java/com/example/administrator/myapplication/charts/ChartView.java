package com.example.administrator.myapplication.charts;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.administrator.myapplication.charts.time.TimeChartCursor;
import com.example.administrator.myapplication.charts.time.TimeTodayChartView;
import com.example.administrator.myapplication.charts.time.TimeTodayCursorView;

/**
 * Created by wudashan on 2015/11/27 0027.
 */
public class ChartView extends RelativeLayout implements RefreshLoadingView.LoadingViewListener{
//    private static final String TAG_5TODAY = "TAG_5TODAY";
//    private static final String TAG_KDAY = "TAG_KDAY";
//    private static final String TAG_KMONTH = "TAG_KMONTH";
//    private static final String TAG_KWEEK = "TAG_KWEEK";
//    private static final String TAG_TODAY = "TAG_TODAY";
    private ChartRefreshCallback chartRefreshCallback;
//    private TabHost chartTabHost;
    private float cursorTextSizeBig;
    private float cursorTextSizeMedium;
    private float cursorTextSizeSmall;
//    private FullScreenListener fullScreenListener;
//    private TextView mBackButton;
//    private ImageButton mRefreshButton;
//    private TextView mStockinfo;
    private TextView mTimeCursorAvgPrice;
    private TextView mTimeCursorPrice;
    private TextView mTimeCursorTime;
    private TextView mTimeCursorVolume;
//    private View refreshLoading;
    private ViewGroup root;
//    private TabWidget tabWidget;
//    private RelativeLayout timeCursorBar;
    private TimeTodayChartView timeTodayChartView;
    private TimeTodayCursorView timeTodayCursorView;
//    private RelativeLayout titleBar;
//    private TextView updataTime;


    public ChartView(Context context){
        super(context);
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initLayout(String stockType, String stockCode){
        timeTodayChartView = new TimeTodayChartView(getContext(), stockType, stockCode);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500));
        addView(timeTodayChartView);
    }

    private void onTimeChartCursor(TimeChartCursor timeChartCursor, String s){
        mTimeCursorAvgPrice.setText(timeTodayChartView.formatPrice(timeChartCursor.price) + "(" + StringHandler.formatPercent(timeChartCursor.percent, 2, false, false) + ")");
        if (timeChartCursor.percent >= 0.0f){
            mTimeCursorPrice.setTextColor(timeTodayChartView.upColor);
        } else {
            mTimeCursorPrice.setTextColor(timeTodayChartView.downColor);
        }
        mTimeCursorAvgPrice.setText(timeTodayChartView.formatPrice(timeChartCursor.avgPrice));
        mTimeCursorVolume.setText(timeChartCursor.volumeForTouch + s);
        mTimeCursorTime.setText(timeChartCursor.time);
    }

    public void onTimeTodayChartCursor(TimeChartCursor timeChartCursor, String s){
        timeTodayCursorView.setCursor(timeChartCursor);
        timeTodayCursorView.postInvalidate();
        onTimeChartCursor(timeChartCursor, s);
    }

    public void refreshChart(){
        timeTodayChartView.refreshChart();
    }





    @Override
    public void hiddenAutoLoading(View paramView) {

    }

    @Override
    public void showAutoLoading(View paramView) {

    }


    public  interface FullScreenListener{
        void onEnterFullScreen();
        void onExitFullScreen();
    }
}
