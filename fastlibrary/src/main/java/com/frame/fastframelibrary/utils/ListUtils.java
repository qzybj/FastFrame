package com.frame.fastframelibrary.utils;

import java.util.ArrayList;
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
}
