package com.frame.fastframelibrary.view;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.widget.Button;

import com.frame.fastframelibrary.utils.dataprocess.StringUtils;


/**
 *  倒计时Button
 */
public class CountDownButton extends Button {
    private int SECOND = 1000;

    public CountDownButton(Context context) {
        super(context);
    }

    public CountDownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountDownButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 开始倒计时(默认处理，设置按钮为不可点击，默认)
     * @param totalTime 倒计时总计时间
     * @param resetMsg 倒计时结束后显示的文字
     */
    public void startCountDown(long totalTime,String resetMsg) {
        startCountDown(totalTime,resetMsg,null);
    }

    /**
     * 开始倒计时(默认处理，设置按钮为不可点击，默认)
     * @param totalTime 倒计时总计时间
     * @param callback 倒计时触发事件回调
     */
    public void startCountDown(long totalTime,CountDownCallback callback) {
        startCountDown(totalTime,null,callback);
    }

    /**
     * 开始倒计时(自定义处理UI)
     * @param totalTime 倒计时总计时间
     * @param callback 倒计时触发事件回调
     */
    public void startCountDown(long totalTime,final String resetMsg,final CountDownCallback callback) {
        setClickable(false);
        CountDownTimerTask mc = new CountDownTimerTask(totalTime, SECOND,
                new CountDownCallback() {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if(callback!=null){
                            callback.onTick(millisUntilFinished);
                        }
                    }
                    @Override
                    public void onFinish() {
                        setClickable(true);
                        if(callback!=null){
                            callback.onFinish();
                        }
                        if(StringUtils.isNotEmpty(resetMsg)){
                            setText(resetMsg);
                        }
                    }
                });
        mc.start();
    }

    /**
     * 倒计时类 - 用于验证码计时
     */
    private class CountDownTimerTask extends CountDownTimer {
        private CountDownCallback mCallBack;

        /**
         *
         * @param millisInFuture 倒计时总计
         * @param countDownInterval 触发onTick方法的间隔
         * @param callback 回调传递
         */
        public CountDownTimerTask(long millisInFuture, long countDownInterval,CountDownCallback callback) {
            super(millisInFuture, countDownInterval);
            this.mCallBack = callback;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if(mCallBack!=null){
                mCallBack.onTick(millisUntilFinished);
            }
        }

        @Override
        public void onFinish() {
            if(mCallBack!=null){
                mCallBack.onFinish();
            }
        }
    }
    public interface CountDownCallback {
        void onTick(long millisUntilFinished);
        void onFinish();
    }
}
