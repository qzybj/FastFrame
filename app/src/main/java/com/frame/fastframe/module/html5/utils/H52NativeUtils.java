package com.frame.fastframe.module.html5.utils;

import android.content.Context;

import com.frame.fastframe.module.common.constant.Constants;
import com.frame.fastframe.module.html5.bean.H5GetUserBean;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.app.AppUtils;
import com.frame.fastframelibrary.utils.device.DeviceUtils;
import com.frame.fastframelibrary.utils.json.GsonUtils;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;

/**
 * Created by ZhangYuanBo on 2016/6/12.<br/>
 * 用来处理H5与App互调的操作
 */
public class H52NativeUtils {


    /** 获取用户信息*/
    public static String getUserInfo(Context con) {
        String returnJson = "";
        try {
            H5GetUserBean returnBean =new H5GetUserBean();
            String userid ="";
            returnBean.setUserid(userid);// 用户id
            returnBean.setUserstate(StringUtils.isNotEmpty(userid)? false : true);// 用户登录状态
            returnBean.setPlatform(Constants.PLATFORM);
            returnBean.setAppversion(AppUtils.getVersionName());
            returnBean.setOsversion(DeviceUtils.getAndroidSDKVersion());
            returnBean.setDevicename(DeviceUtils.getPhoneModel());
            returnBean.setImei(DeviceUtils.getIMEI());
            returnBean.setScreenWidth(DeviceUtils.getScreenWidth(con)+ "");
            returnBean.setScreenHeigh(DeviceUtils.getScreenHeight(con)+ "");
            returnBean.setApptype(Constants.PRODUCT_LINE);// 产品线
            returnJson = GsonUtils.toJson(returnBean);
            LogUtils.d(returnJson);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return returnJson;
    }
}
