package com.frame.fastframelibrary.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.view.ViewUtils;
import butterknife.ButterKnife;

/**
 * 框架 - Activity的base基类，只包含最基本的
 */
public abstract class FrameBaseActivity extends AppCompatActivity implements View.OnClickListener,IBaseUI{
	private Activity mBaseActivity;
	private View mRootView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBaseActivity= this;
		int layoutResId = getLayoutResId();
		if (layoutResId>0) {
			try {
				mRootView = ViewUtils.inflateView(getBaseActivity(),layoutResId);
			} catch (Exception e) {
				LogUtils.e(e);
			}
			if (mRootView != null) {
				setContentView(mRootView);
				ButterKnife.bind(this);
				initTitleBar(mRootView);
				initContentView(mRootView);
			}
		}
		initConstant(savedInstanceState);
		initData(savedInstanceState);
	}
	public Activity getBaseActivity(){
		return mBaseActivity;
	}

	/**
	 * 初始化标题栏
	 * @param view
	 */
	protected abstract void initTitleBar(View view);

	@Override
	public void initConstant(Bundle savedInstanceState) {}
}