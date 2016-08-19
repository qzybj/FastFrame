package brady.com.appframe.common.ui.fragment.interfaces;

import android.view.View;

/**
 * Created by ZhangYuanBo on 2016/5/30.
 * 用于项部栏的点击事件监听回调
 */
public interface ITitleBarClickListener {
    /**
     * 项部栏的点击事件监听回调
     * @param v
     */
    void onTitleBarClick(View v);

    /**
     * 标题栏   -  左边按钮触发事件
     * @param v
     */
     void onClickTitleLeft(View v);

    /**
     * 标题栏   -  右边按钮触发事件
     * @param v
     */
    void onClickTitleRight(View v) ;
}
