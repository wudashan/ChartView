package com.example.administrator.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.myapplication.charts.ChartView;
import com.example.administrator.myapplication.charts.time.TimeTodayChartView;
import com.example.administrator.myapplication.charts.time.TimeTodayCursorView;
import com.example.administrator.myapplication.charts.utils.VolleyUtils;



public class MainActivity extends AppCompatActivity{
    private static final String TAG = "MainActivity";
    private TimeTodayChartView mTimeTodayChartView;
    private TimeTodayCursorView mTimeTodayCursorView;
    private static final Handler mHandler = new Handler();
    private ChartThread mThread;
    private ChartView chartView;





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
                        chartView.refreshChart();
                    }
                });
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        VolleyUtils.init(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

//        mTimeTodayChartView = new TimeTodayChartView(MainActivity.this, "HS", "0000001");

        chartView = new ChartView(this);
        chartView.initLayout("HS", "0000001");
//        chartView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500));
//        chartView.addView(mTimeTodayChartView);

        layout.addView(chartView);
        setContentView(layout);




//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mThread = new ChartThread();
        mThread.start();
    }

    @Override
    protected void onPause() {
        mThread.exit = true;
        super.onPause();
    }

}



