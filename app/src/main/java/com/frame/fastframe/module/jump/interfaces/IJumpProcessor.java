package com.frame.fastframe.module.jump.interfaces;

import android.app.Activity;
import com.frame.fastframe.module.jump.bean.JumpBean;
import com.frame.fastframe.module.jump.bean.JumpCheckResult;

/**事件处理者需要实现的对应方法*/
public interface IJumpProcessor {
	/**
	 * 检查跳转参数
	 * @param jumpBean
	 * @return
	 */
	JumpCheckResult checkJump(JumpBean jumpBean);

	/**
	 * 分发跳转，包括跳转到页面和响应点击事件
	 * @param context
	 * @param jumpBean
	 * @return
	 */
	int dispatch(Activity context, JumpBean jumpBean);
}
