package com.wxlibrary.wxapi.unionlogin.task;


import android.os.AsyncTask;

import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.wxlibrary.wxapi.config.LocalErrorCode;
import com.wxlibrary.wxapi.config.LocalReturnCode;
import com.wxlibrary.wxapi.config.WXConstant;
import com.wxlibrary.wxapi.unionlogin.bean.WXAccessTokenBean;
import com.wxlibrary.wxapi.unionlogin.listener.AuthEventListener;
import com.wxlibrary.wxapi.utils.WXGsonUtils;
import com.wxlibrary.wxapi.utils.WXLogUtils;
import com.wxlibrary.wxapi.utils.WXNetUtils;

import java.io.UnsupportedEncodingException;

public class GetWXAccessTokenTask extends AsyncTask<Void, Void, WXAccessTokenBean> {
    public static final String TAG = WXConstant.TAG;
    private SendAuth.Resp resp;
    public AuthEventListener mAuthEventListener;

    public GetWXAccessTokenTask(SendAuth.Resp resp, AuthEventListener listener) {
        this.resp = resp;
        this.mAuthEventListener = listener;
    }

    @Override
    protected void onPreExecute() {
    }


    @Override
    protected WXAccessTokenBean doInBackground(Void... params) {
        WXAccessTokenBean result = new WXAccessTokenBean();
        String url = String.format(WXConstant.SENDAUTH_GETCODE_URL, resp.code);
        WXLogUtils.d(TAG, "get access token, url = " + url);

        byte[] buf = WXNetUtils.httpGet(url);
        if (buf == null || buf.length == 0) {
            result.setLocalRetCode(LocalReturnCode.ERR_HTTP);
            return result;
        }

        String content;
        try {
            content = new String(buf, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            content = new String(buf);
        }
        if (content == null || content.length() <= 0) {
            result.setLocalRetCode(LocalReturnCode.ERR_JSON);
            return result;
        }
        WXLogUtils.d(TAG, "json:" + content);
        //result.parseFrom(content);

        try {
            result = WXGsonUtils.toObject(content, WXAccessTokenBean.class);
            if (isNotEmpty(result.getAccessToken())&&isNotEmpty(result.getOpenid())) {
                result.setLocalRetCode(LocalReturnCode.ERR_OK);
            } else {
                result.setLocalRetCode(LocalReturnCode.ERR_OTHER);
            }
        } catch (Exception e) {
            WXLogUtils.e(TAG, e);
            result = new WXAccessTokenBean();
            result.setLocalRetCode(LocalReturnCode.ERR_JSON);
        }
        return result;
    }
    @Override
    protected void onPostExecute(WXAccessTokenBean result) {
        if (result.getLocalRetCode() == LocalReturnCode.ERR_OK) {
            WXLogUtils.d(TAG, "successfull - onPostExecute, accessToken = " + result.getAccessToken());

//            if (mAuthEventListener != null) {//如果只需要Openid做联合登录的话，在这里就可以直接回调并结束任务了
//                WXUserInfoBean userInfoResult = new WXUserInfoBean();
//                userInfoResult.setAccess_token(result.getAccessToken());
//                userInfoResult.setOpenid(result.getOpenid());
//                mAuthEventListener.onSuccessful(userInfoResult);
//            }
            //如果要取用户信息及unionid，请往下走。
            GetWXUserInfoTask getUserInfoTask = new GetWXUserInfoTask(result.getAccessToken(), result.getOpenid(), mAuthEventListener);
            getUserInfoTask.execute();
        } else {
            WXLogUtils.d(TAG, "get accesstoken fail" + result.getLocalRetCode().name());
            if (mAuthEventListener != null) {
                mAuthEventListener.onFailure(LocalErrorCode.ERR_ACCESSTOKEN);
            }
        }
    }
    public boolean isNotEmpty(String str) {
        return ((str != null) && (str.length() > 0));
    }
}