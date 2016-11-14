package brady.com.appframe.common.utils;

import com.frame.fastframelibrary.module.login.interfaces.IUserInfo;
import com.frame.fastframelibrary.module.login.interfaces.IUserManager;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.cache.SharedPreferencesUtils;
import com.frame.fastframelibrary.utils.json.GsonUtils;
import brady.com.appframe.common.bean.UserBean;
import brady.com.appframe.net.models.passport.UserinfoBean;

/** 用户管理辅助类*/
public class UserManager implements IUserManager {

    private static UserManager instance = null;
    private static IUserInfo accountBean = null;

    private UserManager() {}

    public synchronized static UserManager instance() {
        if (instance==null) {
            instance = new UserManager();
        }
        return instance;
    }

    public IUserInfo getUser() {
        if(accountBean == null) {
            try {
                String userJson = SharedPreferencesUtils.instance().getString(KEY_USER_MANAGER);
                accountBean = GsonUtils.toObject(userJson,UserBean.class);
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        return accountBean;
    }

    /**
     * 更新用户信息
     * @param userInfo
     */
    public boolean setUser(IUserInfo userInfo) {
        if(userInfo!=null){
            accountBean = userInfo;
            String userJson = GsonUtils.toJson(userInfo);
            SharedPreferencesUtils.instance().setString(KEY_USER_MANAGER,userJson);
            return true;
        }
        return false;
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

    @Override
    public String getUserId() {
        return accountBean == null ? VALUE_NONE : accountBean.getUid();
    }

    /**
     * 获取user token
     * @return
     */
    public String getUserToken() {
        return accountBean == null ? VALUE_NONE : accountBean.getUserToken();
    }

    @Override
    public int getUserType() {
        return accountBean == null ? VALUE_NONE_INT: accountBean.getUserType();
    }

    /**
     * 注销登陆，清除用户信息
     */
    public void loginOut() {
        accountBean = new UserBean();
        setUser(accountBean);
        accountBean = null;
    }

    @Override
    public UserBean convert2Bean(Object obj) {
        if(obj instanceof UserinfoBean){
            UserinfoBean bean = (UserinfoBean)obj;
            UserBean user = new UserBean();
            user.setUserToken(bean.userToken);
            user.setUserToken(bean.userCode);
            user.setUserToken(bean.headImg);
            user.setUserToken(bean.nickName);
            user.setUserType(bean.userType);
            return user;
        }
        return null;
    }
}