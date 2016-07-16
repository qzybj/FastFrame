package earlll.com.testdemoall.aosp.eventbus.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import org.greenrobot.eventbus.EventBus;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.aosp.eventbus.bean.MessageEvent;

/**EventBus 、ButterKnife 使用示例*/
public class EventBusSendActivity extends Activity {

    private int sendNum =0;
    @BindView(R.id.btn_show)
    Button btnShow;
    @OnClick(R.id.btn_show)
    public void onClick() {
        String sendText = "send "+(sendNum++)+" message !!!";
        EventBus.getDefault().post(new MessageEvent(sendText));
        tvShow.setText(sendText);
    }
    @BindView(R.id.tv_show)
    TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResouceId());
        ButterKnife.bind(this);
    }

    public int getLayoutResouceId() {
        return R.layout.activity_eventbus_simple;
    }
}
