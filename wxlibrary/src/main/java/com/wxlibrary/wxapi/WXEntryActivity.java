package com.wxlibrary.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.wxlibrary.wxapi.config.WXConstant;
import com.wxlibrary.wxapi.pay.WXPayUtils;
import com.wxlibrary.wxapi.unionlogin.OAuthUtils;
import com.wxlibrary.wxapi.utils.WXLogUtils;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
	public String TAG = "WXBasicCallBack";

	private IWXAPI wxApi;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(WXConstant.APP_ID != null){
			wxApi = WXApiManager.getInstance(this).getWXAPI();
			wxApi.handleIntent(getIntent(), this);
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		wxApi.handleIntent(intent, this);
	}
	@Override
	public void onReq(BaseReq req) {
		weixinonReq(req);
	}

	@Override
	public void onResp(BaseResp resp) {
		weixinResp(resp);
	}

	/**
	 * 请求响应
	 * @param req
	 */
	public void weixinonReq(BaseReq req) {
		WXLogUtils.i(TAG,"call weixinonReq:"+req.getType());
		switch (req.getType()) {
		case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX://微信向第三方app请求消息数据
			break;
		case ConstantsAPI.COMMAND_LAUNCH_BY_WX :
			break;
		case ConstantsAPI.COMMAND_PAY_BY_WX://微信支付
			if (WXPayUtils.getInstance(this).mResultEventHandler!=null) {
				WXPayUtils.getInstance(this).onReq(req);
			}
			break;
		case ConstantsAPI.COMMAND_SENDAUTH:{
			if (OAuthUtils.getInstance(this).isSupportAuthCallBack()) {
				OAuthUtils.getInstance(this).onReq(req);
			}
		}
		}
	}

	/**
	 * 返回结果响应
	 * @param resp
	 */
	public void weixinResp(BaseResp resp) {
		WXLogUtils.i(TAG,"call weixinResp:"+resp.errCode+resp.errStr);
		String resultStr = getStatusMsg(resp);
		switch (resp.getType()) {
			case ConstantsAPI.COMMAND_PAY_BY_WX://微信支付
				WXLogUtils.i(TAG, "call ConstantsAPI.COMMAND_PAY_BY_WX");
				if (WXPayUtils.getInstance(this).mResultEventHandler!=null) {
					WXLogUtils.i(TAG, "call mResultEventHandler");
					WXPayUtils.getInstance(this).onResp(resp);
				}
				WXLogUtils.i(TAG, "onPayFinish, errCode = " + resp.errCode+" : "+resultStr);
				break;

			case ConstantsAPI.COMMAND_SENDAUTH:
				WXLogUtils.i(TAG,"call ConstantsAPI.COMMAND_SENDAUTH");
				if (OAuthUtils.getInstance(this).isSupportAuthCallBack()) {
					WXLogUtils.i(TAG, "call mAuthEventListener");
					OAuthUtils.getInstance(this).onResp(resp);
				}
				WXLogUtils.i(TAG, "UnionLogin finish, errCode = " + resp.errCode+" : "+resultStr);
				break;
			default:
				break;
		}
		finish();
	}

	public String getStatusMsg(BaseResp resp) {
		String resultStr = "未知错误";
		switch (resp.errCode) {
			case BaseResp.ErrCode.ERR_OK:
				resultStr ="正确返回";
				break;
			case BaseResp.ErrCode.ERR_USER_CANCEL:
				resultStr ="用户取消";
				break;
			case BaseResp.ErrCode.ERR_AUTH_DENIED:
				resultStr ="认证被否决";
				break;
			case BaseResp.ErrCode.ERR_COMM:
				resultStr ="一般错误";
				break;
			case BaseResp.ErrCode.ERR_SENT_FAILED:
				resultStr ="发送失败";
				break;
			case BaseResp.ErrCode.ERR_UNSUPPORT:
				resultStr ="不支持错误";
				break;
			default:
				break;
		}
		WXLogUtils.i(TAG, (TextUtils.isEmpty(resp.toString())?resp.toString():resp.errCode)+"");
		return resultStr;
	}
}