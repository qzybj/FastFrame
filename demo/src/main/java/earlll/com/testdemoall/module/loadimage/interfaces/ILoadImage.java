package earlll.com.testdemoall.module.loadimage.interfaces;

import android.content.Context;
import android.widget.ImageView;
import earlll.com.testdemoall.module.loadimage.LoadImageUtils;


/**
 * Created by ZhangYuanBo on 2016/8/3.
 * 需要实现图片加载
 */
public interface ILoadImage {
    int NONE = -1;

    /**
     * 加载图片是否支持该格式ImageUrl
     * @param imageUrl
     * @return
     */
    boolean isSupportImageUrlType(Object imageUrl);
    /**
     *  加载图片
     * @param con
     * @param iv
     * @param imageUrl     支持的格式：除正常的imageurl字符串外， load(R.drawable.landing_screen); load("file:///android_asset/DvpvklR.png");load(new File(...))
     */
    void loadImage(Context con, ImageView iv, Object imageUrl);
    /**
     *  加载图片
     * @param con
     * @param iv
     * @param imageUrl     支持的格式：除正常的imageurl字符串外， load(R.drawable.landing_screen); load("file:///android_asset/DvpvklR.png");load(new File(...))
     */
    void loadImage(Context con, ImageView iv, Object imageUrl,int loadImgResId);
    /**
     *  加载图片
     * @param con
     * @param iv
     * @param loadImgResId 默认加载的图片
     * @param isTransform   是否加载动画
     * @param imageUrl     支持的格式：除正常的imageurl字符串外， load(R.drawable.landing_screen); load("file:///android_asset/DvpvklR.png");load(new File(...))
     */
    void loadImage(Context con, ImageView iv, Object imageUrl,int loadImgResId,boolean isTransform);
    /**
     *  加载图片
     * @param con
     * @param iv
     * @param loadImgResId 默认加载的图片
     * @param isTransform   是否加载动画
     * @param imageUrl     支持的格式：除正常的imageurl字符串外， load(R.drawable.landing_screen); load("file:///android_asset/DvpvklR.png");load(new File(...))
     * @param callback
     */
    void loadImage(Context con, ImageView iv, Object imageUrl,int loadImgResId,boolean isTransform,LoadImageUtils.ImageLoadCallback callback);

    /**
     *  加载图片
     * @param con
     * @param iv
     * @param imageUrl     支持的格式：除正常的imageurl字符串外， load(R.drawable.landing_screen); load("file:///android_asset/DvpvklR.png");load(new File(...))
     * @param width     指定的图片宽
     * @param height    指定的图片高
     * @param loadImgResId 默认加载的图片
     * @param errImgResId   加载错误时的图片
     * @param isTransform   是否加载动画
     * @param callback
     */
    void loadImage(Context con, ImageView iv, Object imageUrl, int width, int height, int loadImgResId, int errImgResId,boolean isTransform,LoadImageUtils.ImageLoadCallback callback);
}
