package com.frame.fastframe.module.html5.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import com.frame.fastframe.R;
import com.frame.fastframe.module.common.constant.ConstantsCommonKey;
import com.frame.fastframe.module.html5.bridgewebView.ProgressWebView;
import com.frame.fastframe.module.html5.bridgewebView.interfaces.IJSBridgeBean;
import com.frame.fastframe.module.html5.ui.base.BaseWebViewActivity;
import com.frame.fastframe.utils.WebViewUtilPlus;
import com.frame.fastframelibrary.utils.dataprocess.IntentUtils;
import com.frame.fastframelibrary.utils.view.WebViewUtil;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by ZhangYuanBo on 2016/5/24.
 * 通用WebView界面,用于普通的Url展示<BR/>
 * WebViewUtil.goInnerWebView(con,"title","file:///android_asset/jsbridgedemo.html");<BR/>
 */
public class CommonWebViewActivity extends BaseWebViewActivity{

    private final String TAG = this.getClass().getName();
    public final static int RESULT_CODE_UPIMAGE = 0x00101;
    /**是否清除缓存*/
    public static boolean isCleanWebViewCache = false;

    protected String loadUrl = WebViewUtilPlus.COMMON_LOADURL;

    /**图片的处理 */
    protected ValueCallback<Uri> mUploadMessage;

    @ViewInject(R.id.webview)
    private ProgressWebView mWebView;


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
        setWebView(mWebView);
        initWebView();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitlebarContent(IntentUtils.getIntentStr(getIntent(), ConstantsCommonKey.KEY_TITLE));//设置title
        initCustomUI(IntentUtils.getIntentStr(getIntent(), ConstantsCommonKey.KEY_TYPE)); //设置展示特殊类型UI
        loadUrl(IntentUtils.getIntentStr(getIntent(), ConstantsCommonKey.KEY_URL)); //设置加载Url
    }

    /**
     * 根据不同的类型key，来初始化不同的UI
     * @param typeKey 类型定义key
     */
    private void initCustomUI(String typeKey) {}

    protected void onClickTitleRight(View v) {
        callJsMethod("test",null);
    }

    public void initWebView() {
        super.initWebView();
        if(getWebView()!=null){
            mWebView.setWebChromeClient(new WebChromeClient() {
                public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType, String capture) {
                    this.openFileChooser(uploadMsg);
                }
                public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType) {
                    this.openFileChooser(uploadMsg);
                }
                public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                    mUploadMessage = uploadMsg;
                    pickFile();
                }
            });
            //mWebView.setWebViewClient();//自定义WebView内部已经实现，不用再处理
            if (isCleanWebViewCache) {
                WebViewUtil.clearCache(mWebView);
            }
        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void sendMessage(int what, String json) {

    }

    public void pickFile() {
        Intent chooserIntent = new Intent(Intent.ACTION_GET_CONTENT);
        chooserIntent.setType("image/*");
        startActivityForResult(chooserIntent, RESULT_CODE_UPIMAGE);
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

    @Override
    public void paserJsCallApp(IJSBridgeBean bean) {

    }

    @Override
    public void paserCallJsCallback(String json) {

    }

    @Override
    public void paserOtherCallback(Object json) {

    }
}
