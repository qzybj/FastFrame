package com.frame.volleypackageframe.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastHelper {

	public static void showToast(Context context, String msg){
		showToast(context, msg,false);
	}
	public static void showToast(Context context, String msg, boolean isLongTime){
		Toast.makeText(context, msg, isLongTime? Toast.LENGTH_LONG: Toast.LENGTH_SHORT).show();
	}
	public static void showToast(Context context, int strgResouceId){
		showToast(context, strgResouceId,false);
	}
	public static void showToast(Context context, int strgResouceId, boolean isLongTime){
		Toast.makeText(context, context.getString(strgResouceId), isLongTime? Toast.LENGTH_LONG: Toast.LENGTH_SHORT).show();
	}
	public static Toast getToast(Context context, String msg, boolean isLongTime){
		return Toast.makeText(context, msg, isLongTime? Toast.LENGTH_LONG: Toast.LENGTH_SHORT);
	}
}
