package com.frame.fastframe.module.product.moduleview;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.frame.fastframe.module.product.moduleview.interfaces.ModuleViewInterface;
import com.frame.fastframe.module.product.moduleview.utils.LogUtils;
import com.frame.fastframe.utils.ImageViewHelper;
import com.frame.fastframe.utils.ViewUtils;
import org.xutils.x;


/**模块化View实现Base类*/
public abstract class BaseModuleView implements ModuleViewInterface {
	public String TAG = "BaseModuleView";
	private Context mContext = null;
	/**用来区分模板类型*/
	private ModuleViewType modelViewType = null ;
	/** 根View */
	public View mRootView = null;
	public BaseModuleView mParentVeiw;
	/** Views indexed with their IDs */
	private final SparseArray<View> views;
	/** 用于放置动态生成的View控件 */
	public LinearLayout dlayout_root = null;

	public BaseModuleView(Context context, ModuleViewType type){
		this.mContext = context;
		this.mParentVeiw = this;
		this.views = new SparseArray<View>();
		this.modelViewType = type;
		createView();
	}

	public Context getModuleViewContext(){
		return mContext ;
	}

	public BaseModuleView getParentVeiw(){
		return mParentVeiw ;
	}
	/**
	 * 实现根布局，并返赋值给 mRootView
	 * @return
	 */
	private void createView() {
		try {
			int layoutResouceId = getLayoutResouceId();
			mRootView  = ViewUtils.inflateView(getModuleViewContext(),layoutResouceId);
			if (mRootView != null) {
				x.view().inject(mRootView);//Xutils3 调用view绑定
				initContentView(mRootView);
			}
		} catch (Exception e) {
			LogUtils.e(e.getLocalizedMessage());
		}
	}

	/**
	 * 返回根布局
	 * @return
	 */
	public View getRootView() {
		return mRootView;
	}

	/**
	 * 获取当前模板的类型
	 * @return
	 */
	public ModuleViewType getModelViewType(){
		return modelViewType ;
	}


	/**
	 * 跳转逻辑
	 */
	public void jumpToURI(String jumpUri){
//		if (mContext instanceof UCropBaseActivity) {
//			JumpCtrler.doJump((UCropBaseActivity)mContext,jumpUri);
//		}else {
//			LogUtil.d("context is not support");
//		}
	}
	
	/**
	 * 加载图片(不设置图片点击跳转事件)
	 * @param imageView
	 * @param url
	 */
	public void loadingImg(ImageView imageView, String url){
		ImageViewHelper.loadImageUrl(mContext,imageView,url);
	}

    /**
     * 释放所有资源
     */
    public void release(){
    	
    }

	/**
	 * 控制显示或者隐藏
	 * @param isVisible
	 */
	public void setRootVisible(boolean isVisible){
		if( mRootView != null ){
			if( isVisible ){
				mRootView.setVisibility(View.VISIBLE);
			}else {
				mRootView.setVisibility(View.GONE);
			}
		}
	}

	public <T extends View> T getView(int viewId)
	{
		return retrieveView(viewId);
	}

	@SuppressWarnings("unchecked")
	protected <T extends View> T retrieveView(int viewId){
		View view = views.get(viewId);
		if (view == null){
			view = mRootView.findViewById(viewId);
			views.put(viewId, view);
		}
		return (T) view;
	}
}