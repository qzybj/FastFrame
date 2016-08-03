package earlll.com.testdemoall.module.loadimage;

import android.content.Context;
import android.widget.ImageView;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import earlll.com.testdemoall.MyApplication;
import earlll.com.testdemoall.aosp.picasso.PicassoHelper;
import earlll.com.testdemoall.module.loadimage.interfaces.IImageLoadCommon;

/**
 * Created by ZhangYuanBo on 2016/8/3.
 * 图片加载统一控制类
 */
public class ImageLoadUtils{

    private static ImageLoadUtils instance=null;
    private static IImageLoadCommon loadImageInstance=null;

    private ImageLoadUtils() {
        loadImageInstance = new PicassoHelper();
    }

    public static ImageLoadUtils instance() {
        if (instance==null) {
            instance = new ImageLoadUtils();
        }
        return instance;
    }
    private Context getApplication(){
        return MyApplication.instance();
    }

    /**
     *  加载图片
     * @param iv
     * @param imageUrl     支持的格式： load(R.drawable.landing_screen); load("file:///android_asset/DvpvklR.png");load(new File(...))
     */
    public void loadImage(ImageView iv, Object imageUrl) {
        loadImageInstance.loadImage(getApplication(),iv,imageUrl,-1,-1,-1,-1,false,null);
    }

    /**
     *  加载图片
     * @param iv
     * @param imageUrl     支持的格式： load(R.drawable.landing_screen); load("file:///android_asset/DvpvklR.png");load(new File(...))
     * @param width     指定的图片宽
     * @param height    指定的图片高
     * @param loadImgResId 默认加载的图片
     * @param errImgResId   加载错误时的图片
     * @param callback
     */
    private void loadImage(ImageView iv, Object imageUrl, int width, int height,int loadImgResId,int errImgResId,final ImageLoadCallback callback) {
        loadImageInstance.loadImage(getApplication(),iv,imageUrl,width,height,loadImgResId,errImgResId,false,callback);
    }

    /**根据项目内本地图片文件路径获取加载路径*/
    public String getLocaImageUrl(String sourceImageUrl) {
        if(StringUtils.isNotEmpty(sourceImageUrl)){
            sourceImageUrl="file://"+sourceImageUrl.replace("\\","/");
        }
        return sourceImageUrl;
    }

    public interface ImageLoadCallback {
        void onSuccess();
        void onError();
    }
}
