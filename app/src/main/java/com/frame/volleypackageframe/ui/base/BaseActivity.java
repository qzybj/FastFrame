package com.frame.volleypackageframe.ui.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.frame.volleypackageframe.utils.ActivityStack;
import com.frame.volleypackageframe.utils.LogUtils;
import com.frame.volleypackageframe.utils.ToastHelper;
import org.xutils.x;

/**
 * Activity的base基类
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener{
	/**是否为调试模式*/
	protected boolean isDebug =  false;
	protected Context mBaseContext;

	protected View mRootViewContainer ;
	/**提示Toast*/
	public static final int SHOWTOASTMESSAGE = 110001;
	/**通用标题*/
	public TextView mTitleContent_tv;

	protected TextView tv_right;

	/**是否弹出dialog加载进度条(屏蔽界面操作)	 */
	public boolean isLoadProgress = true;
	
	/**是否弹出dialog错误信息框 */
	public boolean isNetShowDialog = true;
	
	/**数据加载进度条*/
	public ProgressDialog mProgress;
	/**错误信息对话框*/
	public AlertDialog mErrorDialog;
	/**信息提示对话框*/
	public AlertDialog mLfAlertDialog;
	/**信息提示对话框*/
	public AlertDialog mExitDialog;
	
	protected View mLoadingProgressBar;
	
	/**网络重试  - handler*/
	private Handler retryHandler = null;
	/**网络重试  - 状态值*/
	private static final int MSG_RETRY_DIALOG = 324324;

	/**网络重试  - handler*/
	public String userid ;

	/**负责基本的 进度框显示 提醒框显示等*/
	public Handler basicHandler = new BasicHandler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBaseContext=  this;
		userid ="111";
		int layoutResouceId = getLayoutResouceId();
		if (layoutResouceId>0) {
			mRootViewContainer  =inflateView(layoutResouceId);
			if (mRootViewContainer != null) {
				setContentView(mRootViewContainer);
				x.view().inject(this);//view绑定
				initContentView();
			}
		}
		initData(savedInstanceState);
		ActivityStack.getInstance().addActivity(this);
	}
	/**
	 * 获取主界面布局资源id<BR>
	 * sample: return R.layout.main<BR>
	 * @return int
	 */
	public abstract int getLayoutResouceId();
	/**
	 *
	 * 布局控件初始化相关操作<BR>
	 * @return
	 */
	public abstract void initContentView();
	/**
	 * 数据初始化相关操作<BR>
	 * @param savedInstanceState
	 */
	public abstract void initData(Bundle savedInstanceState);

	/**
	 * 点击事件处理<BR>
	 * @param v
	 */
	protected abstract void clickEvent(View v);

	/**
	 * 当调用了网络请求后返回的数据会执行该方法，在该方法内可以把数据设置到view上。<BR>
	 * @param obj
	 */
	protected void getNetworkDataComplete(Object obj) {

	}

	/**
	 * 处理handle消息 <BR>
	 * @param msg
	 */
	protected void baseHandleMessage(Message msg) {

	}

	/**界面标题(重写返回值)*/
	protected String getTitleContent(){
		return null;
	}

	/**是否显示标题栏左边返回键(重写返回值)*/
	protected boolean isShowTitleBarLeftBack(){
		return false;
	}
	/**标题栏左边返回监听(重写返回值)*/
	protected View.OnClickListener getTitleBarLeftBackClick(){
		return null;
	}
	/**是否显示标题栏(重写返回值)*/
	protected boolean isShowTitleBar(){
		return false;
	}

	/**界面标题右侧ImageView按钮ResId(重写返回值)*/
	protected int getRightImageViewResID(){
		return 0;
	}
	/**界面标题右侧TextView按钮(重写返回值)*/
	protected int getRightTextViewResID(){
		return 0;
	}
	/**标题栏右边返回监听(重写返回值)*/
	public View.OnClickListener getTitleBarRightClick() {
		return null;
	}
	/**界面标题右侧TextView按钮有特殊需求进行初始化*/
	protected void initUiRightTextView(){
	}

	@Override
	public void onClick(View v) {
		clickEvent(v);
	}

	public void sendMessage(int what) {
		sendMessage(what, 0, 0, null);
	}

	public void sendMessage(int what, Object data) {
		sendMessage(what, 0, 0, data);
	}

	public void sendMessage(int what, int arg1, int arg2, Object data) {
		if (basicHandler != null) {
			Message message = basicHandler.obtainMessage(what, arg1, arg2);
			message.obj = data;
			basicHandler.sendMessage(message);
		}
	}

	/**隐藏错误提示框*/
	public final void dissErrorDialog() {
		if (mErrorDialog!=null){
			mErrorDialog.dismiss();
		}
	}

	private class BasicHandler extends Handler {
		private void dispatchData(Object obj) {

		}

		public void handleMessage(Message msg) {
			switch (msg.what) {
//			case ApiClient.MSG_WHAT_DATA_START:
//				if (isLoadProgress) {
//					showProgress();
//				}
//				break;
//			case ApiClient.MSG_WHAT_DATA_CANCEL:
//				if (isLoadProgress) {
//					dismissProgress();
//				}
//				break;
//			case ApiClient.MSG_WHAT_DATA_DONE:
//				if (isLoadProgress) {
//					dismissProgress();
//				}
//				dispatchData(msg.obj);
//				break;
			case SHOWTOASTMESSAGE:
				showToast((String)msg.obj);
			default:
				baseHandleMessage(msg);
				break;
			}
		}
	}
	

	/**改变界面标题*/
	public void setTitleMsg(final String title) {
		if (mTitleContent_tv != null) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					mTitleContent_tv.setText(title);
				}
			});
		}
	}

	/**根据layout布局填充View*/
	private View inflateView(int layoutResouceId) {
		return LayoutInflater.from(this).inflate(layoutResouceId, null);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		//ImageLoaderHelper.getInstance().getImageLoader().resume();
	}
	@Override
	protected void onPause() {
		super.onPause();
		//ImageLoaderHelper.getInstance().getImageLoader().pause();
	}
	@Override
	protected void onStop() {
		super.onStop();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityStack.getInstance().removeActivity(this);
		//ImageLoaderHelper.getInstance().getImageLoader().stop();
	}

	protected void showExitAlert() {
		if (mExitDialog==null) {
			mExitDialog =new AlertDialog.Builder(this).create();
			mExitDialog.setTitle("提示");
			mExitDialog.setMessage("确认退出应用？");
			mExitDialog.setButton("确定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i) {
					//退出程序
					ActivityStack.getInstance().removeAllActivityExcept();
				}
			});
		}
		mExitDialog.show();
	}


	/**加载效果(界面单独实现)*/
	public void showProgress() {
		mProgress = ProgressDialog.show(this, "", "Loading...");
	};

	/**取消加载效果(界面单独实现)*/
	public void dismissProgress() {
		if(mProgress != null){
			mProgress.dismiss();
		}
	};


	public final void showToast(int res) {
		showToast(getString(res));
	}

	public final void showToast(final String msg) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				ToastHelper.showToast(BaseActivity.this, msg, false);
			}
		});
	}

	public final void showErrorDialog(int errorId) {
		showErrorDialog(getString(errorId));
	}

	public final void showErrorDialog(final String errorInfo) {
		showErrorDialog("", errorInfo);
	}

	/**
	 * 显示错误提示框
	 * @param positiveBtnMsg 确定按钮text
	 * @param errorInfo 错误提示信息
	 */
	public final void showErrorDialog(final String positiveBtnMsg, final String errorInfo) {
		if (mErrorDialog==null) {
			mErrorDialog =new AlertDialog.Builder(this).create();
			mErrorDialog.setTitle("提示");
			mErrorDialog.setMessage("请求数据错误");
			mErrorDialog.setButton("确定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i) {
					mErrorDialog.dismiss();
				}
			});
		}
		mErrorDialog.show();
	}
	
	/**隐藏软键盘*/
	protected void hideSoftInput() {
		try {
			final View v = getWindow().peekDecorView();
			if (v != null && v.getWindowToken() != null) {
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
			}
		} catch (Exception e) {
			LogUtils.e(e.toString());
		}
	}

	@Override
	public void onBackPressed() {
		if(mLoadingProgressBar!=null&&mLoadingProgressBar.getVisibility()== View.VISIBLE){
			//如果是加载，取消加载
			dismissProgress();
		}else {
			super.onBackPressed();
		}
	}
}