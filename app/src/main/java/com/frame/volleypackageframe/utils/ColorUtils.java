package com.frame.volleypackageframe.utils;

import android.content.Context;

/**
 * Created by ZhangYuanBo on 2016/2/19.
 */
public class ColorUtils {
    /**获取指定的颜色值的16进制表现形式。0xAARRGGBB*/
    public static int getResourceColor(Context con, int colorId) {
        if (con != null&&colorId>0) {
            return con.getResources().getColor(colorId);
        }
        return -1;
    }
}
