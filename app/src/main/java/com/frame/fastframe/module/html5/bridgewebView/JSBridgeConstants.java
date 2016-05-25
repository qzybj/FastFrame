package com.frame.fastframe.module.html5.bridgewebView;

/**
 *  定义常量：
 */
public class JSBridgeConstants {
    /**模块TAG*/
    public static final String TAG = JSBridgeConstants.class.getName();

    /**基本值*/
    private static final int BASECODE = 0x13001;

    /**requestcode 基本值*/
    private static final int BASECODE_HANDLE_CODE = BASECODE+1000;
    /** messageCode - JS调用客户端方法 */
    public static final int HANDLE_MSGCODE_JSCALLAPP = BASECODE_HANDLE_CODE + 1;
    /** messageCode - 调用JS方法之后回调客户端 */
    public static final int HANDLE_MSGCODE_CALLBACK_CALLJS = BASECODE_HANDLE_CODE + 2;


    /**JSBRIDGE供H5调用的原生方法名*/
    public final static String JSBRIDGE_HANDLERNAME_JS2NATIVE ="jsbridge_handlername_js2native";
    /**JSBRIDGE供原生调用的H5方法名*/
    public final static String JSBRIDGE_HANDLERNAME_NATIVEJS ="jsbridge_handlername_native2js";


    /**JSBRIDGE供原生调用的H5方法名*/
    public final static String KEY_JSBRIDGE_METHOD ="jsbridge_method";


}
