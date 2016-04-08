package com.frame.fastframe.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.frame.fastframelibrary.ui.base.FrameBaseFragment;
import org.xutils.x;

public class BaseFragment extends FrameBaseFragment {
    protected boolean injected = false;
    protected Activity mParentActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        injected = true;
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mParentActivity  = this.getActivity();
        if (!injected) {
            x.view().inject(this, this.getView());
        }
        initView();
        initData(savedInstanceState);
    }

    /**
     * 初始化布局控件
     */
    protected void initView() {

    }

    /**
     * 初始化界面数据
     */
    protected void initData(Bundle savedInstanceState) {
    }
}