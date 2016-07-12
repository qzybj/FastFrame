package com.frame.fastframe.module.html5.utils;

import android.content.Context;

import com.frame.fastframe.module.common.constant.CommonConstants;
import com.frame.fastframe.module.html5.bean.H5GetUserBean;
import com.frame.fastframelibrary.utils.AppUtils;
import com.frame.fastframelibrary.utils.DeviceUtils;
import com.frame.fastframelibrary.utils.GsonUtils;
import com.frame.fastframelibrary.utils.StringUtils;

/**
 * Created by ZhangYuanBo on 2016/6/12.<br/>
 * 用来处理H5与App互调的操作
 */
public class H5OperateNativeUtils {
    /**秒*/
    public static final long KEY_SECOND = 1000L;
    /**分钟*/
    public static final long KEY_MINUTE = 60L*KEY_SECOND;
    /**小时*/
    public static final long KEY_HOUR = 60L* KEY_MINUTE;
    /**天*/
    public static final long KEY_DAY = 24L* KEY_HOUR;

    /**当前默认设定保存周期为30天<br>
     * 因为服务器回传单位是小时，处理的时候要注意*/
    public static final long DEFAULT_STORAGECYCLE = KEY_DAY*30;


    /** 获取用户信息*/
    public static String getUserInfo(Context con) {
        String returnJson = "";
        try {
            H5GetUserBean returnBean =new H5GetUserBean();
            String userid ="";
            returnBean.setUserid(userid);// 用户id
            returnBean.setUserstate(StringUtils.isNotEmpty(userid)? false : true);// 用户登录状态
            returnBean.setPlatform(CommonConstants.PLATFORM);
            returnBean.setAppversion(AppUtils.getVersionName());
            returnBean.setOsversion(DeviceUtils.getAndroidSDKVersion());
            returnBean.setDevicename(DeviceUtils.getPhoneModel());
            returnBean.setImei(DeviceUtils.getIMEI());
            returnBean.setScreenWidth(DeviceUtils.getScreenWidth(con)+ "");
            returnBean.setScreenHeigh(DeviceUtils.getScreenHeight(con)+ "");
            returnBean.setApptype(CommonConstants.PRODUCT_LINE);// 产品线
            returnJson = GsonUtils.toJson(returnBean);
            TransfersLog.d(returnJson);
        } catch (Exception e) {
            TransfersLog.e(e.getMessage());
        }
        return returnJson;
    }
}
