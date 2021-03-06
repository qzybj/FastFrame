package com.frame.fastframe.module.html5.interfaces.impl;

import android.app.Activity;
import android.webkit.WebView;

import com.frame.fastframelibrary.aosp.bridgewebview.bean.JSBridgeBean;
import com.frame.fastframe.module.html5.config.H5Constant;
import com.frame.fastframe.module.html5.interfaces.IWebView4Activity;
import com.frame.fastframe.module.html5.utils.H52NativeUtils;
import com.frame.fastframelibrary.utils.json.GsonUtils;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;

/**
 * H5交互桥工具类<br/>
 * H5调用原生方法的具体实现
 */
public class H5CallNativeBindIMPL {
	private  String TAG = H5CallNativeBindIMPL.class.getSimpleName();

	public IWebView4Activity mIWebView4Activity;
	public Activity mActivity;
	private WebView mWebView;

	public H5CallNativeBindIMPL() {
		super();
	}

	/**
	 * 设置bind 对应 WebView
	 * @param iBind
     */
	public void bind(IWebView4Activity iBind) {
		if(iBind!=null){
			this.mIWebView4Activity = iBind;
			this.mActivity = iBind.getActivity();
			this.mWebView = iBind.getWebView();
		}
	}

	/**
	 * 是否支持WebView的调用Native方法
	 * @return
	 */
	public boolean isSupportWebCallNative() {
		return getWebView() != null;
	}

	public WebView getWebView() {
		return mWebView;
	}

	/**
	 * WebView操作处理 - 重新加载
	 */
	public void reloadUrl() {
		LogUtils.d(TAG, "reload");
		if (isSupportWebCallNative()) {
			getWebView().reload();
		}
	}

	/**
	 * WebView操作处理 - 打开指定URL
	 * @param url
	 */
	public void loadUrl(String url) {
		LogUtils.d(TAG, "toUrl= " + url);
		if (isSupportWebCallNative()) {
			getWebView().loadUrl(url);
		}
	}

	/**
	 * WebView操作处理 - 回退
	 */
	public void goBack() {
		LogUtils.d(TAG, "goBack");
		if (isSupportWebCallNative()) {
			if (getWebView().canGoBack()) {
				getWebView().goBack();
			}
		}
	}


	/**
	 *  Native支持method - 获取用户信息
	 * @return
     */
	public String getUserInfo() {
		return H52NativeUtils.getUserInfo(mActivity);
	}

	/**
	 *  Native支持method - 调用Native端的对应方法
	 * @return
	 */
	public void h5CallNative(String json) {
		LogUtils.d(TAG, "h5CallNative " + json);
		try {
			JSBridgeBean bean  = GsonUtils.toObject(json,JSBridgeBean.class);
		} catch (Exception e) {
			LogUtils.e(e);
		}
	}

	/**
	 * Native支持method - 根据事先商定协议值，做相应处理
	 * @param what
	 * @param json
	 */
	public void sendHandlerMessage(int what, String json) {
		LogUtils.d(TAG, "sendHandlerMessage msg=%s" + what + ",json=%s" + json);
		mIWebView4Activity.sendMessage(what, json);
	}

	/**
	 * Native支持method - 弹出提示框展示信息
	 */
	public void showMsg(String msg) {
		LogUtils.d(TAG, "showMsg-->msg=" + msg);
		mIWebView4Activity.sendMessage(H5Constant.H5CALLNATIVE_SHOWDIG_MSGID,msg);
	}

	/**
	 * Native支持method - 显示加载进度框
	 */
	
	public void showProgress() {
		LogUtils.d(TAG, "showProgress");
		mIWebView4Activity.sendMessage(H5Constant.H5CALLNATIVE_SHOWPROGRESSDLG_MSGID);
	}

	/**
	 * Native支持method - 隐藏加载进度框
	 */
	
	public void disProgress() {
		LogUtils.d(TAG, "disProgress");
		mIWebView4Activity.sendMessage(H5Constant.H5CALLNATIVE_DISSPROGRESSDLG_MSGID);
	}

	/**Native支持method - 刷新上一页面*/
	
	public void refreshPreviousPage(){
		LogUtils.d(TAG, "disProgress");
		mIWebView4Activity.sendMessage(H5Constant.H5CALLNATIVE_REFRESH_PREVIOUS_PAGE_MSGID);
	}

	/**关闭当前界面*/
	
	public void closeCurrentPage() {
		if (mActivity != null) {
			mActivity.finish();
		}
	}

	/**
	 * 跳转到指定APP页面(当前页)
	 * @param id 区分跳转的页面id(废弃)
	 * @param params 跳转协议
	 */
	
	public void jumpself(int id, String params) {
		LogUtils.d(TAG, "jumpself " + id + " " + params);
		jumpProtocol(params);
	}
	
	
	public void jumpProtocol(String protocolStr) {
		LogUtils.d(TAG, "jumpProtocol " + protocolStr);
		if (!StringUtils.isEmpty(protocolStr)) {
			mIWebView4Activity.sendMessage(H5Constant.H5CALLNATIVE_STARTACTIVITY_MSGID,protocolStr);
		}
	}
	
	//--------------走scheme协议
	/** 
	 * h5调原生对话框
	 * @param params
	 *  示例  yintaimobile://ShowDialog?msg=商品删除成功！
	 *  参数含义：
	 *   msg： 提示内容
	 */
	
	public void showDialog(String params) {
//		JumpURI uri = SchemeParser.parseJumpParams(params);
//		if( uri == null ){
//			//不支持的类型
//			YTLog.e("not support type");
//			return ;
//		}
//		HashMap<String,String> paramMap = uri.getParamMap();
//		JumpType jumpType = uri.getJumpType();
//		if (JumpType.ShowDialog==jumpType) {
//			if (paramMap.containsKey("msg")) {
//				mActivity.showErrorDialog(paramMap.get("msg"));
//			}
//		}
	}
	
	/** 
	 * 
	 * h5调原生对话框
	 * @param params
	 *  示例  yintaimobile://ShowDialog?msg=商品删除成功！
	 *  参数含义：
	 *   msg： 提示内容
	 */
	
	public void setTitleBar(String params) {
		sendHandlerMessage(H5Constant.H5CALLNATIVE_SETTITLEBAR_MSGID, params);
	}

	/**
	 * 跳转到指定APP页面(跳转另一页)
	 * @param params 跳转协议
	 */
	
	public void jump(String params) {
		jumpProtocol(params);
	}

	
	/**H5是否响应标题栏点击返回操作（和响应返回按钮组合使用）*/
	
	public void isResponseClickBack() {
		//mIWebView4Activity.isClickBack();
	}


	public boolean isLogin() {
//		//需要先跳登录页面,判断是否已登录
//		if (mActivity!=null&& StringUtils.isEmpty(mActivity.getUserID())) {
//			//未登录，需要跳转到登录页，并且给出提示
//			Intent intent = new Intent(mActivity, LoginActivity.class);
//			intent.setClass(mActivity, LoginActivity.class);
//			ToastUtils.showToast(mActivity, R.string.template_jump_needlogin);
//			mActivity.startActivity(intent);
//			return false;
//		}
		return true;
	}

}