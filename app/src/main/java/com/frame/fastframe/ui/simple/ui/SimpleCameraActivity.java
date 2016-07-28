package com.frame.fastframe.ui.simple.ui;

import android.os.Bundle;
import android.view.View;

import com.frame.fastframe.R;
import com.frame.fastframe.ui.base.BaseActivity;

import org.xutils.view.annotation.Event;

/**Camera调用示例*/
public class SimpleCameraActivity extends BaseActivity {
    public static final int TAG_CREATE=0x01;
    public static final int TAG_DELETE=0x02;
    public static final int TAG_MODIFY=0x03;

    @Event(value = R.id.btn_popwin1, type = View.OnClickListener.class)
    private void clickBtn1(View view) {
        popStyle4List();
    }

    @Override
    public int getLayoutResouceId() {
        return R.layout.activity_simple_camera;
    }

    @Override
    public void initContentView(View view) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    public void popStyle4List() {
    }


}
