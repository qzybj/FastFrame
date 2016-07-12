package com.wxlibrary.wxapi.pay;

import android.content.Context;
import android.widget.Toast;

import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.wxlibrary.wxapi.WXApiManager;
import com.wxlibrary.wxapi.pay.bean.WeiXinPayInfo;
import com.wxlibrary.wxapi.utils.WXLogUtils;


/**
 * 微信支付工具类
 */
public class WeixinPayUtils implements IWXAPIEventHandler{
	public static final String TAG = "WXpay";
	private static Context mContext;
	private static WeixinPayUtils instance ;
	
	public static final String TAG_SUCCESS = "SUCCESS";
	public static final String TAG_FAIL = "FAIL";
	public static final String TAG_CANCEL = "CANCEL";
	
	/**应用从官方网站申请到的合法appId*/
	public static final String APP_ID ="";
	/** 微信开放平台和商户约定的密钥 */
	//public static final String APP_SECRET = "";
	/** 微信开放平台和商户约定的支付密钥 */
	//public static final String APP_KEY = "";
	/** 商家向财付通申请的商家id */
	//public static final String PARTNER_ID = "";
	/** 微信公众平台商户模块和商户约定的密钥 */
	//public static final String PARTNER_KEY = "";
	
	/** 获取微信支付返回结果key值 */
	public static final String TAG_PAY_RESULT = "pay_result";
	public PayEventHandler mResultEventHandler = null;
	
	private IWXAPI wxApi;

	private WeixinPayUtils(Context con) {
		super();
	}

	public static synchronized WeixinPayUtils getInstance(Context con) {
		mContext =con;
		if (instance == null) {
			instance = new WeixinPayUtils(con);
		}
		return instance;
	}
	
	/**
	 * 获取IWXAPI
	 */
	public IWXAPI getWXAPI() {
		if (wxApi==null) {
			wxApi = WXApiManager.getInstance(mContext).getWXAPI();
		}
		return wxApi;
	}
	/**
	 * 重新注册WXAppID(特殊需求，用来处理WXAppId是服务器回传)
	 */
	public void InitWXAPI(String appid) {
		if (isNotEmpty(appid)) {
			getWXAPI().registerApp(appid);
		}
	}
	
	public void startWeixinPaySDK(WeiXinPayInfo response, PayEventHandler resultEventHandler) {
		this.mResultEventHandler=resultEventHandler;
		if(response!=null&&isSupportWeiXinPay()){
			if (isNotEmpty(response.getAppId())) {//特殊需求，用来处理WXAppId是服务器回传
				InitWXAPI(response.getAppId());
			}
			PayReq req = new PayReq();
			req.appId = response.getAppId();
			req.partnerId = response.getPartnerId();
			req.prepayId = response.getPrepayId();
			req.nonceStr = response.getNonceStr();
			req.timeStamp = response.getTimeStamp();
			req.packageValue = response.getPackageValue();
			req.sign = response.getSign();

			WXLogUtils.i(TAG, "调起支付的package串：" + req.packageValue);
			// 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
			
			if (mResultEventHandler!=null) {
				mResultEventHandler.beforeWxCall(null);
			}
			WeixinPayUtils.getInstance(mContext).getWXAPI().sendReq(req);
		}else {//不支持微信支付
			if (mResultEventHandler!=null) {
				mResultEventHandler.beforeWxCall(null);
			}
		}
	}
	
	/**
	 * 是否支持微信支付
	 */
	public boolean isSupportWeiXinPay() {
		if (!getWXAPI().isWXAppInstalled()) {
			WXLogUtils.i(TAG, "未安装微信");
			showToast("未安装微信", false);
			return false;
		}
		if (getWXAPI().getWXAppSupportAPI() < Build.PAY_SUPPORTED_SDK_INT) {
			showToast("不支持微信支付", false);
			return false;
		}
		return true;
	}

	private void showToast(String showMsg, boolean isLong){
		showToast(mContext, showMsg, isLong);
	}
	private void showToast(Context con, String showMsg, boolean isLong){
		Toast.makeText(con, showMsg,isLong? Toast.LENGTH_LONG: Toast.LENGTH_SHORT).show();
	}
	
	public void setIImageViewOnclicKCallBack(PayEventHandler resultEventHandler){
		this.mResultEventHandler = resultEventHandler;
	}
	
	@Override
	public void onReq(BaseReq arg0) {
		if (mResultEventHandler != null) {
			mResultEventHandler.onReq(arg0);
		}
	}

	@Override
	public void onResp(BaseResp arg0) {
		if (mResultEventHandler != null) {
			mResultEventHandler.onResp(arg0);
		}
	}
	/**用于接收请求、响应的接口*/
	public interface PayEventHandler{
		/**接收调用微信支付前的请求*/
		public abstract void onReq(BaseReq arg0);
		/**接收调用微信支付后的响应*/
		public abstract void onResp(BaseResp arg0);
		/**调起微信支付SDK前的处理(之前为获取AcessToken、预支付ID)*/
		public abstract void beforeWxCall(Object obj);
		
	}

	/**
	 * 
	 * 处理微信支付返回的结果
	 * @param context
	 * @param errCode 
	 * @return
	 */
	public boolean disposePayResult(Context context, int errCode) {
		boolean res = false;
		switch (errCode) {
		case BaseResp.ErrCode.ERR_OK:
			res = true;
			break;
		case BaseResp.ErrCode.ERR_USER_CANCEL:
			break;
		default:
			break;
		}
		return res;
	}

	public static boolean isNotEmpty(String str) {
		return ((str != null) && (str.length() > 0));
	}
}