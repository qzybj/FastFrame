package netcore.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.frame.fastframelibrary.utils.LogUtils;


/**
 * 网络相关工具类
 */
public class NetStatusUtils {
	
	/**当前无网络*/
    public static final int NETWORK_TYPE_UNKNOWN = 0;
    /**WIFI网络开启*/
    public static final int NETWORK_TYPE_WIFI = 1;
    /**mobile网络开启：2G、2.5G、3G、3.5G等*/
    public static final int NETWORK_TYPE_MOBILE = 2;
    /**wifi和mobile网络都已开启*/
    public static final int NETWORK_TYPE_ALL = 3;
    
    /**当前网络为3G网络*/
    public static final int NETWORK_TYPE_MOBILE_3G = 10;
    /**当前网络为4G网络*/
    public static final int NETWORK_TYPE_MOBILE_4G = 11;
    /**当前网络为2G或者其他网络*/
    public static final int NETWORK_TYPE_MOBILE_OTHER = 12;	
    
	
	
	/**
	 * 获取当然有效的网络类型，该函数只区分WIFI和MOBILE。详细区分
	 * wifi、2g、3g、4g请查看函数：<BR>
	 * @param context
	 * @return int 网络类型
	 */
	public static int getNetConnectType(Context context) {
		int res = 0;
        final ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (((wifi != null) && wifi.isAvailable() && wifi.isConnectedOrConnecting())
                && ((mobile != null) && mobile.isAvailable() && mobile.isConnectedOrConnecting())) {
        	res = NETWORK_TYPE_ALL;
        	LogUtils.log("getNetConnectType:----- ConnectType = ------" + "NETWORK_TYPE_ALL");
        } else if ((wifi != null) && wifi.isAvailable() && wifi.isConnectedOrConnecting()) {
        	res = NETWORK_TYPE_WIFI;
        	LogUtils.log("getNetConnectType:----- ConnectType = ------" + "NETWORK_TYPE_WIFI");
        } else if ((mobile != null) && mobile.isAvailable() && mobile.isConnectedOrConnecting()) {
        	res = NETWORK_TYPE_MOBILE;
        	LogUtils.log("getNetConnectType:----- ConnectType = ------" + "NETWORK_TYPE_MOBILE");
        } else {
        	res = NETWORK_TYPE_UNKNOWN;
        	LogUtils.log("getNetConnectType:----- ConnectType = ------" + "NETWORK_TYPE_UNKNOWN");
        }        
        LogUtils.log("getNetConnectType:----- end ------");
        return res;       
    }
	
	
	/**
	 * 获取当前有效网络类型，能够详细区分WIFI、2G、3G等网络类型。如果想只区分
	 * WIFI和MOBILE，请查看函数：
	 * @param context
	 * @return
	 */
	public static int getNetConnectSubType(Context context) {
		int type = NETWORK_TYPE_UNKNOWN;
		int subtype = NETWORK_TYPE_UNKNOWN;
		ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		final NetworkInfo activeNetInfo = connManager.getActiveNetworkInfo();
		if ((activeNetInfo != null) && activeNetInfo.isConnectedOrConnecting()) {
			type = activeNetInfo.getType();
			LogUtils.log("getNetConnectSubType: activeNetInfo.getType() = " + type);
			if (type == ConnectivityManager.TYPE_WIFI) {
				type = NETWORK_TYPE_WIFI;
				subtype = type;
				LogUtils.log("getNetConnectSubType: subtype = " + "NETWORK_TYPE_WIFI");
			} else if (type == ConnectivityManager.TYPE_MOBILE) {
				switch (activeNetInfo.getSubtype()) {
				case TelephonyManager.NETWORK_TYPE_1xRTT:// ~ 50-100 kbps
				case TelephonyManager.NETWORK_TYPE_CDMA:// ~ 14-64 kbps IS95A or IS95B
				case TelephonyManager.NETWORK_TYPE_EDGE:// ~ 50-100 kbps
				case TelephonyManager.NETWORK_TYPE_GPRS: // ~ 100 kbps
					subtype = NETWORK_TYPE_MOBILE_OTHER;
					LogUtils.log("getNetConnectSubType: subtype = " + "NETWORK_TYPE_MOBILE_OTHER");
					break;
				case TelephonyManager.NETWORK_TYPE_EVDO_0:// ~ 400-1000 kbps
				case TelephonyManager.NETWORK_TYPE_EVDO_A:// ~ 600-1400 kbps
//				case TelephonyManager.NETWORK_TYPE_HSDPA: // ~ 2-14 Mbps
//				case TelephonyManager.NETWORK_TYPE_HSPA: // ~ 700-1700 kbps
//				case TelephonyManager.NETWORK_TYPE_HSUPA:// ~ 1-23 Mbps
				case TelephonyManager.NETWORK_TYPE_UMTS:// ~ 400-7000 kbps
					subtype = NETWORK_TYPE_MOBILE_3G;
					LogUtils.log("getNetConnectSubType: subtype = " + "NETWORK_TYPE_MOBILE_3G");
					 break;
				// NOT AVAILABLE YET IN API LEVEL 7
				// case Connectivity.NETWORK_TYPE_EHRPD:// ~ 1-2 Mbps
				// case Connectivity.NETWORK_TYPE_EVDO_B:// ~ 5 Mbps
				// case Connectivity.NETWORK_TYPE_HSPAP:// ~ 10-20 Mbps
				// case Connectivity.NETWORK_TYPE_LTE:// ~ 10+ Mbps
				//	 subtype = NETWORK_TYPE_MOBILE_4G;
				//break;
			    // Unknown
				case TelephonyManager.NETWORK_TYPE_UNKNOWN:
				default:
					subtype = NETWORK_TYPE_UNKNOWN;
					LogUtils.log("getNetConnectSubType: subtype = " + "NETWORK_TYPE_UNKNOWN");
                  break;
				}
			}
		}
		LogUtils.log("getNetConnectSubType:----- end ------");
		return subtype;
	}

	/**
	 * 判断终端网络是否有效
	 * @param context
	 * @return boolean TRUE:代表网络有效
	 */
	public static boolean isNetConnected(Context context) {
		return getNetConnectType(context)!=NETWORK_TYPE_UNKNOWN;
	}
}