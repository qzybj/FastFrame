package com.frame.fastframe.module.jump.bean;

import com.frame.fastframe.module.jump.JumpType;
import com.frame.fastframelibrary.utils.dataprocess.ListUtils;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import org.apache.http.NameValuePair;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * 将H5传递的字符转换成本地处理需要的相关参数
 */
public class JumpBean implements Serializable {
	private final int BASE_RESULTCODE = 0x1101;
	/**跳转scheme*/
	private String scheme = null ;
	/**跳转类型*/
	private JumpType jumpType = null ;
	/**跳转所需参数*/
	private HashMap<String,String> paramMap = null ;

	public JumpBean(JumpType jumpType) {
		this.jumpType = jumpType ;
	}

	public JumpBean(JumpType jumpType, HashMap<String, String> paramMap) {
		this.jumpType = jumpType ;
		this.paramMap = paramMap ;
	}

	public JumpBean(String jumpType, List<NameValuePair> paramsList) {
		//跳转类型转换
		this.jumpType  = null ;
		try {
			if (!StringUtils.isEmpty(jumpType)) {
				this.jumpType = JumpType.valueOf(StringUtils.format(jumpType));
			}
		}catch (Exception e){
			LogUtils.e(e);
		}
		if(this.jumpType != null ){
			//只有当跳转类型被客户端识别时才处理参数
			if(!ListUtils.isEmpty(paramsList)){
				paramMap = new HashMap<String, String>();
				//转换参数
				for( NameValuePair nvp: paramsList ){
					if(!StringUtils.isEmpty(nvp.getName()) &&!StringUtils.isEmpty(nvp.getValue())){
						paramMap.put(nvp.getName(), nvp.getValue());
					}
				}
			}
		}
	}
	
	public String getScheme() {
		return scheme;
	}
	
	/**
	 * 设置scheme
	 * @param scheme
	 */
	public void setScheme(String scheme){
		this.scheme = StringUtils.format(scheme) ;
	}
	
	/**
	 * 跳转类型
	 * @return
	 */
	public JumpType getJumpType() {
		return jumpType;
	}
	
	/**
	 * 跳转所需参数
	 * @return
	 */
	public HashMap<String, String> getParamMap() {
		return paramMap;
	}

	public int getRequestCode() {
		if(jumpType!=null){
			return jumpType.ordinal()+BASE_RESULTCODE;
		}
		return -1;
	}
}
