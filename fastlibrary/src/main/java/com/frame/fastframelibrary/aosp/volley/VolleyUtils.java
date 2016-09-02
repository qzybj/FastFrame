package com.frame.fastframelibrary.aosp.volley;

import android.app.Application;
import android.net.Uri;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.frame.fastframelibrary.FastApplication;
import com.frame.fastframelibrary.aosp.volley.requestimpl.GsonRequest;
import com.frame.fastframelibrary.net.core.bean.NetResponse;
import com.frame.fastframelibrary.net.core.config.NetConstants;
import com.frame.fastframelibrary.net.core.interfaces.IErrorInfo;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.dataprocess.MapUtils;
import java.util.Map;
import java.util.Map.Entry;


/**Volley工具类*/
public class VolleyUtils {
	private final String TAG = VolleyUtils.class.getSimpleName();
	private static VolleyUtils instance;
	private RequestQueue mVolleyQueue;
	private final boolean SHOWLOG = true;

	private VolleyUtils() {
		init(FastApplication.instance());
	}

	public static VolleyUtils instance() {
		if (instance==null) {
			instance = new VolleyUtils();
			return instance;
		} else {
			return instance;
		}
	}
	/**Initialise Volley Request Queue.*/
	private <T extends Application> void init(T con){
		mVolleyQueue = Volley.newRequestQueue(con);
	}

	public RequestQueue getRequestQueue(){
		return mVolleyQueue;
	}

	/**
	 * (class)request net data ,parse json to class
	 * @param method			Request.Method.GET  Request.Method.POST
	 * @param url
	 * @param params 	请求参数
	 * @param clazz				解析用类
	 * @param tag				用于全部取消,请求分组
	 * @param listener   		请求成功回调监听
	 * @param errorListener   	请求失败回调监听
	 */
	@SuppressWarnings("unused")
	public <T> void request4Gson(int method, String url, Map<String, String> headers, Map<String, String> params, Class<T> clazz, String tag,
								 final NetResponse.Listener<T> listener, final NetResponse.ErrorListener errorListener){
		int methodTmp = getMethod(method);
		String requestUrl = methodTmp==Request.Method.GET?getUrl(url,params):url;
		GsonRequest<T> request = new GsonRequest<T>(
				getMethod(method),
				requestUrl,
				clazz,
				null,
				new Response.Listener<T>() {
					@Override
					public void onResponse(T response) {
						if (listener!=null) {
							listener.onResponse(response);
						}
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						if (errorListener!=null) {
							errorListener.onErrorResponse(getErrorInfo(error));
						}
					}
				}
		);
		if(MapUtils.isNotEmpty(headers)){
			try {
				request.setHeaders(headers);
			} catch (AuthFailureError authFailureError) {
				LogUtils.e(authFailureError);
			}
		}
		if(methodTmp == Request.Method.GET&&MapUtils.isNotEmpty(params)){
			try {
				request.setParams(params);
			} catch (AuthFailureError authFailureError) {
				LogUtils.e(authFailureError);
			}
		}
		request.setTag(tag);
		mVolleyQueue.add(request);
		mVolleyQueue.start();
	}


	/**
	 * (string)request net data ,get json string
	 * @param method	Request.Method.GET  Request.Method.POST
	 * @param url
	 * @param QueryParameter 请求参数
	 * @param tag	用于全部取消,请求分组
	 * @param listener   		请求成功回调监听
	 * @param errorListener   	请求失败回调监听
	 */
	public void requestDataByString(int method,String url,Map<String, String> QueryParameter,String tag,
									final NetResponse.Listener<String> listener, final NetResponse.ErrorListener errorListener){
		StringRequest stringRequest = new StringRequest(
				method,
				getUrl(url,QueryParameter),
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						if (listener!=null) {
							listener.onResponse(response);
						}
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						if (errorListener!=null) {
							errorListener.onErrorResponse(getErrorInfo(error));
						}
					}
				}
		);
		stringRequest.setTag(tag);
		mVolleyQueue.add(stringRequest);
		mVolleyQueue.start();
	}

	/**开始任务队列*/
	public void startQueue(){
		mVolleyQueue.start();
	}

	/**取消所有任务队列*/
	public void stopQueue(){
		mVolleyQueue.cancelAll(TAG);
	}

	private void showLog(String msg) {
		if (SHOWLOG) {
			LogUtils.e(TAG,msg);
		}
	}

	private int getMethod(int method){
		switch (method){
			case NetConstants.Method.GET:
				return Request.Method.GET;
			case NetConstants.Method.POST:
				return Request.Method.POST;
			default:
				return Request.Method.POST;
		}
	}
	private String getUrl(String url, Map<String, String> QueryParameter){
		Uri.Builder builder = Uri.parse(url).buildUpon();
		if (QueryParameter!=null&&QueryParameter.size()>0) {
			for (Entry<String, String> element : QueryParameter.entrySet()) {
				builder.appendQueryParameter(element.getKey(),element.getValue());
			}
		}
		return builder.toString();
	}
	public IErrorInfo getErrorInfo(VolleyError error){
		if (error instanceof ParseError) {
			return NetResponse.errorInfo(NetConstants.NetStatusCode.CODE_NO_JSON,error.getMessage());
		}else if (error instanceof NetworkError) {
			return NetResponse.errorInfo(NetConstants.NetStatusCode.CODE_NO_NET,error.getMessage());
		} else if (error instanceof ServerError) {
			return NetResponse.errorInfo(NetConstants.NetStatusCode.CODE_NO_NET,error.getMessage());
		} else if (error instanceof AuthFailureError) {
			return NetResponse.errorInfo(NetConstants.NetStatusCode.CODE_NO_NET,error.getMessage());
		} else if (error instanceof TimeoutError) {
			return NetResponse.errorInfo(NetConstants.NetStatusCode.CODE_NO_NET,error.getMessage());
		}else{
			return NetResponse.errorInfo(NetConstants.NetStatusCode.CODE_NO_READ,error.getMessage());
		}
	}
}