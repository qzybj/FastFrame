package earlll.com.testdemoall.module.mvpdemo.interfaces;

/**
 * Created by ZhangYuanBo on 2016/9/8.
 */
public interface IUserView {
    int getID();
    String getName();
    String getNickName();
    void setName(String name);
    void setNickName(String nickName);
    void setError(String s);
}