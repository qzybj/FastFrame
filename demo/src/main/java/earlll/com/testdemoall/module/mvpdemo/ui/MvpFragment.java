package earlll.com.testdemoall.module.mvpdemo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.view.TextViewUtils;
import butterknife.BindView;
import butterknife.OnClick;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.ui.base.BaseFragment;
import earlll.com.testdemoall.module.mvpdemo.interfaces.IUserView;
import earlll.com.testdemoall.module.mvpdemo.presenter.UserPresenter;


public class MvpFragment extends BaseFragment implements IUserView{

    @BindView(R.id.et_id)
    EditText et_id;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_nickname)
    EditText et_nickname;

    @BindView(R.id.tv_show)
    TextView tv_content;
    @BindView(R.id.btn_save)
    Button btn_save;
    @BindView(R.id.btn_load)
    Button btn_load;
    @BindView(R.id.btn_clear)
    Button btn_clear;

    private UserPresenter mUserPresenter ;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_mvp;
    }

    @Override
    public void initContentView(View view) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mUserPresenter = new UserPresenter(this);
    }

    @OnClick({R.id.btn_save, R.id.btn_load,R.id.btn_clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                mUserPresenter.saveUser(getID(), getName(), getNickName());
                break;
            case R.id.btn_load:
                mUserPresenter.loadUser(getID ());
                break;
            case R.id.btn_clear:
                clear();
                break;
        }
    }

    private void clear() {
        et_id.setText("");
        et_name.setText("");
        et_nickname.setText("");
    }

    @Override
    public int getID() {
        try {
            return Integer.valueOf(et_id.getText().toString());
        } catch (NumberFormatException e) {
            LogUtils.e(e);
        }
        return -1;
    }

    @Override
    public String getName() {
        return et_name.getText().toString();
    }

    @Override
    public String getNickName() {
        return et_nickname.getText().toString();
    }

    @Override
    public void setName(String name) {
        et_name.setText(name);
    }

    @Override
    public void setNickName(String nickName) {
        et_nickname.setText(nickName);
    }

    @Override
    public void setError(String msg) {
        TextViewUtils.setTextViewValue(tv_content,msg);
    }
}