package com.frame.fastframe.module.jump;

import android.app.Activity;
import com.frame.fastframe.module.jump.bean.JumpBean;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import java.net.URI;
import java.util.List;

/**
 * 		跳转控制类，根据URI，解析其参数并跳转到不同的页面<br>
 *		jsonCMD 格式为：<br>
 *			yintaimobile://MessageDetial?messageid=234333&messagetype=0<br>
 *			MessageDetial: 为跳转类型和{@link JumpType}对应<br>
 *			messageid和messagetype: 为跳转到页面所需参数
 *			
 */
public class JumpCtrler {
	private static final String TAG = JumpCtrler.class.toString();
	private static boolean isDevMode = true;
	//跳转类型
	public static final String KEY_JUMPTYPE = "keyJumpType";
	/**默认Scheme*/
	public static final String SCHEME_DEFAULT = "yintaimobile";
	
	//正常跳转
	public static final int ERROR_NONE = 0;
	//不支持跳转类型
	public static final int ERROR_NONSUPPORT_JUMPTYPE = 400000001;
	//不支持跳转scheme
	public static final int ERROR_NONSUPPORT_SCHEME = 400000002;
	//跳转页面参数不存在
	public static final int ERROR_NONSUPPORT_PARAM_NOTEXIST = 400000003;
	//未知异常
	public static final int ERROR_UNKNOWN = 400000004;
	
	/**
	 * 
	 * 根据url，解析起参数并跳转到不同的页面<br>
	 *		
	 * @param activity
	 * 		承载类
	 * @param jsonCMD
	 * 		yintaimobile://MessageDetial?messageid=234333&messagetype=0<br>
	 *		MessageDetial: 为跳转类型和{@link JumpType}对应<br>
	 *		messageid和messagetype: 为跳转到页面所需参数
	 * @return
	 * 		
	 */
	public static int doJump(Activity activity, String jsonCMD){
		//将url转成相对应的bean
		JumpBean jumpUri = parseJumpParams(jsonCMD);
		int result= ERROR_NONE ;
		if( jumpUri == null ){
			//不支持当前跳转类型
			result =  ERROR_NONSUPPORT_JUMPTYPE ;
		} else if(!isSupportScheme(jumpUri.getScheme())){
			//不支持当前scheme
			result = ERROR_NONSUPPORT_SCHEME ;
		}
		
		//分发跳转
		if( result == ERROR_NONE ){
			result = JumpDispatcher.dispatchJump(activity, jumpUri);
		}
		
		if(isDevMode ){
			if(result != ERROR_NONE){
				String errorMsg = null;
				switch (result) {
				case ERROR_NONSUPPORT_JUMPTYPE:
					errorMsg = "不支持的跳转类型";
					break;
				case ERROR_NONSUPPORT_SCHEME:
					errorMsg = "不支持跳转scheme";
					break;
				case ERROR_NONSUPPORT_PARAM_NOTEXIST:
					errorMsg = "跳转页面参数不存在";
					break;
				case ERROR_UNKNOWN:
					errorMsg = "未知异常";
					break;

				default:
					break;
				}
				if(StringUtils.isEmpty(errorMsg)){
					errorMsg = "" + result ;
				}
				errorMsg += "\n" + jsonCMD ;
				//activity.showErrorDialog( errorMsg );
			}
		}
		return result;
	}
	
	/**
	 * 是否是支持的scheme
	 * @param scheme
	 * @return
	 */
	private static boolean isSupportScheme(String scheme){
		return SCHEME_DEFAULT.equalsIgnoreCase(scheme);
	}
	
	/**
	 * 将跳转连接转成相对应的{@link JumpBean}类， 类中包含跳转类型，scheme和参数
	 * @param jumpUrl
	 * @return
	 */
	public static JumpBean parseJumpParams(String jumpUrl){
		try {
			URI uri = new URI(StringUtils.format(jumpUrl));
			String scheme = uri.getScheme();
			//跳转类型
			String jumpType = uri.getHost() ;
			if( jumpType == null ){
				jumpType = uri.getAuthority();
			}
			List<NameValuePair> paramsList = URLEncodedUtils.parse(uri,  "UTF-8");
			JumpBean jumpUri = new JumpBean(jumpType, paramsList);
			//是无法解析的跳转类型
			if( jumpUri.getJumpType() == null ){
				return null ;
			}
			jumpUri.setScheme(scheme);
			return jumpUri ;
		} catch (Exception e) {
			if( e != null ){
				LogUtils.e(TAG, e.getMessage());
			}
		}
		return null ;
	}
}
