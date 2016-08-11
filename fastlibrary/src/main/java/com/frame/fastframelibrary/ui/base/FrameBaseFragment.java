package com.frame.fastframelibrary.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

/**
 * 框架 - Fragment的base基类，只包含最基本的
 */
public abstract class FrameBaseFragment extends Fragment implements IBaseUI{
    protected Activity mParentActivity;
    private View mRootView ;

    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState) {
        mParentActivity  = this.getActivity();
        super.onCreate(savedInstanceState);
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutResId = getLayoutResId();
        if (layoutResId>0) {
            mRootView  = inflater.inflate(layoutResId, container, false);
            if (mRootView != null) {
                ButterKnife.bind(this, mRootView);
                initContentView(mRootView);
                return mRootView;
            }
        }
        initData(savedInstanceState);
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    public Activity getBaseActivity(){
        return mParentActivity;
    }

}