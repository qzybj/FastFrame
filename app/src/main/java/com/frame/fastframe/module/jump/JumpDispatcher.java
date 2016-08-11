package com.frame.fastframe.module.jump;

import android.app.Activity;
import android.view.View;
import com.frame.fastframe.module.jump.bean.JumpBean;
import com.frame.fastframe.module.jump.processor.event.EventProcessor;
import com.frame.fastframe.module.jump.processor.page.PageProcessor;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 	 根据跳转参数分发跳转
 */
public class JumpDispatcher {
	/**
	 * 需要登录的跳转类型列表
	 */
	private static HashSet<JumpType> needLoginHashSet = null;
	/**
	 * 需要关闭的跳转类型列表
	 */
	private static HashSet<JumpType> needCloseHashSet = null;
	/**
	 * 定义不同跳转类型所对应的activity类，只针对跳转到页面的情况
	 */
	private static HashMap<JumpType, Class> jumpPagesMap = null ;
	
	/**
	 * 获取需要登录的跳转类型
	 * @return
	 */
	private static HashSet<JumpType> getNeedLoginSet(){
		if( needLoginHashSet == null ){
			needLoginHashSet = new HashSet<JumpType>();
			/************************** PAGE ***********************/
//			needLoginHashSet.add(JumpType.MyOrderList); // 我的订单列表
			/************************** EVENT **********************/
//			needLoginHashSet.add(JumpType.Msg_GetPromotionTicket);  //领取优惠券
		}
		return needLoginHashSet ;
	}
	/**
	 * 获取需要关闭的跳转类型
	 * @return
	 */
	private static HashSet<JumpType> getNeedClogseSet(){
		if( needCloseHashSet == null ){
			needCloseHashSet = new HashSet<JumpType>();
			/**************************PAGE**********************/
//			needCloseHashSet.add(JumpType.OrderSubmitInfo); //订单提交成功页
	
		}
		return needCloseHashSet ;
	}
	
	/**
	 * 获取不同跳转类型所对应的activity
	 * @return
	 */
	public static HashMap<JumpType, Class> getJumpPageMap(){
		if( jumpPagesMap == null ){
			jumpPagesMap = new HashMap<JumpType, Class>();

			/************************* Main_Page ************************/
//			jumpPagesMap.put(JumpType.Home, HomeActivity.class); //首页
//			jumpPagesMap.put(JumpType.Category, CategoryActivity.class); //分类列表
//			jumpPagesMap.put(JumpType.Search, SearchActivity.class); //搜索页面
//			jumpPagesMap.put(JumpType.Shake, ShakeActivity.class); //摇一摇
//			jumpPagesMap.put(JumpType.Scan, CaptureActivity.class); //扫一扫

			/************************* Common_Page **********************/
//			jumpPagesMap.put(JumpType.ViewLargerImage, BigPicDisplayActivity.class); //大图页面
//			jumpPagesMap.put(JumpType.Feedback, FeedbackActivity.class); //意见反馈
//			jumpPagesMap.put(JumpType.About, AboutActivity.class); //关于
			jumpPagesMap.put(JumpType.OutLinkH5,View.class); //外链h5页面(这个class文件是随便写的，在跳转时需要单独处理)
//			jumpPagesMap.put(JumpType.InnerH5, BaseWebviewActivity.class); //内嵌h5页面

			/************************* Accout_Page **********************/
//			jumpPagesMap.put(JumpType.Login, LoginActivity.class); //登录
//			jumpPagesMap.put(JumpType.Register, RegisterFindPwdActivity.class); //用户注册
//			jumpPagesMap.put(JumpType.ForgetPwd, RegisterFindPwdActivity.class); //忘记密码


			/************************* Product_Page **********************/
//			jumpPagesMap.put(JumpType.ProductList, ProductListActivity.class); //商品列表
//			jumpPagesMap.put(JumpType.ProductDetial, ProductDetailActivity.class); //商品详情页
		}
		return jumpPagesMap ;
	}
	
	/**
	 * 分发跳转
	 * @param context	依附的activity
	 * @param jumpUri	跳转参数
	 * @return
	 */
	protected static int dispatchJump(Activity context, JumpBean jumpUri){
		if(jumpUri == null || context == null ){
			return JumpCtrler.ERROR_NONSUPPORT_JUMPTYPE ;
		}
		if( JumpCtrler.SCHEME_DEFAULT.equalsIgnoreCase(jumpUri.getScheme())){
			return dispatchDefaultScheme(context, jumpUri);
		}
		return JumpCtrler.ERROR_NONE ;
	}
	
	/**
	 * 分发{@link JumpCtrler#SCHEME_DEFAULT}类型<br>
	 * 	根据参数跳到不同的页面
	 * @return
	 */
	public static int dispatchDefaultScheme(Activity context, JumpBean jumpUri){
		if(jumpUri == null || jumpUri.getJumpType() == null ){
			return JumpCtrler.ERROR_UNKNOWN ;
		}
		//检查是否是跳转到页面
		Class targetCalss = getJumpPageMap().get(jumpUri.getJumpType()) ;
		if(targetCalss != null ){
			return PageProcessor.getInstance().dispatch(context, jumpUri);//进入页面跳转
		}else {
			return EventProcessor.getInstance().dispatch(context, jumpUri);//跳转事件
		}
	}
	
	/**
	 * 检查是否需要登录
	 * @param jumpType
	 * @return
	 */
	public static boolean checkNeedLogin(JumpType jumpType){
		if( jumpType != null ){
			return getNeedLoginSet().contains(jumpType);
		}
		return false ;
	}
	/**
	 * 检查是否需要关闭
	 * @param jumpType
	 * @return
	 */
	public static boolean checkNeedClose(JumpType jumpType){
		if( jumpType != null ){
			return getNeedClogseSet().contains(jumpType);
		}
		return false ;
	}
	
}
