package com.frame.fastframe.module.html5.utils;


import com.frame.fastframelibrary.utils.LogUtils;

/**
 * 用转接调用客户端的Log处理 
 */
public class TransfersLog {
	public static final String TAG = "TransfersLog";
    /**
     * 开发模式才显示日志
     */
    public static final boolean SHOWLOG = true;
    
    public static void d(String message) {
    	if (SHOWLOG) {
    		d(TAG, message);
		}
    }
    public static void d(String tag, String message) {
    	if (SHOWLOG) {
    		if (tag==null||message==null) {
				return;
			}
    		LogUtils.d(tag, message);
		}
	}
    
    public static void e(String message) {
		if (SHOWLOG) {
    		e(TAG, message);
    	}		
	}
    public static void e(String tag, String message) {
    	if (SHOWLOG) {
    		if (tag==null||message==null) {
				return;
			}
			LogUtils.e(tag, message);
    	}
    }
    public static void e(Exception e) {
    	if (SHOWLOG) {
			LogUtils.e(e);
    	}
    }
    public static void e(String tag, Exception e) {
    	if (SHOWLOG) {
    		if (tag==null||e==null) {
				return;
			}
			LogUtils.e(tag,e.getMessage());
    	}
    }
    public static void logformat(String format, Object... args) {
    	String msg = String.format(format, args);
    	 String callclassname = new Exception().getStackTrace()[1].getClassName();
         String callmethodname = new Exception().getStackTrace()[1].getMethodName();
         msg = callclassname + " -> " + callmethodname + ": " + msg;
         d(TAG,msg);
    }
}