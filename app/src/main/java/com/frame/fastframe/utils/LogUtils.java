package com.frame.fastframe.utils;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 自定义log
 */
public class LogUtils {

    public static final String TAG = "qcore";

    /**
     * 开发模式才显示日志
     */
    public static final boolean SHOWLOG =true;

	public static void log(Object obj) {
		String msg;
		if (obj instanceof Throwable) {
			StringWriter sw = new StringWriter();
			((Throwable) obj).printStackTrace(new PrintWriter(sw));
			msg = sw.toString();
		} else {
			msg = String.valueOf(obj);
		}
		String callclassname = new Exception().getStackTrace()[1]
				.getClassName();
		String callmethodname = new Exception().getStackTrace()[1]
				.getMethodName();

		msg = callclassname + " -> " + callmethodname + ": " + msg;
		d(TAG,msg);
	}

	public static void d(String msg){
		d(TAG,msg);
	}
	public static void d(String tag, String msg){
    	if (SHOWLOG) {
    		Log.d(tag, msg);
        }
    }

	public static void i(String tag, String msg){
    	if (SHOWLOG) {
    		Log.i(tag, msg);
        }
    }
	public static void v(String tag, String msg){
    	if (SHOWLOG) {
    		Log.v(tag, msg);
        }
    }
	public static void e( String msg){
		e(TAG,msg);
	}

	public static void e(String tag, String msg){
    	if (SHOWLOG) {
			Log.e(tag, msg);
		}
    }
    
	public static void e(String tag, String msg, Throwable tr) {
		if (SHOWLOG) {
			Log.e(tag, msg,tr);
		}
	}
}