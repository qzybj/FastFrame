package earlll.com.testdemoall.module.webviewdemo.ui;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.dataprocess.RandomUtils;
import com.frame.fastframelibrary.utils.view.WebViewUtil;

import java.io.File;

import earlll.com.testdemoall.core.ui.base.BaseWebViewActivity;
import earlll.com.testdemoall.module.webviewdemo.utils.H5HttpHijackUtils;

/**
 * Created by ZhangYuanBo on 2016/7/28.
 * html页面打开时，针对http劫持逻辑的处理
 */
public class InterceptUrlActivity extends BaseWebViewActivity {

    public static String testUrl[] = {
            "http://blog.csdn.net/jiangxinyu/article/details/7885518/",
            "https://www.baidu.com/",
            "http://my.oschina.net/zxcholmes/blog/596192",
            "https://github.com/search",
            "http://www.open-open.com/lib/view/open1416664070023.html",
            "http://www.cnblogs.com/devinzhang/archive/2012/02/29/2373729.html"};

    public void initWebView() {
        WebViewUtil.initWebView(mWebView);
        mWebView.setWebChromeClient(new WebChromeClient() {});
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onLoadResource(WebView view, String url) {
                //LogUtils.d(H5HttpHijackUtils.TAG,"onLoadResource = "+url);
                super.onLoadResource(view, url);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if(H5HttpHijackUtils.isAllowInterceptUrl()){
                    WebResourceResponse hijackResponse = H5HttpHijackUtils.checkHijackUrl(url);
                    if(hijackResponse!=null){
                        return hijackResponse;
                    }
                }
                LogUtils.d(H5HttpHijackUtils.TAG,"shouldInterceptRequest(正常加载) = "+url);
                return super.shouldInterceptRequest(view, url);
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        initInterceptUrl();
    }

    private void initInterceptUrl() {
        H5HttpHijackUtils.clearWhiteList();
        String whiteList[] = {
                "http://blog.csdn.net",
                "https://www.baidu.com/",
                "http://my.oschina.net",
                };
        H5HttpHijackUtils.setResponse(true,H5HttpHijackUtils.VALUE_HIJACK_INTERCEPT_ALLOW,whiteList);
    }

    @Override
    protected void onClickTitleRight(View v) {
        loadUrl(testUrl[RandomUtils.getRandom(testUrl.length-1)]);
    }

    @Override
    public void onClickTitleLeft(View v) {
        File file = H5HttpHijackUtils.getInterceptUrlFile();
        if(file!=null&&file.exists()){
            LogUtils.d(H5HttpHijackUtils.TAG,"getInterceptUrlFile ="+file.getAbsolutePath());
        }else{
            LogUtils.d(H5HttpHijackUtils.TAG,"getInterceptUrlFile =file is null or not exists ");
        }
    }
}
