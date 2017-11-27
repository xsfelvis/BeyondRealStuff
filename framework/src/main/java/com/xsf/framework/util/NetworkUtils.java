package com.xsf.framework.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Author: xushangfei
 * Time: created at 2017/11/6.
 * Description:
 */
public final class NetworkUtils {

    /**
     * 判断网络连接是否可用
     */
    public static Boolean isNetworkConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo networkinfo = manager.getActiveNetworkInfo();
            if (networkinfo != null && networkinfo.isConnected() && networkinfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }
}
