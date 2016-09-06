package com.frame.fastframelibrary.net.core.bean;

import com.frame.fastframelibrary.net.core.NetDataServerUtils;
import com.frame.fastframelibrary.net.core.annotation.NoRequestArgs;
import com.frame.fastframelibrary.net.core.annotation.RequestArgs;
import com.frame.fastframelibrary.net.core.config.NetConstants;
import com.frame.fastframelibrary.utils.json.GsonUtils;
import java.util.HashMap;

public class BasicRequest {

	@RequestArgs("method")
	private String methodName;

	@NoRequestArgs
	private String mUrlAddr;

	@NetConstants.Method
	@NoRequestArgs
	private int mMethod;

	@NoRequestArgs
	private int requestCode;

	/** True if this response was a soft-expired one and a second one MAY be coming. */
	@NetConstants.LoadingType
	@NoRequestArgs
	private int loadType = NetConstants.LoadingType.LOADING_NORMAL;

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

	public void setLoadType(int loadType) {
		this.loadType = loadType;
	}

	public void setRequestCode(int requestCode) {
		this.requestCode = requestCode;
	}

	public int getMethod() {
		return mMethod;
	}

	public String getUrlAddr() {
		return mUrlAddr;
	}

	@NetConstants.LoadingType
	public int getLoadType() {
		return loadType;
	}

	public int getRequestCode() {
		return requestCode;
	}

	public String toJson() {
		return GsonUtils.toJson(this);
	}
	
	public HashMap<String, String> obtainPostData() {
		HashMap<String, String> res = NetDataServerUtils.getParams();
		res.putAll(NetDataServerUtils.getFieldsMap(this));
		res = NetDataServerUtils.encodeReqParams(res,NetConstants.APP_SECRET);
		return res;
	}
	
	public HashMap<String, String> obtainHeader() {
		HashMap<String, String> headers = NetDataServerUtils.getNetHeader();
		return headers;
	}
}