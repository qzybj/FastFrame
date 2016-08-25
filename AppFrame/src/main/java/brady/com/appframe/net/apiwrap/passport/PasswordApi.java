package brady.com.appframe.net.apiwrap.passport;

import brady.com.appframe.net.apiwrap.BaseApi;
import brady.com.appframe.net.apiwrap.MyApiDelegate;
import netcore.api.BoolResponse;

/**
 * Created by tongdesheng on 16/1/28.
 */
public class PasswordApi extends BaseApi {

    /**
     * 修改密码
     * @param userToken
     * @param oldPwd
     * @param newPwd
     * @param apiDelegate
     */
    public void modifyPwd(String userToken, String oldPwd, String newPwd, MyApiDelegate<BoolResponse> apiDelegate) {
        apiClient.setApiMethod("Password.modify");
        apiClient.setParam("old_pwd", oldPwd);
        apiClient.setParam("new_pwd", newPwd);
        apiClient.setParam("user_token", userToken);
        apiClient.asyncReq(apiDelegate, BoolResponse.class);
    }


    /**
     * 重置密码
     * @param account
     * @param smsCaptcha
     * @param newPwd
     * @param apiDelegate
     */
    public void resetPwd(String account, String smsCaptcha, String newPwd, MyApiDelegate<BoolResponse> apiDelegate) {
        apiClient.setApiMethod("Password.reset");
        apiClient.setParam("account", account);
        apiClient.setParam("sms_captcha", smsCaptcha);
        apiClient.setParam("new_pwd", newPwd);
        apiClient.asyncReq(apiDelegate, BoolResponse.class);
    }
}
