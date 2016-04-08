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
import com.frame.fastframe.module.common.constant.CommonConstants;
import com.frame.fastframelibrary.utils.IntentUtils;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.StringUtils;
import com.frame.fastframelibrary.utils.WebViewUtil;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import org.xutils.view.annotation.ViewInject;

/**
 * 用于普通的Url展示
 * WebViewUtil.goInnerWebView(con,"title","file:///android_asset/jsbridgedemo.html");
 */
public class BaseWebViewActivity extends BaseActivity {
    private final String TAG = BaseWebViewActivity.this.getClass().getName();

    public final static int RESULT_CODE_UPIMAGE = 0x00101;

    /**是否清除缓存*/
    public static boolean isCleanWebViewCache = false;

    protected String url = "";

    ValueCallback<Uri> mUploadMessage;

    @ViewInject(R.id.webview)
    protected BridgeWebView mWebView;

    @ViewInject(R.id.progressbar)
    protected ProgressBar mProgressBar;

    @Override
    public int getLayoutResouceId() {
        return R.layout.activity_basewebview;
    }

    @Override
    public void initContentView(View view) {
        mProgressBar.setMax(100);
        initWebView();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitlebarLeftDrawable(0,0);
        setTitlebarLeftText("取消");
        setTitlebarContent(R.string.app_name);
        setTitlebarRightVisibility(true);
        setTitlebarRightDrawable(R.mipmap.ic_launcher,0);

        String title = IntentUtils.getIntentStr(getIntent(), CommonConstants.KEY_TITLE);
        url = IntentUtils.getIntentStr(getIntent(), CommonConstants.KEY_URL);
        if (!StringUtils.isEmpty(title)) {
            setTitlebarContent(title);
        }
        if (!StringUtils.isEmpty(url)) {
            loadUrl(url);
        }
    }

    protected void onClickTitleRight(View v) {
        callJsMethod_shared("点击右上角，触发js的相关功能");
    }

    @Override
    protected void clickEvent(View v) {

    }

    public WebView getWebView() {
        return mWebView;
    }
    public void loadUrl(String url) {
        LogUtils.log(url);
        if (mWebView != null) {
            mWebView.loadUrl(url);
        }
    }

    public void initWebView() {
        WebViewUtil.initWebView(mWebView);
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

        //在html页面里 需要注册 jsBridgeFromH5方法
        mWebView.registerHandler("jsBridgeFromH5", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogUtils.i(TAG, "handler = jsBridge, receive " + data);
                function.onCallBack("jsBridge is call");
            }
        });
        //在html页面里 需要注册 jsBridgeFromH5方法
        mWebView.registerHandler("callNativeShared", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogUtils.i(TAG, "handler = callNativeShared, receive " + data);
                //调用分享操作
                function.onCallBack("callNativeShared is call");
            }
        });
        //mWebView.setWebViewClient(new BasicWebViewClient(this));//自定义WebView内部已经实现，不用再处理
        if (isCleanWebViewCache) {
            WebViewUtil.clearCache(mWebView);
        }
    }

    /**调用js方法*/
    private void callJsMethod(String command){
        mWebView.callHandler("callJsMethod", command, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {

            }
        });
    }
    /**调用js方法*/
    private void callJsMethod_shared(String command){
        mWebView.callHandler("callJsMethod_shared", command, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {

            }
        });
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
}