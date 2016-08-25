package brady.com.appframe.net.apiwrap.passport;

import brady.com.appframe.net.apiwrap.BaseApi;
import brady.com.appframe.net.apiwrap.MyApiDelegate;
import brady.com.appframe.net.apiwrap.passport.response.HotUsersResponse;
import netcore.api.BoolResponse;
import netcore.api.StringResponse;

/**
 * Created by tongdesheng on 16/1/28.
 */
public class UserApi extends BaseApi {

    /**
     * 设置头像
     * @param userToken
     * @param headIco
     * @param apiDelegate
     */
    public void setHeadImg(String userToken, String headIco, MyApiDelegate<StringResponse> apiDelegate) {
        apiClient.setApiMethod("User.setHeadImg");
        apiClient.setParam("user_token", userToken);
        apiClient.setParam("head_ico", headIco);
        apiClient.asyncReq(apiDelegate, StringResponse.class);
    }

    /**
     * 设置昵称
     * @param userToken
     * @param nickName
     * @param apiDelegate
     */
    public void setNickName(String userToken, String nickName, MyApiDelegate<BoolResponse> apiDelegate) {
        apiClient.setApiMethod("User.setNickName");
        apiClient.setParam("user_token", userToken);
        apiClient.setParam("nick_name", nickName);
        apiClient.asyncReq(apiDelegate, BoolResponse.class);
    }


    /**
     * 获取热门用户
     * @param userToken
     * @param positionCode  首页传 home_recommend
     * @param num  首页是4个
     */
    public void getHotUsers(String userToken, String positionCode, String num, MyApiDelegate<HotUsersResponse> apiDelegate) {
        apiClient.setApiMethod("User.hotUsers");
        apiClient.setParam("user_token", userToken);
        apiClient.setParam("postion_code", positionCode);
        apiClient.setParam("num", num);
        apiClient.asyncReq(apiDelegate, HotUsersResponse.class);
    }
}