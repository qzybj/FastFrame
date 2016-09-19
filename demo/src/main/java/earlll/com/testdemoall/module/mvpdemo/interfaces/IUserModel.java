package earlll.com.testdemoall.module.mvpdemo.interfaces;

import earlll.com.testdemoall.module.mvpdemo.model.UserBean;

/**
 * Created by ZhangYuanBo on 2016/9/8.
 */
public interface IUserModel {
    boolean save(int id,String name,String nickName);
    UserBean load (int id);//通过id读取user信息,返回一个UserBean
}