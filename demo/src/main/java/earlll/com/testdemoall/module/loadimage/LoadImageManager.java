package earlll.com.testdemoall.module.loadimage;

import android.content.Context;
import android.widget.ImageView;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import earlll.com.testdemoall.MyApplication;
import earlll.com.testdemoall.aosp.picasso.PicassoHelper;
import earlll.com.testdemoall.module.loadimage.exception.LoadImageException;
import earlll.com.testdemoall.module.loadimage.interfaces.ILoadImage;
import earlll.com.testdemoall.module.loadimage.interfaces.ILoadImageCallback;
import earlll.com.testdemoall.module.loadimage.interfaces.impl.LoadImageCallbackImpl;

/**
 * Created by ZhangYuanBo on 2016/8/3.
 * 图片加载统一控制类
 */
public class LoadImageManager{
    enum LoadMode{
        Picasso
    }
    private final boolean isDebug = true;
    private LoadMode curLoadMode = null;
    private static LoadImageManager instance = null;
    private static ILoadImage loadImageInstance = null;

    private LoadImageManager() {
        loadImageInstance = new PicassoHelper();
        curLoadMode = LoadMode.Picasso;
    }

    public static LoadImageManager instance() {
        if (instance==null) {
            instance = new LoadImageManager();
        }
        return instance;
    }
    private Context getApplication(){
        return MyApplication.instance();
    }
    public LoadMode getLoadMode(){
        return curLoadMode;
    }

    public void loadImage(ImageView iv, Object imageUrl)  {
        if(isSupportImageUrlType(imageUrl)){
            loadImageInstance.loadImage(getApplication(),iv,imageUrl);
        }
    }

    public void loadImage( ImageView iv, Object imageUrl, int loadImgResId)  {
        if(isSupportImageUrlType(imageUrl)){
            loadImageInstance.loadImage(getApplication(),iv,imageUrl,loadImgResId);
        }
    }

    public void loadImage( ImageView iv, Object imageUrl, int loadImgResId, boolean isTransform)  {
        if(isSupportImageUrlType(imageUrl)){
            loadImageInstance.loadImage(getApplication(),iv,imageUrl,loadImgResId,isTransform);
        }
    }

    public void loadImage( ImageView iv, Object imageUrl, int loadImgResId, boolean isTransform,LoadImageCallbackImpl callback)  {
        if(isSupportImageUrlType(imageUrl)){
            loadImageInstance.loadImage(getApplication(),iv,imageUrl,loadImgResId,isTransform,callback);
        }
    }

    /**
     *  加载图片
     * @param iv
     * @param imageUrl      支持的格式：除正常的imageurl字符串外， load(R.drawable.landing_screen); load("file:///android_asset/DvpvklR.png");load(new File(...))
     * @param width         指定的图片宽
     * @param height        指定的图片高
     * @param loadImgResId  默认加载的图片
     * @param errImgResId   加载错误时的图片
     * @param callback      因调接口
     */
    private void loadImage(ImageView iv, Object imageUrl, int width, int height,int loadImgResId,int errImgResId,LoadImageCallbackImpl callback)   {
        if(isSupportImageUrlType(imageUrl)){
            loadImageInstance.loadImage(getApplication(),iv,imageUrl,width,height,loadImgResId,errImgResId,false,callback);
        }
    }

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
