package com.example.movie.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {

    //是否有可用的网络
    public static boolean hasNetwork(Context context) {
        ConnectivityManager cm =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }
    //判断是否是手机网络
    public static boolean isMobileNetwork(Context context) {
        ConnectivityManager cm =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
       NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }
}
