package earlll.com.testdemoall.core.utils;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;

import earlll.com.testdemoall.module.smslisten.broadcastreceivers.InterceptSmsReceiver;

public class SmsUtils {
    /**提示Toast*/
    public static final int MSG_WHAT_SMS_CONTENT = 0x9999;

    private Handler mCallbackHandler;
    private InterceptSmsReceiver smsReceiver = null;

    /**注册短信截取监听*/
    public void regReceiver(Context context,Handler callbackHandler){
        if(context!=null&&smsReceiver!=null&&smsHandlers!=null){
            IntentFilter localIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
            localIntentFilter.setPriority(2147483647);
            smsReceiver = new InterceptSmsReceiver(smsHandlers);
            context.registerReceiver(smsReceiver, localIntentFilter); // 动态创建一个优先级最高的短信广播接收者
        }
    }

    public void unregReceiver(Context context){
        if (smsReceiver != null) {
            try {
                context.unregisterReceiver(smsReceiver);
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }

    private Handler smsHandlers = new Handler() {
        public void handleMessage(Message msg) {
            String smsContent = getCheckCode(msg.obj.toString());
            if(StringUtils.isNotEmpty(smsContent)){
                LogUtils.i("sms", msg.obj.toString());
            }
            if(mCallbackHandler!=null){
                Message message = mCallbackHandler.obtainMessage();
                message.what = MSG_WHAT_SMS_CONTENT;
                message.obj = smsContent;
                message.sendToTarget();
            }
        }
    };

    /**
     * 截取验证码
     * @param smsCon 短信内容
     * @return 短信里的连续6位数字
     */
    public String getCheckCode(String smsCon) {
        int in = 0;
        if(StringUtils.isNotEmpty(smsCon)){
            String str = smsCon.trim();
            String str2 = "";
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                    str2 += str.charAt(i);
                    in++;
                    if (in == 6) {
                        break;
                    }
                } else {
                    if (in < 6) {
                        in = 0;
                        str2 = "";
                    }
                }
            }
            return str2;
        }
        return "";
    }
}
