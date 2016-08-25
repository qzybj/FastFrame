package com.frame.fastframe.utils;

import com.frame.fastframe.module.loign.bean.UserInfoBean;
import com.frame.fastframelibrary.module.login.interfaces.IUserInfo;
import com.frame.fastframelibrary.module.login.interfaces.IUserManager;
import com.frame.fastframelibrary.utils.cache.SharedPreferencesUtils;


/**
 * 用户管理辅助类
 * Created by ZhangYuanBo on 2016/4/8.
 */
public class UserManager implements IUserManager {

    private static UserInfoBean userInfoBean = null;

    public static UserInfoBean getUserInfo() {
        return userInfoBean;
    }

    /**
     * 设为登录状态，并记下来账号密码
     * @param userInfo
     * @param account
     * @param password
     */
    public static void setUserInfo(UserInfoBean userInfo, String account, String password) {
        userInfoBean = userInfo;
        // TODO 需要把用户名和密码进行存储，下次进入软件的时候，如果有用户名和密码，则提交用户登录，能登录成功则直接跳转到首页
        SharedPreferencesUtils.instance().setString("account", account);
        SharedPreferencesUtils.instance().setString("password", password);
    }

    public static void setUserType(int userType) {
        if (userInfoBean != null) {
            userInfoBean.setUserType(userType);
        }
    }

    /**
     * 获取user token
     * @return
     */
    public static String getUserToken() {
        if (userInfoBean != null) {
            return userInfoBean == null ? "" : userInfoBean.getUserToken();
        }
        return "";
    }
    /**
     * 获取user token
     * @return
     */
    public static String getUid() {
        if (userInfoBean != null) {
            return userInfoBean == null ? "" : userInfoBean.getUid();
        }
        return "";
    }

    /**
     * 获取用户的账号密码
     * @return
     */
    public static AccountInfo getAccount() {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.account = SharedPreferencesUtils.instance().getString("account");
        accountInfo.password = SharedPreferencesUtils.instance().getString("password");
        return accountInfo;
    }

    /**
     * 检测是否登陆
     *
     * @return
     */
    public static boolean hasLogin() {
        if(userInfoBean == null) {
            return false;
        }
        return true;
    }

    /**
     * 注销登陆，清除用户信息
     */
    public static void LoginOut() {
        userInfoBean = null;
        //清除记录的账号
        SharedPreferencesUtils.instance().setString("account", "");
        SharedPreferencesUtils.instance().setString("password", "");
    }

    @Override
    public IUserInfo convert2Bean(Object obj) {
        return null;
    }

    public static class AccountInfo {
        private String account;
        private String password;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}