package earlll.com.testdemoall.module.demo.ui;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.ui.base.BaseActivity;
import android.view.WindowManager.LayoutParams;

/**
 * 测试数据生成器，使用示例
 */
public class PopwindowSimpleActivity extends BaseActivity {
    private static final String TAG = PopwindowSimpleActivity.class.getSimpleName();

    @BindView(R.id.mytext)
    public TextView mytext;
    @BindView(R.id.myView)
    public View viewBg;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_simple_popwin;
    }

    @Override
    public void initContentView(View view) {
        viewBg.setVisibility(View.GONE);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    @OnClick({R.id.mytext})
    protected void clickEvent(View v) {
        switch(v.getId()){
            case R.id.mytext:
                showpop(this);
                break;
            default:
                super.clickEvent(v);
                break;
        }

    }

    /**
     * 显示Pop窗口
     * @param context
     */
    private void showpop(final Context context) {
        View view = View.inflate(context, R.layout.dialog_bookshelf_common,
                null);
        // 最后一个参数false 代表：不与其余布局发生交互， true代表：可以与其余布局发生交互事件
        final PopupWindow popWindow = new PopupWindow(view,
                LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, false) {
            // 重写popupWindow消失时事件
            @Override
            public void dismiss() {
                // 在pop消失之前，给咱们加的view设置背景渐变出场动画（Android3.0以上的开发环境，这里建议使用属性动画，那样很柔和，视觉效果更棒！）
                viewBg.startAnimation(AnimationUtils.loadAnimation(context,R.anim.anim_bookshelf_folder_editer_exit));
                viewBg.setVisibility(View.GONE);
                super.dismiss();
            }
        };
        // 设置Pop入场动画效果
        popWindow.setAnimationStyle(R.style.BookShelf_bottom_DialogAnimation);
        // 设置Pop响应内部区域焦点
        popWindow.setFocusable(true);
        // 设置Pop响应外部区域焦点
        popWindow.setOutsideTouchable(true);
        // 设置PopupWindow弹出软键盘模式（此处为覆盖式）
        popWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        // 响应返回键必须的语句
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        // 在显示pop之前，给咱们加的view设置背景渐变入场动画（Android3.0以上的开发环境，这里建议使用属性动画，那样很柔和，视觉效果更棒！）
        viewBg.setVisibility(View.VISIBLE);
        viewBg.startAnimation(AnimationUtils.loadAnimation(context,R.anim.anim_bookshelf_folder_editer_enter));
        // 依附的父布局自己设定，我这里为了方便，这样写的。
        popWindow.showAtLocation(viewBg, Gravity.BOTTOM, 0, 0);
    }
}
