package brady.com.appframe.net.apiwrap.passport;


import brady.com.appframe.net.apiwrap.BaseApi;
import brady.com.appframe.net.apiwrap.MyApiDelegate;
import brady.com.appframe.net.apiwrap.passport.response.AuthQrcodeResponse;
import brady.com.appframe.net.apiwrap.passport.response.FuncListResponse;
import brady.com.appframe.net.apiwrap.passport.response.UserinfoResponse;
import netcore.api.ApiDelegate;
import netcore.api.BoolResponse;

/**
 * Created by tongdesheng on 16/1/28.
 */
public class AuthApi extends BaseApi {

    /**
     * 登陆
     * @param account
     * @param password
     * @param apiDelegate
     */
    public void login(String account, String password, MyApiDelegate<UserinfoResponse> apiDelegate) {
        apiClient.setApiMethod("passport.login");
        apiClient.setParam("account", account);
        apiClient.setParam("password", password);
        apiClient.asyncReq(apiDelegate, UserinfoResponse.class);
    }

    /**
     * 获取用户的主要功能
     * @param userToken
     * @param apiDelegate
     */
    public void getMainFunc(String userToken, MyApiDelegate<FuncListResponse> apiDelegate) {
        apiClient.setApiMethod("passport.getMainFunc");
        apiClient.setParam("user_token", userToken);
        apiClient.asyncReq(apiDelegate, FuncListResponse.class);
    }


    /**
     * 发送手机验证码
     * @param mobile
     * @param apiDelegate
     */
    public void sendSmsCaptcha(String mobile, ApiDelegate apiDelegate) {
        apiClient.setApiMethod("passport.sendSmsCaptcha");
        apiClient.setParam("mobile", mobile);
        apiClient.asyncReq(apiDelegate, BoolResponse.class);
    }

    /**
     * 注册
     * @param account
     * @param password
     * @param smsCaptcha
     * @param nickName
     */
    public void reg(String account, String password, String smsCaptcha, String nickName, MyApiDelegate<UserinfoResponse> apiDelegate) {
        apiClient.setApiMethod("passport.reg");
        apiClient.setParam("account", account);
        apiClient.setParam("password", password);
        apiClient.setParam("sms_captcha", smsCaptcha);
        apiClient.setParam("nick_name", nickName);
        apiClient.asyncReq(apiDelegate, UserinfoResponse.class);
    }

    /**
     * 设置用户类型
     * @param userToken
     * @param userType
     * @param orgType
     * @param apiDelegate
     */
    public void setUserType(String userToken, int userType, int orgType, MyApiDelegate<BoolResponse> apiDelegate) {
        apiClient.setApiMethod("passport.setUserType");
        apiClient.setParam("user_token", userToken);
        apiClient.setParam("user_type", userType);
        apiClient.setParam("org_type", orgType);
        apiClient.asyncReq(apiDelegate, BoolResponse.class);
    }


    /**
     * 请求认证二维码
     * @param apiDelegate
     */
    public void getAuthQrcode(MyApiDelegate<AuthQrcodeResponse> apiDelegate) {
        apiClient.setApiMethod("passport.getAuthQrcode");
        apiClient.asyncReq(apiDelegate, AuthQrcodeResponse.class);
    }


    /**
     * 通过token获取用户信息
     * @param token
     * @param apiDelegate
     */
    public void getAuthByToken(String token, MyApiDelegate<UserinfoResponse> apiDelegate) {
        apiClient.setApiMethod("passport.getAuthByToken");
        apiClient.setParam("token", token);
        apiClient.asyncReq(apiDelegate, UserinfoResponse.class);
    }
}
