package com.frame.fastframelibrary.aosp.bridgewebview;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;

/**
 * Created by ZhangYuanBo on 2016/8/6.
 */
public class CBridgeWebViewClient extends BridgeWebViewClient{
    private WebViewClient mWebViewClient;
    public CBridgeWebViewClient(BridgeWebView webView) {
        super(webView);
    }
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(mWebViewClient!=null){
            mWebViewClient.shouldOverrideUrlLoading(view,url);
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if(mWebViewClient!=null){
            mWebViewClient.onPageStarted(view, url, favicon);
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if(mWebViewClient!=null){
            mWebViewClient.onPageFinished(view, url);
        }
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        if(mWebViewClient!=null){
            mWebViewClient.onReceivedError(view, errorCode, description, failingUrl);
        }
    }

    public void setCustomWebChromeClient(WebViewClient webViewClient){
        this.mWebViewClient = webViewClient;
    }
}
