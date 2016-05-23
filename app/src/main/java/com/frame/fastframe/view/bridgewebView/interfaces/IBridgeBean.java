package com.frame.fastframe.view.bridgewebView.interfaces;

import java.util.LinkedHashMap;

/**
 * Created by ZhangYuanBo on 2016/5/23.
 */
public interface IBridgeBean {
    String getHandlerName();
    String getMethodName();
    LinkedHashMap<String, String> getArgsMap();

}
