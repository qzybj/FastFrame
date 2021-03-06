package earlll.com.testdemoall.core.ui.base;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.frame.fastframelibrary.utils.dataprocess.IntentUtils;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import com.frame.fastframelibrary.utils.view.WebViewUtil;
import butterknife.BindView;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.config.ConstantsKey;
import earlll.com.testdemoall.core.utils.WebViewUtilPlus;

/**
 * 用于普通的Url展示
 * WebViewUtil.goInnerWebView(con,"title","file:///android_asset/jsbridgedemo.html");
 */
public class BaseWebViewActivity extends BaseActivity {

    protected String url = "";

    @BindView(R.id.wv_show)
    protected WebView mWebView;

    @BindView(R.id.progressbar)
    protected ProgressBar mProgressBar;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_basewebview;
    }

    @Override
    public void initContentView(View view) {
        initWebView();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitlebarLeftDrawable(0,0);
        setTitlebarLeftText("取消");
        setTitlebarContent(R.string.app_name);
        setTitlebarRightVisibility(true);
        setTitlebarRightDrawable(R.mipmap.ic_launcher,0);

        mWebView.getSettings().setJavaScriptEnabled(true);
        if (mWebView != null && StringUtils.isNotEmpty(url)) {
            mWebView.loadUrl(url);
        }
        //设置title
        String title = IntentUtils.getString(getIntent(), ConstantsKey.KEY_TITLE);
        if (StringUtils.isNotEmpty(title)) {
            setTitlebarContent(title);
        }

        //设置展示特殊类型UI
        String type = IntentUtils.getString(getIntent(), WebViewUtilPlus.KEY_TYPE);
        if (StringUtils.isNotEmpty(type)) {

        }
        //设置加载Url
        url = IntentUtils.getString(getIntent(), ConstantsKey.KEY_URL);
        if(StringUtils.isEmpty(url)) {
            url = WebViewUtilPlus.COMMON_LOADURL;
        }
        if (StringUtils.isNotEmpty(url)) {
            loadUrl(url);
        }
    }

    protected void onClickTitleRight(View v) {
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
        });
        mWebView.setWebViewClient(new WebViewClient(){
        });
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
}