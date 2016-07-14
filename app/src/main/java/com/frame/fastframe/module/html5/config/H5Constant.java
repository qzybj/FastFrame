
package com.frame.fastframe.module.html5.config;



/**H5调用Native的常量定义*/
public class H5Constant {

	/**WebView中添加的JS交互接口关键字*/
	public static final String H5_CALL_NATIVE_JSINTERFACE = "callYintaiMobileMethod";

	/*与H5对接数据传递用的key值*/
	public static final String H5_KEY_UID ="userid";
	public static final String H5_KEY_ISLOGIN ="userstate";

	public static final String H5_KEY_PLATFORM ="platform";
	public static final String H5_KEY_APPVERSION ="app_version";
	public static final String H5_KEY_SYSTEMVERSION ="os_version";
	/**设备型号*/
	public static final String H5_KEY_MOBILEMODEL ="devicename";
	public static final String H5_KEY_IMEI ="imei";
	public static final String H5_KEY_SCREENWIDTH ="screenWidth";
	public static final String H5_KEY_SCREENHEIGH ="screenHeigh";
	/**纬度*/
	public static final String H5_KEY_GPS_LATITUDE ="gpsx";
	/**经度*/
	public static final String H5_KEY_GPS_LONGITUDE ="gpsy";
	/**产品线*/
	public static final String H5_KEY_PRODUCTLINE ="apptype";
	/**设备Mac地址*/
	public static final String H5_KEY_MACID ="macid";
	/**设备运营商*/
	public static final String H5_KEY_CARRIER ="carrier";


	/**base值，根据传递不同Int值来触发对应事件*/
	private static final int H5CALLNATIVE_BASE_MSGID =1400;
	/**启动activity*/
	public static final int H5CALLNATIVE_STARTACTIVITY_MSGID =H5CALLNATIVE_BASE_MSGID+1;
	/**显示对话框*/
	public static final int H5CALLNATIVE_SHOWDIG_MSGID =H5CALLNATIVE_BASE_MSGID+2;	
	/**显示加载框*/
	public static final int H5CALLNATIVE_SHOWPROGRESSDLG_MSGID =H5CALLNATIVE_BASE_MSGID+3;
	/**关闭加载框*/
	public static final int H5CALLNATIVE_DISSPROGRESSDLG_MSGID =H5CALLNATIVE_BASE_MSGID+4;
	
	public static final int H5CALLNATIVE_SETTITLEBAR_MSGID =H5CALLNATIVE_BASE_MSGID+5;
	/** 刷新上一页面 */
	public static final int H5CALLNATIVE_REFRESH_PREVIOUS_PAGE_MSGID =H5CALLNATIVE_BASE_MSGID+6;


}