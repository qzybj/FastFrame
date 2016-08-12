package com.frame.fastframelibrary.aosp.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.frame.fastframelibrary.module.loadimage.interfaces.impl.LoadImageCallbackImpl;

public abstract class PicassoCallback extends LoadImageCallbackImpl implements Target {
    public PicassoCallback(ImageView iv) {
        super(iv);
    }

    @Override
    public final void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        LoadImageFrom curType = LoadImageFrom.UNKNOWN;
        switch (from){
            case MEMORY:
                curType = LoadImageFrom.MEMORY;
                break;
            case DISK:
                curType = LoadImageFrom.DISK;
                break;
            case NETWORK:
                curType = LoadImageFrom.NETWORK;
                break;
        }
        onLoadImageSuccess(mImageView,bitmap,curType);
    }

    @Override
    public final void onBitmapFailed(Drawable errorDrawable) {
        onLoadImageFailed(mImageView);
    }

    @Override
    public final void onPrepareLoad(Drawable placeHolderDrawable) {

    }
}
