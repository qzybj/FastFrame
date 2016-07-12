package com.wxlibrary.wxapi.unionlogin;

import android.content.Context;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.wxlibrary.wxapi.WXApiManager;
import com.wxlibrary.wxapi.config.LocalErrorCode;
import com.wxlibrary.wxapi.config.WXConstant;
import com.wxlibrary.wxapi.unionlogin.listener.AuthEventListener;
import com.wxlibrary.wxapi.unionlogin.task.GetWXAccessTokenTask;


public class OAuthUtils implements IWXAPIEventHandler {
	public static final String TAG = WXConstant.TAG;
	private static Context mContext;
	private static OAuthUtils instance ;
	public static AuthEventListener mAuthEventListener = null;


	private OAuthUtils() {
		super();
	}

	public static synchronized OAuthUtils getInstance(Context con) {
		mContext =con;
		if (instance == null) {
			instance = new OAuthUtils();
		}
		return instance;
	}

	/**
	 * 微信登录调起微信授权界面
	 */
	public void startAuthLogin(AuthEventListener listener) {
		this.mAuthEventListener = listener;
		// send oauth request 
		SendAuth.Req req = new SendAuth.Req();
        req.scope = WXConstant.WEIXIN_SCOPE;
        req.state = WXConstant.WEIXIN_STATE;
		if (isNotNullAuthCallBack()) {
			mAuthEventListener.beforeCallApi(null);
		}
        WXApiManager.getInstance(mContext).getWXAPI().sendReq(req);
	}
	
	/**
	 * 微信登录_获取access_token
	 */
	public void getAccessToken(SendAuth.Resp resp) {
		GetWXAccessTokenTask getAccessTokenTask = new GetWXAccessTokenTask(resp,mAuthEventListener);
		getAccessTokenTask.execute();
	}

	public boolean isNotNullAuthCallBack() {
		return mAuthEventListener!=null;
	}

	@Override
	public void onReq(BaseReq arg0) {
//		if (isNotNullAuthCallBack()) {
//			mAuthEventListener.beforeCallApi(null);
//		}
	}

	@Override
	public void onResp(BaseResp resp) {
		if (isNotNullAuthCallBack()) {
			switch (resp.errCode) {
				case BaseResp.ErrCode.ERR_OK:
					if (resp instanceof SendAuth.Resp) {
						SendAuth.Resp sendResp = (SendAuth.Resp) resp;
						mAuthEventListener.authSuccessful(sendResp);
					}else{
						mAuthEventListener.onFailure(LocalErrorCode.ERR_OTHER);
					}
					break;
				case BaseResp.ErrCode.ERR_AUTH_DENIED :
					mAuthEventListener.onFailure(LocalErrorCode.ERR_AUTH_DENIED);
					break;
				case BaseResp.ErrCode.ERR_USER_CANCEL :
					mAuthEventListener.onFailure(LocalErrorCode.ERR_USER_CANCEL);
					break;
				default:
					mAuthEventListener.onFailure(LocalErrorCode.ERR_OTHER);
					break;
			}
		}
	}
}