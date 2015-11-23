package com.example.administrator.myapplication.charts.utils;

import android.nfc.Tag;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

/**
 * Created by wudashan on 2015/11/19 0019.
 */
public class GsonUtils {

    public static Gson gson = new GsonBuilder().create();
    private static final String TAG = "GsonUtils";


    public static Map<String, Object> getMap(String paramString){
        Log.d(TAG, paramString);
        TypeToken local2 = new TypeToken<Map<String, Object>>(){
        };

        return gson.fromJson(paramString, local2.getType());
    }


}
