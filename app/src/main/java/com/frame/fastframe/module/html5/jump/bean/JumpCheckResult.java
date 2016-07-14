package com.frame.fastframe.module.html5.jump.bean;

import com.frame.fastframe.module.html5.jump.JumpType;

/**
 * 检查跳转结果
 */
public class JumpCheckResult {
	//是否需要关闭Activity
	private boolean needClose = false ;
	//是否需要登录
	private boolean needLogin = false ;
	//参数是否存在
	private boolean paramExist = true ;
	//跳转类型
	private JumpType jumpType = null ;
	
	public JumpCheckResult(boolean needClose, boolean needLogin, boolean paramExist, JumpType jumpType) {
		super();
		this.needClose = needClose;
		this.needLogin = needLogin;
		this.paramExist = paramExist;
		this.jumpType = jumpType;
	}

	public boolean isNeedLogin() {
		return needLogin;
	}
	public boolean isParamExist() {
		return paramExist;
	}
	public JumpType getJumpType() {
		return jumpType;
	}
	public boolean isNeedClose() {
		return needClose;
	}
}
