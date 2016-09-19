package earlll.com.testdemoall.module.demo.ui.fragment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.ui.fragment.dialog.LoginDialogFragment;
import earlll.com.testdemoall.core.ui.fragment.dialog.InputDialogFragment;


/**
 * Created by ZhangYuanBo on 2016/5/27.
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.tv_fragment_content)
    TextView tv_content;
    @BindView(R.id.btn_inputdialog)
    Button btn_inputdialog;
    @BindView(R.id.btn_logindialog)
    Button btn_logindialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);
        tv_content.setText("this is " + this.getClass().getName());
        return view;
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