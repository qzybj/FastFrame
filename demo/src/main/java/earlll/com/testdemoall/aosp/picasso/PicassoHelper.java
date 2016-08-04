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

import earlll.com.testdemoall.module.loadimage.LoadImageUtils;
import earlll.com.testdemoall.module.loadimage.interfaces.ILoadImage;

/**
 * Created by ZhangYuanBo on 2016/6/16.
 */
public class PicassoHelper implements ILoadImage {

    /**
     * 加载图片(Picasso)
     * @param con
     * @param iv
     * @param imageUrl  支持的格式： load(R.drawable.landing_screen); load("file:///android_asset/DvpvklR.png");load(new File(...))
     */
    @Override
    public void loadImage(Context con, ImageView iv, Object imageUrl) {
        loadImage(con,iv,imageUrl, ILoadImage.NONE, ILoadImage.NONE, ILoadImage.NONE, ILoadImage.NONE,false,null);
    }

    @Override
    public void loadImage(Context con, ImageView iv, Object imageUrl, int loadImgResId) {
        loadImage(con,iv,imageUrl, ILoadImage.NONE, ILoadImage.NONE,loadImgResId, ILoadImage.NONE,false,null);
    }

    @Override
    public void loadImage(Context con, ImageView iv, Object imageUrl, int loadImgResId, boolean isTransform) {
        loadImage(con,iv,imageUrl, ILoadImage.NONE, ILoadImage.NONE,loadImgResId, ILoadImage.NONE,isTransform,null);
    }

    @Override
    public void loadImage(Context con, ImageView iv, Object imageUrl, int loadImgResId, boolean isTransform,LoadImageUtils.ImageLoadCallback callback) {
        loadImage(con,iv,imageUrl, ILoadImage.NONE, ILoadImage.NONE,loadImgResId, ILoadImage.NONE,isTransform,callback);
    }

    /**
     *  加载图片(Picasso)
     * @param con
     * @param iv
     * @param imageUrl   支持的格式： load(R.drawable.landing_screen); load("file:///android_asset/DvpvklR.png");load(new File(...))
     * @param width     指定的图片宽
     * @param height    指定的图片高
     * @param loadImgResId 默认加载的图片
     * @param errImgResId   加载错误时的图片
     * @param isTransform   是否加载动画
     * @param callback
     */
    @Override
    public void loadImage(Context con, ImageView iv, Object imageUrl, int width, int height,int loadImgResId,int errImgResId,boolean isTransform,final LoadImageUtils.ImageLoadCallback callback) {
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
                //requestCreator.skipMemoryCache();//no cache
                //requestCreator.noPlaceholder();//set no Placeholder default image
                //requestCreator.priority(Priority priority);//set request priority
                requestCreator.config(Bitmap.Config.ARGB_8888);
                //requestCreator.fit();//self adaptation (ps:Conflict with method fetch())
                //requestCreator.centerCrop();//Set the ImageView ScaleType attribute,other method centerInside()
                if(width!= ILoadImage.NONE&&height!= ILoadImage.NONE){
                    requestCreator.resize(width, height);//set image display pixels number
                    //requestCreator.resizeDimen(targetWidthResId,targetHeightResId);//set image display pixels by ResId
                }
                //requestCreator.rotate(float degrees);//set rotate degrees
                //requestCreator.rotate(float degrees, float pivotX, float pivotY);//Set rotation degrees to a central point.
                if(isTransform){
                    //requestCreator.noFade();//No fade animation
                    if(width!= ILoadImage.NONE&&height!= ILoadImage.NONE){
                        requestCreator.transform(new ScaleTransformation(width, height));//set animation
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
                if (loadImgResId!= ILoadImage.NONE&&loadImgResId>0) {
                    requestCreator.placeholder(loadImgResId);//Placeholder default image
                }
                if (errImgResId!= ILoadImage.NONE&&errImgResId>0) {
                    requestCreator.error(errImgResId);//error image
                }
                requestCreator.into(iv);
            }else{
                iv.setImageBitmap(null);
            }
        }
    }
    @Override
    public  boolean isSupportImageUrlType(Object imageUrl){
        if(imageUrl instanceof String){
            return true;
        }
        if(imageUrl instanceof Integer){
            return true;
        }
        if(imageUrl instanceof File){
            return true;
        }
        if(imageUrl instanceof Uri){
            return true;
        }
        return false;
    }
}
