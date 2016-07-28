package com.frame.fastframelibrary.utils.dataprocess;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import com.frame.fastframelibrary.utils.view.TextViewUtils;

/**
 * Created by ZhangYuanBo on 2016/5/9.
 */
// TODO: 2016/5/9 增加用户名、密码校验工具类
public class PassWordUtils {
    /**
     * 检查用户名
     *
     * @return
     */
    public boolean checkUserName(Context con, TextView userNameET, ITextViewValueProcessListener listener) {
        boolean returnValue = true;
        String tvValue = TextViewUtils.getTextViewValue(userNameET);
        if (StringUtils.isEmpty(tvValue)) {
            showToast(con, "用户名不能为空", false);
            return false;
        }
        if (listener != null) {
            returnValue = listener.processValueReturn(tvValue);
        }
        return returnValue;
    }

    /**
     * 检查密码
     *
     * @return
     */
    private boolean checkPassword(Context con, TextView mETPassword, TextView mETConfirmPassword) {
        String password = TextViewUtils.getTextViewValue(mETPassword);
        String confirmPassword = TextViewUtils.getTextViewValue(mETConfirmPassword);
        //判断输入密码是否为空
        if (null == password || 0 == password.length()) {
            showToast(con, "请输入密码", false);
            return false;
        }
        //判断密码长度
        if (6 > password.length() || 16 < password.length()) {
            showToast(con, "密码必须是6到16位字符", false);
            return false;
        }
        //判断密码中是否有汉字
        if (StringUtils.isHanzi(password)) {
            showToast(con, "密码必须是英文字母、特殊字符、数字", false);
            return false;
        }
        //判断密码中是否有空格
        if (StringUtils.isHaveBlank(password)) {
            showToast(con, "密码必须是英文字母、特殊字符、数字", false);
            return false;
        }
        //判断输入确认密码是否为空
        if (null == confirmPassword || 0 == confirmPassword.length()) {
            showToast(con, "请输入确认密码", false);
            return false;
        }
        //判断输入密码和确认密码是否一致
        if (!password.equals(confirmPassword)) {
            showToast(con, "两次输入不一致", false);
            return false;
        }
        return true;
    }

    /**
     * 检查支付密码
     *
     * @return
     */
    private boolean checkChangePassword(Context con, TextView currentPassWordTV, TextView newPassWordTV, TextView confirmPasswordTV) {
        String currentPassWord = TextViewUtils.getTextViewValue(currentPassWordTV);
        String newPassWord = TextViewUtils.getTextViewValue(newPassWordTV);
        String confirmPassword = TextViewUtils.getTextViewValue(confirmPasswordTV);

        if (StringUtils.isEmpty(currentPassWord)) {//判断输入原密码是否为空
            showToast(con, "请输入原密码", false);
            return false;
        }
        if (StringUtils.isEmpty(newPassWord)) {//判断输入密码是否为空
            showToast(con, "请输入您的密码", false);
            return false;
        }
        if (6 > currentPassWord.length() || 16 < currentPassWord.length()) {//判断密码长度
            showToast(con, "密码必须是6到16位字符", false);
            return false;
        }

        if (StringUtils.isHanzi(currentPassWord)) {//判断密码中是否有汉字
            showToast(con, "密码必须是英文字母、特殊字符、数字", false);
            return false;
        }
        //判断密码中是否有空格
        if (StringUtils.isHaveBlank(currentPassWord)) {
            showToast(con, "密码必须是英文字母、特殊字符、数字", false);
            return false;
        }
        //判断输入确认密码是否为空
        if (null == confirmPassword || 0 == confirmPassword.length()) {
            showToast(con, "请输入确认密码", false);
            return false;
        }
        //判断输入密码和确认密码是否一致
        if (!currentPassWord.equals(confirmPassword)) {
            showToast(con, "两次输入不一致", false);
            return false;
        }
        return true;
    }

    /**
     * 检查输入的手机号
     * @return
     */
    private boolean checkPhone(Context con, TextView phoneET) {
        String phone = TextViewUtils.getTextViewValue(phoneET);
        if (StringUtils.isEmpty(phone)) {
            showToast(con, "请输入手机号码", false);
            return false;
        }
        if (!StringUtils.isPhone(phone)) {
            showToast(con, "手机号格式错误", false);
            return false;
        }
        return true;
    }

    /**
     * 倒计时类
     */
    private class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            //mTVGetVerifyCode.setText((millisUntilFinished/1000)+"",false);
        }

        @Override
        public void onFinish() {
            //mTVGetVerifyCode.setClickable(true);
            //mTVGetVerifyCode.setEnabled(true);
            //重新获取
            //mTVGetVerifyCode.setText(R.string.bind_phone_re_get);
        }
    }

    private static void showToast(Context con, String msg, boolean isLongTime) {}

    /**用来处理TextView类检查的额外逻辑*/
    public interface ITextViewValueProcessListener {
        /**
         * 获取的TextView的Value值并返回，供用户做自定义处理
         * @param textViewValue
         * @return
         */
        boolean processValueReturn(String textViewValue);
    }
}