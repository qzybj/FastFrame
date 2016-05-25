package com.frame.fastframe.module.html5.interfaces.impl;

import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.frame.fastframe.R;
import com.frame.fastframe.module.html5.config.ModuleConstant4Html5;
import com.frame.fastframe.module.html5.utils.TransfersLog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;


/**
 * H5交互桥工具类
 */
public class H5CallNativeIMPL{
	private static String TAG = "H5CallNativeIMPL";
	public static BaseWebviewActivity mActivity;
	private static WebView mWebView;
	private static H5CallNativeIMPL mNativeHtml5;

	public static final String H5_2_ANDROID_HOMEHOME = "H5_2_Android_home"; // 首页
	public static final String H5_2_Android_login = "H5_2_Android_login"; // 登录
	public static final String H5_2_Android_webview = "H5_2_Android_webview"; // h5展示
	public static final String H5_2_Android_browser = "H5_2_Android_browser"; // 浏览器显示

	public H5CallNativeIMPL() {

	}

	public synchronized static H5CallNativeIMPL getInstance(BaseWebviewActivity activity, WebView webView) {
		mActivity = activity;
		mWebView = webView;
		if (mNativeHtml5 == null) {
			mNativeHtml5 = new H5CallNativeIMPL();
		}
		return mNativeHtml5;
	}
	
	@JavascriptInterface
	public BaseWebviewActivity getActivity() {
		return mActivity;
	}

	/**
	 * 是否支持WebView的调用Native方法
	 * @return
	 */
	public boolean isSupportWebCallNative() {
		return mWebView != null;
	}

	public WebView getWebView() {
		if (isSupportWebCallNative()) {
			return mWebView;
		}
		return null;
	}

	/**
	 * 设置WebView
	 */
	public void SetWebView(WebView webview) {
		if (webview != null) {
			mWebView = webview;
		}
	}

	// --begin WebView操作处理
	/**
	 * 重新加载
	 */
	@Override
	@JavascriptInterface
	public void reloadUrl() {
		TransfersLog.d(TAG, "reload");
		if (isSupportWebCallNative()) {
			getWebView().reload();
		}
	}

	/**
	 * 打开指定URL
	 * 
	 * @param url
	 */
	@Override
	@JavascriptInterface
	public void loadUrl(String url) {
		TransfersLog.d(TAG, "toUrl= " + url);
		if (isSupportWebCallNative()) {
			getWebView().reload();
		}
	}

	/**
	 * 回退
	 */
	@Override
	@JavascriptInterface
	public void goBack() {
		TransfersLog.d(TAG, "goBack");
		if (isSupportWebCallNative()) {
			if (getWebView().canGoBack()) {
				getWebView().goBack();
			}
		}
	}

	/* end-------------------------------------------------------*/
	
	
	/* --begin     AppSupport:原生支持method，如获取用户信息等*/
	/**
	 * 调用大图显示图片
	 * @param imgUrl
	 * @param imgIndex  图片索引
	 * @param from  图片来源
	 */
	@JavascriptInterface
	public void showBigImg(String imgUrl, int imgIndex, String from) {
		Intent intent = new Intent(mActivity, BigPicDisplayActivity.class);
		MobclickAgent.onEvent(mActivity, "20026");
		intent.putExtra(BigPicDisplayActivity.INTENT_KEY_IMG, imgUrl);
		intent.putExtra(BigPicDisplayActivity.INTENT_KEY_IMG_INDEX, imgIndex);
		intent.putExtra(BigPicDisplayActivity.INTENT_KEY_FROM, from);
		mActivity.startActivity(intent);
	}

	/**
	 * 存储CPS信息到客户端
	 * @param cpsValue  cps信息
	 */
	@JavascriptInterface
	public void setCpsInfo(String cpsValue) {
		H5SaveNativeUtils.setCps(mActivity, cpsValue);
	}

	/** 获取用户信息*/
	@JavascriptInterface
	public String getuserinfo() {
		return H5CallNativeUtils.getuserinfo(mActivity);
	}
	
	/**打开加载层*/
	@JavascriptInterface
	public void pageloadopen() {
		showProgress();
	}

	/*** 关闭加载层*/
	@JavascriptInterface
	public void pageloadclose() {
		disProgress();
	}


