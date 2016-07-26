package com.frame.fastframelibrary.net.volley;


import com.frame.fastframelibrary.FastApplication;
import com.frame.fastframelibrary.net.core.base.BasicRequest;
import com.frame.fastframelibrary.net.core.base.BasicResponse;
import com.frame.fastframelibrary.net.core.base.RequestDataServerBean;
import com.frame.fastframelibrary.net.core.listener.RequestResultListener;
import com.frame.fastframelibrary.net.core.listener.ResponseBean;
import com.frame.fastframelibrary.net.volley.listener.VolleyResultListener;
import com.frame.fastframelibrary.R;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.NetUtils;
import com.frame.fastframelibrary.utils.StringUtils;

/**请求数据封装类*/
public class RequestDataServer {
	/**请求网络数据 - 开始*/
	public static final int MSG_WHAT_DATA_START = 0x7f00;// 32512
	/**请求网络数据 - 成功*/
	public static final int MSG_WHAT_DATA_DONE = 0x7f01;
	/**请求网络数据 - 取消*/
	public static final int MSG_WHAT_DATA_CANCEL = 0x7f02;

	/**
	 * 获取数据错误码：无错误 字段或域定义：<code>GET_DATA_CODE_SUC</code>
	 */
	public static final int GET_DATA_CODE_SUC = 0x7f20;// 32544
	/**
	 * 获取数据错误码：无网络连接 字段或域定义：<code>GET_DATA_CODE_NO_NET</code>
	 */
	public static final int GET_DATA_CODE_NO_NET = 0x7f23;
	/**
	 * 获取数据错误码：无法获取网络数据 字段或域定义：<code>GET_DATA_CODE_NO_READ</code>
	 */
	public static final int GET_DATA_CODE_NO_READ = 0x7f24;// 32548

	/**
	 * 获取数据错误码：解析json串错误 字段或域定义：<code>GET_DATA_CODE_NO_JSON</code>
	 */
	public static final int GET_DATA_CODE_NO_JSON = 0x7f25;

	/**
	 * 代表getdata方法传入的参数clazz为null，如果想自己解析json数据可以把clazz传为空。 字段或域定义：
	 * <code>GET_DATA_CODE_NO_RESPONSE</code>
	 */
	public static final int GET_DATA_CODE_NO_RESPONSE = 0x7f26;
	/**
	 * 获取数据Json状态码：正确解析json串 字段或域定义：<code>GET_DATA_CODE_JSON_SUCCESS</code>
	 */
	public static final int GET_DATA_CODE_JSON_SUCCESS = 0x7f27;

	private static RequestDataServer mDataServer = null;

	/**
	 * 它在Application 进行初始化,只能够初始化一次 构造函数
	 */
	private RequestDataServer() {
	}
	
	public static RequestDataServer getInstance(){
		if (mDataServer == null) {
			mDataServer = new RequestDataServer();
		}
		return mDataServer;
	}
	

	/**
	 * 获取数据，并解析json串。<BR>
	 * 1.如果想自己解析json串，则把clazz值为null<BR>
	 * @param req
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasicResponse> void getData(BasicRequest req,
			RequestResultListener<String> listener,Class<T> clazz,String tag) {

		RequestDataServerBean dataBean = null;
		String data = null;
		
		//取缓存数据机制，待处理
		if (data == null) {
			mDataServer.openUrl(req, listener,clazz,tag);
//		} else {//无缓存处理机制该处代码无法触发
//			dataBean = new RequestDataServerBean();
//			dataBean.data = data;
//			if (!NetUtils.isNetConnected(FDFApplication.getContext())) {
//				if (dataBean.errcode == RequestDataServer.GET_DATA_CODE_SUC) {
//					dataBean.errcode = RequestDataServer.GET_DATA_CODE_NO_NET;
//					dataBean.errmsg = "not net connect error";
//				}
//			}
		}
	}
	

	private <T extends BasicResponse> void openUrl(BasicRequest req,
			final RequestResultListener<String> listener,Class<T> clazz,String tag) {
		// 1.检查网络是否存在
		// 2.获取网络数据
		// 3.处理网络数据
		// 4.如果处理成功，不抛异常， 则写入缓存
		// 5.否则，读取缓存数据，加载缓存数据，并提示新数据获取失败
		if (NetUtils.isNetConnected(FastApplication.getInstance())) {
			LogUtils.d("url = " + req.obtainUrlAddr());
			try {
				VolleyHttpUtils.getInstance(FastApplication.getInstance()).
				requestDataByString(
						req.obtainMethod(), 
						req.obtainUrlAddr(), 
						null,
						new VolleyResultListener<String>() {
							@Override
							public void ResponseListener(ResponseBean<String> response) {
								if (listener!=null) {
									if (response!=null) {
										RequestDataServerBean dataBean = new RequestDataServerBean();
										if (response.getCode() == VolleyResultListener.REQUEST_DATA_SUCCESS) {
											dataBean.data = response.getData();
										}else {
											dataBean.errcode = GET_DATA_CODE_NO_READ;
											if (!StringUtils.isEmpty(response.getMsg())) {
												dataBean.errmsg = response.getMsg();
											}else {
												dataBean.errmsg = FastApplication.getInstance()
														.getResources().getString(R.string.request_net_err);
											}
										}
										listener.ResponseListener(dataBean);
									}
								}
							}
						}, 
						tag);
				
			} catch (Exception e1) {
				LogUtils.e(e1.getMessage());
				if (listener!=null) {
					RequestDataServerBean dataBean = new RequestDataServerBean();
					dataBean.errcode = GET_DATA_CODE_NO_READ;
					dataBean.errmsg = FastApplication.getInstance()
							.getResources().getString(R.string.request_net_err);
					listener.ResponseListener(dataBean);
				}
			}
		} else {
			if (listener!=null) {
				RequestDataServerBean dataBean = new RequestDataServerBean();
				dataBean.errcode = GET_DATA_CODE_NO_NET;
				dataBean.errmsg = FastApplication.getInstance()
						.getResources().getString(R.string.not_net_connect_err);
				listener.ResponseListener(dataBean);
			}
		}
	}
	/**
	 * 用于解析JSON数据
	 * @param json
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public <T extends BasicResponse> T parseJson(String json,Class<T> clazz){
		T response = null;
		T response_p = null;
		if (clazz == null) {//no json parse class
			response = (T) new BasicResponse();
			response.setErrMsg("没有指定解析解析类");
			response.setErrCode(GET_DATA_CODE_NO_RESPONSE);
		} else {//json parse to class
			try {
				response_p = clazz.newInstance();
				response = (T) response_p.fromJsonByGson(json);
				response.setErrCode(GET_DATA_CODE_JSON_SUCCESS);
			} catch (Exception e) {
				LogUtils.e(e.toString());
				response=null;
			}
			if (response == null) {//处理解析失败的情况
				try {
					response_p.setErrMsg(FastApplication.getInstance()
							.getString(R.string.parse_json_err));
					response_p.setErrCode(GET_DATA_CODE_NO_JSON);
					response = response_p;
				} catch (Exception e) {
					LogUtils.e(e.toString());
				}
			} 
		}
		return response;
	}
}