package com.wxlibrary.wxapi.utils;

import android.util.Log;
import com.wxlibrary.wxapi.config.WXConstant;

public class WXLogUtils {
    public static boolean isDebug = WXConstant.ISDEV;

    public static void i(String tag, String msg) {
        i(tag, msg,null);
    }
    public static void i(String tag, String msg, Throwable tr) {
        if (isDebug) {
            Log.i(tag, msg,tr);
        }
    }


    /**
     * debug
     * @param msg
     */
    public static void d(String tag, String msg) {
        d(tag, msg,null);
    }
    public static void d(String tag, String msg, Throwable tr) {
        if (isDebug) {
            Log.d(tag, msg,tr);
        }
    }
    public static void e(String tag, Exception e){
        if( e != null ){
            e(tag,e.getMessage());
        }
    }
    /**
     * error
     * @param msg
     */
    public static void e(String tag, String msg) {
        e(tag, msg,null);
    }
    public static void e(String tag, String msg, Throwable tr) {
        if (isDebug) {
            Log.e(tag, msg,tr);
        }
    }
}
