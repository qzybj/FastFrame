package earlll.com.testdemoall.module.testnetframe.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.frame.fastframelibrary.aosp.volley.VolleyProcess;
import com.frame.fastframelibrary.net.core.NetDataServer;
import com.frame.fastframelibrary.net.core.config.NetConstants;
import com.frame.fastframelibrary.net.core.interfaces.IErrorInfo;
import com.frame.fastframelibrary.net.core.interfaces.IRequestListener;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import com.frame.fastframelibrary.utils.view.TextViewUtils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import butterknife.BindView;
import butterknife.OnClick;
import earlll.com.testdemoall.MainActivity;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.config.DemoConstants;
import earlll.com.testdemoall.core.ui.base.BaseActivity;
import earlll.com.testdemoall.module.testnetframe.bean.UserinfoBean;
import earlll.com.testdemoall.module.testnetframe.net.load.NetProgressLoad;
import earlll.com.testdemoall.module.testnetframe.request.TestRequest;


/** 新网络框架使用示例:
 * 调用多个请求示例*/
public class TestNetActivity extends BaseActivity implements IRequestListener {
    private static final String TAG = TestNetActivity.class.getSimpleName();
    private final int baseCode = 10010;

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
    @OnClick({R.id.tv_click,R.id.tv_clickall,R.id.tv_clicksync})
    protected void clickEvent(View v) {
        switch(v.getId()){
            case R.id.tv_click:
                request();
                break;
            case R.id.tv_clicksync:
                new Thread(() -> {requestSync();}).start();
                break;
            case R.id.tv_clickall:
                request();
                request1();
                request2();
                request3();
                break;
            default:
                super.clickEvent(v);
                break;
        }
    }

    private void requestSync() {
        RequestFuture future = RequestFuture.newFuture();
        StringRequest request = new StringRequest("http://vjson.com", future, future);
        VolleyProcess.instance().addRequest(request);
        try {
            String result = (String)future.get();
            future.get(3000, TimeUnit.MILLISECONDS);
            sendMessage(1111,result);
            Log.d(MainActivity.class.getSimpleName(), result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void baseHandleMessage(Message msg) {
        switch (msg.what) {
            case 1111:
                TextViewUtils.setTextViewValue(tv_log,msg.obj.toString());
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
    private void request1() {
        TestRequest request = new TestRequest();
        request.setAccount("18600957006");
        request.setPassword("111111");
        request.setRequestCode(baseCode+2);
        request.setLoadType(NetConstants.LoadingType.LOADING_START);
        NetDataServer.instance().request(request,UserinfoBean.class,this);
    }
    private void request2() {
        TestRequest request = new TestRequest();
        request.setAccount("18600957006");
        request.setPassword("111111");
        request.setRequestCode(baseCode+3);
        request.setLoadType(NetConstants.LoadingType.LOADING_END);
        NetDataServer.instance().request(request,UserinfoBean.class,this);
    }
    private void request3() {
        TestRequest request = new TestRequest();
        request.setAccount("18600957006");
        request.setPassword("111111");
        request.setRequestCode(baseCode+4);
        request.setLoadType(NetConstants.LoadingType.LOADING_NONE);
        NetDataServer.instance().request(request,UserinfoBean.class,this);
    }

    @Override
    public void onResponse(int requestCode,Object response) {
        if(response!=null&&StringUtils.isNotEmpty(response.toString())){
            LogUtils.d(TAG,response.toString());
            TextViewUtils.setTextViewValue(tv_log,response.toString());
            if(response instanceof UserinfoBean){
                switch (requestCode){
                    case baseCode+1:
                        LogUtils.d("request","requestCode = "+baseCode+1);
                        break;
                    case baseCode+2:
                        LogUtils.d("request","requestCode = "+baseCode+2);
                        break;
                    case baseCode+3:
                        LogUtils.d("request","requestCode = "+baseCode+3);
                        break;
                    case baseCode+4:
                        LogUtils.d("request","requestCode = "+baseCode+4);
                        break;
                    default:
                        LogUtils.d("request","requestCode is null");
                        break;
                }
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