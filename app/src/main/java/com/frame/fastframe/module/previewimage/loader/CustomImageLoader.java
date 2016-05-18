package com.frame.fastframe.module.previewimage.loader;

import android.content.Context;
import android.widget.ImageView;

import com.frame.fastframe.R;
import com.squareup.picasso.Picasso;
import com.veinhorn.scrollgalleryview.loader.MediaLoader;

/**
 * Created by Administrator on 2016/5/18.
 */
public class CustomImageLoader implements MediaLoader {
    private String mImgUrl;

    public CustomImageLoader(String imgUrl) {
        mImgUrl = imgUrl;
    }

    @Override
    public boolean isImage() {
        return true;
    }

    @Override
    public void loadMedia(Context context, ImageView imageView, SuccessCallback callback) {
        //we aren't loading bitmap, because full image loaded on thumbnail step
        Picasso.with(context).load(mImgUrl).placeholder(R.mipmap.ic_launcher).into(imageView);//.resize(480,800)
        if (callback != null) {
            callback.onSuccess();
        }
    }

    @Override
    public void loadThumbnail(Context context, ImageView thumbnailView, SuccessCallback callback) {
        Picasso.with(context).load(mImgUrl).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(thumbnailView);
        if (callback != null) {
            callback.onSuccess();
        }
    }
}