package brady.com.appframe.module.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import com.frame.fastframelibrary.utils.task.CountDownTimerTask;
import com.frame.fastframelibrary.utils.view.TextViewUtils;
import brady.com.appframe.MainActivity;
import brady.com.appframe.R;
import brady.com.appframe.common.bean.AccountBean;
import brady.com.appframe.common.ui.base.BaseActivity;
import brady.com.appframe.common.utils.UserManager;
import brady.com.appframe.net.apiwrap.MyApiDelegate;
import brady.com.appframe.net.apiwrap.passport.AuthApi;
import brady.com.appframe.net.apiwrap.passport.response.UserinfoResponse;
import brady.com.appframe.net.models.passport.UserinfoBean;
import butterknife.BindView;
import butterknife.OnClick;
import netcore.api.BoolResponse;
import netcore.api.TipBean;

/**注册页面*/
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_captcha)
    EditText et_captcha;
    @BindView(R.id.tv_hint)
    TextView tv_hint;
    @BindView(R.id.btn_next)
    Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    public void initContentView(View view) {
        //setTitlebarContent("注册"); //设置标题
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }


    @OnClick({R.id.tv_hint, R.id.btn_next})
    protected void clickEvent(View v) {
        switch (v.getId()) {
            case R.id.tv_hint:
                getSmsCaptcha();
                break;
            case R.id.btn_next:
                btnNext();
                break;
        }
    }

    private void getSmsCaptcha() {
        String mobile = et_phone.getText().toString().trim();
        if(StringUtils.isEmpty(mobile)) {
            showToast("手机号不能为空");
            return;
        }
        getSmsCaptcha(mobile);
    }

    /** 下一步 */
    private void btnNext() {
        //下一步
        String name  = TextViewUtils.getText(et_name);//昵称
        final String account  = TextViewUtils.getText(et_phone);//电话
        final String password  = TextViewUtils.getText(et_password);//密码
        String captcha = TextViewUtils.getText(et_captcha);//验证短信
        //提交注册
        if(StringUtils.isEmpty(name) || name.length()<3) {
            showToast("昵称不能小于三个字符");
            return;
        }
        if(password.length() < 6) {
            showToast("密码不能小于6个字符");
            return;
        }
        new AuthApi().reg(account, password, captcha, name, new MyApiDelegate<UserinfoResponse>(){
            @Override
            public void onSuccess(UserinfoResponse rspData) {
                if (rspData.code == 200) {
                    //注册成功，跳转到用户身份选择
                    loginSuccess(rspData.data, account, password);
                } else {
                    showToast(rspData.message);
                }
            }
            @Override
            public void onError(TipBean tipObj) {
                showToast(tipObj.message);
            }
        });
    }

    /**登录成功*/
    private void loginSuccess(UserinfoBean bean, String account, String password)  {
        AccountBean accountBean = UserManager.instance().convert2Bean(bean);
        UserManager.instance().setUserInfo(accountBean);
        UserManager.instance().setAutoLoginInfo(account, password);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**检查输入参数*/
    private boolean checkInput(){
        String name  = TextViewUtils.getText(et_name);//昵称
        String phone  = TextViewUtils.getText(et_phone);//电话
        String password  = TextViewUtils.getText(et_password);//密码
        String message = TextViewUtils.getText(et_captcha);//验证短信

        //检查该手机号是否已经注册
        //检查密码等是否为空
        return false;
    }

    /**获取短信验证码*/
    private void getSmsCaptcha(String mobile){
        //发送短信验证码
        new AuthApi().sendSmsCaptcha(mobile, new MyApiDelegate<BoolResponse>() {
            @Override
            public void onSuccess(BoolResponse rspData) {
                if (rspData.code == 200) {
                    startSendSmsTask();
                }else {
                    //发送错误
                    showToast(rspData.message);
                }
            }
            @Override
            public void onError(TipBean rspData) {

            }
        });
    }

    @Override
    public void onTitleBarClick(View v) {}


    private CountDownTimerTask timeCount;
    /**
     * 用于重新发送短信的倒计时
     */
    private void startSendSmsTask(){
        timeCount = new CountDownTimerTask(
                60*CountDownTimerTask.SECOND,
                CountDownTimerTask.SECOND,
                new CountDownTimerTask.CountDownTimerCallback() {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        tv_hint.setClickable(false);
                        TextViewUtils.setTextViewValue(tv_hint, millisUntilFinished/CountDownTimerTask.SECOND + "s后重新获取");
                    }
                    @Override
                    public void onFinish() {
                        tv_hint.setClickable(true);
                        TextViewUtils.setTextViewValue(tv_hint, "重新获取");
                    }
                });
        timeCount.start();
    }

    @Override
    protected void onDestroy() {
        if(timeCount!=null){
            timeCount.cancel();
            timeCount.onFinish();
            timeCount= null;
        }
        super.onDestroy();
    }
}
