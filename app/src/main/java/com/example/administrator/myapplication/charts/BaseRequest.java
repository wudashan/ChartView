package com.example.administrator.myapplication.charts;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.administrator.myapplication.charts.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wudashan on 2015/11/19 0019.
 */
public class BaseRequest<T> extends Request<T>{
    //new feature start
    private static final String TAG = "BaseRequest";
    private static String UA_APP_SUFFIX = null;
    private static String UA_DEFAULT = System.getProperty("http.agent", "");
    private Map<String, String> headers;
    private Response.Listener<T> listener;
    private Context mContext;
    private OnResListener<T> mHandler;
    private Map<String, String> params;

    public BaseRequest(int paramInt, Context paramContext, String paramString, Map<String, String> paramMap1, Map<String, String> paramMap2, OnResListener paramOnResListener){
        this(paramContext, paramInt, paramString, paramMap2, paramOnResListener, paramOnResListener);
        this.params = paramMap1;
        this.mHandler = paramOnResListener;
    }


    public BaseRequest(int paramInt, String paramString, Response.ErrorListener paramErrorListener){
        super(paramInt, paramString, paramErrorListener);
    }



    public BaseRequest(Context paramContext, int paramInt, String paramString, Map<String, String> paramMap, Response.Listener<T> paramListener, Response.ErrorListener paramErrorListenr){
        super(paramInt, paramString, paramErrorListenr);
//        Log.d(TAG, "BaseRequest");
        this.mContext = paramContext;
        if (UA_APP_SUFFIX == null){
            UA_APP_SUFFIX = HttpUtils.getUserAgentSuffix(paramContext);
        }
        if (paramMap == null){
            paramMap = new HashMap<>();
        }
//        paramMap.put("User-Agent", UA_DEFAULT + UA_APP_SUFFIX);
//        Log.d(TAG, "BaseRequest " + UA_DEFAULT + UA_APP_SUFFIX);
//        paramMap.put("Referer", "http://i.money.163.com/");
//        paramMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//        paramMap.put("Accept-Encoding", "gzip, deflate, sdch");
//        paramMap.put("Accept-Language", "zh-CN,zh;q=0.8");
//        paramMap.put("Cache-Control", "max-age=0");
//        paramMap.put("Connection", "keep-alive");
//        paramMap.put("Host", "img1.money.126.net");
//        paramMap.put("If-Modified-Since", "Fri, 20 Nov 2015 07:01:10 GMT");
//        paramMap.put("Upgrade-Insecure-Requests", "1");
//        paramMap.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36");
        this.headers = paramMap;
        this.listener = paramListener;
        setRetryPolicy(new DefaultRetryPolicy(2000, 2, 0.5f));
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse paramNetworkResponse) {
//        Log.d(TAG, "parseNetworkResponse");
        if (this.mHandler != null){
            return this.mHandler.parseResponse(paramNetworkResponse);
        }
        return null;
    }

    @Override
    protected void deliverResponse(T paramT) {
//        Log.d(TAG, "deliverResponse");
        if (this.listener != null){
            this.listener.onResponse(paramT);
        }
    }

    protected Cache.Entry getCacheEntry(NetworkResponse paramNetworkResponse){
        if (paramNetworkResponse.data != null && paramNetworkResponse.data.length > 102400){
            return null;
        }
        return HttpHeaderParser.parseCacheHeaders(paramNetworkResponse);
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError{
        if (this.headers != null){
            return this.headers;
        }
        return super.getHeaders();
    }

    protected Map<String, String> getParams(){
        return this.params;
    }




    public static abstract class OnResListener<T> implements Response.Listener<T>, Response.ErrorListener{
        @Override
        public void onErrorResponse(VolleyError error) {
        }

        @Override
        public void onResponse(T response) {
        }

        public abstract Response<T> parseResponse(NetworkResponse paramNetworkResponse);
    }
}
