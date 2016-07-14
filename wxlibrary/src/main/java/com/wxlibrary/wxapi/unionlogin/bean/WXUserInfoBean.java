package com.wxlibrary.wxapi.unionlogin.bean;

import com.google.gson.annotations.SerializedName;
import com.wxlibrary.wxapi.config.LocalReturnCode;
import com.wxlibrary.wxapi.config.WXConstant;
import java.util.ArrayList;

public class WXUserInfoBean {
    public static final String TAG = WXConstant.TAG;
    private LocalReturnCode localRetCode = LocalReturnCode.ERR_OTHER;

    @SerializedName("access_token")
    private String access_token;
    /**普通用户的标识，对当前开发者帐号唯一*/
    @SerializedName("openid")
    private String openid;
    /**普通用户昵称*/
    @SerializedName("nickname")
    private String nickname;
    /**普通用户性别，1为男性、2为女性、值为0时是未知*/
    @SerializedName("sex")
    private int sex;
    /**普通用户个人资料填写的省份*/
    @SerializedName("province")
    private String province;
    /**普通用户个人资料填写的城市*/
    @SerializedName("city")
    private String city;
    /**国家，如中国为CN*/
    @SerializedName("country")
    private String country;
    /**用户特权信息，JOSN数组，如微信沃卡用户为（Chinaunicom）*/
    @SerializedName("privilege")
    private ArrayList<String> privilege;
    /**用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空*/
    @SerializedName("headimgurl")
    private String headimgurl;
    /**用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。*/
    @SerializedName("unionid")
    private String unionid;
    @SerializedName("errCode")
    private int errCode;
    @SerializedName("errMsg")
    private String errMsg;

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ArrayList<String> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(ArrayList<String> privilege) {
        this.privilege = privilege;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
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

    public String getOpenid() {
        return openid;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUnionid() {
        return unionid;
    }
    public String getAccess_token() {
        return access_token;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public LocalReturnCode getLocalRetCode() {
        return localRetCode;
    }

    public void setLocalRetCode(LocalReturnCode localRetCode) {
        this.localRetCode = localRetCode;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
