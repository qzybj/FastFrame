package com.frame.fastframelibrary.net.volley;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Application;
import android.net.Uri;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.frame.fastframelibrary.FastApplication;
import com.frame.fastframelibrary.net.core.listener.ResponseBean;
import com.frame.fastframelibrary.net.volley.requestimpl.GsonRequest;
import com.frame.fastframelibrary.net.volley.listener.VolleyResultListener;
import com.frame.fastframelibrary.utils.LogUtils;



/**Volley工具类*/
public class VolleyUtils {
	private final String TAG = VolleyUtils.class.getSimpleName();
	private static VolleyUtils instance;
	private RequestQueue mVolleyQueue;
	private final boolean SHOWLOG = true;
	
	private VolleyUtils() {
		instance.init(FastApplication.instance());
	}
	
	public static VolleyUtils getInstance() {
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
	 * 
	 * (class)request net data ,parse json to class
	 * @param method	Request.Method.GET  Request.Method.POST
	 * @param url
	 * @param QueryParameter 请求参数
	 * @param listener   JSON请求结果监听
	 * @param clazz		解析用类
	 * @param tag	用于全部取消,请求分组
	 */
	@SuppressWarnings("unused")
	public <T> void requestDataByGson(int method, String url, Map<String, String> QueryParameter,
									  final VolleyResultListener<T> listener, Class<T> clazz, String tag){
		Uri.Builder builder = Uri.parse(url).buildUpon();
		if (QueryParameter!=null&&QueryParameter.size()>0) {
			for (Entry<String, String> element : QueryParameter.entrySet()) {
				builder.appendQueryParameter(element.getKey(),element.getValue());
			}
		}
		GsonRequest<T> gsonObjRequest =
				new GsonRequest<T>(
						method,
						builder.toString(),
						clazz, 
						null,
						new Response.Listener<T>() {
							@Override
							public void onResponse(T response) {
								if (listener!=null) {
									ResponseBean<T> bean = new ResponseBean<T>();
									bean.setCode(VolleyResultListener.REQUEST_DATA_SUCCESS);
									bean.setData(response);
									listener.ResponseListener(bean);
								}
							}
						}, 
						new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								if (listener!=null) {
									ResponseBean<T> bean = new ResponseBean<T>();
									bean.setCode(VolleyResultListener.REQUEST_DATA_FAIL);
									bean.setMsg(error.getLocalizedMessage());
									listener.ResponseListener(bean);
									
									if (error instanceof NetworkError) {
									} else if (error instanceof ServerError) {
									} else if (error instanceof AuthFailureError) {
									} else if (error instanceof ParseError) {
									} else if (error instanceof NoConnectionError) {
									} else if (error instanceof TimeoutError) {
									}
								}
								showLog(error.getMessage());
							}
						}
						){

			//			 @Override
			//				protected Response<String> parseNetworkResponse(NetworkResponse response) {
			//			    	 try {
			//			             String jsonString = new String(response.data, "UTF-8");
			//			             return Response.success(jsonString,
			//			                     HttpHeaderParser.parseCacheHeaders(response));
			//			         } catch (UnsupportedEncodingException e) {
			//			             return Response.error(new ParseError(e));
			//			         } catch (Exception je) {
			//			             return Response.error(new ParseError(je));
			//			         }
			//				}

		};
		gsonObjRequest.setTag(tag);
		mVolleyQueue.add(gsonObjRequest);
		mVolleyQueue.start();  
	}
	
	/**
	 * (string)request net data ,get json string
	 * @param method	Request.Method.GET  Request.Method.POST
	 * @param url
	 * @param QueryParameter 请求参数
	 * @param listener   json请求结果监听
	 * @param tag	用于全部取消,请求分组
	 */
	@SuppressWarnings("unused")
	public void requestDataByString(int method,String url,Map<String, String> QueryParameter,
			final VolleyResultListener<String> listener,String tag){
		Uri.Builder builder = Uri.parse(url).buildUpon();
		if (QueryParameter!=null&&QueryParameter.size()>0) {
			for (Entry<String, String> element : QueryParameter.entrySet()) {
				builder.appendQueryParameter(element.getKey(),element.getValue());
			}
		}
		StringRequest stringRequest = new StringRequest(
				method,
				builder.toString(),
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						if (listener!=null) {
							ResponseBean<String> bean = new ResponseBean<String>();
							bean.setCode(VolleyResultListener.REQUEST_DATA_SUCCESS);
							bean.setData(response);
							listener.ResponseListener(bean);
						}
					}
				}, 
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						if (listener!=null) {
							ResponseBean<String> bean = new ResponseBean<String>();
							bean.setCode(VolleyResultListener.REQUEST_DATA_FAIL);
							bean.setMsg(error.getLocalizedMessage());
							listener.ResponseListener(bean);

							if (error instanceof NetworkError) {
							} else if (error instanceof ServerError) {
							} else if (error instanceof AuthFailureError) {
							} else if (error instanceof ParseError) {
							} else if (error instanceof NoConnectionError) {
							} else if (error instanceof TimeoutError) {
							}
						}
						showLog(error.getMessage());
					}
				}
				){
			@Override
			protected Response<String> parseNetworkResponse(NetworkResponse response) {
				try {
					String jsonString = new String(response.data, "UTF-8");
					return Response.success(jsonString,
							HttpHeaderParser.parseCacheHeaders(response));
				} catch (UnsupportedEncodingException e) {
					return Response.error(new ParseError(e));
				} catch (Exception je) {
					return Response.error(new ParseError(je));
				}
			}
		};
		stringRequest.setTag(tag);
		mVolleyQueue.add(stringRequest);
		mVolleyQueue.start();  
	}
	/**
	 * (jsonOjbect)request net data ,get jsonOjbect
	 * @param method	Request.Method.GET  Request.Method.POST
	 * @param url
	 * @param QueryParameter 请求参数
	 * @param listener   json请求结果监听
	 * @param tag	用于全部取消
	 */
	@SuppressWarnings("unused")
	public void requestDataByJsonObj(int method,String url,Map<String, String> QueryParameter,
			final VolleyResultListener<JSONObject> listener,String tag){
		Uri.Builder builder = Uri.parse(url).buildUpon();
		if (QueryParameter!=null&&QueryParameter.size()>0) {
			for (Entry<String, String> element : QueryParameter.entrySet()) {
				builder.appendQueryParameter(element.getKey(),element.getValue());
			}
		}
		JsonObjectRequest stringRequest = new JsonObjectRequest(
				method,
				builder.toString(),
				null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						if (listener!=null) {
							ResponseBean<JSONObject> bean = new ResponseBean<JSONObject>();
							bean.setCode(VolleyResultListener.REQUEST_DATA_SUCCESS);
							bean.setData(response);
							listener.ResponseListener(bean);
						}
					}
				}, 
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						if (listener!=null) {
							ResponseBean<JSONObject> bean = new ResponseBean<JSONObject>();
							bean.setCode(VolleyResultListener.REQUEST_DATA_FAIL);
							bean.setMsg(error.getLocalizedMessage());
							listener.ResponseListener(bean);

							if (error instanceof NetworkError) {
							} else if (error instanceof ServerError) {
							} else if (error instanceof AuthFailureError) {
							} else if (error instanceof ParseError) {
							} else if (error instanceof NoConnectionError) {
							} else if (error instanceof TimeoutError) {

							}
						}
						showLog(error.getMessage());
					}
				}
				){
			 @Override
			    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
			        try {
			            // solution 1:
			            String jsonString = new String(response.data, "UTF-8");
			            // solution 2:
			            response.headers.put("Content-Type",response.headers.get("content-type"));
			            String tmp = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
			            return Response.success(new JSONObject(tmp),HttpHeaderParser.parseCacheHeaders(response));
			        } catch (UnsupportedEncodingException e) {
			            return Response.error(new ParseError(e));
			        } catch (JSONException je) {
			            return Response.error(new ParseError(je));
			        }
			    }
		};
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
}