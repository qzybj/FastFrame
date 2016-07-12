package com.wxlibrary.wxapi.unionlogin.listener;

import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.wxlibrary.wxapi.config.LocalErrorCode;
import com.wxlibrary.wxapi.unionlogin.bean.WXUserInfoBean;

/***/
public interface AuthEventListener{
    /**即将调用指定SDK的API时*/
    void beforeCallApi(Object obj);
    /**用户同意授权*/
    void authSuccessful(SendAuth.Resp resp);
    /**调用API登录成功*/
    void onSuccessful(WXUserInfoBean result);
    /**调用API登录失败*/
    void onFailure(LocalErrorCode code);
}