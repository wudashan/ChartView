package com.example.administrator.myapplication.charts;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.example.administrator.myapplication.charts.utils.GsonUtils;

import java.util.Map;

/**
 * Created by wudashan on 2015/11/19 0019.
 */
public class Gson4MapRequest extends GsonRequest<Map<String, Object>>{

    private static final String TAG = "Gson4MapRequest";

    public Gson4MapRequest(Context paramContext, String paramString, Map<String, String> paramMap, Response.Listener<Map<String, Object>> paramListener, Response.ErrorListener paramErrorListener){
        super(paramContext, paramString, null, paramMap, paramListener, paramErrorListener);
    }


    protected String dealResultJson(String paramString){
        String str = paramString;
        if (paramString.startsWith("_ntes_quote_callback")){
            str = paramString.substring(21, paramString.length() - 2);
        }
        return str;
    }

    @Override
    protected Response<Map<String, Object>> parseNetworkResponse(NetworkResponse paramNetworkResponse) {
        Log.d(TAG, "parseNetworkResponse " + new String(paramNetworkResponse.data));
        return  Response.success(GsonUtils.getMap(getResponseStr(paramNetworkResponse)), getCacheEntry(paramNetworkResponse));

    }


}
