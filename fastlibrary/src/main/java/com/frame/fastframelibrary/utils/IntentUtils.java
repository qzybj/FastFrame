package com.frame.fastframelibrary.utils;

import android.content.Intent;
import android.os.Bundle;

public class IntentUtils {
	
	public static boolean isEmpty(Intent intent){
		if (intent!=null&&intent.getExtras()!=null&&!intent.getExtras().isEmpty()) {
			return false;
		}
		return true;
	}
	public static boolean isEmpty(Bundle extras){
		if (extras!=null&&!extras.isEmpty()) {
			return false;
		}
		return true;
	}
	
	
	public static Bundle getBundle(Intent intent){
		if(!isEmpty(intent)){
			return intent.getExtras();
		}
		return null;
	}
	
	public static Bundle setIntentStr(Intent intent,String key,String value){
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
	
	public static String getIntentStr(Intent intent,String key){
		if(!isEmpty(intent)){
			return getBundleStr(intent.getExtras(), key);
		}
		return null;
	}
	public static int getIntentInt(Intent intent,String key,int defValue){
		if(!isEmpty(intent)){
			return getBundleInt(intent.getExtras(), key,defValue);
		}
		return defValue;
	}
	
	public static boolean getIntentBoolean(Intent intent,String key,boolean defValue){
		if(!isEmpty(intent)){
			return getBundleBoolean(intent.getExtras(), key,defValue);
		}
		return defValue;
	}
	
	public static String[] getIntentStrArray(Intent intent,String key){
		if(!isEmpty(intent)){
			return getBundleStringArray(intent.getExtras(), key);
		}
		return null;
	}
	
	public static String getBundleStr(Bundle extras,String key){
		if (containsKey(extras,key)) {
			return extras.getString(key);
		}
		return null;
	}
	public static int getBundleInt(Bundle extras,String key,int defValue){
		if (containsKey(extras,key)) {
			return extras.getInt(key,defValue);
		}
		return defValue;
	}
	public static boolean getBundleBoolean(Bundle extras,String key,boolean defValue){
		if (containsKey(extras,key)) {
			return extras.getBoolean(key,defValue);
		}
		return defValue;
	}
	public static String[] getBundleStringArray(Bundle extras,String key){
		if (containsKey(extras,key)) {
			return extras.getStringArray(key);
		}
		return null;
	}
	
	public static boolean containsKey(Bundle extras,String key){
		if (extras!=null&&!extras.isEmpty()&&extras.containsKey(key)) {
			return true;
		}
		return false;
	}
	
	public static void removeIntentStr(Intent intent,String key){
		if(!isEmpty(intent)){
			removeBundleStr(intent.getExtras(), key);
		}
	}
	public static void removeBundleStr(Bundle extras,String key){
		if (containsKey(extras,key)) {
			extras.remove(key);
		}
	}
}
