package com.frame.fastframelibrary.aosp.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.frame.fastframelibrary.module.loadimage.interfaces.ILoadImageCallback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public abstract class PicassoCallback implements ILoadImageCallback,Target {
    @Override
    public final void onPrepareLoad(Drawable placeHolderDrawable) {
        onPrepareLoadImage(placeHolderDrawable);
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
        onLoadImageSuccess(bitmap,curType);
    }

    @Override
    public final void onBitmapFailed(Drawable errorDrawable) {
        onLoadImageFailed(errorDrawable);
    }
}
