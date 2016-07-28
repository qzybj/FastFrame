package com.frame.fastframelibrary.utils.dataprocess;

import java.util.Map;


public class MapUtils {
	public static boolean isEmpty(Map<?,?> map){
		if (map!=null&&map.size()>0) {
			return false;
		}
		return true;
	}
	public static boolean isNotEmpty(Map<?,?> map){
		return !isEmpty(map);
	}

	public static <T> Object getData(Map<?,?> map,T key){
		if (isNotEmpty(map)) {
			if (map.containsKey(key)) {
				return map.get(key);
			}
		}
		return null;
	}
}
