package com.frame.fastframe.module.html5.jump.processor.event;

import android.app.Activity;
import android.content.Intent;
import com.frame.fastframe.R;
import com.frame.fastframe.module.html5.jump.JumpCtrler;
import com.frame.fastframe.module.html5.jump.JumpDispatcher;
import com.frame.fastframe.module.html5.jump.JumpType;
import com.frame.fastframe.module.html5.jump.JumpUtil;
import com.frame.fastframe.module.html5.jump.bean.JumpBean;
import com.frame.fastframe.module.html5.jump.bean.JumpCheckResult;
import com.frame.fastframe.module.html5.jump.interfaces.IJumpProcessor;
import com.frame.fastframe.module.html5.jump.processor.ConstantsJumpParam;
import com.frame.fastframe.module.loign.ui.LoginActivity;
import com.frame.fastframe.utils.AccountUtils;
import com.frame.fastframelibrary.utils.view.ToastUtils;

import java.util.HashMap;

/**
 * 	事件处理者
 */
public class EventProcessor implements IJumpProcessor {

	private static EventProcessor instance = null ;
	private EventProcessor() {}
	
	/**
	 * 获取实例
	 * @return
	 */
	public static EventProcessor getInstance(){
		if( instance == null ){
			instance = new EventProcessor();
		}
		return instance ;
	}

	@Override
	public int dispatch(Activity context, JumpBean jumpBean) {
		if( context == null ||
				jumpBean == null ||
				jumpBean.getJumpType() == null ){
 			return JumpCtrler.ERROR_UNKNOWN ;
		}
		//检查登录和参数信息
		JumpCheckResult checkResult = checkJump(jumpBean);
		if( !checkResult.isParamExist() ){
			//参数不正确
			return JumpCtrler.ERROR_NONSUPPORT_PARAM_NOTEXIST ;
		}
		if( checkResult.isNeedLogin() ){
			//需要先跳登录页面,判断是否已登录
			if (AccountUtils.hasLogin()) {
				//未登录，需要跳转到登录页，并且给出提示
				Intent intent = new Intent(context, LoginActivity.class);
				ToastUtils.showToast(context, R.string.jump_tip_needlogin);
				context.startActivity(intent);
				return JumpCtrler.ERROR_NONE;
			}
		}
		
		//做正常处理
		if( checkResult.getJumpType() != null ){
			if(jumpBean.getJumpType() == JumpType.Share){
				// TODO: 2016/7/14 实现分享功能

			}else if(jumpBean.getJumpType() == JumpType.UnionLogin){
				// TODO: 2016/7/14 实现第三方登录
			}else {
				return JumpCtrler.ERROR_NONSUPPORT_JUMPTYPE ;
			}
		}
		return JumpCtrler.ERROR_NONE;
	}
	
	@Override
	public JumpCheckResult checkJump(JumpBean jumpBean) {
		if( jumpBean == null ||
				jumpBean.getJumpType() == null ){
			return null ;
		}
		boolean needLogin = JumpDispatcher.checkNeedLogin(jumpBean.getJumpType());
		boolean needClose = JumpDispatcher.checkNeedClose(jumpBean.getJumpType());
		boolean paramExist = checkParams(jumpBean) ;
		return new JumpCheckResult(needClose,needLogin, paramExist, jumpBean.getJumpType());
	}
	
	/**
	 * 检查每个跳转页面的参数是否正确
	 * @param jumpUri
	 * @return
	 */
	private static boolean checkParams(JumpBean jumpUri){
		if( jumpUri == null || jumpUri.getJumpType() == null ){
			return false ;
		}
		JumpType jumpType = jumpUri.getJumpType() ;
		HashMap<String, String> paramsMap = jumpUri.getParamMap();
		boolean isExist = true ;
		if(JumpType.Share == jumpType ){
			//分享, 必须包含分享页面地址和分享内容   1:分享页面地址、2:分享内容
			isExist = JumpUtil.paramExist(paramsMap,
					ConstantsJumpParam.PARAM_KEY_SHARE_URL,ConstantsJumpParam.PARAM_KEY_SHARE_TITLE,
					ConstantsJumpParam.PARAM_KEY_SHARE_CONTENT);
		}
		return isExist ;
	}
}
