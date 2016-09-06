package com.frame.fastframelibrary.net.core.interfaces;

import com.frame.fastframelibrary.net.core.config.NetConstants;

/**
 * Created by ZhangYuanBo on 2016/9/5.
 */
public interface ITag {
    int getRequstCode();
    @NetConstants.LoadingType
    int getLoadingType();
    long getRequstTime();
}
