package com.frame.volleypackageframe.module.product.moduleview.bean;

import com.frame.volleypackageframe.module.product.moduleview.ModuleViewType;
import java.io.Serializable;

/**
 * 	模块化View刷新数据需要信息
 */
public class BaseModuleViewBean implements Serializable {
	private static final long serialVersionUID = 1L;

	protected ModuleViewType moduleType = null;

	/**
	 * 获取模版类型所对应的常量
	 * @return
	 */
	public ModuleViewType getTypeEnum(){
		return moduleType ;
	}

	/**
	 * 设置模版类型
	 * @param type
	 */
	public void setTypeEnum(ModuleViewType type){
		moduleType = type ;
	}
	
	
}