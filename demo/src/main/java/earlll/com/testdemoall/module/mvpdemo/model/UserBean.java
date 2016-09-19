package earlll.com.testdemoall.module.mvpdemo.model;

/**
 * Created by ZhangYuanBo on 2016/9/8.
 */
public class UserBean {
    private String name;
    private String nickName;
    private int sex;

    public UserBean(String name, String nickName, int sex) {
        this.name = name;
        this.nickName = nickName;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
