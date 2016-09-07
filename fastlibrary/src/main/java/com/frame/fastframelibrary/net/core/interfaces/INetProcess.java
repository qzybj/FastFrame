package com.frame.fastframelibrary.net.core.interfaces;

import com.frame.fastframelibrary.net.core.bean.NetResponse;

import java.util.Map;


public interface INetProcess {

	/**Get reuquest count times*/
	int getRequestCount();

	/**Reuquest result parse by gson*/
	<T> void request4Gson(int method, String url, Map<String, String> headers, Map<String, String> params,
											 Class<T> clazz, Object tag, NetResponse.Listener<T> listener);
	/**Reuquest result source string ,no parse */
	void request4String(int method, String url,Map<String, String> headers,  Map<String, String> params,
						Object tag,NetResponse.Listener<String> listener);
	/**Cancel all reuquest*/
	void cancelAll(Object tag);

	/**Get reuquest error info*/
	IErrorInfo getErrorInfo(Object error);
}
