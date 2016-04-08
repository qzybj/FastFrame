package com.frame.fastframe;

import com.frame.fastframelibrary.FastApplication;
import org.xutils.x;

public class MyApplication extends FastApplication {

    @Override
    public void onCreate() {
        x.Ext.init(this);
        x.Ext.setDebug(true); //是否输出debug日志
        super.onCreate();
    }
}
