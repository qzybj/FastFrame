package earlll.com.testdemoall.aosp.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.io.File;

import earlll.com.testdemoall.module.loadimage.ImageLoadUtils;
import earlll.com.testdemoall.module.loadimage.interfaces.IImageLoad;

/**
 * Created by ZhangYuanBo on 2016/6/16.
 */
public class PicassoHelper implements IImageLoad {

    /**
     * 加载图片(Picasso)
     * @param con
     * @param iv
     * @param imageUrl  支持的格式： load(R.drawable.landing_screen); load("file:///android_asset/DvpvklR.png");load(new File(...))
     */
    @Override
    public void loadImage(Context con, ImageView iv, Object imageUrl) {
        loadImage(con,iv,imageUrl, IImageLoad.NONE, IImageLoad.NONE, IImageLoad.NONE, IImageLoad.NONE,false,null);
    }

    @Override
    public void loadImage(Context con, ImageView iv, Object imageUrl, int loadImgResId) {
        loadImage(con,iv,imageUrl, IImageLoad.NONE, IImageLoad.NONE,loadImgResId, IImageLoad.NONE,false,null);
    }

    @Override
    public void loadImage(Context con, ImageView iv, Object imageUrl, int loadImgResId, boolean isTransform) {
        loadImage(con,iv,imageUrl, IImageLoad.NONE, IImageLoad.NONE,loadImgResId, IImageLoad.NONE,isTransform,null);
    }

    /**
     *  加载图片(Picasso)
     * @param con
     * @param iv
     * @param imageUrl     支持的格式： load(R.drawable.landing_screen); load("file:///android_asset/DvpvklR.png");load(new File(...))
     * @param width     指定的图片宽
     * @param height    指定的图片高
     * @param loadImgResId 默认加载的图片
     * @param errImgResId   加载错误时的图片
     * @param isTransform   是否加载动画
     * @param callback
     */
    @Override
    public void loadImage(Context con, ImageView iv, Object imageUrl, int width, int height,int loadImgResId,int errImgResId,boolean isTransform,final ImageLoadUtils.ImageLoadCallback callback) {
        if(con!=null&&iv!=null){
            RequestCreator requestCreator =null;
            if(imageUrl instanceof String){
                String imageStr = (String)imageUrl;
                if(StringUtils.isNotEmpty(imageStr)){
                    requestCreator = Picasso.with(con).load(imageStr);
                }
            }else if(imageUrl instanceof Integer){
                int imageInt = (int)imageUrl;
                if(imageInt>0){
                    requestCreator = Picasso.with(con).load(imageInt);
                }
            }else if(imageUrl instanceof File){
                File file = (File)imageUrl;
                if(file!=null&&file.exists()){
                    requestCreator = Picasso.with(con).load(file);
                }
            }else if(imageUrl instanceof Uri){
                Uri uri = (Uri)imageUrl;
                if(uri!=null){
                    requestCreator = Picasso.with(con).load(uri);
                }
            }
            if(requestCreator!=null){
                //requestCreator.skipMemoryCache();
                requestCreator.config(Bitmap.Config.ARGB_8888);
                requestCreator.fit();
                if(width!= IImageLoad.NONE&&height!= IImageLoad.NONE){
                    requestCreator.resize(width, height);
                    if(isTransform){
                        requestCreator.transform(new ScaleTransformation(width, height));
                    }
                }
                if (callback != null) {
                    requestCreator.fetch(new Callback(){
                        @Override
                        public void onSuccess() {
                            callback.onSuccess();
                        }
                        @Override
                        public void onError() {
                            callback.onError();
                        }
                    });
                }
                if (loadImgResId!= IImageLoad.NONE&&loadImgResId>0) {
                    requestCreator.placeholder(loadImgResId);//占位
                }
                if (errImgResId!= IImageLoad.NONE&&errImgResId>0) {
                    requestCreator.error(errImgResId);//错误
                }
                requestCreator.into(iv);
            }else{
                iv.setImageBitmap(null);
            }
        }
    }
}
