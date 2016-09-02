package earlll.com.testdemoall.module.testnetframe.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.frame.fastframelibrary.net.core.NetDataServer;
import com.frame.fastframelibrary.net.core.bean.NetResponse;
import com.frame.fastframelibrary.net.core.interfaces.IErrorInfo;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import com.frame.fastframelibrary.utils.view.TextViewUtils;
import butterknife.BindView;
import butterknife.OnClick;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.ui.base.BaseActivity;
import earlll.com.testdemoall.module.testnetframe.bean.UserinfoBean;
import earlll.com.testdemoall.module.testnetframe.request.TestRequest;

/**
 * 新网络框架使用示例
 */
public class TestNetActivity extends BaseActivity implements NetResponse.Listener, NetResponse.ErrorListener {
    private static final String TAG = TestNetActivity.class.getSimpleName();

    @BindView(R.id.tv_click)
    public TextView tv_click;

    @BindView(R.id.tv_log)
    public TextView tv_log;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_test_net;
    }

    @Override
    public void initContentView(View view) {
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    @OnClick({R.id.tv_click})
    protected void clickEvent(View v) {
        switch(v.getId()){
            case R.id.tv_click:
                request();
                break;
            default:
                super.clickEvent(v);
                break;
        }
    }

    private void request() {
        String tag = "test";
        TestRequest request = new TestRequest();
        request.setAccount("18600957006");
        request.setPassword("111111");
        NetDataServer.instance().getData(request,UserinfoBean.class,tag,this,this);
    }

    @Override
    public void onResponse(Object response) {
        if(response!=null&&StringUtils.isNotEmpty(response.toString())){
            LogUtils.d(TAG,response.toString());
            TextViewUtils.setTextViewValue(tv_log,response.toString());
            if(response instanceof UserinfoBean){

            }
        }
    }

    @Override
    public void onErrorResponse(IErrorInfo error) {
        if(error!=null&&StringUtils.isNotEmpty(error.getMessage())){
            LogUtils.d(TAG,error.getMessage());
            TextViewUtils.setTextViewValue(tv_log,error.getMessage());
        }
    }

}
