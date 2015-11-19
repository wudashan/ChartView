package com.example.administrator.myapplication.charts;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.example.administrator.myapplication.charts.utils.GsonUtils;

import java.util.Map;

/**
 * Created by wudashan on 2015/11/19 0019.
 */
public class Gson4MapRequest extends GsonRequest<Map<String, Object>>{

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

    protected Response<Map<String, Object>> parseNetWorkResponse(NetworkResponse paramNetworkResponse){
        Response localResponse = Response.success(GsonUtils.getMap(dealResultJson(getResponseStr(paramNetworkResponse))), getCacheEntry(paramNetworkResponse));
        return localResponse;
    }

}
