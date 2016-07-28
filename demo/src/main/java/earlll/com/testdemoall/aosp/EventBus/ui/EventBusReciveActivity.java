package earlll.com.testdemoall.aosp.eventbus.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.frame.fastframelibrary.utils.dataprocess.IntentUtils;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import com.frame.fastframelibrary.utils.view.TextViewUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.module.demo.bean.TestBean;
import earlll.com.testdemoall.aosp.eventbus.bean.MessageEvent;

/**EventBus 、ButterKnife 使用示例*/
public class EventBusReciveActivity extends Activity {

    @BindView(R.id.btn_show)
    Button btnShow;

    @OnClick(R.id.btn_show)
    public void onClick() {
        goActivity(getTestBean("单个item展示",EventBusSendActivity.class.getName()));
    }
    @BindView(R.id.tv_show)
    TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResouceId());
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        initContentView(null);
        initData(savedInstanceState);
    }

    public int getLayoutResouceId() {
        return R.layout.activity_eventbus_simple;
    }

    public void initContentView(View view) {
    }

    public void initData(Bundle savedInstanceState) {

    }

    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe
    public void onEvent(MessageEvent event) {
        // UI updates must run on MainThread
        Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show();
        String text =TextViewUtils.getTextViewValue(tvShow);
        if(StringUtils.isNotEmpty(text)){
            text += "\n"+event.message;
        }else{
            text = event.message;
        }
        tvShow.setText(text);
    }

    protected void  goActivity(TestBean data){
        Intent intent = new Intent();
        intent.setClassName(this,data.getText());
        if(!IntentUtils.isEmpty(data.getArgs())){
            intent.putExtras(data.getArgs());
        }
        startActivity(intent);
    }

    public  static TestBean getTestBean(String describe,String targetActivity) {
        TestBean bean = new TestBean();
        bean.setName(describe);
        bean.setText(targetActivity);
        bean.setDate("2015-12-01");
        bean.setImageurl("http://p10.ytrss.com/product/20/647/7390/ViewImage/3490.jpg");
        return bean;
    }
}
