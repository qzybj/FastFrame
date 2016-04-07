package com.frame.fastframelibrary;

import android.app.Application;

public class FastApplication extends Application {
    private static FastApplication instance;
    public  static FastApplication getInstance(){
        return instance;
    }
    @Override
    public void onCreate() {
        this.instance=this;
        super.onCreate();
    }
}
