package com.frame.fastframe.module.html5.interfaces;

/**用于Bind BaseWebViewActivity,必须要实现的一些方法 */
public interface IWebView4Activity {
	/**WebView	-	加载指定URL网址*/
    void loadUrl(String url);
    /**WebView	-	WebView回退*/
    boolean goBack();
    /**WebView	-	初始化*/
    void initWebView();
}