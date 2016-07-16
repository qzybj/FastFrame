package com.frame.fastframelibrary.aosp.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;
import com.frame.fastframelibrary.utils.StringUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.io.File;

/**
 * Created by ZhangYuanBo on 2016/6/16.
 */
public class ImageLoadHelper {

    /**
     * @param con
     * @param iv
     * @param imageUrl
     */
    public static void loadImage(Context con, ImageView iv, Object imageUrl) {
        loadImage(con,iv,imageUrl,-1,-1,-1,-1,null);
    }

    /**
     * Picasso.with(context).load(R.drawable.landing_screen).into(imageView1);<br/>
     * Picasso.with(context).load("file:///android_asset/DvpvklR.png").into(imageView2);<br/>
     * Picasso.with(context).load(new File(...)).into(imageView3);<br/>
     * 加载图片(Picasso)
     * @param imageUrl
     */
    private static void loadImage(Context con, ImageView iv, Object imageUrl, int width, int height,int loadImgResId,int errImgResId,Callback callback) {
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
                requestCreator.skipMemoryCache().config(Bitmap.Config.ARGB_8888);
                requestCreator.fit();
                if(width!=-1&&height!=-1){
                    requestCreator.transform(new ScaleTransformation(width, height));
                }
                if (callback != null) {
                    requestCreator.fetch(callback);
                }
                if (loadImgResId>0) {
                    requestCreator.placeholder(loadImgResId);//占位
                }
                if (errImgResId>0) {
                    requestCreator.error(errImgResId);//错误
                }
                requestCreator.into(iv);
            }else{
                iv.setImageBitmap(null);
            }
        }
    }




    public static String getloadImgUrl(String sourceImageUrl) {
        if(StringUtils.isNotEmpty(sourceImageUrl)){
            sourceImageUrl="file://"+sourceImageUrl.replace("\\","/");
        }
        return sourceImageUrl;
    }
}
