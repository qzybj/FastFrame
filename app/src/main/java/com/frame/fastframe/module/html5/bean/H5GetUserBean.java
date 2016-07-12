package com.frame.fastframe.module.html5.bean;

import com.frame.fastframe.module.html5.config.ModuleConstant_H5;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * h5页面调用原生的用户信息Bean类
 */
public class H5GetUserBean implements Serializable {
	@SerializedName(ModuleConstant_H5.H5_KEY_PLATFORM)
	private String platform;// "android"
	@SerializedName(ModuleConstant_H5.H5_KEY_PRODUCTLINE)
	private String apptype;// "1"
	@SerializedName(ModuleConstant_H5.H5_KEY_APPVERSION)
	private String appversion;// "4.0.7"
	@SerializedName(ModuleConstant_H5.H5_KEY_IMEI)
	private String imei;// "864601028630895"
	@SerializedName(ModuleConstant_H5.H5_KEY_MACID)
	private String macid;// "f4:e3:fb:65:e2:77"
	@SerializedName(ModuleConstant_H5.H5_KEY_UID)
	private String userid;// "y0trFoU59\/jRHgUgDDTqXPWoudVQSQcMKUduJ9B08EsZUv5+SAEJufLOXQvDHLPG8SwUtR+cI2CX7RjNikjTbg=="
	@SerializedName(ModuleConstant_H5.H5_KEY_GPS_LATITUDE)
	private String gpsx;// "39.90458600000000188856574823148548603057861328125"
	@SerializedName(ModuleConstant_H5.H5_KEY_GPS_LONGITUDE)
	private String gpsy;// "116.507938999999993257006281055510044097900390625"
	@SerializedName(ModuleConstant_H5.H5_KEY_SYSTEMVERSION)
	private String osversion;// "4.4.2"
	@SerializedName(ModuleConstant_H5.H5_KEY_SCREENWIDTH)
	private String screenWidth;// "1080"
	@SerializedName(ModuleConstant_H5.H5_KEY_SCREENHEIGH)
	private String screenHeigh;// "1776"
	@SerializedName(ModuleConstant_H5.H5_KEY_CARRIER)
	private String carrier;// ""
	@SerializedName(ModuleConstant_H5.H5_KEY_MOBILEMODEL)
	private String devicename;// "PE-TL10"
	@SerializedName(ModuleConstant_H5.H5_KEY_ISLOGIN)
	private boolean userstate;// "true"

	public String getMacid() {
		return macid;
	}

	public void setMacid(String macid) {
		this.macid = macid;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}


	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getGpsy() {
		return gpsy;
	}

	public void setGpsy(String gpsy) {
		this.gpsy = gpsy;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getGpsx() {
		return gpsx;
	}

	public void setGpsx(String gpsx) {
		this.gpsx = gpsx;
	}


	public String getOsversion() {
		return osversion;
	}

	public void setOsversion(String osversion) {
		this.osversion = osversion;
	}

	public String getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(String screenWidth) {
		this.screenWidth = screenWidth;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getDevicename() {
		return devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	public boolean getUserstate() {
		return userstate;
	}

	public void setUserstate(boolean userstate) {
		this.userstate = userstate;
	}

	public String getApptype() {
		return apptype;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype;
	}

	public String getAppversion() {
		return appversion;
	}

	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}

	public String getScreenHeigh() {
		return screenHeigh;
	}

	public void setScreenHeigh(String screenHeigh) {
		this.screenHeigh = screenHeigh;
	}
}
