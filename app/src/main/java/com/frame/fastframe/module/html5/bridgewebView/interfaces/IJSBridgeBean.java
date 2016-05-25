package com.frame.fastframe.module.html5.bridgewebView.interfaces;

import java.util.LinkedHashMap;

/**
 * Created by ZhangYuanBo on 2016/5/23.
 * 定义数据传递必须要传递一些值的获取方法
 */
public interface IJSBridgeBean {
    /** Js调用Native的句柄*/
    String getHandlerName();
    /** Js调用Native的方法名*/
    String getMethodName();
    /** Js调用Native的参数*/
    LinkedHashMap<String, String> getArgsMap();

}
