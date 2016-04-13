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
import com.frame.fastframelibrary.utils.WebViewUtil;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.google.gson.JsonObject;

public class ProgressWebView extends BridgeWebView {
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
        this.registerHandler("goUrlWithShare", new BridgeHandler() {
            @Override
            public void handler(String strData, CallBackFunction function) {
                //进行跳转
                JsonObject jsonObj=  GsonUtils.toJsonObject(strData);
                if (jsonObj.has(WebViewUtil.KEY_URL)) {
                    Intent intent = new Intent(context, BaseWebViewActivity.class);
                    intent.putExtra("url", jsonObj.get(WebViewUtil.KEY_URL).getAsString());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                function.onCallBack("");
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