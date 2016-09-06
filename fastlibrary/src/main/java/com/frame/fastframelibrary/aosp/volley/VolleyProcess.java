package com.frame.fastframelibrary.aosp.volley;

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
import com.android.volley.toolbox.Volley;
import com.frame.fastframelibrary.FastApplication;
import com.frame.fastframelibrary.aosp.volley.requestimpl.GsonRequest;
import com.frame.fastframelibrary.aosp.volley.requestimpl.StringRequestC;
import com.frame.fastframelibrary.net.core.NetDataServerUtils;
import com.frame.fastframelibrary.net.core.bean.NetResponse;
import com.frame.fastframelibrary.net.core.bean.ResultBean;
import com.frame.fastframelibrary.net.core.config.NetConstants;
import com.frame.fastframelibrary.net.core.interfaces.IErrorInfo;
import com.frame.fastframelibrary.net.core.interfaces.INetProcess;
import com.frame.fastframelibrary.net.core.interfaces.IResultBean;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.dataprocess.MapUtils;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;

/**Volley工具类*/
public class VolleyProcess implements INetProcess{
	private final String TAG = VolleyProcess.class.getSimpleName();
	private static VolleyProcess instance;
	private RequestQueue mVolleyQueue;
	private final boolean SHOWLOG = true;

	private VolleyProcess() {
		init();
	}

	public static VolleyProcess instance() {
		if (instance==null) {
			instance = new VolleyProcess();
			return instance;
		} else {
			return instance;
		}
	}
	/**Initialise Volley Request Queue.*/
	private void init(){
		mVolleyQueue = Volley.newRequestQueue(FastApplication.instance());
	}

	public int getRequestCount(){
		return getRequestQueue().getSequenceNumber();
	}



	private RequestQueue getRequestQueue(){
		if(mVolleyQueue==null){
			init();
		}
		return mVolleyQueue;
	}

	/**
	 * (class)request net data ,parse json to class
	 * @param method			Request.Method.GET  Request.Method.POST
	 * @param url				Url
	 * @param params 			请求参数
	 * @param cls				解析用类
	 * @param tag				用于全部取消,请求分组
	 * @param listener   		请求成功、失败回调监听
	 */
	@Override
	public <T extends ResultBean> void request4Gson(int method, String url, Map<String, String> headers, Map<String, String> params,
													Class<T> cls, Object tag, final NetResponse.Listener<T> listener) {
		int methodTmp = getMethod(method);
		String requestUrl = methodTmp==Request.Method.GET?getUrl(url,params):url;
		GsonRequest<T> request = new GsonRequest<T>(getMethod(method),requestUrl,cls,null,
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
						if (listener!=null) {
							listener.onErrorResponse(getErrorInfo(error));
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
		getRequestQueue().add(request);
		startQueue();
	}

	/**
	 * (string)request net data ,get json string
	 * @param method	Request.Method.GET  Request.Method.POST
	 * @param url
	 * @param params 请求参数
	 * @param tag	用于全部取消,请求分组
	 * @param listener   		请求成功、失败回调监听
	 */
	@Override
	public void request4String(int method, String url,Map<String, String> headers, Map<String, String> params,Object tag,
							   final NetResponse.Listener<String> listener){
		int methodTmp = getMethod(method);
		String requestUrl = methodTmp==Request.Method.GET?getUrl(url,params):url;

		StringRequestC request = new StringRequestC(getMethod(method),requestUrl,
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
						if (listener!=null) {
							listener.onErrorResponse(getErrorInfo(error));
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
		getRequestQueue().add(request);
		startQueue();
	}

	/**开始任务队列*/
	private void startQueue(){
		getRequestQueue().start();
	}

	@Override
	/**取消所有任务队列*/
	public void cancelAll(Object tag) {
		getRequestQueue().cancelAll(tag);
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

	@Override
	public IErrorInfo getErrorInfo(Object error){
		if (error instanceof ParseError) {
			return NetDataServerUtils.getErrorInfo(NetConstants.NetStatusCode.CODE_NO_JSON,((VolleyError)error).getMessage());
		}else if (error instanceof NetworkError||error instanceof ServerError||
				error instanceof AuthFailureError||error instanceof TimeoutError) {
			return NetDataServerUtils.getErrorInfo(NetConstants.NetStatusCode.CODE_NO_NET,((VolleyError)error).getMessage());
		}else{
			return NetDataServerUtils.getErrorInfo(NetConstants.NetStatusCode.CODE_NO_READ,((VolleyError)error).getMessage());
		}
	}
}