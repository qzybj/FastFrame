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
import brady.com.appframe.R;
import brady.com.appframe.common.ui.base.BaseActivity;
import brady.com.appframe.net.apiwrap.MyApiDelegate;
import brady.com.appframe.net.apiwrap.passport.AuthApi;
import brady.com.appframe.net.apiwrap.passport.PasswordApi;
import butterknife.BindView;
import butterknife.OnClick;
import netcore.api.ApiDelegate;
import netcore.api.BoolResponse;
import netcore.api.TipBean;


/**找回密码页面*/
public class FindPasswordActivity extends BaseActivity implements MyApiDelegate{

    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_captcha)
    EditText et_captcha;

    @BindView(R.id.cid_login_tv_hint)
    TextView tv_hint;

    @BindView(R.id.btn_submit)
    private Button btn_next;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_find_password;
    }

    @Override
    public void initContentView(View view) {
        //setTitlebarContent("设置新密码");
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @OnClick({R.id.cid_login_tv_hint, R.id.btn_submit})
    protected void clickEvent(View v) {
        switch (v.getId()) {
            case R.id.cid_login_tv_hint:
                getSmsCaptcha();
                break;
            case R.id.btn_submit:
                submit();
                break;
        }
    }

    private void submit() {
        final String account  = TextViewUtils.getText(et_phone);//电话
        final String password  = TextViewUtils.getText(et_password);//密码
        String captcha = TextViewUtils.getText(et_captcha);//验证短信
        //提交修改
        if(password.length() < 6) {
            showToast("密码不能小于6个字符");
            return;
        }
        new PasswordApi().resetPwd(account, captcha, password, new MyApiDelegate<BoolResponse>(){
            @Override
            public void onSuccess(BoolResponse rspData) {
                if(rspData.code == 200) {
                    showToast("新密码设置成功，请重新登录");
                    //跳转到用户身份类型选择页
                    Intent intent = new Intent(FindPasswordActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    showToast(rspData.message);
                }
            }

            @Override
            public void onError(TipBean rspData) {

            }
        });
    }


    /**获取短信验证码*/
    private void getSmsCaptcha(){
        String mobile = et_phone.getText().toString().trim();
        if(StringUtils.isEmpty(mobile)) {
            showToast("手机号不能为空");
            return;
        }
        //发送短信验证码
        new AuthApi().sendSmsCaptcha(mobile,this);
    }

    @Override
    public void onSuccess(Object response) {
        if(response instanceof BoolResponse){
            BoolResponse data = (BoolResponse)response;
            if (data.code == 200) {
                startSendSmsTask();
            }else {
                //发送错误
                showToast(data.message);
            }
        }
    }

    @Override
    public void onError(TipBean rspData) {

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