package com.example.administrator.myapplication.charts;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import com.android.volley.Response;

import java.util.Map;

/**
 * Created by wudashan on 2015/11/18 0018.
 */
public class ChartRequest{

public static class TimeTodayDataRequest extends Gson4MapRequest {

    private static final String TAG = "TimeTodayDataRequest";

    public TimeTodayDataRequest(Context paramContext, String paramString1, String paramString2, Response.Listener<Map<String, Object>> paramListener, Response.ErrorListener paramErrorListener) {
        super(paramContext,String.format("http://img1.money.126.net/data/%s/time/today/%s.json", paramString1.toLowerCase(),paramString2), null, paramListener, paramErrorListener);
//        Log.d(TAG, "TimeTodayDataRequest");
    }
}

}
