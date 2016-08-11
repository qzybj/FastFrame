package com.frame.fastframelibrary.net.core.base;

import java.lang.reflect.Field;
import java.util.HashMap;
import android.content.Context;
import com.android.volley.Request.Method;
import com.frame.fastframelibrary.utils.json.GsonUtils;
import com.google.gson.annotations.SerializedName;

public class BasicRequest {
	/** 网址 */
	private String mUrlAddr;
	/** 网络请求方式
	 * Request.Method.GET
	 * Request.Method.POST
	 */
	private int mMethod;
	
	private String mPostDataKey;
	
	private boolean mNeedCache;
	private int mSavetime;
	private int mAvailablescope = 10 * 60;
	private boolean usegzip;
	private boolean cookieCacheEnabled;
	private boolean cookieCachepersistent;	
	private boolean isRefresh = false;

	public BasicRequest(String urladdr){
		mUrlAddr = urladdr;
		mMethod = Method.GET;
	}
	
	public BasicRequest(String urladdr,int method){
		mUrlAddr = urladdr;
		mMethod = method;
	}
	
	public BasicRequest setUrlAddr(String urladdr) {
		mUrlAddr = urladdr;
		return this;
	}
	
	public BasicRequest setMethod(int method) {
		mMethod = method;
		return this;
	}
	
	public BasicRequest setNeedCache(boolean needCache) {
		mNeedCache = needCache;
		return this;
	}
	
	public BasicRequest setSavetime(int savetime) {
		mSavetime = savetime;
		return this;
	}
	
	public BasicRequest setAvailablescope(int availablescope) {
		mAvailablescope = availablescope;
		return this;
	}
	
	public BasicRequest setPostDataKey(String postdatakey) {
		mPostDataKey = postdatakey;
		return this;
	}
	
	public BasicRequest setUsegzip(boolean use) {
		usegzip = use;
		return this;
	}
	
	public BasicRequest setCookieCacheEnabled(boolean enabled) {
		cookieCacheEnabled = enabled;
		return this;
	}
	public BasicRequest setCookieCachepersistent(boolean enabled) {
		cookieCachepersistent = enabled;
		return this;
	}
	/**
	 * 获取请求方法<BR>
	 * 注意：该方法名前缀不能够含有get即不为getXXXX<BR>
	 * 否则转换为json时，会被转换到json串里面。例如：<BR>
	 * {……"getXXXX":"GET"……}<BR>
	 * @return
	 */
	public int obtainMethod() {
		return mMethod;
	}
	
	public String obtainUrlAddr() {
		return mUrlAddr;
	}
	
	/**
	 * 获取是否需要缓存<BR>
	 * 注意：该方法名前缀不能够含有is即不为isXXXX<BR>
	 * 否则转换为json时，会被转换到json串里面。例如：<BR>
	 * * {……"isXXXX":"false"……}<BR>
	 * @return
	 */
	public boolean obtainNeedCache() {
		return mNeedCache;
	}
	
	public int obtainSavetime() {
		return mSavetime;
	}
	
	public int obtainAvailablescope() {		
		return mAvailablescope;
	}
	
	public boolean obtainUseGzip(){
        return usegzip;
    }
	
	public boolean obtainCookieCacheEnabled() {
		return cookieCacheEnabled;
	}
	public boolean obtainCookieCachepersistent() {
		return cookieCachepersistent;	
	}
	
	public BasicRequest setIsRefresh(boolean isRefresh){
		this.isRefresh = isRefresh;
		return this;
	}
	
	public boolean getIsRefresh(){
		return isRefresh;
	}
	
	public String  toJson() {			 
		return GsonUtils.toJson(this);
	}
	
	public HashMap<String, String> obtainPostData() {
		HashMap<String, String> res = new HashMap<String, String>();
		try {
			if (mPostDataKey == null || mPostDataKey.equals("")) {
				Field[] fields = getClass().getFields();
				Field t_field = null;
				String key = null;
				Object value = null;
				SerializedName jsonProperty=null;//使用annotation
				for (int i = 0; i < fields.length; i++) {
					t_field = fields[i];
					key = null;
					jsonProperty = t_field.getAnnotation(SerializedName.class);
					if(jsonProperty!=null){
						key = jsonProperty.value();
					}
					
					if(key==null||key.equals("")){
						key = t_field.getName();
					}
					value = t_field.get(this);
					if (value != null) {
						res.put(key, value.toString());
					}
				}
			} else {
				res.put(mPostDataKey, this.toJson());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//CLog.log4important(res);
		return res;
	}
	
	public HashMap<String, String> obtainRequestHeader(Context cnt) {
		if (cnt == null) {
			return null;
		}
		HashMap<String, String> headers = new HashMap<String, String>();
//		headers.put("platform_n", "android");
//		headers.put("uid", "");
//		headers.put("client_v", AppUtils.getVersionNameInManifest());
//		headers.put("content_type", "json");
//		headers.put("model", DeviceUtils.getPhoneModel());
//		headers.put("imsi", (null == DeviceUtils.readSimSerialNum() || ""
//				.equals(DeviceUtils.readSimSerialNum())) ? "NaN"
//				: DeviceUtils.readSimSerialNum());
//		headers.put("imei",(null == DeviceUtils.readTelephoneSerialNum() || ""
//						.equals(DeviceUtils.readTelephoneSerialNum())) ? "NaN": DeviceUtils.readTelephoneSerialNum());
//		headers.put("sourceid", FDFAppInfoConfig.SORUCEID); // 主渠道号
//		headers.put("language",(null == DeviceUtils.getSysLanguage() || "".equals(DeviceUtils
//						.getSysLanguage())) ? "NaN" : DeviceUtils.getSysLanguage());
//		headers.put("cn_operator",(null == DeviceUtils.getCarrier() || "".equals(DeviceUtils
//						.getCarrier())) ? "NaN" : DeviceUtils.getCarrier());
//		headers.put("sms_center_number", "");
//		headers.put("version", AppUtils.getVersionNameInManifest());
//		headers.put("macid", (null == DeviceUtils.getLocalMacAddress() || ""
//				.equals(DeviceUtils.getLocalMacAddress())) ? "NaN": DeviceUtils.getLocalMacAddress());
//		String userid = FDFAccountManager.getUserId();
//		headers.put("userid", userid == null ? "NaN" : userid);
//		String usersign = FDFAccountManager.getUserSign();
//		headers.put("usersign", usersign == null ? "NaN" : usersign);
//		headers.put("screenWidth", DeviceUtils.getScreenWidth() + "");
//		headers.put("screenHeigh", DeviceUtils.getScreenHeight() + "");
//		headers.put("operateSystem", DeviceUtils.getAndroidSDKVersion());
//		headers.put("applicationName", DataServer.getLFApplication().getString(R.string.lefeng_store_en));
//		headers.put("channelSource", "17");
//		headers.put("sourcesubid", FDFAppInfoConfig.SUB_SORUCEID);
		
		return headers;
	}
	
}
