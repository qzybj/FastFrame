package com.frame.fastframelibrary.net.core;

import com.frame.fastframelibrary.FastApplication;
import com.frame.fastframelibrary.aosp.volley.VolleyProcess;
import com.frame.fastframelibrary.net.core.bean.BasicRequest;
import com.frame.fastframelibrary.net.core.bean.NetResponse;
import com.frame.fastframelibrary.net.core.bean.ResultBean;
import com.frame.fastframelibrary.net.core.bean.TagBean;
import com.frame.fastframelibrary.net.core.config.NetConstants;
import com.frame.fastframelibrary.net.core.interfaces.IErrorInfo;
import com.frame.fastframelibrary.net.core.interfaces.IProgressLoad;
import com.frame.fastframelibrary.net.core.interfaces.IRequestListener;
import com.frame.fastframelibrary.net.core.interfaces.ITag;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.device.NetUtils;


/**请求数据封装类*/
public class NetDataServer {
	private static NetDataServer mDataServer = null;

	public static final int DEFAULT_REQUEST_TAG = 11001;
	public static final int DEFAULT_REQUEST_CODE = 11002;

	private IProgressLoad mProgressLoadProcess;
	private NetDataServer() {}

	public synchronized static NetDataServer instance(){
		if (mDataServer == null) {
			mDataServer = new NetDataServer();
		}
		return mDataServer;
	}
	public  <T extends ResultBean> void request(BasicRequest req, Class<T> cls, IRequestListener listener) {
		TagBean tag  = new TagBean(req);
		startRequest(tag);
		if (!NetUtils.isNetConnected(FastApplication.instance())) {
			errorCommon(listener,tag);
			return;
		}
		requestData(req,cls,listener,tag);
	}

	private  <T extends ResultBean> void requestData(BasicRequest req, Class<T> cls,final IRequestListener listener, final TagBean tag) {
		try {
			VolleyProcess.instance().request4Gson(req.getMethod(),req.getUrlAddr(),req.obtainHeader(),
					req.obtainPostData(),cls,DEFAULT_REQUEST_TAG,
					new NetResponse.Listener<T>() {
						@Override
						public void onResponse(T response) {
							stopRequest(tag);
							if (listener!=null) {
								listener.onResponse(tag.getRequstCode(),response);
							}
						}
						@Override
						public void onErrorResponse(IErrorInfo error) {
							stopRequest(tag);
							if (listener!=null) {
								listener.onErrorResponse(tag.getRequstCode(),error);
							}
						}
					});
		} catch (Exception e) {
			LogUtils.e(e.getMessage());
			errorCommon(listener,tag);
		}
	}

	/**Cancel all request*/
	public void cancelAll(){
		VolleyProcess.instance().cancelAll(DEFAULT_REQUEST_TAG);
	}

	protected void errorCommon(IRequestListener listener, ITag tag) {
		if (listener!=null) {
			stopRequest(tag);
			listener.onErrorResponse(tag.getRequstCode(),NetDataServerUtils.getErrorInfo(NetConstants.NetStatusCode.CODE_NO_NET));
		}
	}

	public void bindProgressLoad(IProgressLoad load) {
		unbindProgressLoad();
		this.mProgressLoadProcess = load ;
	}

	public void unbindProgressLoad() {
		if (mProgressLoadProcess!=null) {
			mProgressLoadProcess.destroy();
			mProgressLoadProcess = null;
		}
	}

	protected void startRequest(ITag tag) {
		if (mProgressLoadProcess!=null) {
			mProgressLoadProcess.startRequest(tag);
		}
	}

	protected void stopRequest(ITag tag) {
		if (mProgressLoadProcess!=null) {
			mProgressLoadProcess.stopRequest(tag);
		}
	}
}