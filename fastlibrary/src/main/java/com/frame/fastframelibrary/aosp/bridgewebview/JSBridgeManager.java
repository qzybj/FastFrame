package com.frame.fastframelibrary.aosp.bridgewebview;

import android.os.Handler;
import android.os.Message;
import com.frame.fastframelibrary.aosp.bridgewebview.JSBridgeConstants;
import com.frame.fastframelibrary.aosp.bridgewebview.bean.JSBridgeBean;
import com.frame.fastframelibrary.aosp.bridgewebview.interfaces.IJSBridgeBean;
import com.frame.fastframelibrary.utils.json.GsonUtils;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

/**
 * Created by ZhangYuanBo on 2016/5/20.
 * Bind  Bridge  Method</br>
 * 绑定H5与app的调用及回调方法都在该类中实现
 */
public class JSBridgeManager {
    /**用于事件注册Bind的webview*/
    public BridgeWebView mWebView;
    /**webview用于接收传值用*/
    public Handler mWebViewHandler;
    /**当前页面用于js回调用*/
    private BaseJSBridgeHandler mBridgeHandler ;

    public JSBridgeManager(BridgeWebView webView, Handler handler){
        this.mWebView = webView;
        this.mWebViewHandler = handler;
        this.mBridgeHandler= new BaseJSBridgeHandler(mWebViewHandler);
        registerDefaultJsBridge();
    }

    /** 初始化Js调用Native的句柄(注册被调用通用方法)*/
    private void registerDefaultJsBridge() {
        //注册被H5调用事件 - 通用支持事件(在html页面里 需要对应注册)
        registerJsBridge(JSBridgeConstants.JSBRIDGE_HANDLERNAME_JS2NATIVE);
    }

    /**
     * 外部调用，新增Js调用Native的句柄(注册被调用新方法)
     * @param handlerName
     */
    public void register4JSCall(String handlerName) {
        if(isSupportWebview(handlerName)){
            //单独注册指定handlerName的事件
            if((handlerNameCheck(handlerName))){
                registerJsBridge(handlerName);
            }
        }
    }

    /**注册被调用句柄前的检查*/
    private boolean handlerNameCheck(String handlerName) {
        if(JSBridgeConstants.JSBRIDGE_HANDLERNAME_JS2NATIVE.equalsIgnoreCase(handlerName)){//禁止占用默认通用句柄
            return false;
        }
        return true;
    }

    private void registerJsBridge(String handlerName) {
        //单独注册指定handlerName的事件
        mWebView.registerHandler(handlerName,mBridgeHandler);
    }


    /**
     * Native调用js方法
     * @param bridge
     */
    public void callJsMethod(IJSBridgeBean bridge){
        if(isSupportWebview(bridge)){
            String jsonCommand ="";
            try {
                jsonCommand = GsonUtils.toJson(bridge);
            } catch (Exception e) {
                LogUtils.e(e);
            }
            if(StringUtils.isNotEmpty(bridge.getHandlerName())){//Native的句柄不能为空
                mWebView.callHandler(bridge.getHandlerName(),jsonCommand,
                        new CallBackFunction() {
                            @Override
                            public void onCallBack(String jsonStr) {
                                sendMsgCallbackCallJs(jsonStr);
                            }
                        });
            }
        }
    }

    /**JS调用客户端方法*/
    private void sendMsgJsCallApp(IJSBridgeBean bridge){
        sendHandlerMsg4Bridge(JSBridgeConstants.HANDLE_MSGCODE_JSCALLAPP,bridge);
    }
    /**调用JS方法之后回调客户端*/
    private void sendMsgCallbackCallJs(Object obj){
        sendHandlerMsg4Bridge(JSBridgeConstants.HANDLE_MSGCODE_CALLBACK_CALLJS,obj);
    }

    /**
     * 发送封装过的message消息
     * @param type  1.JSBridgeConstants.HANDLE_MSGCODE_JSCALLAPP
     *              2. JSBridgeConstants.HANDLE_MSGCODE_CALLBACK_CALLJS
     * @param obj
     */
    private void sendHandlerMsg4Bridge(int type,Object obj){
        if(obj!=null&&mWebViewHandler!=null){
            Message msg = mWebViewHandler.obtainMessage();
            msg.what = type;
            msg.obj = obj;
            mWebViewHandler.sendMessage(msg);
        }
    }

    /**与JsBridge交互用Handler */
    private class BaseJSBridgeHandler implements com.github.lzyzsd.jsbridge.BridgeHandler{
        public Handler mWebViewPassHandler;
        BaseJSBridgeHandler(Handler handler){
            mWebViewPassHandler = handler;
        }
        @Override
        public void handler(String jsonStr, CallBackFunction function) {
            JSBridgeBean bridge = GsonUtils.toObject(jsonStr, JSBridgeBean.class);
            if(bridge!=null&& mWebViewPassHandler !=null){
                sendMsgJsCallApp(bridge);
            }else{
                function.onCallBack(jsonStr);
            }
        }
    }

    private boolean isSupportWebview(){
        if(mWebView!=null){
            return true;
        }
        return false;
    }
    private boolean isSupportWebview(IJSBridgeBean bridge){
        if(isSupportWebview()&&bridge!=null){
            return true;
        }
        return false;
    }
    private boolean isSupportWebview(String handlerName){
        if(isSupportWebview()&&StringUtils.isNotEmpty(handlerName)){
            return true;
        }
        return false;
    }
}