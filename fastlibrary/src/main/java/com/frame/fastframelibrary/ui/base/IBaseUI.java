package com.frame.fastframelibrary.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by ZhangYuanBo on 2016/8/11.
 * base UI common method
 */
public interface IBaseUI {

    /** 获取Activity*/
    Activity getBaseActivity();

    /**
     * 初始化系统变量，保留<BR>
     */
    void initConstant(Bundle savedInstanceState);

    /**
     * 获取主界面布局资源id<BR>
     * sample: return R.layout.main<BR>
     * @return int
     */
    int getLayoutResId();

    /**
     *
     * 布局控件初始化相关操作<BR>
     * @param view
     */
    void initContentView(View view);
    /**
     * 数据初始化相关操作<BR>
     * @param savedInstanceState
     */
    void initData(Bundle savedInstanceState);
}
