package earlll.com.testdemoall.module.testnetframe.bean;

import com.frame.fastframelibrary.net.core.bean.NetResponse;
import com.frame.fastframelibrary.net.core.bean.ResultBean;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 * Created by tongdesheng on 16/1/8.
 */
public class UserinfoBean extends ResultBean {

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

    @Override
    public Type getType() {
        return new TypeToken<NetResponse<UserinfoBean>>() {}.getType();
    }
}
