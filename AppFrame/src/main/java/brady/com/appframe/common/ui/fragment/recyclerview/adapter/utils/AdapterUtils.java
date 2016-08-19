package brady.com.appframe.common.ui.fragment.recyclerview.adapter.utils;

import android.widget.ImageView;
import com.frame.fastframelibrary.module.loadimage.LoadImageManager;


/**
 * Created by ZhangYuanBo on 2016/8/17.
 */
public class AdapterUtils {

    public static void loadImage(ImageView iv, Object imageUrl)  {
        LoadImageManager.instance().loadImage(iv, imageUrl);
    }

}
