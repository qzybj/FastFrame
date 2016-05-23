package com.frame.fastframe.view.bridgewebView.bridgeimpl;

import android.os.Handler;
import android.os.Message;
import com.frame.fastframe.view.bridgewebView.bean.BridgeBean;
import com.frame.fastframe.view.bridgewebView.BridgeConstants;
import com.frame.fastframe.view.bridgewebView.interfaces.IBridgeBean;
import com.frame.fastframelibrary.utils.GsonUtils;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.StringUtils;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;


/**
 * Created by ZhangYuanBo on 2016/5/20.
 * Bind  Bridge  Method
 */
public class BindBridgeListener {

    private BaseBridgeHandler mBridgeHandler ;
    public Handler mHandler;

    public BindBridgeListener(BridgeWebView webView,Handler handler){
        this.mHandler = handler;
        this.mBridgeHandler= new BaseBridgeHandler(mHandler);
        registerJsBridge(webView);
    }

    /**
     * 初始化Js调用Native的句柄(注册被调用方法)
     * @param webView
     */
    private void registerJsBridge(BridgeWebView webView) {
        if(webView==null){
            return;
        }
        //注册被H5调用事件 - 通用支持事件(在html页面里 需要注册)
        webView.registerHandler(BridgeConstants.JSBRIDGE_METHOD_JS2APP,mBridgeHandler);
        //也可以单独注册指定handlerName的事件
    }

    /**
     * Native调用js方法
     * @param webView
     * @param bridge
     * @param receiveHandler
     */
    public void callJsMethod(BridgeWebView webView,IBridgeBean bridge,final Handler receiveHandler){
        if(webView!=null&& bridge!=null){
            String jsonCommand ="";
            try {
                jsonCommand = GsonUtils.toJson(bridge);
            } catch (Exception e) {
                LogUtils.e(e);
            }
            if(StringUtils.isNotEmpty(bridge.getHandlerName())){
                webView.callHandler(
                        bridge.getHandlerName(),
                        jsonCommand,
                        new CallBackFunction() {
                            @Override
                            public void onCallBack(String jsonStr) {
                                sendMsgCallbackCallJs(receiveHandler,jsonStr);
                            }
                        });
            }
        }
    }

    /**与JsBridge交互用Handler */
    private class BaseBridgeHandler implements com.github.lzyzsd.jsbridge.BridgeHandler{
        public Handler bridgeHandler;
        BaseBridgeHandler(Handler handler){
            bridgeHandler = handler;
        }
        @Override
        public void handler(String jsonStr, CallBackFunction function) {
            BridgeBean bridge = GsonUtils.toObject(jsonStr, BridgeBean.class);
            if(bridge!=null&&bridgeHandler!=null){
                sendMsgJsCallApp(bridgeHandler,bridge);
            }else{
                function.onCallBack(jsonStr);
            }
        }
    }

    /**JS调用客户端方法*/
    private void sendMsgJsCallApp(Handler handler,IBridgeBean bridge){
        sendHandlerMsg4Bridge(BridgeConstants.HANDLE_MSGCODE_JSCALLAPP,handler,bridge);
    }
    /**调用JS方法之后回调客户端*/
    private void sendMsgCallbackCallJs(Handler handler,Object obj){
        sendHandlerMsg4Bridge(BridgeConstants.HANDLE_MSGCODE_CALLBACK_CALLJS,handler,obj);
    }

    /**
     * 发送封装过的message消息
     * @param type  1.BridgeConstants.HANDLE_MSGCODE_JSCALLAPP  2. HANDLE_MSGCODE_CALLBACK_CALLJS
     * @param handler
     * @param obj
     */
    private void sendHandlerMsg4Bridge(int type, Handler handler, Object obj){
        if(obj!=null&&handler!=null){
            Message msg = handler.obtainMessage();
            msg.what = type;
            msg.obj = obj;
            handler.sendMessage(msg);
        }
    }
}