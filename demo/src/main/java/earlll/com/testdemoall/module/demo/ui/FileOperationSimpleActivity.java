package earlll.com.testdemoall.module.demo.ui;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import java.io.File;
import butterknife.BindView;
import butterknife.OnClick;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.ui.base.BaseActivity;

/**
 * 测试数据生成器，使用示例
 */
public class FileOperationSimpleActivity extends BaseActivity {
    private static final String TAG = FileOperationSimpleActivity.class.getSimpleName();

    @BindView(R.id.btn_1)
    public Button btn_1;
    @BindView(R.id.btn_2)
    public Button btn_2;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_common_button;
    }

    @Override
    public void initContentView(View view) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_1, R.id.btn_2})
    public void customClickEvent(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                test();

                break;
            case R.id.btn_2:

                break;
        }
    }

    private void test() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File path = Environment.getExternalStorageDirectory();

        }
    }
}