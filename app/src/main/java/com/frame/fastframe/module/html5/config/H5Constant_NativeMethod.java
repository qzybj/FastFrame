
package com.frame.fastframe.module.html5.config;

import com.frame.fastframelibrary.utils.MapUtils;
import java.util.HashMap;

/**H5调用Native的方法名称*/
public class H5Constant_NativeMethod {
	public static HashMap<String,Integer> initMap =  new HashMap<String,Integer>();

	public static final String N_METHOD_GETUSERINFO ="getuserinfo";
	public static final String N_METHOD_GOLOGIN ="gologin";


	public static HashMap<String,Integer> getMethodMap(){
		if(MapUtils.isEmpty(initMap)){
			//initMap.put(N_METHOD_GETUSERINFO,)
		}
		return initMap;
	}

}