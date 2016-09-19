package earlll.com.testdemoall.core.ui.base;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.frame.fastframelibrary.ui.base.FrameBaseActivity;
import com.frame.fastframelibrary.utils.app.ActivityStack;
import com.frame.fastframelibrary.utils.jump.JumpBaseUtils;
import com.frame.fastframelibrary.utils.view.ToastUtils;
import com.frame.fastframelibrary.utils.view.ViewUtils;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.config.Constants;
import earlll.com.testdemoall.core.ui.fragment.bar.TabBarFragment;
import earlll.com.testdemoall.core.ui.fragment.bar.TitleBarFragment;
import earlll.com.testdemoall.core.ui.fragment.dialog.AlertDialogFragment;
import earlll.com.testdemoall.core.ui.fragment.interfaces.ITitleBarClickListener;
import earlll.com.testdemoall.core.utils.AccountUtils;
import earlll.com.testdemoall.core.utils.JumpUtils;

/**
 * Activity的base基类
 */
public abstract class BaseFragmentActivity extends FrameBaseActivity implements ITitleBarClickListener {

	//-------------控制开关 --------------
	/**是否弹出dialog加载进度条(屏蔽界面操作) */
	public boolean isLoadProgress = true;
	/**是否弹出dialog错误信息框 */
	public boolean isNetShowDialog = true;

	//-------------通用标题 --------------
	protected TitleBarFragment titleBar;
	//------------- Tab栏 --------------
	protected TabBarFragment tabBar;

	//-------------加载提示 --------------
	/**数据加载进度条*/
	public ProgressDialog mProgress;
	/**错误信息对话框*/
	public AlertDialog mErrorDialog;
	/**信息提示对话框*/
	public AlertDialog mLfAlertDialog;

	/**信息提示对话框*/
	public AlertDialogFragment mExitDialog;

	protected View mLoadingProgressBar;
	//-------------控制器 --------------
	/**负责触发消息传递: 进度框显示 提醒框显示等*/
	public Handler basicHandler = new BasicHandler();

	//-------------通用变量 --------------
	/**用户ID*/
	protected String userid ;

	@Override
	public final void initConstant(Bundle savedInstanceState) {
		userid = AccountUtils.getUid();
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

	public void initTitleBar(View mainView) {
		titleBar =  (TitleBarFragment)getFragmentManager().findFragmentById(R.id.fragment_title);
	}

	/**标题栏   -  左边按钮触发事件*/
	public void onClickTitleLeft(View v) {
		finish();
	}
	/**标题栏   -  右边按钮触发事件*/
	public void onClickTitleRight(View v) {	}

	/**
	 * 点击事件处理<BR>
	 * @param v
	 */
	protected void customClickEvent(View v){}

	@Override
	final public void onClick(View v) {
		customClickEvent(v);
	}
//------------- 界面弹出框，提示信息等实现 --------------

	/**加载效果(界面单独实现)*/
	public void showProgress() {
		mProgress = ProgressDialog.show(this, "", "Loading...");
	}

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
			mExitDialog = new AlertDialogFragment();
		}
		mExitDialog.setTitle("提示");
		mExitDialog.setContent("确认退出应用？");
		mExitDialog.setBtn("a","b");
		showDialogFragment(mExitDialog);
	}

	public final void showToast(int res) {
		showToast(getString(res));
	}

	public final void showToast(final String msg) {
		ToastUtils.showToast(BaseFragmentActivity.this, msg, false);
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
			getNetworkDataComplete(obj);
		}
		@Override
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

	/**
	 * 展示Fragment
	 * @param targetFragment 目标fragment
     */
	protected void showDialogFragment(DialogFragment targetFragment){
		showDialogFragment(targetFragment,targetFragment.getTag());
	}

	/**
	 * 展示Fragment
	 * @param targetFragment 目标fragment
	 * @param tag
     */
	protected void showDialogFragment(DialogFragment targetFragment,String tag){
		if(!isFinishing()&&targetFragment!=null){
			targetFragment.show(getFragmentManager(), tag);
		}
	}

	/**
	 *
	 * @param cls
	 * @param title
	 * @param args
     */
	protected void goActivity(Class cls,String title,Bundle args){
		JumpBaseUtils.goActivity(this, JumpUtils.getJumpBean(cls,title,args));
	}

	protected boolean isSupportTitleBar(){
		return titleBar !=null;
	}
	protected boolean isSupportTabBar(){
		getTabBar();
		return tabBar !=null;
	}
	protected TabBarFragment getTabBar(){
		if(tabBar==null){
			tabBar = (TabBarFragment)getFragmentManager().findFragmentById(R.id.fragment_tabbar);
		}
		return tabBar;
	}
}