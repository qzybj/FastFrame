package com.frame.fastframe.utils;

import android.content.Context;
import android.content.Intent;

import com.frame.fastframe.module.common.constant.CommonConstants;
import com.frame.fastframe.ui.base.BaseWebViewActivity;
import com.frame.fastframelibrary.utils.WebViewUtil;

/**
 * Created by ZhangYuanBo on 2016/4/7.
 */
public class WebViewUtilPlus extends WebViewUtil {
    /** 打开指定的页面<br/>
     * "file:///android_asset/demo.html"
     * */
    public  static void goInnerWebView(Context con, String title, String loadUrl) {
        if (con != null) {
            Intent intent = new Intent(con, BaseWebViewActivity.class);
            intent.putExtra(CommonConstants.KEY_TITLE,"家居圈");
            intent.putExtra(CommonConstants.KEY_URL,"file:///android_asset/jsbridgedemo.html");
            con.startActivity(intent);
        }
    }
}
