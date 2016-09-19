package earlll.com.testdemoall.module.dragger2.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.frame.fastframelibrary.net.core.NetDataServer;
import com.frame.fastframelibrary.net.core.config.NetConstants;
import com.frame.fastframelibrary.net.core.interfaces.IErrorInfo;
import com.frame.fastframelibrary.net.core.interfaces.IRequestListener;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import com.frame.fastframelibrary.utils.view.TextViewUtils;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.ui.base.BaseActivity;
import earlll.com.testdemoall.module.dragger2.bean.ActivityModule;
import earlll.com.testdemoall.module.dragger2.bean.UserModel;
import earlll.com.testdemoall.module.dragger2.interfaces.ActivityComponent;
import earlll.com.testdemoall.module.dragger2.interfaces.DaggerActivityComponent;
import earlll.com.testdemoall.module.testnetframe.bean.UserinfoBean;
import earlll.com.testdemoall.module.testnetframe.net.load.NetProgressLoad;
import earlll.com.testdemoall.module.testnetframe.request.TestRequest;


/** Dragger2使用示例:
 * */
public class DraggerActivity extends BaseActivity implements IRequestListener {
    private static final String TAG = DraggerActivity.class.getSimpleName();
    private final int baseCode = 10010;

    private ActivityComponent mActivityComponent;
    @Inject
    UserModel userModel;

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
        NetDataServer.instance().bindProgressLoad(new NetProgressLoad(this));
    }

    @Override
    public void initData(Bundle savedInstanceState) {}

    @Override
    @OnClick({R.id.tv_click,R.id.tv_clickall})
    protected void clickEvent(View v) {
        switch(v.getId()){
            case R.id.tv_click:
                request();
                break;
            case R.id.tv_clickall:
                mActivityComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule()).build();
                mActivityComponent.inject(this);

                tv_log.setText(userModel.id + "\n" + userModel.name + "\n" + userModel.gender);
                break;
            default:
                super.clickEvent(v);
                break;
        }
    }

    private void request() {
        TestRequest request = new TestRequest();
        request.setAccount("18600957006");
        request.setPassword("111111");
        request.setRequestCode(baseCode+1);
        request.setLoadType(NetConstants.LoadingType.LOADING_NORMAL);
        NetDataServer.instance().request(request,UserinfoBean.class,this);
    }

    @Override
    public void onResponse(int requestCode,Object response) {
        if(response!=null&&StringUtils.isNotEmpty(response.toString())){
            LogUtils.d(TAG,response.toString());
            TextViewUtils.setTextViewValue(tv_log,response.toString());
            if(response instanceof UserinfoBean){

            }
        }
    }

    @Override
    public void onErrorResponse(int requestCode,IErrorInfo error) {
        if(error!=null&&StringUtils.isNotEmpty(error.getMessage())){
            LogUtils.d(TAG,error.getMessage());
            TextViewUtils.setTextViewValue(tv_log,error.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetDataServer.instance().unbindProgressLoad();
    }
}