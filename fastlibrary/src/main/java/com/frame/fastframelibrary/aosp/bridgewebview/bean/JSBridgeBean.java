package com.frame.fastframelibrary.aosp.bridgewebview.bean;

import com.frame.fastframelibrary.aosp.bridgewebview.interfaces.IJSBridgeBean;
import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Created by ZhangYuanBo on 2016/5/23.
 *  包含handlerName字段主要是为了在原生中做区分
 */
public class JSBridgeBean implements IJSBridgeBean,Serializable{
    /**被H5调用 - 句柄名称*/
    private String handlerName;
    /**被H5调用 - 方法名称*/
    private String methodName;
    /**被H5调用 - 参数Map*/
    private LinkedHashMap<String ,String> argsMap;

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
