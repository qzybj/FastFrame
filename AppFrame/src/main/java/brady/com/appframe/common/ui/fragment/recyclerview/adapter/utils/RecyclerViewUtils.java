package brady.com.appframe.common.ui.fragment.recyclerview.adapter.utils;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;
import com.frame.fastframelibrary.module.loadimage.LoadImageManager;
import com.frame.fastframelibrary.utils.LogUtils;

import brady.com.appframe.CApplication;
import brady.com.appframe.common.ui.fragment.recyclerview.annotation.RecyclerViewStyle;


/**
 * Created by ZhangYuanBo on 2016/8/17.
 */
public class RecyclerViewUtils {

    public static void loadImage(ImageView iv, Object imageUrl)  {
        LoadImageManager.instance().loadImage(iv, imageUrl);
    }

    public static RecyclerView.LayoutManager getRecyclerViewManager(int currStyle,int span)  {
        LogUtils.d("aaa","currStyle ="+currStyle+"   span="+span);
        RecyclerView.LayoutManager mLayoutManager;
        switch (currStyle) {
            case RecyclerViewStyle.VERTICAL_LIST:
                mLayoutManager = new LinearLayoutManager(CApplication.instance(), LinearLayoutManager.VERTICAL, false);
                break;
            case RecyclerViewStyle.HORIZONTAL_LIST:
                mLayoutManager = new LinearLayoutManager(CApplication.instance(), LinearLayoutManager.HORIZONTAL, false);
                break;
            case RecyclerViewStyle.VERTICAL_GRID:
                mLayoutManager = new GridLayoutManager(CApplication.instance(), span, GridLayoutManager.VERTICAL, false);
                break;
            case RecyclerViewStyle.HORIZONTAL_GRID:
                mLayoutManager = new GridLayoutManager(CApplication.instance(), span, GridLayoutManager.HORIZONTAL, false);
                break;
            case RecyclerViewStyle.STAGGERED_GRID:
                mLayoutManager = new StaggeredGridLayoutManager(span, StaggeredGridLayoutManager.VERTICAL);
                break;
            default:
                mLayoutManager = new LinearLayoutManager(CApplication.instance(), LinearLayoutManager.VERTICAL, false);
                break;
        }
        return mLayoutManager;
    }


}
