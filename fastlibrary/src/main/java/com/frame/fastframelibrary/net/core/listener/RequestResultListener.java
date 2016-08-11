package com.frame.fastframelibrary.net.core.listener;

import com.frame.fastframelibrary.net.core.base.RequestDataServerBean;

/**
 * 网络请求结果监听
 */
public interface RequestResultListener<T> {
	int REQUEST_DATA_SUCCESS = 0x00101;
	int REQUEST_DATA_FAIL = 0x00102;
	/**请求成功*/
	void responseSuccessed(RequestDataServerBean response);
	/**请求失败*/
	void responseFailed(RequestDataServerBean response);
}
