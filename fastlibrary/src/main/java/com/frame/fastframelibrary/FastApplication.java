package com.frame.fastframelibrary;

import android.app.Application;

import com.frame.fastframelibrary.aosp.picasso.PicassoHelper;
import com.frame.fastframelibrary.module.loadimage.LoadImageManager;

public class FastApplication extends Application {
    private static Application instance;
    public static Application instance(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        LoadImageManager.init(new PicassoHelper());
    }
}
