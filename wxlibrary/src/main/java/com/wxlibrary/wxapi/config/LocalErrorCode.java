package com.wxlibrary.wxapi.config;

public enum LocalErrorCode {
    /**获取ACCESSTOKEN失败*/
    ERR_ACCESSTOKEN,
    /**获取用户信息失败*/
    ERR_GETUSERINFO,
    /**用户拒绝授权*/
    ERR_AUTH_DENIED,
    /**用户取消授权*/
    ERR_USER_CANCEL,
    /**其它错误*/
    ERR_OTHER
}
