package earlll.com.testdemoall.module.mvpdemo.model;

import java.util.HashMap;
import java.util.Map;

import earlll.com.testdemoall.module.mvpdemo.interfaces.IUserModel;

/**
 * Created by ZhangYuanBo on 2016/9/8.
 */
public class UserModel implements IUserModel{

    private Map<Integer,UserBean> map = new HashMap<>();

    @Override
    public boolean save(int id, String name, String nickName) {
        map.put(id,new UserBean(name,nickName,0));
        return false;
    }

    @Override
    public UserBean load(int id) {
        return map.get(id);
    }
}
