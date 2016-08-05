package earlll.com.testdemoall.module.loadimage.interfaces.impl;

import android.widget.ImageView;
import earlll.com.testdemoall.module.loadimage.interfaces.ILoadImageCallback;

/**
 * Created by ZhangYuanBo on 2016/8/4.
 * 第三方的接口
 */
public abstract class LoadImageCallbackImpl implements ILoadImageCallback {
    /**用于ImageView的传递 */
    protected ImageView mImageView = null;
    public LoadImageCallbackImpl(ImageView iv) {
        mImageView = iv;
    }
}
