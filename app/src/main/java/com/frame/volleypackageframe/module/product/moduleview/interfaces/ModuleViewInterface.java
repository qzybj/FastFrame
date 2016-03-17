package com.frame.volleypackageframe.module.product.moduleview.interfaces;

import android.view.View;

import com.frame.volleypackageframe.module.product.moduleview.bean.BaseModuleViewBean;

public interface ModuleViewInterface {
    /**
     *获取解析布局
     */
    int getLayoutResouceId();

    /**
     * 布局控件初始化相关操作<BR>
     * @param mRootView
     * @return
     */
    void initContentView(View mRootView);
    /**
     * 刷新数据
     * @param bean
     */
    void refreshData(BaseModuleViewBean bean);
}
