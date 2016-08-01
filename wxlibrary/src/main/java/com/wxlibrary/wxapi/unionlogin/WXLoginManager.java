package com.wxlibrary.wxapi.unionlogin;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.wxlibrary.R;
import com.wxlibrary.wxapi.WXApiManager;
import com.wxlibrary.wxapi.config.LocalErrorCode;
import com.wxlibrary.wxapi.config.WXConstant;
import com.wxlibrary.wxapi.unionlogin.bean.WXUserInfoBean;
import com.wxlibrary.wxapi.unionlogin.listener.AuthEventListener;
import com.wxlibrary.wxapi.utils.WXLogUtils;
import com.wxlibrary.wxapi.utils.WXToastUtils;

/**
 * 微信登录管理器
 */
public class WXLoginManager {
    public static final String TAG = WXConstant.TAG;

    public static final int MSG_WHAT_BASE = 0x1f00;
    public static final int MSG_WHAT_GETDATA_START = MSG_WHAT_BASE + 1;
    public static final int MSG_WHAT_GETDATA_DONE = MSG_WHAT_BASE + 2;
    public static final int MSG_WHAT_SHOW_PROGRESSDAILOG = MSG_WHAT_BASE + 3;
    public static final int MSG_WHAT_DISMISS_PROGRESSDAILOG = MSG_WHAT_BASE + 4;


    private Activity mContext;
    private WXLoginListener mListener;

    public WXLoginManager(Activity activity, WXLoginListener listener){
        this.mListener = listener;
        this.mContext = activity;
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message aMsg) {
            super.handleMessage(aMsg);
            switch (aMsg.what) {
                case MSG_WHAT_GETDATA_START:
                    startRequest();
                    break;
                case MSG_WHAT_GETDATA_DONE:
                    complete(aMsg.obj);
                    break;
                case MSG_WHAT_SHOW_PROGRESSDAILOG:
                    showProgressDialog();
                    break;
                case MSG_WHAT_DISMISS_PROGRESSDAILOG:
                    dismissProgressDialog();
                    break;
                default:
                    break;
            }
        }
    };

    private void startRequest() {}
    private void complete(Object obj) {}
    private void showProgressDialog() {}
    private void dismissProgressDialog() {}

    /** 微信授权登录*/
    public void wxAuthLogin() {
        if (WXApiManager.getInstance(mContext).isWXAppInstalled()&&
                WXApiManager.getInstance(mContext).getWXAPI().getWXAppSupportAPI() >= Build.OPENID_SUPPORTED_SDK_INT) {
            mHandler.sendEmptyMessage(MSG_WHAT_SHOW_PROGRESSDAILOG);
            OAuthUtils.getInstance(mContext).startAuthLogin(
                    new AuthEventListener() {
                        @Override
                        public void beforeCallApi(Object obj) {
                            WXLogUtils.d(TAG, "beforeCallApi:");
                        }
                        @Override
                        public void authSuccessful(SendAuth.Resp resp) {
                            WXLogUtils.d(TAG, "authSuccessful:");
                            OAuthUtils.getInstance(mContext).getAccessToken(resp);
                        }
                        @Override
                        public void onSuccessful(WXUserInfoBean result) {
                            mHandler.sendEmptyMessage(MSG_WHAT_DISMISS_PROGRESSDAILOG);
                            WXLogUtils.d(TAG,"onSuccessful:"+result.toString());
                            if(isSupportWXLoginListener()){
                                mListener.onSuccessful(result);
                            }
                        }
                        @Override
                        public void onFailure(LocalErrorCode code) {
                            WXLogUtils.d(TAG, "onFailure:"+code.getClass().getSimpleName());
                            mHandler.sendEmptyMessage(MSG_WHAT_DISMISS_PROGRESSDAILOG);
                            if(isSupportWXLoginListener()){
                                mListener.onFailure(getErrString(code));
                            }
                        }
                    });
        }else {
            WXLogUtils.d(TAG, mContext.getString(R.string.wxapp_no_Installed_warn));
            WXToastUtils.showToast(mContext,mContext.getString(R.string.wxapp_no_Installed_warn), false);
            mHandler.sendEmptyMessage(MSG_WHAT_DISMISS_PROGRESSDAILOG);
        }
    }

    @NonNull
    private String getErrString(LocalErrorCode code) {
        String msg = "用户授权失败";
        if (code == LocalErrorCode.ERR_ACCESSTOKEN){
            msg ="获取accesstoken失败";
        }else if (code == LocalErrorCode.ERR_GETUSERINFO){
            msg ="获取用户信息失败";
        }else if (code == LocalErrorCode.ERR_USER_CANCEL){
            msg ="用户取消授权";
        }else if (code == LocalErrorCode.ERR_AUTH_DENIED){
            msg ="用户拒绝授权";
        }
        return msg;
    }

    private boolean isSupportWXLoginListener(){
        return mListener!=null;
    }

    public interface WXLoginListener {
        void onSuccessful(WXUserInfoBean result);
        void onFailure(String errMsg);
    }
}
