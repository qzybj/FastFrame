package com.frame.fastframelibrary.net.core.config;

import android.support.annotation.IntDef;

import com.google.gson.annotations.SerializedName;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by ZhangYuanBo on 2016/8/30.
 */
public class NetConstants {

    public static final String API_BASE_URL = "http://api.jiajuquan.com/";
    public static final String APP_CLIENT_TYPE= "android";
    public static final String APP_IS_DEBUG= "1";
    public static final String API_VERSION= "2";

    public static final String APP_KEY = "qiankunda";

    public static final String APP_SECRET = "b8d097bcc9096bba10aa4bbeca2d2bf5";


    public static final int MSG_BASECODE = 31000;

    /**Msg what for handler - start */
    public static final int MSG_WHAT_START = MSG_BASECODE + 1;
    /**Msg what for handler - succeed */
    public static final int MSG_WHAT_SUCCEED = MSG_BASECODE + 2;
    /**Msg what for handler - cancel  */
    public static final int MSG_WHAT_CANCEL = MSG_BASECODE + 3;

    @IntDef({Method.GET, Method.POST})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Method {
        /**Show and dismiss loading view*/
        int GET =  1;
        /**Only show loading view*/
        int POST =  2;
    }

    @IntDef({NetStatusCode.CODE_SUCCEED, NetStatusCode.CODE_NO_NET, NetStatusCode.CODE_NO_READ,
            NetStatusCode.CODE_NO_JSON, NetStatusCode.CODE_NO_RESPONSE, NetStatusCode.CODE_JSON_SUCCESS})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NetStatusCode {
        /** Get data status code： successfull */
        int CODE_SUCCEED =  1;
        /** Get data status code：无网络连接*/
        int CODE_NO_NET =  2;
        /** Get data status code：无法获取网络数据*/
        int CODE_NO_READ =  3;
        /** Get data status code：解析JSON错误*/
        int CODE_NO_JSON =  4;
        /** Get data status code：代表getdata方法传入的参数clazz为null，如果想自己解析json数据可以把clazz传为空。*/
        int CODE_NO_RESPONSE =  5;
        /** 获取数据Json状态码：正确解析json串*/
        int CODE_JSON_SUCCESS =  6;
    }

    @IntDef({LoadingType.LOADING_NORMAL, LoadingType.LOADING_START, LoadingType.LOADING_END, LoadingType.LOADING_NONE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LoadingType {
        /**Show and dismiss loading view*/
        int LOADING_NORMAL =  1;
        /**Only show loading view*/
        int LOADING_START =  2;
        /**Only dismiss loading view*/
        int LOADING_END =  3;
        /**No show and no dismiss loading view*/
        int LOADING_NONE =  4;
    }
}