	@Override
	@JavascriptInterface
	public void h5CallNative(String json) {
		TransfersLog.d(TAG, "h5CallNative " + json);
		try {
			JSONObject jsonObj = new JSONObject(json);
			if (jsonObj.has("command")) {
				String params = jsonObj.getString("command");
				h5CallNative(params);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据事先商定协议值，做相应处理
	 * @param msg
	 * @param json
	 */
	@Override
	@JavascriptInterface
	public void sendHandlerMessage(int msg, String json) {
		TransfersLog.d(TAG, "sendHandlerMessage msg=%s" + msg + ",json=%s" + json);
		mActivity.sendMessage(msg, json);
	}

	/**
	 * 引出提示框展示信息
	 */
	@Override
	@JavascriptInterface
	public void showMsg(String msg) {
		TransfersLog.d(TAG, "showMsg-->msg=" + msg);
		mActivity.sendMessage(ModuleConstant4Html5.H5CALLNATIVE_SHOWDIG_MSGID,msg);
	}

	/**
	 * 显示加载进度框
	 */
	@Override
	@JavascriptInterface
	public void showProgress() {
		TransfersLog.d(TAG, "showProgress");
		mActivity.sendMessage(ModuleConstant4Html5.H5CALLNATIVE_SHOWPROGRESSDLG_MSGID);
	}

	/**
	 * 隐藏加载进度框
	 */
	@Override
	@JavascriptInterface
	public void disProgress() {
		TransfersLog.d(TAG, "disProgress");
		mActivity.sendMessage(ModuleConstant4Html5.H5CALLNATIVE_DISSPROGRESSDLG_MSGID);
	}

	/**刷新上一页面*/
	@JavascriptInterface
	public void refreshPreviousPage(){
		TransfersLog.d(TAG, "disProgress");
		mActivity.sendMessage(ModuleConstant4Html5.H5CALLNATIVE_REFRESH_PREVIOUS_PAGE_MSGID);
	}

	/**关闭当前界面*/
	@JavascriptInterface
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
	@JavascriptInterface
	public void jumpself(int id, String params) {
		TransfersLog.d(TAG, "jumpself " + id + " " + params);
		jumpProtocol(params);
	}
	
	@Override
	@JavascriptInterface
	public void jumpProtocol(String protocolStr) {
		TransfersLog.d(TAG, "jumpProtocol " + protocolStr);
		if (!H5WebViewHelper.isEmpty(protocolStr)) {
			mActivity.sendMessage(
					ModuleConstant4Html5.H5CALLNATIVE_STARTACTIVITY_MSGID,
					protocolStr);
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
	@JavascriptInterface
	public void showDialog(String params) {
		JumpURI uri = SchemeParser.parseJumpParams(params);
		if( uri == null ){
			//不支持的类型
			YTLog.e("not support type");
			return ;
		}
		HashMap<String,String> paramMap = uri.getParamMap();
		JumpType jumpType = uri.getJumpType();
		if (JumpType.ShowDialog==jumpType) {
			if (paramMap.containsKey("msg")) {
				mActivity.showErrorDialog(paramMap.get("msg"));
			}
		}
	}
	
	/** 
	 * 
	 * h5调原生对话框
	 * @param params
	 *  示例  yintaimobile://ShowDialog?msg=商品删除成功！
	 *  参数含义：
	 *   msg： 提示内容
	 */
	@JavascriptInterface
	public void setTitleBar(String params) {
		sendHandlerMessage(ModuleConstant4Html5.H5CALLNATIVE_SETTITLEBAR_MSGID, params);
	}

	/**
	 * 跳转到指定APP页面(跳转另一页)
	 * @param params 跳转协议
	 */
	@JavascriptInterface
	public void jump(String params) {
		jumpProtocol(params);
	}
	
	/**
	 * 立即支付
	 * @param params 
	 * 示例："yintaimobile://PayNow?orderId=125544&orderSource=1 
	 *  参数含义：
	 * 	   orderId：String  订单号
	 * 	   orderSource  String   1:电子小票支付、2:ERP订单支付"
	 */
	@JavascriptInterface
	public void startPayNow(String params){
		if (isLogin()) {
			mActivity.startPayNow(params);
		}
	}
	
	/**H5是否响应标题栏点击返回操作（和响应返回按钮组合使用）*/
	@JavascriptInterface
	public void isResponseClickBack() {
		mActivity.isResponseClickBack();
	}

	/**设置界面是否开启下拉刷新
	 * @param param  true 开启  false 关闭
     */
	@JavascriptInterface
	public void setPull2Refreshable(String param) {
		if(mActivity!=null){
			boolean flag =false;
			if ("true".equalsIgnoreCase(param)) {
				flag = true;
			}
			mActivity.setPull2Refreshable(flag);
		}
	}

	public boolean isLogin() {
		//需要先跳登录页面,判断是否已登录
		if (mActivity!=null&&StringUtil.isEmpty(mActivity.getUserID())) {
			//未登录，需要跳转到登录页，并且给出提示
			Intent intent = new Intent(mActivity, LoginActivity.class);
			intent.setClass(mActivity, LoginActivity.class);
			ViewHelper.showToast(mActivity, R.string.template_jump_needlogin);
			mActivity.startActivity(intent);
			return false;
		}
		return true;
	}
	
	
	//---end----------------------------------------------------

	 /**接收H5 页面BI数据  */
	@Override
	@JavascriptInterface
	public void onBiEvent(String eventJson) {
		Gson gson = new Gson();
		HashMap<String, Object> allMapList = gson.fromJson(eventJson,new TypeToken<HashMap<String, Object>>() {}.getType());
		YintaiBiAgent.onEvent(allMapList);
	}
	// ---end----------------------------------------------------
}