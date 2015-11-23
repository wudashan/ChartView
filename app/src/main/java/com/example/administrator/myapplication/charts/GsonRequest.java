package com.example.administrator.myapplication.charts;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by wudashan on 2015/11/19 0019.
 */
public class GsonRequest<T> extends BaseRequest<T> {

    private static final String TAG = "GsonRequest";

    private final Class<T> clazz;
    private final Gson gson = new Gson();
    private String mCharset = "utf-8";

    public GsonRequest(Context paramContext, String paramString, Class<T> paraClass, Map<String, String> paramMap, Response.Listener<T> paramListener, Response.ErrorListener paramErrorListener){
        super(paramContext, 0, paramString, paramMap, paramListener, paramErrorListener);
        this.clazz = paraClass;
    }

    protected Gson getGson() {
        return this.gson;
    }

    protected final String getResponseStr(NetworkResponse paramNetworkResponse){
        if (!TextUtils.isEmpty(this.mCharset)){
            String localStr = this.mCharset;
            try {
                Log.d(TAG,  new String(paramNetworkResponse.data, localStr));

                return new String(paramNetworkResponse.data, localStr);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse paramNetworkResponse) {
        Log.d(TAG, "parseNetworkResponse");
        String str = getResponseStr(paramNetworkResponse);
        return Response.success(this.gson.fromJson(str,this.clazz), getCacheEntry(paramNetworkResponse));
    }

    protected void setCharset(String paramString){
        this.mCharset = paramString;
    }
}
