package earlll.com.testdemoall.module.viewdemo.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.ui.base.BaseFragmentActivity;
import earlll.com.testdemoall.core.utils.CountDownTimerTask;


/** 各种布局使用示例:<br>
 * ViewStub，merge，include,自定义ProgressBar样式 */
public class SimpleLayoutActivity extends BaseFragmentActivity {

    public static final String TAG = "layout";

    @BindView(R.id.btn_progress)
    Button btn_progress;

    @BindView(R.id.tv_show)
    TextView tvShow;

    @BindView(R.id.pb_download)
    ProgressBar pb_download;

    @BindView(R.id.vs_show)
    ViewStub stub_empty;


    public int getLayoutResouceId() {
        return R.layout.activity_simple_layout;
    }

    public void initContentView(View view) {}

    public void initData(Bundle savedInstanceState) {

    }

    private final int SECOND = 1000;
    private int count=0;
    private void loadProgress(){
        btn_progress.setEnabled(false);
        count=0;
        CountDownTimerTask mc = new CountDownTimerTask(10*SECOND, SECOND,
                new CountDownTimerTask.CountDownTimerCallback() {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        pb_download.setProgress((++count)*10);
                    }
                    @Override
                    public void onFinish() {
                        pb_download.setProgress(100);
                        btn_progress.setEnabled(true);
                    }
                });
        mc.start();
    }
    private void showEmptyView(boolean isShow){
        if (isShow) {
            if(stub_empty==null){//inflate后View就不为空了
                stub_empty.inflate();
            }
        }
        stub_empty.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    private boolean isShowEmptyView;
    @OnClick({R.id.btn_show,R.id.btn_show_viewstub,R.id.btn_progress})
    public void customClickEvent(View view) {
        switch (view.getId()){
            case R.id.btn_show:
                break;
            case R.id.btn_progress:
                loadProgress();
                break;
            case R.id.btn_show_viewstub:
                showEmptyView(isShowEmptyView=!isShowEmptyView);
                break;
        }
    }


    @Override
    public void initTitleBar(View view) {

    }
    @Override
    public void onTitleBarClick(View v) {

    }
}
