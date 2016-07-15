package com.frame.fastframe.module.netframe;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

import com.frame.fastframe.module.common.util.DataCleanManager;
import com.yintai.R;
import com.yintai.module.welcome.bean.PublicKeyRequest;
import com.yintai.tools.DeviceUtils;
import com.yintai.tools.ExportPackageUtils;
import com.yintai.tools.FileUtils;
import com.yintai.tools.IOUtils;
import com.yintai.tools.StringUtil;
import com.yintai.tools.YTLog;
import com.yintai.tools.net.http.CHttpBuilder;
import com.yintai.tools.net.http.CHttpConstant.Method;
import com.yintai.tools.net.http.CHttpException;
import com.yintai.tools.net.http.cache.CacheManager;
import com.yintai.tools.net.http.resp.AbstractRequest;
import com.yintai.tools.net.http.resp.BasicRequest;
import com.yintai.tools.net.http.resp.BasicResponse;

import org.apache.http.HttpStatus;
import org.apache.http.client.ResponseHandler;

import java.io.File;

/**
 * <P>
 * 此类根据用的时候完善
 * </P>
 */
public class DataServer {
	private static final String TAG = DataServer.class.toString();
	public static final int MSG_WHAT_DATA_START = 0x7f00;// 32512
	public static final int MSG_WHAT_DATA_DONE = 0x7f01;
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
	 * 服务器端返回请求失败提示
	 */
	public static final int GET_DATA_CODE_ACCESSSERVER_FAILURE = 0x7f27;

	Application mApp;
	CacheManager mCacheManager;
	private static DataServer mDataServer = null;

	/**
	 * 它在Application 进行初始化,只能够初始化一次 构造函数
	 * 
	 * @param app
	 */
	public DataServer(Application app) {
		mApp = app;
		mCacheManager = new CacheManager(app.getApplicationContext());
		if (mDataServer == null) {
			mDataServer = this;
		} else {
			throw new IllegalStateException("Can only be initialized once!");
		}
	}

	private String getCacheData(AbstractRequest req) {
		String res = null;
		if (req.obtainNeedCache()) {
			res = mCacheManager.getCache4Request(req);
		} else {
			res = null;
		}
		return res;
	}

	public static final Application getApplication() {
		return mDataServer.mApp;
	}

	/**
	 * 异步获取数据，并解析json串<BR>
	 * 同步方法请参见<{@link #getData(BasicRequest, Class)}<BR>
	 * DataServer.asyncGetData()<BR>
	 * @param context
	 * 		建议传入Activity对象
	 * @param req
	 * @param clazz
	 * @return
	 */
	public static <T extends BasicResponse> AsyncDataServer<T> asyncGetData(
			AbstractRequest req, Class<T> clazz, Handler handler) {
		AsyncDataServer<T> task = new AsyncDataServer<T>(req, clazz, handler);
		task.execute();
		return task;
	}

