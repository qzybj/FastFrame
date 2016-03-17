package com.frame.volleypackageframe.utils;

import java.util.ArrayList;

/**
 * Created by ZhangYuanBo on 2016/2/19.
 */
public class ArrayListUtils {
    public static boolean isNullArrayList(ArrayList<?> list){
        if (list!=null&&list.size()>0) {
            return false;
        }
        return true;
    }
}
