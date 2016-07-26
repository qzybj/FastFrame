package earlll.com.testdemoall.module.sensordemo.ui;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.frame.fastframelibrary.utils.StringUtils;
import com.frame.fastframelibrary.utils.TextViewUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.aosp.eventbus.bean.MessageEvent;

/** 获取传感器的值 使用示例*/
public class SimpleSensorActivity extends Activity {

    private SensorManager sm;
    private Sensor ligthSensor;

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
        initSensor();
    }

    public int getLayoutResouceId() {
        return R.layout.activity_sensor_simple;
    }

    public void initContentView(View view) {
    }

    public void initData(Bundle savedInstanceState) {

    }

    private void initSensor(){
        //获取SensorManager对象
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        //通过调用getDefaultSensor方法获取某一个类型的默认传感器
        ligthSensor = sm.getDefaultSensor(Sensor.TYPE_LIGHT);

        sm.registerListener(new MySensorListener(), ligthSensor, SensorManager.SENSOR_DELAY_NORMAL);

        //得到手机上所有的传感器
//        List<Sensor> listSensor = sm.getSensorList(Sensor.TYPE_ALL);
//        int i = 1;
//        for (Sensor sensor : listSensor) {
//            Log.d("sensor " + i, sensor.getName());
//            i++;
//        }
    }
    public class MySensorListener implements SensorEventListener {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        public void onSensorChanged(SensorEvent event) {
            //获取精度
            float acc = event.accuracy;
            //获取光线强度
            float lux = event.values[0];
            String value ="";
            value = "acc ----> " + acc +"\n";
            value += "lux ----> " + lux+"\n";
            value += "当前光线状态" + getStatusDescribe(lux)+"\n";

            tvShow.setText(value);
        }
    }
    private String getStatusDescribe(float lux){
        String describe = "";
        if(rangeInDefined(lux,SensorManager.LIGHT_NO_MOON,SensorManager.LIGHT_FULLMOON)){
            describe =  "luminance at night with no moon in lux";
        }else  if(rangeInDefined(lux,SensorManager.LIGHT_FULLMOON,SensorManager.LIGHT_CLOUDY)){
            describe =  " luminance at night with full moon in lux ";
        }else  if(rangeInDefined(lux,SensorManager.LIGHT_CLOUDY,SensorManager.LIGHT_SUNRISE)){
            describe =  "luminance under a cloudy sky in lux ";
        }else  if(rangeInDefined(lux,SensorManager.LIGHT_SUNRISE,SensorManager.LIGHT_OVERCAST)){
            describe =  "luminance at sunrise in lux ";
        }else  if(rangeInDefined(lux,SensorManager.LIGHT_OVERCAST,SensorManager.LIGHT_SHADE)){
            describe =  " luminance under an overcast sky in lux ";
        }else  if(rangeInDefined(lux,SensorManager.LIGHT_SHADE,SensorManager.LIGHT_SUNLIGHT)){
            describe =  " luminance in shade in lux ";
        }else  if(rangeInDefined(lux,SensorManager.LIGHT_SUNLIGHT,SensorManager.LIGHT_SUNLIGHT_MAX)){
            describe =  "luminance of sunlight in lux ";
        }else  if(lux>=SensorManager.LIGHT_SUNLIGHT_MAX){
            describe =  " Maximum luminance of sunlight in lux ";
        }
        return describe;
    }

    /**
     * 判断一个数字在不在某个范围
     * @param current
     * @param min
     * @param max
     * @return
     */
    public static boolean rangeInDefined(float current, float min, float max){
        return Math.max(min, current) == Math.min(current, max);
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
