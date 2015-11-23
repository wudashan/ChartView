package com.example.administrator.myapplication.charts;

/**
 * Created by wudashan on 2015/11/20 0020.
 */
public class StringHandler {

    public static String formatDouble(double paramDouble, int paramInt, boolean paramBoolean1, boolean paramBoolean2){
        StringBuilder  localStringBuilder = new StringBuilder("%");
        localStringBuilder.append(".").append(paramInt).append("f");
        return String.format(localStringBuilder.toString(), paramDouble);
    }

    public static String formatPercent(double paramDouble, int paramInt, boolean paramBoolean1, boolean paramBoolean2){
//        StringBuilder  localStringBuilder = new StringBuilder("%");
//        localStringBuilder.append(".").append(paramInt).append("f");
//        return String.format(localStringBuilder.toString(), paramDouble);
        return (new StringBuilder()).append(formatDouble(100 * paramDouble, paramInt, paramBoolean1, paramBoolean2)).append("%").toString();
    }

}
