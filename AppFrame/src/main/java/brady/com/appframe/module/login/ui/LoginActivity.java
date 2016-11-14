package brady.com.appframe.module.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import brady.com.appframe.MainActivity;
import brady.com.appframe.R;
import brady.com.appframe.common.bean.UserBean;
import brady.com.appframe.common.ui.base.BaseActivity;
import brady.com.appframe.common.utils.UserManager;
import brady.com.appframe.net.apiwrap.MyApiDelegate;
import brady.com.appframe.net.apiwrap.passport.AuthApi;
import brady.com.appframe.net.apiwrap.passport.response.UserinfoResponse;
import brady.com.appframe.net.models.passport.UserinfoBean;
import butterknife.BindView;
import butterknife.OnClick;
import netcore.api.TipBean;

/**登录页面*/
public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_account)
    EditText et_account;

    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.tv_register)
    TextView tv_register;

    @BindView(R.id.btn_login)
    Button btn_login;

    @BindView(R.id.tv_forgetpassword)
    TextView tv_forgetpassword;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    public void initContentView(View view) {
        //setTitlebarContent("登陆");
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @OnClick({R.id.tv_register, R.id.tv_forgetpassword,R.id.btn_login})
    protected void clickEvent(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                register();
                break;
            case R.id.tv_forgetpassword:
                forgetPassword();
                break;
            case R.id.btn_login:
                login();
                break;
        }
    }
    /**注册*/
    private void register() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void login() {
        final String account = et_account.getText().toString();
        final String password = et_password.getText().toString();
        new AuthApi().login(account, password, new MyApiDelegate<UserinfoResponse>() {
            @Override
            public void onSuccess(UserinfoResponse rspData) {
                if(rspData!=null){
                    loginSuccess(rspData.data,account, password);
                }
            }

            @Override
            public void onError(TipBean tip) {
                showToast(tip.message);
            }
        });
    }


    /**登录成功*/
    private void loginSuccess(UserinfoBean bean,String account, String password)  {
        UserBean accountBean = UserManager.instance().convert2Bean(bean);
        UserManager.instance().setUserInfo(accountBean);
        UserManager.instance().setAutoLoginInfo(account, password);

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    /**忘记密码*/
    private void forgetPassword() {
        Intent intent = new Intent(LoginActivity.this, FindPasswordActivity.class);
        startActivity(intent);
    }

    @Override
    public void onTitleBarClick(View v) {}
}