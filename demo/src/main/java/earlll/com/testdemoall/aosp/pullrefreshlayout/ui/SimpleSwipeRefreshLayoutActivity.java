package earlll.com.testdemoall.aosp.pullrefreshlayout.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import earlll.com.testdemoall.config.ConstantsKey;
import earlll.com.testdemoall.view.ObservableWebView;
import com.frame.fastframelibrary.utils.dataprocess.IntentUtils;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import com.frame.fastframelibrary.utils.view.WebViewUtil;
import butterknife.BindView;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.ui.base.BaseActivity;
import earlll.com.testdemoall.core.utils.WebViewUtilPlus;
import earlll.com.testdemoall.core.view.ScrollSwipeRefreshLayout;

/**
 * 用于普通的Url展示
 * WebViewUtil.goInnerWebView(con,"title","file:///android_asset/jsbridgedemo.html");
 */
public class SimpleSwipeRefreshLayoutActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final int REFRESH_COMPLETE = 0X110;
    protected String url = "";

    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg){
            switch (msg.what){
                case REFRESH_COMPLETE:
                    mSwipeLayout.setRefreshing(false);
                    break;
            }
        };
    };

    @BindView(R.id.wv_show)
    protected ObservableWebView mWebView;

    @BindView(R.id.srlayout_show)
    protected ScrollSwipeRefreshLayout mSwipeLayout;

    @BindView(R.id.progressbar)
    protected ProgressBar mProgressBar;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_basewebview_swiperefreshlayout;
    }

    @Override
    public void initContentView(View view) {
        mSwipeLayout.setViewGroup(mWebView);//设置监听滚动的子view
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        initWebView();

        mWebView.setOnScrollChangedCallback(new ObservableWebView.OnScrollChangedCallback() {
            @Override
            public void onScroll(int dx, int dy,String type) {
                float webContentHeight = mWebView.getContentHeight()*mWebView.getScale();//webview的高度
                float webNowHeight = mWebView.getHeight()+ mWebView.getScrollY();//当前webview的高度
                if( webContentHeight - webNowHeight==0){
                    //已经处于底端
                    mSwipeLayout.setEnabled(false);
                }else {
                    mSwipeLayout.setEnabled(false);
                }
                if (mWebView.getY() == 0) {
                    //已经处于顶端
                    mSwipeLayout.setEnabled(true);
                }
            }
           });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if (mWebView != null && StringUtils.isNotEmpty(url)) {
            mWebView.loadUrl(url);
        }
        //设置title
        String title = IntentUtils.getIntentStr(getIntent(), ConstantsKey.KEY_TITLE);
        if (StringUtils.isNotEmpty(title)) {
        }

        //设置展示特殊类型UI
        String type = IntentUtils.getIntentStr(getIntent(), ConstantsKey.KEY_TYPE);
        if (StringUtils.isNotEmpty(type)) {

        }
        //设置加载Url
        url = IntentUtils.getIntentStr(getIntent(), ConstantsKey.KEY_URL);
        if(StringUtils.isEmpty(url)) {
            url = WebViewUtilPlus.COMMON_LOADURL;
        }
        if (StringUtils.isNotEmpty(url)) {
            loadUrl(url);
        }
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
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mSwipeLayout.setRefreshing(false); //隐藏进度条
                } else {
                    if (!mSwipeLayout.isRefreshing())
                        mSwipeLayout.setRefreshing(true);
                }

                super.onProgressChanged(view, newProgress);
            }
        });
        mWebView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mHandler.sendEmptyMessage(REFRESH_COMPLETE);
            }
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

    @Override
    public void onRefresh() {
        loadUrl(url);
    }
}