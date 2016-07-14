package com.frame.fastframelibrary.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListUtils {
	public static boolean isEmpty(List<?> list){
		if (list!=null&&list.size()>0) {
			return false;
		}
		return true;
	}
	public static boolean isNotEmpty(List<?> list){
		return !isEmpty(list);
	}

	public static <T> T getData(List<T> list,int index){
		if (!isEmpty(list)) {
			if (list.size()>index) {
				return list.get(index);
			}
		}
		return null;
	}

	public static <T> ArrayList<T> convert2ArrayList(Set<T> set){
		if (set!=null&&set.size()>0) {
			try {
				ArrayList<T> list = new ArrayList<T>(set);
				return list;
			} catch (Exception e) {
				LogUtils.e(e);
			}
		}
		return null;
	}
	/**
	 * 判断是否为空
	 * @param objList
	 * @return
	 */
	public static boolean isEmpty(Object... objList){
		if(objList == null ||
				objList.length == 0){
			return true ;
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmptySet(HashSet target){
		if( target == null ||
				target.size() == 0 ){
			return true ;
		}
		return false ;
	}
}
