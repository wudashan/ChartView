package com.example.administrator.myapplication.charts.time;

import com.example.administrator.myapplication.charts.ChartData;
import com.example.administrator.myapplication.charts.utils.ModelUtils;
import com.example.administrator.myapplication.charts.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wudashan on 2015/11/18 0018.
 */
public class TimeTodayChartData implements ChartData{

    private static final Map<String, int[]> timeLabelKeyMap;
    private static final Map<String, String[]> timeLabelValueMap;
    private static final Map<String, Integer> volumeDivideMap;
    private static final Map<String,String> volumeUnitMap;

    private float[] avgPrices;
    private TimeTodayChartView chartView;
    private String code;
    private int count;
    private String date;
    private float high = 0.0f;
    private boolean isLoadAllData = false;
    private int length;
    private float low = Float.MAX_VALUE;
    private String market;
    private float maxVolume = 0.0f;
    private String name;
    private float[] prices;
    private String symbol;
    private String tag;
    private int[] timeLabelKeys;
    private String[] timeLabelValues;
    private String[] times;
    private int volumeDivide;
    private String volumeUnit;
    private float[] volumes;
    private float yestclose;


    static {
        timeLabelKeyMap = new HashMap<>();
        timeLabelKeyMap.put("HS", new int[]{1, 61, 121, 182, 242});
        timeLabelKeyMap.put("HK", new int[]{1, 91, 151, 212, 272, 332});
        timeLabelKeyMap.put("US", new int[]{1, 91, 151, 211, 271, 331, 391});

        timeLabelValueMap = new HashMap<>();
        timeLabelValueMap.put("HS", new String[]{"9:30", "10:30", "11:30/13:00", "14:00", "15:00"});
        timeLabelValueMap.put("HK", new String[]{"9:30", "11:00", "12:00/13:00", "14:00", "15:00", "16:00"});
        timeLabelValueMap.put("US", new String[]{"9:30", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00"});

        volumeDivideMap = new HashMap<>();
        volumeDivideMap.put("HS", 100);
        volumeDivideMap.put("HK", 10000);
        volumeDivideMap.put("US", 10000);

        volumeUnitMap = new HashMap<>();
        volumeUnitMap.put("HS", "手");
        volumeUnitMap.put("HK", "万股");
        volumeUnitMap.put("US", "万股");
    }


    public TimeTodayChartData(TimeTodayChartView paramTimeTodayChartView, String market, String code){
        this.chartView = paramTimeTodayChartView;
        this.market = market;
        this.code = code;
        this.volumeDivide = volumeDivideMap.get(market);
        this.volumeUnit = volumeUnitMap.get(market);
        this.timeLabelKeys = timeLabelKeyMap.get(market);
        this.timeLabelValues = timeLabelValueMap.get(market);
        this.tag = "CHART_TIME_TODAY_FRESH_TAG";
        loadData();
    }

    private void addData(Map<String, Object> paramMap){
        this.symbol = ModelUtils.getStringValue(paramMap, "symbol");
        this.name = ModelUtils.getStringValue(paramMap, "name");
        this.date = ModelUtils.getStringValue(paramMap, "date");
        this.yestclose = (float) ModelUtils.getDoubleValue(paramMap, "yestclose", 0.0);
        this.count = (int) ModelUtils.getDoubleValue(paramMap, "count", 0.0);
        List localList = ModelUtils.getListValue(paramMap, "data");
        if (StringUtils.hasText(this.symbol) && StringUtils.hasText(this.name) && StringUtils.hasText(this.date) && this.count > 0 && localList != null && localList.size() > 0){
        }

    }

    private void loadData() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean noData() {
        return false;
    }
}
