package com.example.user.testapphandh.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.example.user.testapphandh.BuildConfig;

public class Utils {

    private static final String TAG = "LOG_";

    public static void p(Object obj) {
        if (BuildConfig.LOGS_ENABLED && obj != null)
            Log.d(TAG, obj.toString());
    }

    public static void e(Object obj) {
        if (BuildConfig.LOGS_ENABLED && obj != null)
            Log.e(TAG, obj.toString());
    }

    public static void e(String tag, Object obj) {
        if (BuildConfig.LOGS_ENABLED && obj != null)
            Log.e(TAG + tag, obj.toString());
    }

    public static void p(String tag, Object obj) {
        if (BuildConfig.LOGS_ENABLED && obj != null)
            Log.d(TAG + tag, obj.toString());
    }

    public static void err(String tag, Object obj, Throwable throwable) {
        if (BuildConfig.LOGS_ENABLED && obj != null)
            Log.e(TAG + tag, obj.toString(), throwable);
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showToast(Context context, int message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @SuppressWarnings("deprecation")
    public static boolean hasConnection(Context appContext) {
        boolean connection = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null) { // connected to the internet
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        // connected to wifi
                        connection = true;
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        // connected to the mobile provider's data plan
                        connection = true;
                    } else {
                        p(TAG, "connection type: " + activeNetwork.getType());
                    }
                } else {
                    // not connected to the internet
                    connection = false;
                }
            }
        } catch (Exception ex) {
            err(TAG, "InternetController: has NO Connection() ", ex);
            ex.printStackTrace();
            connection = false;
        }
        return connection;
    }
}
