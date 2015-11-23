package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.myapplication.charts.time.TimeTodayChartView;
import com.example.administrator.myapplication.charts.utils.VolleyUtils;


public class MainActivity extends AppCompatActivity {

    private TimeTodayChartView mTimeTodayChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        VolleyUtils.init(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mTimeTodayChartView = new TimeTodayChartView(this, "HS", "0000001");
        layout.addView(mTimeTodayChartView);
        setContentView(layout);


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

    }
}



