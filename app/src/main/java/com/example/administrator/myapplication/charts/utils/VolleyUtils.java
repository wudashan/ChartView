package com.example.administrator.myapplication.charts.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.Volley;

/**
 * Created by wudashan on 2015/11/18 0018.
 */
public class VolleyUtils {

    private static RequestQueue mRequestQueue;
    private static Context context;

    public static void init(Context paramContext){
        context = paramContext;
        mRequestQueue = Volley.newRequestQueue(paramContext);
        mRequestQueue.start();
    }


    public static void addRequest(Request<?> paramRequest)
    {
        mRequestQueue.add(paramRequest);
    }

    public static void cancelRequest(String paramString)
    {
        mRequestQueue.cancelAll(paramString);
    }

}
