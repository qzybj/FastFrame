package brady.com.appframe.common.utils;

import com.frame.fastframelibrary.module.login.interfaces.IUserManager;
import com.frame.fastframelibrary.utils.cache.SharedPreferencesUtils;
import brady.com.appframe.common.bean.AccountBean;
import brady.com.appframe.net.models.passport.UserinfoBean;

/**
 * 用户管理辅助类
 * Created by ZhangYuanBo on 2016/4/8.
 */
public class UserManager implements IUserManager {

    protected static String USER_TOKEN = "user_token";
    protected static String USER_UID = "uid";
    protected static String USER_NICKNAME = "nick_name";
    protected static String USER_HEADIMG = "head_img";
    protected static String USER_TYPE = "user_type";

    protected static String USER_NAME = "username";
    protected static String USER_PASSWORD = "password";

    private static UserManager instance = null;
    private static AccountBean accountBean = null;

    private UserManager() {

    }

    public synchronized static UserManager instance() {
        if (instance==null) {
            instance = new UserManager();
        }
        return instance;
    }

    public AccountBean getUserInfo() {
        if(accountBean == null) {
            accountBean = new AccountBean();
            accountBean.setUserName(SharedPreferencesUtils.instance().getString(USER_NAME));
            accountBean.setUserToken(SharedPreferencesUtils.instance().getString(USER_TOKEN));
            accountBean.setHeadImg(SharedPreferencesUtils.instance().getString(USER_HEADIMG));
            accountBean.setNickName(SharedPreferencesUtils.instance().getString(USER_NICKNAME));
            accountBean.setUserType(SharedPreferencesUtils.instance().getInt(USER_TYPE));
        }
        return accountBean;
    }

    /**
     * 更新用户信息
     * @param userInfo
     */
    public void setUserInfo(AccountBean userInfo) {
        if(userInfo!=null){
            accountBean = userInfo;
            SharedPreferencesUtils.instance().setString(USER_NAME,accountBean.getUserName());
            SharedPreferencesUtils.instance().setString(USER_TOKEN,accountBean.getUserToken());
            SharedPreferencesUtils.instance().setString(USER_HEADIMG,accountBean.getHeadImg());
            SharedPreferencesUtils.instance().setString(USER_NICKNAME,accountBean.getNickName());
            SharedPreferencesUtils.instance().setInt(USER_TYPE,accountBean.getUserType());
        }
    }

    /**
     * 获取user token
     * @return
     */
    public String getUserToken() {
        return accountBean == null ? "" : accountBean.getUserToken();
    }

    /**
     * 检测是否登陆
     * @return
     */
    public boolean isLogin() {
        if(accountBean == null) {
            return false;
        }
        return true;
    }

    /**
     * 注销登陆，清除用户信息
     */
    public void loginOut() {
        accountBean = new AccountBean();
        setUserInfo(accountBean);
        accountBean = null;
    }

    /**
     * 获取用户的登录账号、密码
     * @return
     */
    public AutoLoginInfo getAutoLoginInfo() {
        AutoLoginInfo accountInfo = new AutoLoginInfo();
        accountInfo.setAccount(SharedPreferencesUtils.instance().getString(USER_NAME));
        accountInfo.setPassword(SharedPreferencesUtils.instance().getString(USER_PASSWORD));
        return accountInfo;
    }
    /**
     * 存储用户的登录来账号、密码
     * @param account
     * @param password
     */
    public void setAutoLoginInfo(String account, String password) {
        SharedPreferencesUtils.instance().setString(USER_NAME, account);
        SharedPreferencesUtils.instance().setString(USER_PASSWORD, password);
    }

    @Override
    public AccountBean convert2Bean(Object obj) {
        UserinfoBean bean = (UserinfoBean)obj;
        AccountBean user = new AccountBean();
        user.setUserToken(bean.userToken);
        user.setUserToken(bean.userCode);
        user.setUserToken(bean.headImg);
        user.setUserToken(bean.nickName);
        user.setUserType(bean.userType);
        return user;
    }

    /**用于用户自动登录*/
    public static class AutoLoginInfo {
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