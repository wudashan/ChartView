package com.example.administrator.myapplication.charts;

/**
 * Created by wudashan on 2015/11/18 0018.
 */
public interface ChartData {
    String CHART_KLINE_DAY_FRESH_TAG = "CHART_KLINE_DAY_FRESH_TAG";
    String CHART_KLINE_MONTH_FRESH_TAG = "CHART_KLINE_MONTH_FRESH_TAG";
    String CHART_KLINE_WEEK_FRESH_TAG = "CHART_KLINE_WEEK_FRESH_TAG";
    String CHART_TIME_4DAYS_FRESH_TAG = "CHART_TIME_4DAYS_FRESH_TAG";
    String CHART_TIME_TODAY_FRESH_TAG = "CHART_TIME_TODAY_FRESH_TAG";

    void destroy();

    boolean isEmpty();

    boolean noData();
}
