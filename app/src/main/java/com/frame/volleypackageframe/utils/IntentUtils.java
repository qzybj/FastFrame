package com.frame.volleypackageframe.utils;

import android.content.Intent;
import android.os.Bundle;

public class IntentUtils {
	
	public static boolean isExtrasEmpty(Intent intent){
		if (intent!=null&&intent.getExtras()!=null&&!intent.getExtras().isEmpty()) {
			return false;
		}
		return true;
	}
	public static Bundle setIntentStr(Intent intent, String key, String value){
		if(intent==null){
			intent = new Intent();
		}
		return setBundleStr(intent.getExtras(), key,value);
	}
	
	public static Bundle setBundleStr(Bundle extras, String key, String value){
		if (extras==null) {
			extras = new Bundle();
		}
		extras.putString(key, value);
		return extras;
	}
	public static String getIntentStr(Intent intent, String key){
		if(intent!=null){
			return getBundleStr(intent.getExtras(), key);
		}
		return null;
	}
	
	public static String getBundleStr(Bundle extras, String key){
		if (extras!=null&&!extras.isEmpty()) {
			if (extras.containsKey(key)) {
				return extras.getString(key);
			}
		}
		return null;
	}
	
	public static void removeIntentStr(Intent intent, String key){
		if(intent!=null){
			removeBundleStr(intent.getExtras(), key);
		}
	}
	public static void removeBundleStr(Bundle extras, String key){
		if (extras!=null&&!extras.isEmpty()) {
			if (extras.containsKey(key)) {
				extras.remove(key);
			}
		}
	}
}
