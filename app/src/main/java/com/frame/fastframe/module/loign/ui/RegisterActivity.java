package com.frame.fastframe.module.loign.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.frame.fastframe.R;
import com.frame.fastframe.ui.base.BaseActivity;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

public class RegisterActivity extends BaseActivity {

	@ViewInject(R.id.banner_dotlayout)
	private Button btn_login;

	@Override
	public int getLayoutResouceId() {
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