	/**
	 * 获取数据，并解析json串。<BR>
	 * 1.如果想自己解析json串，则把clazz值为null<BR>
	 * DataServer.getData()<BR>
	 * @param req
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends BasicResponse> T getData(AbstractRequest req,
			Class<T> clazz) {
		DataServerBean dataBean = null;
		T response = null;
		T response_p = null;
		
		//检查本地保存的Token
		response= ResponseHandler.checkLocalToken(response,req);
		if(null!=response){
			return response;
		}

		dataBean = getOriginalData(req);
		if (clazz == null) {
			response = (T) new BasicResponse();
			if (dataBean.errcode == GET_DATA_CODE_SUC) {// 不改变其他错误code值
				dataBean.errcode = GET_DATA_CODE_NO_RESPONSE;
			}
			response.setErrCode(dataBean.errcode);
			response.setErrMsg(dataBean.errmsg);
		} else {

			try {
				response_p = clazz.newInstance();
				ResponseHandlerBean<T> handlerBean =ResponseHandler.responseHandler(dataBean,req,response_p);
				response=handlerBean.getResponse_p();
				dataBean=handlerBean.getDataServerBean();
			} catch (Exception e) {
				YTLog.e(e.toString());
				response=null;
			}
			
			if (response == null) {
				// JSON解析错误
				if (dataBean.errcode == GET_DATA_CODE_SUC) {// 不改变其他错误code值
					dataBean.errcode = GET_DATA_CODE_NO_JSON;
					dataBean.errmsg = mDataServer.mApp.getResources()
							.getString(R.string.net_parse_json_error);
				}
				//HTTP请求失败（状态码为非200）
				if(dataBean.httpRespnseCode != HttpStatus.SC_OK){
					//错误提示语
					dataBean.errmsg = "HTTP请求失败，请稍后重试（错误码：L"+(2000000+dataBean.errcode)+"）";
				}
				response_p.setErrCode(dataBean.errcode);
				response_p.setErrMsg(dataBean.errmsg);
				response = response_p;
			} else {
				//单独处理接口返回失败的情况
				if( response.isServerInterfaceReturnFailed()){
					//HTTP请求正确，接口返回非200，并且没有错误信息
					String errorMsg = response.getDescription();
					if(StringUtil.isEmpty(errorMsg)){
						errorMsg = mDataServer.mApp.getResources()
								.getString(R.string.net_accessserver_error);
					}
					//访问服务器，返回失败
					dataBean.errcode = DataServer.GET_DATA_CODE_ACCESSSERVER_FAILURE ;
					dataBean.errmsg = StringUtil.f(errorMsg);
				}
				response.setErrCode(dataBean.errcode);
				response.setErrMsg(dataBean.errmsg);
			}
		}

		if (dataBean.fromNet && dataBean.errcode == GET_DATA_CODE_SUC && req.obtainNeedCache()) {
			mDataServer.mCacheManager.putCache4Reqeust(req, dataBean.data);
		}
		//对获取App key请求做单独处理
		//遇到无网络的错误，显示错误提示框。
		if(req instanceof PublicKeyRequest&&
				GET_DATA_CODE_NO_NET==response.getErrCode()){
			req.setShowErrorDialog(true);
		}
		/**将请求码传递给BasicResponse*/
		response.setReqCode(req.reqCode);
		/**将请求是否显示错误提示框传递给BasicResponse*/
		response.setShowErrorDialog(req.isShowErrorDialog());
		response.setDismissLoading(req.isShowLoadding());
		YTLog.d("response = " + response);
		return response;
	}

	/**
	 * 获取最原始的数据，此数据是从缓存或者网络获取的最初数据。<BR>
	 * 注意：该函数不会缓存从网络获取的数据，因为不指定原始数据是否有效，缓存只缓存有效数据。<BR>
	 * DataServer.getOriginalData()<BR>
	 * @param req
	 * @return
	 */
	public static DataServerBean getOriginalData(AbstractRequest req) {
		DataServerBean dataBean = null;
		String data = null;
		if (req.obtainNeedCache() && (!req.isRefresh())) {
			if (mDataServer.mCacheManager.requestisavalidate(req)) {
				// 缓存数据有效
				data = mDataServer.getCacheData(req);
			}
		}

		if (data == null) {
			Log.i(TAG, "mDataServer==" + mDataServer + "==req==" + req);
			dataBean = mDataServer.openUrl(req);
			dataBean.fromNet = true;
			if (dataBean.errcode != GET_DATA_CODE_SUC) {// 返回数据不成功，使用缓存数据
				dataBean.data = mDataServer.getCacheData(req);
				dataBean.fromNet = false;
			}
		} else {
			dataBean = new DataServerBean();
			dataBean.data = data;
			if (!DeviceUtils.hasActiveNetwork(mDataServer.mApp)) {
				if (dataBean.errcode == DataServer.GET_DATA_CODE_SUC) {
					dataBean.errcode = DataServer.GET_DATA_CODE_NO_NET;
					dataBean.errmsg = mDataServer.mApp.getResources()
							.getString(R.string.net_unavailable_net_tip);
				}
			}
		}

		YTLog.d("请求结果" + dataBean);
		return dataBean;
	}

	private DataServerBean openUrl(AbstractRequest req) {
		DataServerBean dataBean = new DataServerBean();
		// 检查网络是否存在
		// 获取网络数据
		// 处理网络数据
		// 如果处理成功，不抛异常， 则写入缓存
		// 否则，读取缓存数据，加载缓存数据，并提示新数据获取失败
		if (DeviceUtils.hasActiveNetwork(mDataServer.mApp)) {
			YTLog.d("url = " + req.obtainUrlAddr());
			try {
				if (Method.GET == req.obtainMethod()) {
					dataBean.data = CHttpBuilder.getUrl(mDataServer.mApp,
							req.obtainUrlAddr(),
							req.obtainRequestHeader(mDataServer.mApp),
							req.obtainPostData(), false,
							req.obtainCookieCacheEnabled(),
							req.obtainCookieCachepersistent(),
							req.getConnectTimeOut());
				} else {
					dataBean.data = CHttpBuilder.postUrl(mDataServer.mApp,
							req.obtainUrlAddr(),
							req.obtainRequestHeader(mDataServer.mApp),
							req.obtainPostData(), false,
							req.obtainCookieCacheEnabled(),
							req.obtainCookieCachepersistent(),
							req.getConnectTimeOut());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				if(e1 instanceof CHttpException){
					dataBean.httpRespnseCode=((CHttpException)e1).getResponseCode();
				}
				dataBean.errcode = GET_DATA_CODE_NO_READ;
				dataBean.errmsg = mDataServer.mApp.getResources().getString(
						R.string.net_get_data_failure);
			}
		} else {
			dataBean.errcode = GET_DATA_CODE_NO_NET;
			dataBean.errmsg = mDataServer.mApp.getResources().getString(
					R.string.net_unavailable_net_tip);
		}
//		writeJson2Disk(req.interfaceName, StringUtil.f(dataBean.data));
		return dataBean;
	}

	/**
	 * 将json写到文件
	 * @param method
	 * @param json
	 */
	private void writeJson2Disk(String method, String json){
		if(ExportPackageUtils.isDevMode){
			File cacheDir = FileUtils.getDiskCacheDir(mApp, "yintaicache");
	        if(!cacheDir.exists()){
	            cacheDir.mkdirs();
	        }
	        File cacheFile =  new File(cacheDir,method + ".txt");
	        int i=1;
	        while(cacheFile.exists()) {
	        	cacheFile =  new File(cacheDir,method +i+ ".txt");
	        	i++;
	        }
	        IOUtils.writeFile(cacheFile.getAbsolutePath(), json);
		}
		
	}
	
	/**
	 * 此方法根据用的时候完善
	 */
	public static void cleanApplicationData() {
		// TODO 保存不删除的文件 比如：sourid.txt
		// 1.保留BI日志。
		// 2.保留用户登录信息。（userid、username、usersign、登录状态、是否记住我的登录状态、购物车ID）
		// 3.保留应用程序已第一次启动信息。
		// 4.保留我的地区
		// 5.保留GPS定位地址

		// 清除数据缓存
		mDataServer.mCacheManager.clearToCache(mDataServer.mApp);
		DataCleanManager.isCleanWebViewCache = true;
	}
}
