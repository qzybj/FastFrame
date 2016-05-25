package com.frame.fastframe.module.html5.ui.base;

import android.view.KeyEvent;
import android.webkit.WebView;

import com.frame.fastframe.module.html5.bridgewebView.JSBridgeConstants;
import com.frame.fastframe.module.html5.bridgewebView.ProgressWebView;
import com.frame.fastframe.module.html5.bridgewebView.bean.JSBridgeBean;
import com.frame.fastframe.module.html5.bridgewebView.interfaces.IBridgeCallBack;
import com.frame.fastframe.module.html5.bridgewebView.interfaces.IJSBridgeBean;
import com.frame.fastframe.module.html5.interfaces.IWebView4Activity;
import com.frame.fastframe.ui.base.BaseActivity;
import com.frame.fastframelibrary.utils.StringUtils;
import com.frame.fastframelibrary.utils.WebViewUtil;

import java.util.LinkedHashMap;

/**
 * BaseWebView基类,继承该类需要给 mWebView赋值
 */
public abstract class BaseWebViewActivity extends BaseActivity implements IBridgeCallBack,IWebView4Activity{

    private final String TAG = this.getClass().getName();

    private ProgressWebView mWebView;

    public WebView getWebView() {
        return mWebView;
    }

    /**继承之后一定要通过该方法设定WebView*/
    public void setWebView(ProgressWebView webView) {
         this.mWebView = webView;
    }

    public void loadUrl(String url) {
        if (getWebView() != null && StringUtils.isNotEmpty(url)) {
            mWebView.loadUrl(url);
        }
    }

    public void initWebView() {
        if(getWebView()!=null){
            WebViewUtil.initWebView(mWebView);
            mWebView.setJSBridgeCallBackListener(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WebViewUtil.destroyWebView(mWebView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (!goBack()) {
                finish();
            }
        }
        return false;
    }

    public boolean goBack() {
        if(getWebView()!=null){
            boolean res = mWebView.canGoBack();
            if (res) {
                mWebView.goBack();
            }
            return res;
        }
        return false;
    }

    /**调用JS方法*/
    public void callJsMethod(String methodName,LinkedHashMap<String ,String> argsMap) {
        if(getWebView()!=null&&mWebView.getJSBridgeManager()!=null){
            JSBridgeBean bean = new JSBridgeBean();
            bean.setHandlerName(JSBridgeConstants.JSBRIDGE_HANDLERNAME_NATIVEJS);
            bean.setMethodName(methodName);
            bean.setArgsMap(argsMap);
            mWebView.getJSBridgeManager().callJsMethod(bean);
        }
    }

    @Override
    public void paserJsCallApp(IJSBridgeBean bean) {

    }

    @Override
    public void paserCallJsCallback(String bean) {

    }

    @Override
    public void paserOtherCallback(Object bean) {

    }
}