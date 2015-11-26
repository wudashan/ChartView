package com.example.administrator.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.myapplication.charts.time.TimeTodayChartView;
import com.example.administrator.myapplication.charts.utils.VolleyUtils;



public class MainActivity extends AppCompatActivity{
    private static final String TAG = "MainActivity";
    private TimeTodayChartView mTimeTodayChartView;
    private static final Handler mHandler = new Handler();
    private ChartThread mThread = new ChartThread();
    private LinearLayout layout;





    public class ChartThread extends Thread{
        public volatile boolean exit = false;
        @Override
        public void run() {
            while (!exit){
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTimeTodayChartView.refreshChart();
                    }
                });
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        VolleyUtils.init(this);

        LinearLayout main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500));
        mTimeTodayChartView = new TimeTodayChartView(layout.getContext(), "HS", "0000001");
        main.addView(layout);
        layout.addView(mTimeTodayChartView);
        setContentView(main);

        mThread.start();


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

    }


    @Override
    protected void onDestroy() {
        mThread.exit = true;
        super.onDestroy();
    }
}



