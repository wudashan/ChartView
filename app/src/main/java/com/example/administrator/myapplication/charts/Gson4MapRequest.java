package com.example.administrator.myapplication.charts;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
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


    protected String dealResultJson(String s)
    {
        String s1 = s;
        if(s.startsWith("_ntes_quote_callback"))
            s1 = s.substring(21, -2 + s.length());
        return s1;
    }

    @Override
    protected Response<Map<String, Object>> parseNetworkResponse(NetworkResponse paramNetworkResponse) {
        Log.d(TAG, "parseNetworkResponse " + new String(paramNetworkResponse.data));
        Response response;
        try {
            response = Response.success(GsonUtils.getMap(dealResultJson(getResponseStr(paramNetworkResponse))), getCacheEntry(paramNetworkResponse));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
        return response;

    }


}
