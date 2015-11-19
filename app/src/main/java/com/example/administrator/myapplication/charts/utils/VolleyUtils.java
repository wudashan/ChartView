package com.example.administrator.myapplication.charts.utils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

/**
 * Created by wudashan on 2015/11/18 0018.
 */
public class VolleyUtils {

    private static RequestQueue mRequestQueue;

    public static void addRequest(Request<?> paramRequest)
    {
        mRequestQueue.add(paramRequest);
    }

    public static void cancelRequest(String paramString)
    {
        mRequestQueue.cancelAll(paramString);
    }

}
