package com.example.administrator.myapplication.charts.time;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.administrator.myapplication.charts.ChartData;
import com.example.administrator.myapplication.charts.ChartRequest;
import com.example.administrator.myapplication.charts.utils.ModelUtils;
import com.example.administrator.myapplication.charts.utils.StringUtils;
import com.example.administrator.myapplication.charts.utils.VolleyUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by wudashan on 2015/11/18 0018.
 */
public class TimeTodayChartData implements ChartData{

    private static final String TAG = "TimeTodayChartData";

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
        List dataList = ModelUtils.getListValue(paramMap, "data");
        if (StringUtils.hasText(this.symbol) && StringUtils.hasText(this.name) && StringUtils.hasText(this.date) && this.count > 0 && dataList != null && dataList.size() > 0){
            this.date = this.date.substring(0, 4) + "-" + this.date.substring(4, 6) + "-" + this.date.substring(6);
            this.length = dataList.size();
            this.times = new String[this.length];
            this.prices = new float[this.length];
            this.avgPrices = new float[this.length];
            this.volumes = new float[this.length];

            Iterator dataIterator = dataList.iterator();

            for (int i = 0; dataIterator.hasNext(); i++){
                List dataItem = (List) dataIterator.next();
                String timeTemp = (String) dataItem.get(0);
                String timeString = timeTemp.substring(0, 2) + ":" + timeTemp.substring(2);
                this.times[i] = timeString;
                float immediatePrice = ((Double) dataItem.get(1)).floatValue();
                this.prices[i] = immediatePrice;
                if (immediatePrice > this.high){
                    this.high = immediatePrice;
                }
                if (immediatePrice < this.low){
                    this.low = immediatePrice;
                }
                this.avgPrices[i] = ((Double) dataItem.get(2)).floatValue();
                float tradeTotal = ((Double)dataItem.get(3)).floatValue() / this.volumeDivide;
                this.volumes[i] = tradeTotal;
                if (tradeTotal > this.maxVolume){
                    this.maxVolume = tradeTotal;
                }
            }
            adjustVolumeUnit();
        }

    }

    private void adjustVolumeUnit() {
        if ("HK".equals(this.market) || "US".equals(this.market)){
            if (this.maxVolume < 1.0){
                this.maxVolume *= this.volumeDivide;
                this.volumeUnit = "股";
            }
            int i = 0;
            int j = this.volumes.length;
            while(i < j){
                this.volumes[i] *= this.volumeDivide;
                i++;
            }
        }
        if ("HS".equals(this.market) && this.maxVolume >= 10000){
            this.maxVolume = (float) (0.0001 * this.maxVolume);
            this.volumeUnit = "万手";
            int k = 0;
            int m = this.volumes.length;
            while(k < m){
                this.volumes[k] *= 0.0001;
                k++;
            }
        }
    }

    private void loadData() {
//        Log.d(TAG, "loadData");
        ResponseListener localResponseListener = new ResponseListener();
//        Log.d(TAG, "this.market: " + this.market);
        ChartRequest.TimeTodayDataRequest localTimeTodayDataRequest = new ChartRequest.TimeTodayDataRequest(this.chartView.getContext(), this.market, this.code, localResponseListener, localResponseListener);
        localTimeTodayDataRequest.setTag(this.tag);
        VolleyUtils.addRequest(localTimeTodayDataRequest);
    }



    public float getTouchVolumeForHs(float paramFloat){
        if ("HS".equals(this.market) && "万手".equals(this.volumeUnit)){
            paramFloat = Math.round(100.0f * (10000.0f * paramFloat)) / 100.0f;
        }
        return paramFloat;
    }

    public String getTouchVolumeUnit(){
        if ("HS".equals(this.market) && "万手".equals(this.volumeUnit)){
            return "手";
        }
        return this.volumeUnit;
    }

    public int getCount() {
        return count;
    }

    public static Map<String, int[]> getTimeLabelKeyMap() {
        return timeLabelKeyMap;
    }

    public static Map<String, String[]> getTimeLabelValueMap() {
        return timeLabelValueMap;
    }

    public static Map<String, Integer> getVolumeDivideMap() {
        return volumeDivideMap;
    }

    public static Map<String, String> getVolumeUnitMap() {
        return volumeUnitMap;
    }

    public float[] getAvgPrices() {
        return avgPrices;
    }

    public TimeTodayChartView getChartView() {
        return chartView;
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public float getHigh() {
        return high;
    }

    public boolean isLoadAllData() {
        return isLoadAllData;
    }

    public int getLength() {
        return length;
    }

    public float getLow() {
        return low;
    }

    public String getMarket() {
        return market;
    }

    public float getMaxVolume() {
        return maxVolume;
    }

    public String getName() {
        return name;
    }

    public float[] getPrices() {
        return prices;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getTag() {
        return tag;
    }

    public int[] getTimeLabelKeys() {
        return timeLabelKeys;
    }

    public String[] getTimeLabelValues() {
        return timeLabelValues;
    }

    public String[] getTimes() {
        return times;
    }

    public int getVolumeDivide() {
        return volumeDivide;
    }

    public String getVolumeUnit() {
        return volumeUnit;
    }

    public float[] getVolumes() {
        return volumes;
    }

    public float getYestclose() {
        return yestclose;
    }

    @Override
    public void destroy() {
        VolleyUtils.cancelRequest(this.tag);
    }

    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override
    public boolean noData() {
        return this.length == 0 && this.isLoadAllData;
    }

    private class ResponseListener implements Response.Listener<Map<String, Object>>, Response.ErrorListener{
        private ResponseListener(){

        }

        @Override
        public void onErrorResponse(VolleyError paramVolleyError) {
//            Log.d(TAG, "onErrorResponse");
            if (paramVolleyError.networkResponse != null && paramVolleyError.networkResponse.statusCode == 404){
                chartView.redrawChart();
            }
        }

        @Override
        public void onResponse(Map<String, Object> paramMap) {
//            Log.d(TAG, "onResponse");
            addData(paramMap);
            chartView.redrawChart();
        }
    }
}
