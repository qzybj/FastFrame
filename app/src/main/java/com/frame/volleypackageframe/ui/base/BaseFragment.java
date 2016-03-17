package com.frame.volleypackageframe.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frame.volleypackageframe.ui.simple.adapter.FragmentPagerAdapter;

import org.xutils.x;

/**
 * Created by wyouflf on 15/11/4.
 */
public class BaseFragment extends Fragment {
    public final static String KEY_TITLE = "title";

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