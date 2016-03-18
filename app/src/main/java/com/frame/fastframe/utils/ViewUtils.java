package com.frame.fastframe.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;


public class ViewUtils {
    /**根据layout布局填充View*/
    public static View inflateView(Context con, int layoutResId) {
        if (layoutResId>0) {
        return LayoutInflater.from(con).inflate(layoutResId, null,false);
        }
        return null;
    }

}
