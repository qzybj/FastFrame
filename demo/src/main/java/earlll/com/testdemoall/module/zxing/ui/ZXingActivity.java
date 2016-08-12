package earlll.com.testdemoall.module.zxing.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.OnClick;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.ui.base.BaseFragmentActivity;

/**
 * Created by ZhangYuanBo on 2016/8/1.
 * 二维码扫码处理
 */
public class ZXingActivity extends BaseFragmentActivity {

    public static final String TAG = "ZXing";


    public int getLayoutResId() {
        return R.layout.activity_simple_zxing;
    }

    public void initContentView(View view) {}

    public void initData(Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_type1,R.id.btn_type2,R.id.btn_type3})
    public void customClickEvent(View view) {
        switch (view.getId()){
            case R.id.btn_type1:
                new IntentIntegrator(this).initiateScan();
                break;
            case R.id.btn_type2:
                callZxing();
                break;
            case R.id.btn_type3:
                callZxingType3();
                break;
        }
    }
    /**设置参数通用界面*/
    private void callZxing(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt("Scan a barcode");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    /**自定义界面*/
    private void callZxingType3(){
        new IntentIntegrator(this).setOrientationLocked(false).setCaptureActivity(CustomUIActivity.class).initiateScan();
    }

    @Override
    public void initTitleBar(View view) {

    }
    @Override
    public void onTitleBarClick(View v) {

    }


    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
