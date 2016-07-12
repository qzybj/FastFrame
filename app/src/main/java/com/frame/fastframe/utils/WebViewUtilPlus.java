package com.frame.fastframe.utils;

import android.content.Context;
import android.content.Intent;

import com.frame.fastframe.R;
import com.frame.fastframe.module.html5.ui.base.BaseWebViewActivity;
import com.frame.fastframelibrary.utils.StringUtils;
import com.frame.fastframelibrary.utils.WebViewUtil;

/**
 * 继承WebViewUtil并进行和项目有关的功能增强
 */
public class WebViewUtilPlus extends WebViewUtil {
    /** 如果打开界面的时候传递的url为空，则加载该URL */
    public final static String COMMON_LOADURL = "https://www.baidu.com/";
    /**显示类型 - 用于控制通用界面的UI初始化的*/
    public final static String KEY_TYPE = "type";
    public static final int TYPE_WEBVIEW_DEFAULT = 0x0021001;
    /**
     * 打开指定的页面<br/>
     * @param con
     * @param title
     * @param loadUrl  file:///android_asset/demo.html"
     */
    public  static void goInnerWebView(Context con,String title,String loadUrl) {
        goInnerWebView(con,title,loadUrl,TYPE_WEBVIEW_DEFAULT);
    }
    public  static void goInnerWebView(Context con,String title,String loadUrl,int showType) {
        if (con != null) {
            Intent intent = new Intent(con, BaseWebViewActivity.class);
            if(StringUtils.isEmpty(title)) {
                title = con.getString(R.string.app_name);
            }
            intent.putExtra(WebViewUtilPlus.KEY_TITLE,title);
            if(StringUtils.isNotEmpty(loadUrl)) {
                intent.putExtra(WebViewUtilPlus.KEY_URL,loadUrl);
            }
            con.startActivity(intent);
        }
    }

    /** 调用示例：打开"file:///android_asset/demo.html"
     * */
    public  static void goInnerWebViewSimple(Context con) {
        goInnerWebView(con,"测试页面","file:///android_asset/jsbridgedemo.html");
    }


}
