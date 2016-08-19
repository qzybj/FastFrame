package brady.com.appframe.common.bean;

import com.frame.fastframelibrary.module.login.interfaces.IUserInfo;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 账号信息实体
 */
public class AccountBean implements IUserInfo {
    @SerializedName("uid")
    private String uid = "";
    @SerializedName("user_token")
    private String userToken = "";
    @SerializedName("user_type")
    private int userType = -1;
    @SerializedName("username")
    private String userName = "";


    @SerializedName("nickname")
    private String nickName = "";
    @SerializedName("head_img")
    private String headImg = "";

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}