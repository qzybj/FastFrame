package earlll.com.testdemoall.module.smslisten.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsMessage;
import com.frame.fastframelibrary.utils.LogUtils;


/**
 * 拦截监听短信
 */
public class InterceptSmsReceiver extends BroadcastReceiver {
    public static final String TAG = "ImiChatSMSReceiver";
    public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private Handler mSmsHandlers;

    public InterceptSmsReceiver(Handler smsHandler){
        super();
        this.mSmsHandlers = smsHandler;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVED_ACTION)) {
            SmsMessage[] messages = getMessagesFromIntent(intent);
            for (SmsMessage smsMessage : messages) {
                if(mSmsHandlers!=null){
                    Message msg = mSmsHandlers.obtainMessage();
                    msg.obj = smsMessage.getDisplayMessageBody();
                    mSmsHandlers.sendMessage(msg);
                    LogUtils.i(TAG, smsMessage.getOriginatingAddress() + " : " +
                            smsMessage.getDisplayOriginatingAddress() + " : " +
                            smsMessage.getDisplayMessageBody() + " : " +
                            smsMessage.getTimestampMillis());
                }
            }
        }
    }

    public SmsMessage[] getMessagesFromIntent(Intent intent) {
        Object[] messages = (Object[]) intent.getSerializableExtra("pdus");
        byte[][] pduObjs = new byte[messages.length][];
        for (int i = 0; i < messages.length; i++) {
            pduObjs[i] = (byte[]) messages[i];
        }
        byte[][] pdus = new byte[pduObjs.length][];
        int pduCount = pdus.length;
        SmsMessage[] msgs = new SmsMessage[pduCount];
        for (int i = 0; i < pduCount; i++) {
            pdus[i] = pduObjs[i];
            msgs[i] = SmsMessage.createFromPdu(pdus[i]);
        }
        return msgs;
    }
}