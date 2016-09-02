package com.frame.fastframelibrary.net.core;

import com.frame.fastframelibrary.FastApplication;
import com.frame.fastframelibrary.R;
import com.frame.fastframelibrary.aosp.volley.VolleyUtils;
import com.frame.fastframelibrary.net.core.base.BasicRequest;
import com.frame.fastframelibrary.net.core.bean.NetResponse;
import com.frame.fastframelibrary.net.core.config.NetConstants;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.device.NetUtils;


/**请求数据封装类*/
public class NetDataServer {

	private static NetDataServer mDataServer = null;

	private NetDataServer() {
		//init
	}

	public synchronized static NetDataServer instance(){
		if (mDataServer == null) {
			mDataServer = new NetDataServer();
		}
		return mDataServer;
	}

	public <T> void getData(BasicRequest req, Class<T> clazz, String tag, NetResponse.Listener<T> listener, NetResponse.ErrorListener errorListener) {
		// 1.Check net is exist
		// 2.Call method for get net data
		if (NetUtils.isNetConnected(FastApplication.instance())) {
			mDataServer.openUrl(req,clazz,tag,listener,errorListener);
		} else {
			if (errorListener!=null) {
				errorListener.onErrorResponse(NetResponse.errorInfo(NetConstants.NetStatusCode.CODE_NO_NET,FastApplication.instance().getResources().getString(R.string.not_net_connect_err)));
			}
		}
	}

	private <T> void openUrl(BasicRequest req, Class<T> clazz,String tag,NetResponse.Listener<T> listener,NetResponse.ErrorListener errorListener) {
		// 1.Get net data
		// 2.Parse net data to json model
		// 3.Try catch exception
		try {
			VolleyUtils.instance().request4Gson(req.obtainMethod(),req.obtainUrlAddr(),req.obtainHeader(),req.obtainPostData(),clazz,tag,listener,errorListener);
		} catch (Exception e) {
			LogUtils.e(e.getMessage());
			if (errorListener!=null) {
				errorListener.onErrorResponse(NetResponse.errorInfo(NetConstants.NetStatusCode.CODE_NO_READ,FastApplication.instance().getResources().getString(R.string.request_net_err)));
			}
		}
	}
}