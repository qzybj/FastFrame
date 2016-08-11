package com.frame.fastframe.module.jump;

/**
 * Operability Method Type
 */
public enum JumpType {

	/************************* Main_Page ************************/
	/**Main_Page - 首页*/
	Home,
	/**Main_Page - 分类列表*/
	Category,
	/**Main_Page - 搜索页面*/
	Search,
	/**Main_Page - 摇一摇*/
	Shake,
	/**Main_Page - 扫一扫*/
	Scan,
	/************************* Accout_Page **********************/
	/**Accout_Page - 登录*/
	Login,
	/**Accout_Page - 注册*/
	Register,
	/**Accout_Page - 忘记密码*/
	ForgetPwd,
	/************************* Product_Page **********************/
	/**Product_Page - 商品列表*/
	ProductList,
	/**Product_Page - 商品详情页*/
	ProductDetial,

	/************************* Common_Page **********************/
	/**Common_Page - 查看大图页面*/
	ViewLargerImage,
	/**Common_Page - 意见反馈*/
	Feedback,
	/**Common_Page - 关于*/
	About,
	/**Common_Page - 外链h5页面*/
	OutLinkH5,
	/**Common_Page - 内嵌h5页面*/
	InnerH5,
	/************************* Action_Event **********************/
	/**Action_Event - 分享*/
	Share,
	/**Action_Event - 操作标题栏*/
	SetTitleBar,
	/**Action_Event - 屏蔽点击事件*/
	ShieldTopBottom,
	/**Action_Event - 弹出确认框Dialog*/
	ShowDialog,
	/**Action_Event - 联合登录*/
	UnionLogin
}