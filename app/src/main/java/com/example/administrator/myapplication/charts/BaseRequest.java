package com.example.administrator.myapplication.charts;

import android.app.DownloadManager;
import android.content.Context;

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

    private static String UA_APP_SUFFIX = null;
    private static String UA_DEFAULT = System.getProperty("http.agent", "");
    private Map<String, String> headers;
    private Response.Listener<T> listener;
    private Context mContext;
    private OnResListener mHandler;
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
        this.mContext = paramContext;
        if (UA_APP_SUFFIX == null){
            UA_APP_SUFFIX = HttpUtils.getUserAgentSuffix(paramContext);
        }
        if (paramMap == null){
            paramMap = new HashMap<>();
        }
        paramMap.put("User-Agent", UA_DEFAULT + UA_APP_SUFFIX);
        if (!paramMap.containsKey("Referer")){
            paramMap.put("Referer", "http://i.money.163.com/");
        }
        paramMap.put("Accept-Encoding", "gzip");
        this.headers = paramMap;
        this.listener = paramListener;
        setRetryPolicy(new DefaultRetryPolicy(2000, 2, 0.5f));
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse paramNetworkResponse) {
        if (this.mHandler != null){
            return this.mHandler.parseResponse(paramNetworkResponse);
        }
        return null;
    }

    @Override
    protected void deliverResponse(T paramT) {
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
