package netcore.api;

import android.content.Context;
import com.android.volley.Request;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import brady.com.appframe.R;
import netcore.network.HttpErrorListener;
import netcore.network.HttpSuccessListener;
import netcore.network.HttpUtils;
import netcore.network.MultiPartStack;
import netcore.network.NetStatusUtils;

/**
 * 负责api请求
 */
public class ApiClient {

    /**请求网络数据 - 开始*/
    public static final int MSG_WHAT_DATA_START = 0x7f00;// 32512
    /**请求网络数据 - 成功*/
    public static final int MSG_WHAT_DATA_DONE = 0x7f01;
    /**请求网络数据 - 取消*/
    public static final int MSG_WHAT_DATA_CANCEL = 0x7f02;

    /**
     * 获取数据错误码：无错误 字段或域定义：<code>GET_DATA_CODE_SUCCESS</code>
     */
    public static final int GET_DATA_CODE_SUCCESS = 0x7f20;// 32544

    /**
     * 获取数据错误码：无错误 字段或域定义：<code>GET_DATA_CODE_SUC</code>
     */
    public static final int GET_DATA_CODE_FAILURE = 0x7f201;

    /**
     * 获取数据错误码：无网络连接 字段或域定义：<code>GET_DATA_CODE_NO_NET</code>
     */
    public static final int GET_DATA_CODE_NO_NET = 0x7f23;

    private  String mApiBaseUrl = "";

    private  String mAppKey = "";

    private String mAppSecret = "";

    private String mApiMethod = "";

    private int mRequestMethod = Request.Method.POST;

    //基本参数
    private Map<String, String> mBaseParams = new HashMap<String, String>();

    //附加参数
    private Map<String, String> mAppendParams = new HashMap<String, String>();

    private Context mContext;

    public void setContext(Context context) {
        mContext = context;
    }

    /**
     * 构造函数
     * @param apiBaseUrl api基础地址
     * @param appKey  appkey
     * @param appKey  appSecret
     */
    public ApiClient(String apiBaseUrl, String appKey, String appSecret) {
        mApiBaseUrl = apiBaseUrl;
        mAppKey = appKey;
        mAppSecret = appSecret;
        mBaseParams.put("app_key", appKey);  //appkey是基础参数
    }

    /**
     * 设置API方法,该方法要在设计其他参数之前调用，否则当一个接口类连续调用方法时，会造成请求参数累加
     * @param apiMethod
     */
    public void setApiMethod(String apiMethod) {
        mAppendParams.clear();
        mAppendParams.put("method", apiMethod);
    }

    /**
     * 设置请求方法
     * @param requestMethod
     */
    public void setRequestMethod(int requestMethod) {
        mRequestMethod = requestMethod;
    }

    /**
     * 设置参数
     * @param key
     * @param value
     */
    public void setParam(String key, int value) {
        mAppendParams.put(key, String.valueOf(value));
    }

    /**
     * 清空参数
     */
    public void clearParam() {
        mAppendParams.clear();
    }

    /**
     * 设置基础参数
     * @param baseParams
     */
    public void setBaseParams(Map<String, String> baseParams) {
        mBaseParams.putAll(baseParams);
    }

    /**
     *
     * @param key
     * @param value
     */
    public void setParam(String key, float value ) {
        mAppendParams.put(key, String.valueOf(value));
    }

    /**
     * 设置字符串参数
     * @param key
     * @param value
     */
    public void setParam(String key, String value) {
        mAppendParams.put(key, value);
    }

    /**
     * 构建请求参数，比如加密签名等
     * @return
     */
    public Map<String, String> encodeReqParams() {
        mAppendParams.putAll(mBaseParams);
        String[] keys = mAppendParams.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < keys.length; i ++) {
            String key = keys[i];
            Object value = mAppendParams.get(key);
            if(value!=null&& StringUtils.isEmpty(value.toString())) {
                continue;  //值为空则不参与签名
            }
            if(str.toString() != "") {
                str.append("&");
            }
            str.append(key + "=" + value);
        }
        mAppendParams.put("sign", StringUtils.md5(mAppSecret + str.toString() + mAppSecret).toUpperCase());
        return mAppendParams;
    }


    /**
     * 异步请求
     * @param apiDelegate   成功返回的类
     * @param responseClass
     * @param <T>
     */
    public <T extends BaseResponse> void asyncReq(final ApiDelegate<T> apiDelegate, Class<T> responseClass) {
        if(NetStatusUtils.isNetConnected(mContext)) {
            HttpUtils.getInstance().init(mContext).
                    objectRequest(mRequestMethod, mApiBaseUrl, encodeReqParams(), responseClass,
                            new HttpSuccessListener<T>() {
                                @Override
                                public void onSuccess(T obj) {
                                    if (obj.code == TipBean.SUCCEED) {
                                        //成功返回
                                        apiDelegate.onSuccess(obj);
                                    } else {
                                        //接口返回错误信息
                                        TipBean tip = new TipBean(obj.code, obj.message);
                                        apiDelegate.onError(tip);
                                        LogUtils.e("code" + obj.code + " message:" + obj.message);
                                    }
                                }

                            },
                            new HttpErrorListener() {
                                @Override
                                public void onError(Object object) {
                                    super.onError(object);
                                    TipBean tip = new TipBean(TipBean.NET_ERROR, mContext.getResources().getString(R.string.not_net_connect_err));
                                    apiDelegate.onError(tip);
                                }
                            }
                    );
        }else{
            TipBean tip = new TipBean(TipBean.NET_ERROR, mContext.getResources().getString(R.string.not_net_connect_err));
            apiDelegate.onError(tip);
        }
    }

    /**
     * 上传文件
     * @param apiDelegate
     */
    public <T extends BaseResponse> void uploadFile(final Map<String, File> files, final ApiDelegate<T> apiDelegate, Class<T> responseClass) {
        if(NetStatusUtils.isNetConnected(mContext)) {
            HttpUtils.getInstance().init(mContext,new MultiPartStack()).
                    uploadRequest(mRequestMethod, mApiBaseUrl, encodeReqParams(), responseClass, files,
                            new HttpSuccessListener<T>() {
                                @Override
                                public void onSuccess(T obj) {
                                    if(obj.code == TipBean.SUCCEED) {
                                        apiDelegate.onSuccess(obj);
                                    }else {
                                        //接口返回错误信息
                                        TipBean tip = new TipBean(obj.code, obj.message);
                                        apiDelegate.onError(tip);
                                        LogUtils.e("code" + obj.code + " message:" + obj.message);
                                    }
                                }
                            },
                            new HttpErrorListener(){
                                @Override
                                public void onError(Object object) {
                                    TipBean tip = new TipBean(TipBean.NET_ERROR, mContext.getResources().getString(R.string.not_net_connect_err));
                                    apiDelegate.onError(tip);
                                }
                            });
        }else{
            TipBean tip = new TipBean(TipBean.NET_ERROR, mContext.getResources().getString(R.string.not_net_connect_err));
            apiDelegate.onError(tip);
        }
    }
}
