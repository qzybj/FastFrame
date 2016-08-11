package com.frame.fastframelibrary;

import android.app.Application;

public class FastApplication extends Application {
    private static Application instance;
    public static Application instance(){
        return instance;
    }
    @Override
    public final void onCreate() {
        super.onCreate();
        instance = this;
    }
}
