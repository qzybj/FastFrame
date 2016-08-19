package com.frame.fastframe.module.loign.bean;

import com.frame.fastframelibrary.module.login.interfaces.IUserInfo;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ZhangYuanBo on 2016/5/3.
 * 用户信息类
 */
public class UserInfoBean implements IUserInfo {
    @SerializedName("uid")
    private String uid="";
    @SerializedName("user_token")
    private String userToken="";
    @SerializedName("user_type")
    private int userType=-1;
    @SerializedName("username")
    private String userName="";
    @SerializedName("head_img")
    private String headImg="";

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}
