package com.wxlibrary.wxapi;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.wxlibrary.wxapi.pay.WeixinPayUtils;


/**
 * 微信的API单例
 */
public class WXApiManager {
	@SuppressWarnings("unused")
	private static final String TAG = "WeixinApiManager";
	private static WXApiManager instance;
	private IWXAPI wxApi;
	private Context context;
	
	private final String APP_ID = WeixinPayUtils.APP_ID;
	
	private WXApiManager(Context context) {
		this.context = context;
		initWeixinShare(context);
	}

	/**
	 * 获取WeixinShareManager单例
	 */
	public static synchronized WXApiManager getInstance(Context context) {
		if (instance == null) {
			instance = new WXApiManager(context);
		}
		return instance;
	}
	/**
	 * 获取IWXAPI
	 */
	public IWXAPI getWXAPI() {
		if (instance == null) {
			instance = new WXApiManager(context);
		}
		if (wxApi == null) {
			initWeixinShare(context);
		}
		return wxApi;
	}
	/**
	 * 重新注册WXAppID(特殊需求，用来处理WXAppId是服务器回传)
	 */
	public void InitWXAPI(String appid) {
		getWXAPI().registerApp(appid);
	}
	
	
	/**
	 * 初始化微信代码
	 * @param context
	 */
	private void initWeixinShare(Context context) {
		if (!TextUtils.isEmpty(APP_ID)) {
			//参3为checkSignature，是否检查签名
			wxApi = WXAPIFactory.createWXAPI(context, APP_ID, true);
			wxApi.registerApp(APP_ID);
		}else {
			prtLog("APP_ID为空，微信api初始化失败！");
		}
	}
	
	public boolean isWeixinAppInstalled() {
		if (wxApi==null) {
			initWeixinShare(context);
		}
		return wxApi.isWXAppInstalled();
	}
	
	public void prtLog(String msg){
		Log.i("WeixinShareManager", msg);
	}
	
	public void showToast(String showMsg, boolean isLong){
		showToast(context, showMsg, isLong);
	}
	private void showToast(Context con, String showMsg, boolean isLong){
		Toast.makeText(con, showMsg,isLong? Toast.LENGTH_LONG: Toast.LENGTH_SHORT).show();
	}
	
}