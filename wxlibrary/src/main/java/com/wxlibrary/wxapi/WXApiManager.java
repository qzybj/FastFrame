package com.wxlibrary.wxapi;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.wxlibrary.wxapi.config.WXConstant;
import com.wxlibrary.wxapi.utils.WXLogUtils;


/**
 * 用于管理及提供微信的API
 */
public class WXApiManager {
	private static WXApiManager instance;
	private IWXAPI wxApi;
	private Context context;

	private WXApiManager(Context context) {
		this.context = context;
		initWXApi(context);
	}

	public static synchronized WXApiManager getInstance(Context context) {
		if (instance == null) {
			instance = new WXApiManager(context);
		}
		return instance;
	}

	/**
	 * get a instantiate wxapi
	 * @return
     */
	public IWXAPI getWXAPI() {
		isSupportWXApi();
		return wxApi;
	}

	private void isSupportWXApi() {
		if (instance == null) {
			instance = new WXApiManager(context);
		}
		if (wxApi == null) {
			initWXApi(context);
		}
	}

	/**
	 * init wxapi
	 * @param con
	 */
	private void initWXApi(Context con) {
		if (WXConstant.APP_ID!=null&&!TextUtils.isEmpty(WXConstant.APP_ID.trim())) {
			//参3为checkSignature，是否检查签名
			wxApi = WXAPIFactory.createWXAPI(con, WXConstant.APP_ID, true);
			wxApi.registerApp(WXConstant.APP_ID);
		}else {
			WXLogUtils.e(WXConstant.TAG,"app_id is null,wxapi init failed!");
		}
	}
	
	public boolean isWXAppInstalled() {
		if (wxApi==null) {
			initWXApi(context);
		}
		return wxApi.isWXAppInstalled();
	}
}