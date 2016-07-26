package earlll.com.testdemoall.module.demo.bean;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 账号信息实体
 */
public class AccountBean implements Serializable {
	/**是否注册成功*/
	private String isSuccessful;
	/** 注册联网请求状态值*/
	private int statusCode;
	private String imageUrl;
	/** 登录注册成功提示信息*/
	private String description;
	/**用户id*/ 
	private String userId;
	/**用户等级*/
	@SerializedName("class")
	private String acclass;
	/** 银元*/
	private String coin;
	/** 银元余额*/
	private String money;
	/** 用户名*/
	private String name;
	/**积分*/
	private String point;
	/** 可用积分*/
	private String pointfrozen;
	/**绑定手机号**/
	private String mobile;
	/**是否绑定过手机号**/
	@SerializedName("iscellphoneconfirmed")
	private boolean isPhoneConfirmed;
	/**绑定的手机号**/
	private String cellphoneconfusion;
	/**会员GUID**/
	@SerializedName("customerguid")
	private String customerGuid;
	/**会员标签**/
	@SerializedName("customerlabels")
	private ArrayList<String> customerLabels;
	private String extern_token;
	/** logintype 16 银泰贵宾卡*/
	private int logintype=-1;
	/** 第三方唯一标识 */
	private String openid;
	private AccountDataBean accountData;

	/**消息数量*/
	private String pushid;

	/** 是否可以更改支付密码*/
	private boolean setpaypassword;

	/**是否设置支付密码**/
	private Boolean setpassword;

	public boolean getSetpaypassword() {
		return setpaypassword;
	}

	public void setSetpaypassword(Boolean setpaypassword) {
		this.setpaypassword = setpaypassword;
	}
	public String getExtern_token() {
		return extern_token;
	}

	public void setExtern_token(String extern_token) {
		this.extern_token = extern_token;
	}
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getPushid() {
		return pushid;
	}

	public void setPushid(String pushid) {
		this.pushid = pushid;
	}
	public Boolean getSetpassword() {
		return setpassword;
	}

	public void setSetpassword(Boolean setpassword) {
		this.setpassword = setpassword;
	}

	public String getIsSuccessful() {
		return isSuccessful;
	}

	public void setIsSuccessful(String isSuccessful) {
		this.isSuccessful = isSuccessful;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAcclass() {
		return acclass;
	}

	public void setAcclass(String acclass) {
		this.acclass = acclass;
	}

	public String getCoin() {
		return coin;
	}

	public void setCoin(String coin) {
		this.coin = coin;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getPointfrozen() {
		return pointfrozen;
	}

	public void setPointfrozen(String pointfrozen) {
		this.pointfrozen = pointfrozen;
	}



	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public boolean isPhoneConfirmed() {
		return isPhoneConfirmed;
	}

	public void setPhoneConfirmed(boolean isPhoneConfirmed) {
		this.isPhoneConfirmed = isPhoneConfirmed;
	}

	public AccountDataBean getAccountData() {
		return accountData;
	}

	public void setAccountData(AccountDataBean accountData) {
		this.accountData = accountData;
	}

	public String getCellphoneconfusion() {
		return cellphoneconfusion;
	}

	public void setCellphoneconfusion(String cellphoneconfusion) {
		this.cellphoneconfusion = cellphoneconfusion;
	}

	public String getCustomerGuid() {
		return customerGuid;
	}

	public void setCustomerGuid(String customerGuid) {
		this.customerGuid = customerGuid;
	}

	public ArrayList<String> getCustomerLabels() {
		return customerLabels;
	}

	public void setCustomerLabels(ArrayList<String> customerLabels) {
		this.customerLabels = customerLabels;
	}
	public void setSetpaypassword(boolean setpaypassword) {
		this.setpaypassword = setpaypassword;
	}
	public int getLogintype() {
		return logintype;
	}
	public void setLogintype(int logintype) {
		this.logintype = logintype;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
}
