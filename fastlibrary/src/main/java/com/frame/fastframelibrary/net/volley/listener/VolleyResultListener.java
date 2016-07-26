package com.frame.fastframelibrary.net.volley.listener;


import com.frame.fastframelibrary.net.core.listener.ResponseBean;

/**
 * 网络请求结果监听
 */
public interface VolleyResultListener<T> {
	public static final int REQUEST_DATA_SUCCESS = 0x00101;
	public static final int REQUEST_DATA_FAIL = 0x00102;
	
	public void ResponseListener(ResponseBean<T> response);
}
