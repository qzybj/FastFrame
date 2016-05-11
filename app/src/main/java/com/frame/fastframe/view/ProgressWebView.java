package com.frame.fastframe.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.frame.fastframe.R;
import com.frame.fastframe.ui.base.BaseWebViewActivity;
import com.frame.fastframelibrary.utils.GsonUtils;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.WebViewUtil;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.google.gson.JsonObject;

public class ProgressWebView extends BridgeWebView {
    private final String TAG = ProgressWebView.this.getClass().getName();
    /**JSBRIDGE供H5调用的原生方法名*/
    public final static String JSBRIDGE_METHOD4NATIVE="jsbridge_method4native";
    /**JSBRIDGE供原生调用的H5方法名*/
    public final static String JSBRIDGE_METHOD4H5="jsbridge_method4h5";
    private ProgressBar progressbar;
    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 10, 0, 0));
        Drawable drawable = context.getResources().getDrawable(R.drawable.progress_bar_states);
        progressbar.setProgressDrawable(drawable);
        addView(progressbar);
        //setWebViewClient(new WebViewClient(){});
        setWebChromeClient(new WebChromeClient());
        getSettings().setSupportZoom(true);
        getSettings().setBuiltInZoomControls(true);
    }

    /**
     * 初始化Js调用的句柄
     */
    public void initJsHandler(final Context context) {
        //注册被H5调用事件 - 通用支持事件(在html页面里 需要注册)
        this.registerHandler(JSBRIDGE_METHOD4NATIVE, new BridgeHandler() {
            @Override
            public void handler(String jsonStr, CallBackFunction function) {
                //进行跳转
                JsonObject jsonObj = GsonUtils.toJsonObject(jsonStr);
                String returnStr ="";
                if(jsonObj!=null&&jsonObj.isJsonNull()){
                    if (jsonObj.has(WebViewUtil.KEY_URL)) {
                        //打开支持分享的页面

                    }
                }
                function.onCallBack(returnStr);
            }
        });
        //注册被H5调用事件 - (在html页面里 需要注册)
        this.registerHandler("goUrlWithShare", new BridgeHandler() {
            @Override
            public void handler(String jsonStr, CallBackFunction function) {
                //进行跳转
                JsonObject jsonObj=  GsonUtils.toJsonObject(jsonStr);
                if (jsonObj.has(WebViewUtil.KEY_URL)) {
                    Intent intent = new Intent(context, BaseWebViewActivity.class);
                    intent.putExtra("loadUrl", jsonObj.get(WebViewUtil.KEY_URL).getAsString());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                function.onCallBack("");
            }
        });
    }

    /**调用js方法
     * @param jsonCommand  json String 格式
     */
    public void callJsMethod(String jsonCommand){
        this.callHandler(JSBRIDGE_METHOD4H5,jsonCommand, new CallBackFunction() {
            @Override
            public void onCallBack(String jsonStr) {

            }
        });
    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
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
}