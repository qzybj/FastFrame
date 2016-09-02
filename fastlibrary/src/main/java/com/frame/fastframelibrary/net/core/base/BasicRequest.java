package com.frame.fastframelibrary.net.core.base;

import com.frame.fastframelibrary.net.core.NetUtils;
import com.frame.fastframelibrary.net.core.annotation.NoRequestArgs;
import com.frame.fastframelibrary.net.core.annotation.RequestArgs;
import com.frame.fastframelibrary.net.core.config.NetConstants;
import com.frame.fastframelibrary.utils.json.GsonUtils;
import java.util.HashMap;

public class BasicRequest {

	@NoRequestArgs
	private String mUrlAddr;

	@NetConstants.Method
	@NoRequestArgs
	private int mMethod;

	@RequestArgs("method")
	private String methodName;

	/** True if this response was a soft-expired one and a second one MAY be coming. */
	@NetConstants.LoadingType
	public int loadType = NetConstants.LoadingType.LOADING_NORMAL;

	public BasicRequest(){
		this.mUrlAddr = NetConstants.API_BASE_URL;
		this.mMethod = NetConstants.Method.POST;
	}

	protected BasicRequest setUrlAddr(String urladdr) {
		this.mUrlAddr = urladdr;
		return this;
	}

	protected BasicRequest setMethod(int method) {
		this.mMethod = method;
		return this;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public int obtainMethod() {
		return mMethod;
	}

	public String obtainUrlAddr() {
		return mUrlAddr;
	}

	public String toJson() {
		return GsonUtils.toJson(this);
	}
	
	public HashMap<String, String> obtainPostData() {
		HashMap<String, String> res = NetUtils.getParams();
		res.putAll(NetUtils.getFieldsMap(this));
		//res.put("method", methodName);
		res = NetUtils.encodeReqParams(res,NetConstants.APP_SECRET);
		return res;
	}
	
	public HashMap<String, String> obtainHeader() {
		HashMap<String, String> headers = NetUtils.getNetHeader();
		return headers;
	}
}