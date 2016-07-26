package earlll.com.testdemoall.module.annotationdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.frame.fastframelibrary.utils.GsonUtils;
import com.frame.fastframelibrary.utils.ListUtils;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.StringUtils;
import com.frame.fastframelibrary.utils.TextViewUtils;
import com.google.gson.annotations.SerializedName;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.lang.reflect.Field;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.aosp.eventbus.bean.MessageEvent;
import earlll.com.testdemoall.module.annotationdemo.annotation.AnnotationTest;
import earlll.com.testdemoall.module.annotationdemo.bean.AnnotationTestBean;


/** 注解 使用示例*/
public class SimpleAnnotationActivity extends Activity {

    public static final String TAG = "annotation";

    @BindView(R.id.btn_show)
    Button btnShow;

    @OnClick(R.id.btn_show)
    public void onClick() {

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
        return R.layout.activity_simple_annotation;
    }

    public void initContentView(View view) {}

    public void initData(Bundle savedInstanceState) {
        getClassAnnotation(AnnotationTestBean.class);
        AnnotationTestBean bean = new AnnotationTestBean();
        bean.name = "aaaaa";
        bean.likename ="bbbbb";
        LogUtils.d(TAG,"json = "+GsonUtils.toJson(bean).toString());
    }

    /**
     * 分装POST请求参数
     * @return
     */
    public <T> void getClassAnnotation(Class<T> t) {
        try {
            Field[] fields = t.getFields();
            for (int i = 0; i < fields.length; i++) {
                Field t_field = fields[i];
                String fieldName = t_field.getName();
                AnnotationTest AnnotationTestInfo = t_field.getAnnotation(AnnotationTest.class);
                if(AnnotationTestInfo!=null){
                    AnnotationTest.Type[] keys = AnnotationTestInfo.value();
                    if(!ListUtils.isEmptyArray(keys)){
                        String tmp="";
                        for (AnnotationTest.Type type:keys) {
                            LogUtils.d(TAG,type.name());
                            tmp+=type.name()+",";
                        }
                        LogUtils.d(TAG,fieldName+"["+tmp+"]");
                    }
                }
                SerializedName SerializedNameInfo = t_field.getAnnotation(SerializedName.class);
                if(SerializedNameInfo!=null){
                    String key = SerializedNameInfo.value();
                    if(StringUtils.isNotEmpty(key)){
                        LogUtils.d(TAG,key);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
