package com.frame.fastframe.module.loign.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.frame.fastframe.R;
import com.frame.fastframe.ui.base.BaseActivity;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

public class LoginActivity extends BaseActivity {

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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}