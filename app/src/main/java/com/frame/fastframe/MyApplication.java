package com.frame.fastframe;

import android.app.Application;

import org.xutils.x;

/**
 * Created by ZhangYuanBo on 2016/1/29.
 */
public class MyApplication extends Application {
    private static MyApplication instance;
    public  static MyApplication getInstance(){
        return instance;
    }
    @Override
    public void onCreate() {
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志
        this.instance=this;
        super.onCreate();
    }
}
