package com.example.administrator.myapplication.charts.utils;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by wudashan on 2015/11/18 0018.
 */
public class StringUtils {

    /**
     *  大写字母转小写字母
     */
    public static String upLow(String paramString)
    {
        if (TextUtils.isEmpty(paramString)){
            return null;
        }
        if (Pattern.compile("[A-Z]+").matcher(paramString).find()){
            return paramString.toLowerCase();
        }
        return paramString.toUpperCase();
    }

    public static boolean hasText(String paramString) {
        return (paramString != null) && (paramString.trim().length() > 0);
    }
}
