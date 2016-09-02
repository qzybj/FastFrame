package com.frame.fastframelibrary.module.loadimage;

import android.content.Context;
import android.widget.ImageView;
import com.frame.fastframelibrary.FastApplication;
import com.frame.fastframelibrary.aosp.picasso.PicassoHelper;
import com.frame.fastframelibrary.module.loadimage.exception.LoadImageException;
import com.frame.fastframelibrary.module.loadimage.interfaces.ILoadImage;
import com.frame.fastframelibrary.module.loadimage.interfaces.ILoadImageCallback;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;

/**
 * Created by ZhangYuanBo on 2016/8/3.
 * 图片加载统一控制类
 */
public class LoadImageManager{
    enum LoadMode{
        Picasso
    }
    private boolean isDebug = false;
    private LoadMode curLoadMode = null;
    private static LoadImageManager instance = null;
    private static ILoadImage loadImageInstance = null;

    private LoadImageManager() {
        loadImageInstance = new PicassoHelper();
        curLoadMode = LoadMode.Picasso;
    }

    public synchronized static LoadImageManager instance() {
        if (instance==null) {
            instance = new LoadImageManager();
        }
        return instance;
    }
    private Context getApplication(){
        return FastApplication.instance();
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public LoadMode getLoadMode(){
        return curLoadMode;
    }

    public void loadImage(ImageView iv, Object imageUrl)  {
        loadImage(iv,imageUrl,ILoadImage.NONE,ILoadImage.NONE,ILoadImage.NONE,ILoadImage.NONE,false,null);
    }

    public void loadImage( ImageView iv, Object imageUrl, int loadImgResId)  {
        loadImage(iv,imageUrl,ILoadImage.NONE,ILoadImage.NONE,loadImgResId,ILoadImage.NONE,false,null);
    }

    public void loadImage( ImageView iv, Object imageUrl, int loadImgResId, boolean isTransform)  {
        loadImage(iv,imageUrl,ILoadImage.NONE,ILoadImage.NONE,loadImgResId,ILoadImage.NONE,isTransform,null);
    }

    public void loadImage( ImageView iv, Object imageUrl, int loadImgResId, boolean isTransform,ILoadImageCallback callback)  {
        loadImage(iv,imageUrl,ILoadImage.NONE,ILoadImage.NONE,loadImgResId,ILoadImage.NONE,isTransform,callback);
    }

    /**
     *  加载图片
     * @param iv
     * @param imageUrl      支持的格式：除正常的imageurl字符串外， load(R.drawable.landing_screen); load("file:///android_asset/DvpvklR.png");load(new File(...))
     * @param width         指定的图片宽
     * @param height        指定的图片高
     * @param loadImgResId  默认加载的图片
     * @param errImgResId   加载错误时的图片
     * @param isTransform   是否显示加载动画
     * @param callback      因调接口
     */
    public void loadImage(ImageView iv, Object imageUrl, int width, int height, int loadImgResId, int errImgResId,boolean isTransform,ILoadImageCallback callback){
        if(isSupportImageUrlType(imageUrl)){
            loadImageInstance.loadImage(getApplication(),iv,imageUrl,width,height,loadImgResId,errImgResId,isTransform,callback);
        }
    }

    /**
     * Check support image url type
     * @param imageUrl
     * @return
     */
    public boolean isSupportImageUrlType(Object imageUrl){
        if(loadImageInstance.isSupportImageUrlType(imageUrl)){
            return true;
        }
        if(isDebug){
            throw new LoadImageException("ImageUrl format nonsupport.");
        }
        return false;
    }

    /** 根据项目内本地图片文件路径获取加载路径*/
    public String getLocaImageUrl(String sourceImageUrl) {
        if(StringUtils.isNotEmpty(sourceImageUrl)){
            sourceImageUrl="file://"+sourceImageUrl.replace("\\","/");
        }
        return sourceImageUrl;
    }
}
