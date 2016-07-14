package com.frame.fastframe.module.html5.jump.processor.page;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.frame.fastframe.module.common.constant.ConstantsCommonKey;
import com.frame.fastframe.module.html5.jump.JumpCtrler;
import com.frame.fastframe.module.html5.jump.JumpDispatcher;
import com.frame.fastframe.module.html5.jump.JumpType;
import com.frame.fastframe.module.html5.jump.JumpUtil;
import com.frame.fastframe.module.html5.jump.bean.JumpBean;
import com.frame.fastframe.module.html5.jump.bean.JumpCheckResult;
import com.frame.fastframe.module.html5.jump.interfaces.IJumpProcessor;
import com.frame.fastframe.module.previewimage.ui.PreviewImagesActivity;
import com.frame.fastframe.module.product.ui.ProductListActivity;
import com.frame.fastframe.module.search.ui.SearchActivity;
import com.frame.fastframelibrary.utils.MapUtils;
import com.frame.fastframelibrary.utils.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 	页面处理者
 */
public class PageProcessor implements IJumpProcessor {

	private static PageProcessor instance = null ;
	private PageProcessor() {}
	
	/**
	 * 获取实例
	 * @return
	 */
	public static PageProcessor getInstance(){
		if( instance == null ){
			instance = new PageProcessor();
		}
		return instance ;
	}
	
	@Override
	public int dispatch(Activity context, JumpBean jumpBean) {
		if (JumpType.OutLinkH5 == jumpBean.getJumpType()) {
			//跳到外部浏览器需单独处理
			return jump2Browser(context, jumpBean);
		}
		
		//检查登录和参数信息
		JumpCheckResult result = checkJump(jumpBean);
		if( result == null ||
				!result.isParamExist() ){
			//参数不正确
			return JumpCtrler.ERROR_NONSUPPORT_PARAM_NOTEXIST ;
		}
		
		//跳转到页面
		Intent jumpIntent = JumpUtil.parseJumpParams2Intent(context,
				JumpDispatcher.getJumpPageMap().get(jumpBean.getJumpType()), jumpBean.getParamMap());
		//添加本地跳转所需参数
		jumpIntent = appendLocalParam(jumpIntent, jumpBean);
		return JumpUtil.startActivity(context, jumpIntent, result,jumpBean.getRequestCode());
	}
	
	/**
	 * 根据不同的类型，添加或者转换本地需要传递的参数
	 * @param jumpIntent
	 * @param jumpUri
	 * @return
	 */
	private Intent appendLocalParam(Intent jumpIntent, JumpBean jumpUri){
		if( jumpIntent == null || jumpUri == null ||
				jumpUri.getJumpType() == null ){
			return jumpIntent ;
		}
		JumpType jumpType = jumpUri.getJumpType();

		boolean isNotEmpty = MapUtils.isNotEmpty(jumpUri.getParamMap());//检测参数Map是否为空

		if( JumpType.ProductList == jumpType){
			//商品列表
			if(isNotEmpty){
				String title = jumpUri.getParamMap().get(ConstantsCommonKey.KEY_TITLE);
				if(StringUtils.isNotEmpty(title)){
					jumpIntent.putExtra(ConstantsCommonKey.KEY_TITLE, title);
				}
			}
		}else if( JumpType.InnerH5 == jumpType ){
			//内嵌H5不显示底部栏
			if(isNotEmpty){
				String title = jumpUri.getParamMap().get(ConstantsCommonKey.KEY_TITLE);
				if(StringUtils.isNotEmpty(title)){
					jumpIntent.putExtra(ConstantsCommonKey.KEY_TITLE, title);
				}
			}
		}else if( JumpType.ViewLargerImage == jumpType ){
			//查看大图页面
			if (isNotEmpty) {
				//图片集合
				String imgUrls=StringUtils.format(jumpUri.getParamMap().get(PreviewImagesActivity.KEY_IMGURLS));
				if(StringUtils.isNotEmpty(imgUrls)){
					ArrayList<String> imgUrlList = StringUtils.str2ArrayList(imgUrls,",",true);
					int defIndex = StringUtils.str2Int(jumpUri.getParamMap().get(PreviewImagesActivity.KEY_IMG_INDEX),0);
					if(defIndex>=imgUrlList.size()){
						defIndex=0;
					}
					jumpIntent.putExtra(PreviewImagesActivity.KEY_IMG_INDEX, defIndex);
					jumpIntent.putExtra(PreviewImagesActivity.KEY_IMGURLS, imgUrlList);
				}
			}
		}else if( JumpType.Register == jumpType ){
			//用户注册
			if(isNotEmpty){

			}
		}else if( JumpType.ForgetPwd == jumpType ){
			//忘记密码
			if(isNotEmpty){

			}
		}
		
		return jumpIntent ;
	}

	/**
	 * 跳到外部浏览器
	 * @param context
	 * @param jumpUri
	 * @return
	 */
	private int jump2Browser(Activity context, JumpBean jumpUri){
		if( context == null || jumpUri == null ){
			return JumpCtrler.ERROR_UNKNOWN ;
		}
		if (JumpType.OutLinkH5 == jumpUri.getJumpType()) {
			//打开外部浏览器
			Intent jumpIntent = new Intent();
			jumpIntent.setAction(Intent.ACTION_VIEW);
			if(JumpUtil.paramExist(jumpUri.getParamMap(), ConstantsCommonKey.KEY_URL)){
				//设置外部浏览器打开参数
				String url = StringUtils.format(jumpUri.getParamMap().get(ConstantsCommonKey.KEY_URL));
				if(!url.toLowerCase().startsWith("http://") &&
						!url.toLowerCase().startsWith("https://")){
					//当url不是以http或者https开头时，默认加上http，防止调用浏览器崩溃
					url = "http://" + url ;
				}
				Uri content_url = Uri.parse(url);
				jumpIntent.setData(content_url);  
				//启动浏览器
				context.startActivity(jumpIntent);
			}else {
				//参数不正确
				return JumpCtrler.ERROR_NONSUPPORT_PARAM_NOTEXIST ;
			}
		}
		return JumpCtrler.ERROR_NONE ;
	}
	
	/*****************************参数验证**************************/

	/**
	 * 检查传入参数以及是否需要登录
	 * @param jumpBean
	 * @return
	 */
	public JumpCheckResult checkJump(JumpBean jumpBean){
		if( jumpBean == null ||jumpBean.getJumpType()==null){
			return null ;
		}
		//检查是否需要登录
		boolean needLogin = JumpDispatcher.checkNeedLogin(jumpBean.getJumpType());
		//检查是否需要关闭
		boolean needClose = JumpDispatcher.checkNeedClose(jumpBean.getJumpType());
		//检查参数是否正确
		boolean paramExist = checkParams(jumpBean) ;
		return new JumpCheckResult(needClose,needLogin,paramExist, jumpBean.getJumpType());
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
		if( JumpType.InnerH5 == jumpType){
			//内嵌h5页面
			isExist = JumpUtil.paramExist(paramsMap, ConstantsCommonKey.KEY_TITLE,ConstantsCommonKey.KEY_URL);
		}else if( JumpType.ProductList == jumpType){
			//商品列表
			isExist = JumpUtil.paramExist(paramsMap,ProductListActivity.KEY_INDEX_ID);
		}else if( JumpType.Search == jumpType){
			// 搜索页
			isExist = JumpUtil.paramExist(paramsMap, SearchActivity.KEY_KEYWORD);
		}
		return isExist ;
	}
}
