package com.frame.fastframelibrary.module.login.interfaces;

/**
 * Created by ZhangYuanBo on 2016/5/3.
 * User manager util class:includes user info manage(save,get)
 */
public interface IUserManager {
     /**用户信息存储key*/
     String KEY_USER_MANAGER = "key_user_manager";
     /**返值 - 错误默认值*/
     int VALUE_NONE_INT = -1;
     /**返值 - 错误默认值*/
     String VALUE_NONE = "";

     IUserInfo getUser();

     int getUserType();

     String getUserId();

     boolean setUser(IUserInfo iUserInfo);

     void loginOut();

     boolean isLogin();

     /**用于处理将bean转换为需要UserBean,如果返回为null，则解析不成功*/
     IUserInfo convert2Bean(Object obj);
}