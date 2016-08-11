package com.frame.fastframelibrary.utils.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.frame.fastframelibrary.FastApplication;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;

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

    public static SharedPreferencesUtils instance() {
        if (instance == null ) {
            instance = new SharedPreferencesUtils(FastApplication.instance());
        }
        return instance;
    }

    private SharedPreferences getSharedPreferences() {
        if(sp==null){
            sp = FastApplication.instance().getSharedPreferences(SHARED_PATH, Context.MODE_PRIVATE);
        }
        return sp;
    }
    private SharedPreferences.Editor getEditor() {
        if (editor==null){
            editor = getSharedPreferences().edit();
        }
        return editor;
    }

    public long getLong(String key) {
        if (checkKey(key)) {
            return getSharedPreferences().getLong(key, 0);
        }
        return 0;
    }
    public void setLong(String key, long value) {
        if (checkKey(key)) {
            getEditor().putLong(key, value).commit();
        }
    }

    public String getString(String key) {
        if (checkKey(key)) {
            return getSharedPreferences().getString(key, null);
        }
        return null;
    }
    public void setString(String key, String value) {
        if (checkKey(key)) {
            getEditor().putString(key, value).commit();
        }
    }

    public int getInt(String key) {
        if (checkKey(key)) {
            return getSharedPreferences().getInt(key, 0);
        }
        return 0;
    }
    public void setInt(String key, int value) {
        if (checkKey(key)) {
            getEditor().putInt(key, value).commit();
        }
    }

    public boolean getBoolean(String key) {
        if (checkKey(key)) {
            return getSharedPreferences().getBoolean(key, false);
        }
        return true;
    }
    public void setBoolean(String key, boolean value) {
        if (checkKey(key)) {
            getEditor().putBoolean(key, value).commit();
        }
    }

    public float getFloat(String key) {
        if (checkKey(key)) {
            return getSharedPreferences().getFloat(key, 0);
        }
        return 0;
    }
    public void setFloat(String key, Float value) {
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
