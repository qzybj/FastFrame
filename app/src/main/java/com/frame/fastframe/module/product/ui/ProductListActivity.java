package com.frame.fastframe.module.product.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.frame.fastframe.R;
import com.frame.fastframe.ui.base.BaseActivity;
import com.frame.fastframelibrary.config.ConstantsCommonKey;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

public class ProductListActivity extends BaseActivity {

	public static final String KEY_INDEX_ID = ConstantsCommonKey.KEY_INDEX_ID;

	@ViewInject(R.id.banner_dotlayout)
	private Button btn_login;

	@Override
	public int getLayoutResId() {
		return R.layout.activity_product;
	}

	@Override
	public void initContentView(View view) {

	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	@Event(value={R.id.banner_dotlayout, R.id.banner_dotlayout},type = View.OnClickListener.class)
	protected void customClickEvent(View v) {
		switch (v.getId()){
			case R.id.banner_dotlayout:
				break;
		}
	}
}