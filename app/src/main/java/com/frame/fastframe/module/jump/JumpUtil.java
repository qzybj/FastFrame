package com.frame.fastframe.module.jump;

import android.app.Activity;
import android.content.Intent;
import com.frame.fastframe.module.jump.bean.JumpCheckResult;
import com.frame.fastframe.module.loign.ui.LoginActivity;
import com.frame.fastframe.utils.UserManager;
import com.frame.fastframelibrary.utils.dataprocess.ListUtils;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * 跳转工具类:
 */
public class JumpUtil {
	/**
	 * 将跳转参数转成相关intent
	 * @param activity 当前activity
	 * @param targetActivity 需要跳转的activity
	 * @param params 跳转所需参数
	 * @return
	 */
	public static Intent parseJumpParams2Intent(Activity activity, Class targetActivity,HashMap<String, String> params){
		if(activity==null|| targetActivity == null){
			return null ;
		}
		Intent intent = new Intent();
		intent.setClass(activity, targetActivity);
		if( params != null && params.size() > 0 ){
			Iterator<Entry<String,String>> it = params.entrySet().iterator();
			Entry<String,String> entry = null ;
			//循环添加参数
			while( it.hasNext() ){
				entry = it.next();
				if(!StringUtils.isEmpty(entry.getKey()) &&
						!StringUtils.isEmpty(entry.getValue())){
					//当参数和返回值都存在时放入intent中
					intent.putExtra(StringUtils.format(entry.getKey()), StringUtils.format(entry.getValue()));
				}
			}
		}
		return intent ;
	}
	
	/**
	 * 检查所有参数是否存在
	 * @param params 所有参数列表
	 * @param keys 参数名称列表
	 * @return
	 */
	public static boolean paramExist(HashMap<String,String> params,String... keys){
		if( ListUtils.isEmptyArray(keys) ){
			return true;
		}
		int size = keys.length;
		String key = null ;
		for(int i= 0; i < size; i ++){
			key = keys[i];
			if(!StringUtils.isEmpty(key)){
				//检查参数是否存在
				if( params != null){
					if( params.get(key) == null){
						//有参数不存在
						return false ;
					}
				}
			}
		}
		return true ;
	}
	
	/**
	 * 执行页面跳转
	 * @param activity
	 * @return
	 */
	public static int startActivity(Activity activity, Intent intent, JumpCheckResult checkResult, int requestCode){
		if( intent == null || checkResult == null ){
			return JumpCtrler.ERROR_NONSUPPORT_JUMPTYPE ;
		}
		if( !checkResult.isParamExist() ){
			//参数验证不通过
			return JumpCtrler.ERROR_NONSUPPORT_PARAM_NOTEXIST ;
		}
		if( checkResult.isNeedLogin() ){
			//需要先跳登录页面,判断是否已登录
			if (UserManager.hasLogin()) {
				//未登录，需要登录
				intent.setClass(activity, LoginActivity.class);
				if( checkResult.getJumpType() != null ){
					intent.putExtra(JumpCtrler.KEY_JUMPTYPE, checkResult.getJumpType());
				}
			}
		}
		activity.startActivityForResult(intent, requestCode);
		//判断是否需要关闭当前Activity
		if(checkResult.isNeedClose()){
			activity.finish();
		}
		return JumpCtrler.ERROR_NONE;
	}
}
