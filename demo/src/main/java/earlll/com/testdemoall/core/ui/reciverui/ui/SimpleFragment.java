package earlll.com.testdemoall.core.ui.reciverui.ui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.ui.base.BaseFragment;
import earlll.com.testdemoall.core.ui.fragment.dialog.InputDialogFragment;
import earlll.com.testdemoall.core.ui.fragment.dialog.LoginDialogFragment;


/**
 * Created by ZhangYuanBo on 2016/5/27.
 */
public class SimpleFragment extends BaseFragment {

    @BindView(R.id.tv_fragment_content)
    TextView tv_content;
    @BindView(R.id.btn_inputdialog)
    Button btn_inputdialog;
    @BindView(R.id.btn_logindialog)
    Button btn_logindialog;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_content;
    }

    @Override
    public void initContentView(View view) {
        tv_content.setText("this is " + this.getClass().getName());
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_inputdialog, R.id.btn_logindialog})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_inputdialog:
                InputDialogFragment inputDialogFragment = new InputDialogFragment();
                showFragment(inputDialogFragment);
                break;
            case R.id.btn_logindialog:
                LoginDialogFragment loginDialogFragment = new LoginDialogFragment();
                showFragment(loginDialogFragment);
                break;
        }
    }

    private void showFragment(DialogFragment targetFragment){
        if(getActivity()!=null&&!getActivity().isFinishing()&&targetFragment!=null){
            targetFragment.show(getFragmentManager(), "loginDialog");
        }
    }
}