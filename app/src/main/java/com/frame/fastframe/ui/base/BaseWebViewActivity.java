package com.frame.fastframe.ui.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import com.frame.fastframe.R;
import com.frame.fastframe.utils.WebViewUtilPlus;
import com.frame.fastframe.view.bridgewebView.JSBridgeConstants;
import com.frame.fastframe.view.bridgewebView.ProgressWebView;
import com.frame.fastframe.view.bridgewebView.bean.JSBridgeBean;
import com.frame.fastframe.view.bridgewebView.interfaces.IJSBridgeBean;
import com.frame.fastframe.view.bridgewebView.interfaces.IBridgeCallBack;
import com.frame.fastframelibrary.utils.IntentUtils;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.StringUtils;
import com.frame.fastframelibrary.utils.WebViewUtil;

import org.xutils.view.annotation.ViewInject;

import java.util.LinkedHashMap;

/**
 * BaseWebView基类,继承该类需要给 mWebView赋值
 */
public class BaseWebViewActivity extends BaseActivity implements IBridgeCallBack {
    private final String TAG = BaseWebViewActivity.this.getClass().getName();

    public final static int RESULT_CODE_UPIMAGE = 0x00101;

    /**是否清除缓存*/
    public static boolean isCleanWebViewCache = false;

    protected String loadUrl = WebViewUtilPlus.COMMON_LOADURL;

    ValueCallback<Uri> mUploadMessage;

    @ViewInject(R.id.webview)
    protected ProgressWebView mWebView;

    @ViewInject(R.id.progressbar)
    protected ProgressBar mProgressBar;

    @Override
    public int getLayoutResouceId() {
        return R.layout.activity_basewebview;
    }

    @Override
    public void initContentView(View view) {
        setTitlebarLeftDrawable(0,0);
        setTitlebarLeftText("取消");
        setTitlebarContent(R.string.app_name);
        setTitlebarRightVisibility(true);
        setTitlebarRightDrawable(R.mipmap.ic_launcher,0);
        initWebView();
        if(mProgressBar!=null){
            mProgressBar.setMax(100);
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitlebarContent(IntentUtils.getIntentStr(getIntent(), WebViewUtilPlus.KEY_TITLE));//设置title
        initCustomUI(IntentUtils.getIntentStr(getIntent(), WebViewUtilPlus.KEY_TYPE)); //设置展示特殊类型UI
        loadUrl(IntentUtils.getIntentStr(getIntent(), WebViewUtilPlus.KEY_URL)); //设置加载Url
    }

    /**
     * 根据不同的类型key，来初始化不同的UI
     * @param typeKey 类型定义key
     */
    private void initCustomUI(String typeKey) {

    }

    protected void onClickTitleRight(View v) {
        callJsMethod("test",null);
    }

    @Override
    protected void clickEvent(View v) {

    }
    public WebView getWebView() {
        return mWebView;
    }
    public void loadUrl(String url) {
        LogUtils.log(url);
        if (mWebView != null && StringUtils.isNotEmpty(url)) {
            mWebView.loadUrl(url);
        }
    }

    public void initWebView() {
        if(mWebView!=null){
            WebViewUtil.initWebView(mWebView);
            mWebView.setJSBridgeCallBackListener(this);
            mWebView.setWebChromeClient(new WebChromeClient() {
                @SuppressWarnings("unused")
                public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType, String capture) {
                    this.openFileChooser(uploadMsg);
                }

                @SuppressWarnings("unused")
                public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType) {
                    this.openFileChooser(uploadMsg);
                }

                public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                    mUploadMessage = uploadMsg;
                    pickFile();
                }
            });
            //mWebView.setWebViewClient(new BasicWebViewClient(this));//自定义WebView内部已经实现，不用再处理
            if (isCleanWebViewCache) {
                WebViewUtil.clearCache(mWebView);
            }


        }
    }

    public void pickFile() {
        Intent chooserIntent = new Intent(Intent.ACTION_GET_CONTENT);
        chooserIntent.setType("image/*");
        startActivityForResult(chooserIntent, RESULT_CODE_UPIMAGE);
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

    protected final void showProgressBar(final int progress) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mProgressBar.getVisibility() != View.VISIBLE) {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
                mProgressBar.setProgress(progress);
            }
        });
    }

    protected final void dissProgressBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mProgressBar.getVisibility() == View.VISIBLE) {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    public boolean goBack() {
        boolean res = mWebView.canGoBack();
        if (res) {
            mWebView.goBack();
        }
        return res;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == RESULT_CODE_UPIMAGE) {
            if (null == mUploadMessage){
                return;
            }
            Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        }
    }

    public void callJsMethod(String methodName,LinkedHashMap<String ,String> argsMap) {
        if(mWebView!=null&&mWebView.getJSBridgeManager()!=null){
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
}