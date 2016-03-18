package com.frame.fastframe.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;

public class ImageViewHelper {

    public static void loadImageUrl(Context context,ImageView view, String imageUrl){
        loadImageUrl(context,view,imageUrl,0,0,null);
    }

    /**
     * load(R.drawable.landing_screen)//支持直接根据resourceid加载图片
     * load("file:///android_asset/DvpvklR.png")//文件路径方式加载图片
     */
    public static void loadImageUrl(Context context,ImageView view,Object imageUrl,int loadingImgResId,int errorImgResId,Callback callback){
        RequestCreator requestCreator = null;
        if (imageUrl instanceof Integer) {
            requestCreator = Picasso.with(context).load((Integer)imageUrl);
        }else if(imageUrl instanceof String){
            requestCreator = Picasso.with(context).load((String)imageUrl);
        }else if(imageUrl instanceof File){
            requestCreator = Picasso.with(context).load((File)imageUrl);
        }else if(imageUrl instanceof Uri){
            requestCreator = Picasso.with(context).load((Uri)imageUrl);
        }
        if (requestCreator != null) {
            if (callback != null) {
                requestCreator.fetch(callback);
            }
            if (loadingImgResId>0) {
                requestCreator.placeholder(loadingImgResId);//占位
            }
            if (errorImgResId>0) {
                requestCreator.error(errorImgResId);//错误
            }
            requestCreator.into(view);
        }
    }
}
