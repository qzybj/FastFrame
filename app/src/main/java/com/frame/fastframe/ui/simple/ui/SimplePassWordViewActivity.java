package com.frame.fastframe.ui.simple.ui;

import android.os.Bundle;
import android.view.View;
import com.frame.fastframe.R;
import com.frame.fastframe.ui.base.BaseActivity;
import com.frame.fastframelibrary.view.PasswordView;
import org.xutils.view.annotation.ViewInject;

public class SimplePassWordViewActivity extends BaseActivity {

    @ViewInject(R.id.password_til)
    PasswordView password_til;
    @ViewInject(R.id.password_strike)
    PasswordView password_strike;

    @Override
    public int getLayoutResouceId() {
        return R.layout.activity_simple_passwordview;
    }

    @Override
    public void initContentView(View view) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

}
