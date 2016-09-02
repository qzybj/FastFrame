package earlll.com.testdemoall.module.testnetframe.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tongdesheng on 16/1/8.
 */
public class UserinfoBean {

    @SerializedName("user_code")
    public String userCode;

    /**user_type：1为个人，2为企业*/
    @SerializedName("user_type")
    public int userType;

    @SerializedName("org_id")
    public String orgId;

    @SerializedName("nick_name")
    public String nickName;

    @SerializedName("head_img")
    public String headImg;

    public String dynamic;

    @SerializedName("user_token")
    public String userToken;

    @SerializedName("im_token")
    public String imToken;

    @Override
    public String toString() {
        return "UserinfoBean{" +
                "userCode='" + userCode + '\'' +
                ", userType=" + userType +
                ", orgId='" + orgId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", headImg='" + headImg + '\'' +
                ", dynamic='" + dynamic + '\'' +
                ", userToken='" + userToken + '\'' +
                ", imToken='" + imToken + '\'' +
                '}';
    }
}
