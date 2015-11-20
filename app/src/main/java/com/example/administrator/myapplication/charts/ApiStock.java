package com.example.administrator.myapplication.charts;

/**
 * Created by wudashan on 2015/11/20 0020.
 */
public class ApiStock {
    public static String getPriceFormatted(double paramDouble){
        return getPriceFormatted(paramDouble, 2);
    }

    public static String getPriceFormatted(double paramDouble, int paramInt){
        return StringHandler.formatDouble(paramDouble, paramInt, false, false);
    }
}
