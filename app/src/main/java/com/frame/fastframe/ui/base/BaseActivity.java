package com.frame.fastframe.ui.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.frame.fastframe.module.common.constant.CommonConstants;
import com.frame.fastframe.utils.AccountUtils;
import com.frame.fastframelibrary.ui.base.FrameBaseActivity;
import com.frame.fastframelibrary.utils.ActivityStack;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.ToastUtils;
import com.frame.fastframelibrary.utils.ViewUtils;

/**
 * Activity的base基类
 */
public abstract class BaseActivity extends FrameBaseActivity{

	//-------------控制开关 --------------
	/**是否弹出dialog加载进度条(屏蔽界面操作)	 */
	public boolean isLoadProgress = true;
	/**是否弹出dialog错误信息框 */
	public boolean isNetShowDialog = true;

	//-------------通用标题 --------------
	/**通用标题*/
	public TextView mTitleContent_tv;
	protected TextView tv_right;

	//-------------加载提示 --------------
	/**数据加载进度条*/
	public ProgressDialog mProgress;
	/**错误信息对话框*/
	public AlertDialog mErrorDialog;
	/**信息提示对话框*/
	public AlertDialog mLfAlertDialog;
	/**信息提示对话框*/
	public AlertDialog mExitDialog;
	protected View mLoadingProgressBar;
	//-------------控制器 --------------
	/**负责触发消息传递: 进度框显示 提醒框显示等*/
	public Handler basicHandler = new BasicHandler();

	//-------------通用变量 --------------
	/**用户ID*/
	protected String userid ;

	@Override
	public final void initSystemOperation(Bundle savedInstanceState) {
		userid = AccountUtils.getUid();
		ActivityStack.getInstance().addActivity(this);
	}

	protected void clickEvent(View v){

	}

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

//------------- 界面弹出框，提示信息等实现 --------------

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
	/**隐藏错误提示框*/
	public final void dissErrorDialog() {
		if (mErrorDialog!=null){
			mErrorDialog.dismiss();
		}
	}
	protected void showExitAlert() {
		if (mExitDialog==null) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("确认退出应用？");
			builder.setTitle("提示");
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which){
					//退出程序
					ActivityStack.getInstance().removeAllActivityExcept();
				}
			});
			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which){
					dialog.dismiss();
				}
			});
			mExitDialog = builder.create();
		}
		mExitDialog.show();
	}
	public final void showToast(int res) {
		showToast(getString(res));
	}

	public final void showToast(final String msg) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				ToastUtils.showToast(BaseActivity.this, msg, false);
			}
		});
	}

	//------------- 消息传递控制机制实现 --------------
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

	private class BasicHandler extends Handler {
		private void dispatchData(Object obj) {

		}
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case CommonConstants.MSG_WHAT_DATA_START:
					if (isLoadProgress) {
						showProgress();
					}
					break;
				case CommonConstants.MSG_WHAT_DATA_CANCEL:
					if (isLoadProgress) {
						dismissProgress();
					}
					break;
				case CommonConstants.MSG_WHAT_DATA_DONE:
					if (isLoadProgress) {
						dismissProgress();
					}
					dispatchData(msg.obj);
					break;
				case CommonConstants.MSG_WHAT_SHOWTOAST:
					showToast((String)msg.obj);
				default:
					baseHandleMessage(msg);
					break;
			}
		}
	}

	//------------- Activity界面操作监听 --------------
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityStack.getInstance().removeActivity(this);
	}
	@Override
	public void onBackPressed() {
		if(ViewUtils.isVisibleView(mLoadingProgressBar)){
			dismissProgress();//如果是加载，取消加载
		}else {
			super.onBackPressed();
		}
	}
}