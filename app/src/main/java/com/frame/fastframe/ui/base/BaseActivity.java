package com.frame.fastframe.ui.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.frame.fastframe.R;
import com.frame.fastframe.module.common.constant.Constants;
import com.frame.fastframe.utils.UserManager;
import com.frame.fastframelibrary.ui.base.FrameBaseActivity;
import com.frame.fastframelibrary.utils.app.ActivityStack;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import com.frame.fastframelibrary.utils.view.TextViewUtils;
import com.frame.fastframelibrary.utils.view.ToastUtils;
import com.frame.fastframelibrary.utils.view.ViewUtils;

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
	public final void initConstant(Bundle savedInstanceState) {
		userid = UserManager.getUid();
		ActivityStack.getInstance().addActivity(this);
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

	//------------- 标题栏修改实现 --------------
	public LinearLayout mTitlebar_layout_leftbtn;
	public LinearLayout mTitlebar_layout_rightbtn;
	public TextView mTitlebar_tv_content;
	public TextView mTitlebar_tv_left;
	public TextView mTitlebar_tv_right;

	public void initTitleBar(View mainView) {
		mTitlebar_layout_leftbtn = (LinearLayout) mainView.findViewById(R.id.titlebar_layout_left);
		mTitlebar_tv_left = (TextView) mainView.findViewById(R.id.titlebar_tv_left);
		mTitlebar_layout_rightbtn = (LinearLayout) mainView.findViewById(R.id.titlebar_layout_right);
		mTitlebar_tv_right = (TextView) mainView.findViewById(R.id.titlebar_tv_right);
		mTitlebar_tv_content = (TextView) mainView.findViewById(R.id.titlebar_tv_content);
		if (mTitlebar_layout_leftbtn != null) {
			mTitlebar_layout_leftbtn.setOnClickListener(this);
		}
		if (mTitlebar_layout_rightbtn != null) {
			mTitlebar_layout_rightbtn.setOnClickListener(this);
		}

	}

	/**控制标题栏左边按钮显示或隐藏*/
	public final void setTitlebarLeftVisibility(boolean isVisibility) {
		if (mTitlebar_layout_leftbtn != null) {
			mTitlebar_layout_leftbtn.setVisibility(isVisibility?View.VISIBLE:View.GONE);
		}
	}
	/**
	 * 设置title左边按钮的显示内容
	 * @param titleResid
	 */
	public final void setTitlebarLeftText(int titleResid) {
		if (titleResid>0) {
			setTitlebarLeftText(getString(titleResid));
		}
	}
	/**
	 * 设置title左边按钮的显示内容
	 * @param titleStr
	 */
	public final void setTitlebarLeftText(String titleStr) {
		if (mTitlebar_tv_left != null&&StringUtils.isNotEmpty(titleStr)) {
			//TextViewUtils.setTextViewDrawable(mTitlebar_tv_left,null);
			mTitlebar_tv_left.setText(titleStr);
		}
	}

	/**
	 * 设置title左边按钮的显示图片
	 * @param drawableResid
	 */
	public final void setTitlebarLeftDrawable(int drawableResid,int padding) {
		if (mTitlebar_tv_left != null) {
			if (drawableResid>0) {
				Drawable drawable= getResources().getDrawable(drawableResid);
				if (drawable != null) {
					TextViewUtils.setTextViewDrawable(mTitlebar_tv_left,drawable,padding);
				}
			}else{
				TextViewUtils.setTextViewDrawable(mTitlebar_tv_left,null,padding);
			}
		}
	}

	/**
	 * 设置title内容
	 * @param titleResid
	 */
	public final void setTitlebarContent(int titleResid) {
		setTitlebarContent(getString(titleResid));
	}
	/**
	 * 设置title内容
	 * @param titleStr
	 */
	public final void setTitlebarContent(String titleStr) {
		if (mTitlebar_tv_content != null &&StringUtils.isNotEmpty(titleStr)) {
			mTitlebar_tv_content.setText(titleStr);
		}
	}

	/**控制标题栏右边按钮显示或隐藏*/
	public final void setTitlebarRightVisibility(boolean isVisibility) {
		if (mTitlebar_layout_rightbtn != null) {
			mTitlebar_layout_rightbtn.setVisibility(isVisibility?View.VISIBLE:View.GONE);
		}
	}

	/**
	 * 设置title右边按钮的显示内容
	 * @param titleResid
	 */
	public final void setTitlebarRightText(int titleResid) {
		if (titleResid>0) {
			setTitlebarRightText(getString(titleResid));
		}
	}
	/**
	 * 设置title右边按钮的显示内容
	 * @param titleStr
	 */
	public final void setTitlebarRightText(String titleStr) {
		if (mTitlebar_tv_right != null&&StringUtils.isNotEmpty(titleStr)) {
			//TextViewUtils.setTextViewDrawable(mTitlebar_tv_right,null);
			mTitlebar_tv_right.setText(titleStr);
		}
	}

	/**
	 * 设置title右边按钮的显示图片
	 * @param drawableResid
	 */
	public final void setTitlebarRightDrawable(int drawableResid,int padding) {
		if (mTitlebar_tv_right != null&&drawableResid>0) {
			Drawable drawable= getResources().getDrawable(drawableResid);
			if (drawable != null) {
				TextViewUtils.setTextViewDrawable(mTitlebar_tv_right,drawable,padding);
			}
		}
	}

	/**
	 * 点击事件处理<BR>
	 * @param v
	 */
	protected void customClickEvent(View v){}

	@Override
	final public void onClick(View v) {
		if (v == mTitlebar_layout_leftbtn) {
			onClickTitleLeft(v);
			return;
		} else if (v == mTitlebar_layout_rightbtn) {
			onClickTitleRight(v);
			return;
		} else{
			customClickEvent(v);
		}
	}

	/**标题栏   -  左边按钮触发事件*/
	public void onClickTitleLeft(View v) {
		finish();
	}
	/**标题栏   -  右边按钮触发事件*/
	protected void onClickTitleRight(View v) {

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
				case Constants.MSG_WHAT_DATA_START:
					if (isLoadProgress) {
						showProgress();
					}
					break;
				case Constants.MSG_WHAT_DATA_CANCEL:
					if (isLoadProgress) {
						dismissProgress();
					}
					break;
				case Constants.MSG_WHAT_DATA_DONE:
					if (isLoadProgress) {
						dismissProgress();
					}
					dispatchData(msg.obj);
					break;
				case Constants.MSG_WHAT_SHOWTOAST:
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