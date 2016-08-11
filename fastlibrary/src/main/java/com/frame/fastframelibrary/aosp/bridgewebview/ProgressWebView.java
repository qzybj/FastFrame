package com.frame.fastframelibrary.aosp.bridgewebview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.widget.AbsoluteLayout;
import android.widget.ProgressBar;

import com.frame.fastframelibrary.R;
import com.frame.fastframelibrary.aosp.bridgewebview.interfaces.IJSBridgeBean;
import com.frame.fastframelibrary.aosp.bridgewebview.bridgeimpl.JSBridgeManager;
import com.frame.fastframelibrary.aosp.bridgewebview.interfaces.IBridgeCallBack;
import com.github.lzyzsd.jsbridge.BridgeWebView;

/**
 * 一个带progressBar进度样式的webview，<br/>
 * 实现和jsbridge交互实现方法在BindBridgeListener里添加对应方法
 * */
public class ProgressWebView extends BridgeWebView{
    private ProgressBar progressbar;
    private JSBridgeManager mJSBridgeManager;

    private Handler bridgeHandler = new JSBridgeHandler();
    private IBridgeCallBack mIBridgeCallBack;

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new AbsoluteLayout.LayoutParams(AbsoluteLayout.LayoutParams.MATCH_PARENT, 10, 0, 0));
        Drawable drawable = context.getResources().getDrawable(R.drawable.progresswebview_bar_states);
        progressbar.setProgressDrawable(drawable);
        addView(progressbar);
        //setWebViewClient(new WebViewClient(){});//不能再设置webview的WebViewClient
        setWebChromeClient(new WebChromeClient());
        getSettings().setSupportZoom(true);
        getSettings().setBuiltInZoomControls(true);
        initJsBridge(context);
    }

    private void initJsBridge(Context context) {
        mJSBridgeManager = new JSBridgeManager(this,bridgeHandler);
    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(View.GONE);
            } else {
                if (progressbar.getVisibility() == View.GONE){
                    progressbar.setVisibility(View.VISIBLE);
                }
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        AbsoluteLayout.LayoutParams lp = (AbsoluteLayout.LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    /**
     * 返回JSBridge的管理器
     * @return
     */
    public JSBridgeManager getJSBridgeManager(){
        return mJSBridgeManager;
    }

    /**
     * 设置JSBridge的接口回调
     * @param callBack
     */
    public void setJSBridgeCallBackListener(IBridgeCallBack callBack) {
        this.mIBridgeCallBack = callBack;
    }

    /**解析处理JS调用客户端方法,发送过来的message*/
    private void paserJsCallApp(IJSBridgeBean bean) {
        if(mIBridgeCallBack!=null){
            mIBridgeCallBack.paserJsCallApp(bean);
        }
    }
    /**解析处理调用JS方法之后回调客户端的message*/
    private void paserCallJsCallback(String bean) {
        if(mIBridgeCallBack!=null){
            mIBridgeCallBack.paserCallJsCallback(bean);
        }
    }
    /**解析处理未知的数据传递*/
    private void paserOtherCallback(Object bean) {
        if(mIBridgeCallBack!=null){
            mIBridgeCallBack.paserOtherCallback(bean);
        }
    }

    /**与JsBridge交互用Handler */
    private class JSBridgeHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case JSBridgeConstants.HANDLE_MSGCODE_JSCALLAPP://JS调用客户端方法
                    if(msg.obj instanceof IJSBridgeBean){
                        IJSBridgeBean bean = (IJSBridgeBean) msg.obj;
                        paserJsCallApp(bean);
                    }
                    break;
                case JSBridgeConstants.HANDLE_MSGCODE_CALLBACK_CALLJS://调用JS方法之后回调客户端
                    if(msg.obj instanceof String){
                        String bean = (String) msg.obj;
                        paserCallJsCallback(bean);
                    }
                    break;
                default:
                    paserOtherCallback(msg.obj);
                    break;
            }
        }
    }
}