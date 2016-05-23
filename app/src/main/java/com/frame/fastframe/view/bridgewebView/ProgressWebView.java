package com.frame.fastframe.view.bridgewebView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.ProgressBar;
import com.frame.fastframe.R;
import com.frame.fastframe.view.bridgewebView.bridgeimpl.BindBridgeListener;
import com.frame.fastframe.view.bridgewebView.interfaces.IBridgeBean;
import com.github.lzyzsd.jsbridge.BridgeWebView;

/**
 * 一个带progressBar进度样式的webview，<br/>
 * 实现和jsbridge交互实现方法在BindBridgeListener里添加对应方法
 * */
public class ProgressWebView extends BridgeWebView {

    private ProgressBar progressbar;
    private BindBridgeListener mBindBridgeListener;
    public Handler bridgeHandler = new BridgeHandler();

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 10, 0, 0));
        Drawable drawable = context.getResources().getDrawable(R.drawable.progress_bar_states);
        progressbar.setProgressDrawable(drawable);
        addView(progressbar);
        //setWebViewClient(new WebViewClient(){});//不能再设置webview的WebViewClient
        setWebChromeClient(new WebChromeClient());
        getSettings().setSupportZoom(true);
        getSettings().setBuiltInZoomControls(true);
        initJsBridge(context);
    }

    private void initJsBridge(Context context) {
        mBindBridgeListener = new BindBridgeListener(this,bridgeHandler);
    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE){
                    progressbar.setVisibility(VISIBLE);
                }
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    /**解析处理JS调用客户端方法,发送过来的message*/
    private void paserJsCallApp(IBridgeBean bean) {

    }
    /**解析处理调用JS方法之后回调客户端的message*/
    private void paserCallJsCallback(Message msg) {

    }

    /**与JsBridge交互用Handler */
    private class BridgeHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
        }
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BridgeConstants.HANDLE_MSGCODE_JSCALLAPP://JS调用客户端方法
                    if(msg.obj instanceof IBridgeBean){
                        IBridgeBean bean = (IBridgeBean) msg.obj;
                        paserJsCallApp(bean);
                    }
                    break;
                case BridgeConstants.HANDLE_MSGCODE_CALLBACK_CALLJS://调用JS方法之后回调客户端
                    if(msg.obj instanceof String){
                        String bean = (String) msg.obj;
                        paserCallJsCallback(msg);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}