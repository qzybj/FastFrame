package com.wxlibrary.wxapi.unionlogin.bean;

import com.google.gson.annotations.SerializedName;
import com.wxlibrary.wxapi.config.LocalReturnCode;
import com.wxlibrary.wxapi.config.WXConstant;

public class WXAccessTokenBean {
    public static final String TAG = WXConstant.TAG;
    private LocalReturnCode localRetCode = LocalReturnCode.ERR_OTHER;
    /**接口调用凭证*/
    @SerializedName("access_token")
    private String accessToken;
    /**access_token接口调用凭证超时时间，单位（秒）*/
    @SerializedName("expiresIn")
    private int expiresIn;
    /**用户刷新access_token*/
    @SerializedName("refresh_token")
    private String refresh_token;
    /**授权用户唯一标识*/
    @SerializedName("openid")
    private String openid;
    /**用户授权的作用域，使用逗号（,）分隔*/
    @SerializedName("scope")
    private String scope;
    @SerializedName("errCode")
    private int errCode;
    @SerializedName("errMsg")
    private String errMsg;

    public LocalReturnCode getLocalRetCode() {
        return localRetCode;
    }

    public void setLocalRetCode(LocalReturnCode localRetCode) {
        this.localRetCode = localRetCode;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}
