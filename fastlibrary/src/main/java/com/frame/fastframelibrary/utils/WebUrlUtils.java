package com.frame.fastframelibrary.utils;

import android.net.Uri;

import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


/**URL辅助工具类*/
public class WebUrlUtils {

	public static final String TAG =WebUrlUtils.class.getName();
	
	/**
	 * 获取URL中指定key的参数 
	 * @param webUrl
	 * @return
	 */
	public static String getParam(String webUrl,String key){
		try {
			if (StringUtils.isNotEmpty(webUrl)) {
				Uri uri = Uri.parse(StringUtils.format(webUrl));
				if (uri!=null) {
					return uri.getQueryParameter(key);
				}
			}
		} catch (Exception e) {
			LogUtils.e(TAG, e.getMessage());
		}
		return "";
	}

	/**
	 * 解析URL中的参数,并以map返回
	 * @param webUrl
	 * @return
	 */
	public static HashMap<String, String> getParams2Map(String webUrl){
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			if (StringUtils.isNotEmpty(webUrl)) {
				Uri uri = Uri.parse(StringUtils.format(webUrl));
				if (uri!=null) {
					Set<String> names = uri.getQueryParameterNames();
					if (names!=null&&names.size()>0) {
						Iterator<String> it = names.iterator();
						while (it.hasNext()){
							String key = it.next();
							if (StringUtils.isNotEmpty(key)) {
								map.put(key,uri.getQueryParameter(key));
							}
						}
					}
					return map;
				}
			}
		} catch (Exception e) {
			LogUtils.e(TAG, e.getMessage());
		}
		return map ;
	}
}