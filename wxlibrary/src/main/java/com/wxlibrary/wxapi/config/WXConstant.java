package com.wxlibrary.wxapi.config;


public class WXConstant {

    public final static String TAG = "wxunionlogin";

    public final static String APP_ID = "";
    public final static String APP_SECRET = "";
    /**开发模式下，会有详细信息日志输出，用于排查*/
    public final static boolean ISDEV = true;

    /**
     * 获取的授权
     *<p>	接口							接口说明
     *<p>	/sns/oauth2/access_token	通过code换取access_token、refresh_token和已授权scope
     *<p>	/sns/oauth2/refresh_token	刷新或续期access_token使用
     *<p>	/sns/auth					检查access_token有效性
     * */
    public final static String SCOPE_BASE ="snsapi_base";
    /**
     * 获取的授权
     *<p>	接口	接口说明
     *<p>	/sns/userinfo	获取用户个人信息
     * */
    public final static String SCOPE_USERINFO ="snsapi_userinfo";

    /**通过code换取access_token、refresh_token和已授权scope*/
    public final static String GET_ACCESS_TOKEN_URL ="/sns/oauth2/access_token";
    /**刷新或续期access_token使用*/
    public final static String REFRESH_TOKEN_URL ="/sns/oauth2/refresh_token";
    /**检查access_token有效性*/
    public final static String CHECK_VALID_URL ="/sns/auth";
    /**获取用户个人信息*/
    public final static String GET_USERINFO_URL ="/sns/userinfo";

    /**应用授权作用域，如获取用户个人信息则填写snsapi_userinfo*/
    public final static String WEIXIN_SCOPE = SCOPE_USERINFO;
    /**用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验*/
    public final static String WEIXIN_STATE = "yintai_weixin_login";//+new Random(4);

    public final static String SERVER_URL= "https://api.weixin.qq.com";

    /**通过code获取access_token*/
    public final static String SENDAUTH_GETCODE_URL = SERVER_URL+GET_ACCESS_TOKEN_URL+"?grant_type=authorization_code&appid="+WXConstant.APP_ID+"&secret="+WXConstant.APP_SECRET+"&code=%s";


    public final static String SENDAUTH_GET_USERINFO_URL_PARAMETERS = "?access_token=%s&openid=%s";
    /**获取用户个人信息（UnionID机制）
     * <p>参数			是否必须	说明
     * <p>access_token	是		调用凭证
     * <p>openid		是		普通用户的标识，对当前开发者帐号唯一，获取该openid的好友列表
     * */
    public final static String SENDAUTH_GET_USERINFO_URL = SERVER_URL+GET_USERINFO_URL+SENDAUTH_GET_USERINFO_URL_PARAMETERS;


    /**刷新access_token有效期*/
    public final static String SENDAUTH_REFRESH_TOKEN_URL = SERVER_URL+REFRESH_TOKEN_URL+"?appid="+WXConstant.APP_ID+"&grant_type=refresh_token&refresh_token=";
    /**检验授权凭证（access_token）是否有效*/
    public final static String SENDAUTH_TOKEN_CHECKVALID_URL = SERVER_URL+CHECK_VALID_URL;

}
