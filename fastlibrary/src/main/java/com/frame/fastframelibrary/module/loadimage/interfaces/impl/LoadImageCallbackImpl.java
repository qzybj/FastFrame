package com.frame.fastframelibrary.module.loadimage.interfaces.impl;

import android.widget.ImageView;
import com.frame.fastframelibrary.module.loadimage.interfaces.ILoadImageCallback;
import com.frame.fastframelibrary.aosp.picasso.PicassoCallback;

/**
 * Created by ZhangYuanBo on 2016/8/4.
 * 第三方的接口
 * 需要实现：onLoadImageSuccess、 onLoadImageFailed两个方法，具体实现可参照 {@link PicassoCallback}
 */
public abstract class LoadImageCallbackImpl implements ILoadImageCallback {
    /**用于ImageView的传递 */
    protected ImageView mImageView = null;
    public LoadImageCallbackImpl(ImageView iv) {
        mImageView = iv;
    }
}
