package com.example.administrator.myapplication.charts.utils;

import android.text.TextUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by wudashan on 2015/11/18 0018.
 */
public class ModelUtils {

    public static String getStringValue(Map<String, Object> paramMap, String paramString) {
        Object localObject = getValue(paramMap, paramString);
        if (localObject != null){
            return localObject.toString();
        }
        return "";
    }

    public static double getDoubleValue(Map<String, Object> paramMap, String paramString, double paramDouble) {
        Object localObject = getValue(paramMap, paramString);
        if ((localObject != null) && (localObject instanceof Number)){
            paramDouble = ((Number)localObject).doubleValue();
        }
        return paramDouble;
    }

    public static List<Object> getListValue(Map<String, Object> paramMap, String paramString)
    {
        Object localObject = getValue(paramMap, paramString);
        if ((localObject != null) && (localObject instanceof List)){
            return (List)localObject;
        }
        return null;
    }

    public static Object getValue(Map<String, Object> paramMap, String paramString) {
        Object localObject;
        if ((paramMap == null) || (TextUtils.isEmpty(paramString))){
            localObject = null;
        }
        localObject = paramMap.get(paramString);
        if (localObject != null){
            return localObject;
        }
        return paramMap.get(StringUtils.upLow(paramString));
    }

}
