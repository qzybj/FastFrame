package earlll.com.testdemoall.module.demo.ui;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.ui.base.BaseActivity;

/**
 * 测试数据生成器，使用示例
 */
public class AnimationSimpleActivity extends BaseActivity {
    private static final String TAG = AnimationSimpleActivity.class.getSimpleName();

    @BindView(R.id.tv_click)
    public TextView tv_click;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_simple_animation;
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
                startAnimation(v);
                break;
            default:
                super.clickEvent(v);
                break;
        }
    }
    private int i=0;

    private void startAnimation(View v) {
        if(v!=null){
            int type = i%3;
            switch (type){
                case 0:
                    rotateyZhyAnimRun(v);
                    break;
                case 1:
                    rotateyAnim(v);
                    break;
                case 2:
                    propertyValuesHolder(v);
                    break;
            }
        }
        i++;
    }
    public void rotateyAnim(final View view){
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "rotationX", 0.0F, 360.0F,0.0F).setDuration(500);
        anim.start();
    }

    public void propertyValuesHolder(View view){
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX",1f,0, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY",1f,0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY,pvhZ).setDuration(1000).start();
    }
    public void rotateyZhyAnimRun(final View view){
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "zhy", 1.0F,0.0F,1.0F).setDuration(2000);
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                float cVal = (Float) animation.getAnimatedValue();
                view.setAlpha(cVal);
                view.setScaleX(cVal);
                view.setScaleY(cVal);
            }
        });
    }
}
