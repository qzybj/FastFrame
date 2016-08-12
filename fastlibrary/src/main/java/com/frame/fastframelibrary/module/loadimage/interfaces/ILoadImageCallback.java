package com.frame.fastframelibrary.module.loadimage.interfaces;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by ZhangYuanBo on 2016/8/4.
 * 主要用于对接第三方图片加载回调相关参数
 */
public interface ILoadImageCallback {
    enum LoadImageFrom {
        MEMORY,
        DISK,
        NETWORK,
        UNKNOWN
    }
    /**
     * 图片加载成功回调监听
     * @param iv
     * @param bitmap
     * @param from 来源 {@link LoadImageFrom}
     */
    void onLoadImageSuccess(ImageView iv,Bitmap bitmap, LoadImageFrom from);
    /**
     * 图片加载失败回调监听
     * @param iv
     */
    void onLoadImageFailed(ImageView iv);
}
