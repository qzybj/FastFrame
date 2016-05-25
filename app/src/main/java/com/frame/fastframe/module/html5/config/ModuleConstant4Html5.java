
package com.frame.fastframe.module.html5.config;



/**H5调用Native的常量定义*/
public class ModuleConstant4Html5 {
	public static final String H5_KEY_UID ="userid";
	public static final String H5_KEY_ISLOGIN ="userstate";

	public static final String H5_KEY_APPPLATFORM ="os";
	public static final String H5_KEY_APPVERSION ="clientv";
	public static final String H5_KEY_SYSTEMVERSION ="osversion";
	/**设备型号*/
	public static final String H5_KEY_MOBILEMODEL ="devicename";
	public static final String H5_KEY_IMEI ="imei";
	public static final String H5_KEY_SCREENWIDTH ="screenWidth";
	public static final String H5_KEY_SCREENHEIGH ="screenHeigh";
	public static final String H5_KEY_GPS_LATITUDE ="gpsx";//纬度
	public static final String H5_KEY_GPS_LONGITUDE ="gpsy";//经度
	public static final String H5_KEY_GUID ="guid";
	/**用户ID的唯一标识符*/
	public static final String H5_KEY_UGUID ="uguid";

	/**客户端销售渠道号（ERP中的渠道）*/
	public static final String H5_KEY_SOURCEID ="source";
	/**产品线*/
	public static final String H5_KEY_PRODUCTLINE ="apptype";
	/**客户端推广渠道号(CPS主渠道、子渠道、thkey值)*/
	public static final String H5_KEY_CPS ="cps";
	/**购物车Id*/
	public static final String H5_KEY_SHOP_CART_ID ="cartId";
	/**银泰贵宾卡唯一标示*/
	public static final String H5_KEY_YT_VIP_OPEN_ID ="ytVipOpenID";
	/**设备Mac地址*/
	public static final String H5_KEY_MACID ="macid";
	/**设备运营商*/
	public static final String H5_KEY_CARRIER ="carrier";
	/**银泰特殊用*/
	public static final String H5_KEY_YINTAISOURCEID ="yintaisourceId";


	/**WebView中添加的JS交互接口关键字*/
	public static final String H5_CALL_NATIVE_JSINTERFACE = "callYintaiMobileMethod";

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
	/** 刷新上衣页面 */
	public static final int H5CALLNATIVE_REFRESH_PREVIOUS_PAGE_MSGID =H5CALLNATIVE_BASE_MSGID+6;


}