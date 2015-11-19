package com.example.administrator.myapplication.charts.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by wudashan on 2015/11/19 0019.
 */
public class HttpUtils {

    private static String USER_AGENT_SUFFIX = "";

    public static String getUserAgentSuffix(Context paramContext) {
        try {
            PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
            String str = localPackageInfo.versionName;
            int i = localPackageInfo.versionCode;
            USER_AGENT_SUFFIX = " iMoney/" + str + "_" + i;
        } catch (PackageManager.NameNotFoundException e) {
            USER_AGENT_SUFFIX = "";
            e.printStackTrace();
        }
        return USER_AGENT_SUFFIX;
    }
}
