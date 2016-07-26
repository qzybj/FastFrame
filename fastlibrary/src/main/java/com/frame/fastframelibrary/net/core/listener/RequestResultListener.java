package com.frame.fastframelibrary.net.core.listener;

import com.frame.fastframelibrary.net.core.base.RequestDataServerBean;

/**
 * 网络请求结果监听
 */
public interface RequestResultListener<T> {
	public static final int REQUEST_DATA_SUCCESS = 0x00101;
	public static final int REQUEST_DATA_FAIL = 0x00102;
	
	public void ResponseListener(RequestDataServerBean response);
}
