package com.frame.fastframelibrary.aosp.volley.listener;


import com.frame.fastframelibrary.net.core.base.ResponseBean;

/**
 * 网络请求结果监听
 */
public interface VolleyResultListener<T> {
	int REQUEST_DATA_SUCCESS = 0x00101;
	int REQUEST_DATA_FAIL = 0x00102;
	/**请求成功*/
	void responseSuccessed(ResponseBean<T> response);
	/**请求失败*/
	void responseFailed(ResponseBean<T> response);
}
