package com.frame.fastframe.view.bridgewebView.bean;

import com.frame.fastframe.view.bridgewebView.interfaces.IBridgeBean;

import java.util.LinkedHashMap;

/**
 * Created by ZhangYuanBo on 2016/5/23.
 */
public class BridgeBean implements IBridgeBean {
    /**被H5调用 - 句柄名称*/
    private String handlerName;
    /**被H5调用 - 方法名称*/
    private String methodName;
    /**被H5调用 - 参数Map*/
    private LinkedHashMap<String ,String > argsMap;

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public LinkedHashMap<String, String> getArgsMap() {
        return argsMap;
    }

    public void setArgsMap(LinkedHashMap<String, String> argsMap) {
        this.argsMap = argsMap;
    }
}
