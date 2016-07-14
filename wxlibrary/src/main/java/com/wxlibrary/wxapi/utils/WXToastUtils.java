package com.wxlibrary.wxapi.utils;

import android.content.Context;
import android.widget.Toast;

public class WXToastUtils {
    public static void showToast(Context con, String showMsg, boolean isLong){
        Toast.makeText(con, showMsg,isLong? Toast.LENGTH_LONG: Toast.LENGTH_SHORT).show();
    }
}