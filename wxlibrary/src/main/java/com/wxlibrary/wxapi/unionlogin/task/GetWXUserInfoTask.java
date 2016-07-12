package com.wxlibrary.wxapi.unionlogin.task;

import android.os.AsyncTask;
import com.wxlibrary.wxapi.config.LocalErrorCode;
import com.wxlibrary.wxapi.config.LocalReturnCode;
import com.wxlibrary.wxapi.config.WXConstant;
import com.wxlibrary.wxapi.unionlogin.bean.WXUserInfoBean;
import com.wxlibrary.wxapi.unionlogin.listener.AuthEventListener;
import com.wxlibrary.wxapi.utils.WXGsonUtils;
import com.wxlibrary.wxapi.utils.WXLogUtils;
import com.wxlibrary.wxapi.utils.WXNetUtils;

import java.io.UnsupportedEncodingException;

public class GetWXUserInfoTask extends AsyncTask<Void, Void, WXUserInfoBean> {
    public static final String TAG = WXConstant.TAG;
    private String access_token;
    private String openid;
    public AuthEventListener mAuthEventListener;

    public GetWXUserInfoTask(String access_token, String openid, AuthEventListener listener) {
        this.access_token = access_token;
        this.openid = openid;
        this.mAuthEventListener = listener;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected WXUserInfoBean doInBackground(Void... params) {
        WXUserInfoBean result = new WXUserInfoBean();
        String url = String.format(WXConstant.SENDAUTH_GET_USERINFO_URL, access_token, openid);
        WXLogUtils.d(TAG, "get userinfo, url = " + url);

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
//        result.parseFrom(content);

        try {
            result = WXGsonUtils.toObject(content, WXUserInfoBean.class);
            if (isNotEmpty(result.getOpenid())) {
                result.setLocalRetCode(LocalReturnCode.ERR_OK);
            } else {
                result.setLocalRetCode(LocalReturnCode.ERR_OTHER);
            }
        } catch (Exception e) {
            WXLogUtils.e(TAG, e);
            result = new WXUserInfoBean();
            result.setLocalRetCode(LocalReturnCode.ERR_JSON);
        }
        return result;
    }

    @Override
    protected void onPostExecute(WXUserInfoBean result) {
        if (result.getLocalRetCode() == LocalReturnCode.ERR_OK) {
            WXLogUtils.d(TAG, "successfull,result： " + result.toString());
            //回调调用处的方法
            if (mAuthEventListener != null) {
                result.setAccess_token(access_token);
                mAuthEventListener.onSuccessful(result);
            }
        } else {
            WXLogUtils.d(TAG, "fail,reason:" + result.getErrCode() + result.getErrMsg());
            if (mAuthEventListener != null) {
                mAuthEventListener.onFailure(LocalErrorCode.ERR_GETUSERINFO);
            }
        }
    }

    public boolean isNotEmpty(String str) {
        return ((str != null) && (str.length() > 0));
    }
}