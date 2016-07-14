package com.frame.fastframelibrary.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.frame.fastframelibrary.utils.ViewUtils;
import org.xutils.x;

/**
 * 框架Activity的base基类
 */
public abstract class FrameBaseActivity extends AppCompatActivity implements View.OnClickListener{
	protected Activity mBaseActivity;
	protected View mRootViewContainer ;

	@Override
	protected final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBaseActivity= this;
		int layoutResouceId = getLayoutResouceId();
		if (layoutResouceId>0) {
			mRootViewContainer  = ViewUtils.inflateView(getBaseActivity(),layoutResouceId);
			if (mRootViewContainer != null) {
				setContentView(mRootViewContainer);
				x.view().inject(this);//view绑定
				initTitleBar(mRootViewContainer);
				initContentView(mRootViewContainer);
			}
		}
		initSystemOperation(savedInstanceState);
		initData(savedInstanceState);
	}

	protected Activity getBaseActivity(){
		return mBaseActivity;
	}

	/**
	 * 初始化系统变量操作，保留<BR>
	 */
	public abstract void initSystemOperation(Bundle savedInstanceState);

	/**
	 * 获取主界面布局资源id<BR>
	 * sample: return R.layout.main<BR>
	 * @return int
	 */
	public abstract int getLayoutResouceId();

	/**
	 * 初始化标题栏
	 * @param view
     */
	public abstract void initTitleBar(View view);

	/**
	 *
	 * 布局控件初始化相关操作<BR>
	 * @param view
     */
	public abstract void initContentView(View view);
	/**
	 * 数据初始化相关操作<BR>
	 * @param savedInstanceState
	 */
	public abstract void initData(Bundle savedInstanceState);

}