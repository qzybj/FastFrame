package com.frame.fastframe.module.html5.interfaces;

import android.app.Activity;
import android.webkit.WebView;


/**用于Bind H5CallNativeBindIMPL的activity,必须要实现的一些方法 */
public interface IWebView4Activity {
	/**WebView	-	加载指定URL网址*/
    void loadUrl(String url);
    /**WebView	-	WebView回退*/
    boolean goBack();
    /**WebView	-	初始化*/
    void initWebView();
    /**用于Bind	-	获取上下文*/
    Activity getActivity();
    /**用于Bind	-	获取WebView*/
    WebView getWebView();

    void sendMessage(int what);
    void sendMessage(int what, String json);
}