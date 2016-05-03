package com.frame.fastframelibrary.module.login.interfaces;

/**
 * Created by ZhangYuanBo on 2016/5/3.
 * 用户信息抽象接口
 */
public interface IUserInfo {
    String getUid();
    void setUid(String uid);

     String getUserToken();

     void setUserToken(String userToken);

     int getUserType();

     void setUserType(int userType);

     String getName();
     void setName(String userName);

     String getHeadImg();
     void setHeadImg(String headImg);
}
