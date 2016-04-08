package com.frame.fastframelibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.frame.fastframelibrary.FastApplication;

/**
 * 当前类注释:当前为SharedPerferences进行封装基本的方法,SharedPerferences已经封装成单例模式
 */
public class SharedPreferencesUtils {
    private static final String SHARED_PATH = "frame_shared";
    private static SharedPreferencesUtils instance;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private SharedPreferencesUtils(Context context) {
        sp = context.getSharedPreferences(SHARED_PATH, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public static SharedPreferencesUtils getInstance() {
        if (instance == null ) {
            instance = new SharedPreferencesUtils(FastApplication.getInstance());
        }
        return instance;
    }

    private SharedPreferences getSharedPreferences() {
        if(sp==null){
            sp = FastApplication.getInstance().getSharedPreferences(SHARED_PATH, Context.MODE_PRIVATE);
        }
        return sp;
    }
    private SharedPreferences.Editor getEditor() {
        if (editor==null){
            editor = getSharedPreferences().edit();
        }
        return editor;
    }

    public long getLongValue(String key) {
        if (checkKey(key)) {
            return getSharedPreferences().getLong(key, 0);
        }
        return 0;
    }

    public String getStringValue(String key) {
        if (checkKey(key)) {
            return getSharedPreferences().getString(key, null);
        }
        return null;
    }

    public int getIntValue(String key) {
        if (checkKey(key)) {
            return getSharedPreferences().getInt(key, 0);
        }
        return 0;
    }

    public int getIntValueByDefault(String key){
        if (checkKey(key)) {
            return getSharedPreferences().getInt(key, 0);
        }
        return 0;
    }
    public boolean getBooleanValue(String key) {
        if (checkKey(key)) {
            return getSharedPreferences().getBoolean(key, false);
        }
        return true;
    }

    public float getFloatValue(String key) {
        if (checkKey(key)) {
            return getSharedPreferences().getFloat(key, 0);
        }
        return 0;
    }

    public void setStringValue(String key, String value) {
        if (checkKey(key)) {
            getEditor().putString(key, value).commit();
        }
    }

    public void setIntValue(String key, int value) {
        if (checkKey(key)) {
            getEditor().putInt(key, value).commit();
        }
    }

    public void setBooleanValue(String key, boolean value) {
        if (checkKey(key)) {
            getEditor().putBoolean(key, value).commit();
        }
    }

    public void setLongValue(String key, long value) {
        if (checkKey(key)) {
            getEditor().putLong(key, value).commit();
        }
    }

    public void setFloatValue(String key, Float value) {
        if (checkKey(key)) {
            getEditor().putFloat(key, value).commit();
        }
    }
    public boolean checkKey(String key) {
        return StringUtils.isNotEmpty(key);
    }

    public void clear() {
        getEditor().clear().commit();
    }
}
