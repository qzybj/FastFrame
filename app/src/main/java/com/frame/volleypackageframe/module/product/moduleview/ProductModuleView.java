package com.frame.volleypackageframe.module.product.moduleview;

import android.content.Context;
import android.view.View;
import com.frame.volleypackageframe.R;
import com.frame.volleypackageframe.module.product.moduleview.bean.BaseModuleViewBean;
import com.frame.volleypackageframe.module.product.moduleview.utils.LogUtils;

/**模块化View - 商品View*/
public class ProductModuleView extends BaseModuleView {

	public ProductModuleView(Context context, ModuleViewType type){
		super(context,type);
	}

	@Override
	public int getLayoutResouceId() {
		return R.layout.moduleview_product;
	}

	@Override
	public void initContentView(View mRootView) {
		if (mRootView!=null) {
			try {
				//body_sv = (ScrollView)mRootView.findViewById(R.id.goodsreturned_body_sv);
			} catch (Exception e) {
				LogUtils.e(e.getLocalizedMessage());
			}
		}
	}

	@Override
	public void refreshData(BaseModuleViewBean bean) {

	}

}