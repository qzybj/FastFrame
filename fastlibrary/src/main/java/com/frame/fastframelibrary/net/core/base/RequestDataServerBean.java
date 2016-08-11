package com.frame.fastframelibrary.net.core.base;

import com.frame.fastframelibrary.net.RequestDataServer;

/**
 * 数据请求基本bean类
 */
public class RequestDataServerBean {
	public String data;
	public int errcode = RequestDataServer.GET_DATA_CODE_SUC;
	public String errmsg;
	/**数据来源： true 网络  false 缓存*/
	public boolean fromNet = true;
	
	@Override
	public String toString() {		
		return "errcode="+errcode+";errmsg="+errmsg+";data="+data;		
	}
}