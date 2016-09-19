package earlll.com.testdemoall.core.ui.reciverui.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.dataprocess.IntentUtils;
import earlll.com.testdemoall.MyApplication;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.config.ConstantsKey;
import earlll.com.testdemoall.core.ui.base.BaseFragmentActivity;
import earlll.com.testdemoall.core.ui.fragment.bar.TabBarFragment;
import earlll.com.testdemoall.core.ui.fragment.interfaces.IDialogCallBack;
import earlll.com.testdemoall.core.ui.fragment.interfaces.ISendData;
import earlll.com.testdemoall.core.ui.fragment.interfaces.ITabBarClickListener;
import earlll.com.testdemoall.core.ui.fragment.utils.TabBottomBarUtils;
import earlll.com.testdemoall.module.demo.ui.fragment.HomeFragment;

/**
 *  用于接收Fragment的容器Activity:<br>
 *  包含标题栏 、底部动态栏
 */
public class ContainerActivity extends BaseFragmentActivity implements ISendData,ITabBarClickListener,IDialogCallBack {
    public static final String KEY_FRAGMENT = "fragment";

    private Fragment mFragment;
    private Fragment oldFragment;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_fragment_container;
    }

    @Override
    public void initContentView(View view) {
        initBar();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        try {
            mFragment = Fragment.instantiate(MyApplication.instance(), IntentUtils.getString(getIntent(), KEY_FRAGMENT));
            showFragment(mFragment);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void initBar() {
        if(isSupportTitleBar()){
            titleBar.setRightDrawable(R.mipmap.ic_launcher,0);
            titleBar.setTitle("");
        }
        if(IntentUtils.getBoolean(getIntent(), ConstantsKey.KEY_TABBAR, false)){
            if(isSupportTabBar()){
                tabBar.initTabView(TabBottomBarUtils.getTestTabItemList());
            }
        }else{
            setTabBarVisiable(false);
        }
    }

    @Override
    protected void customClickEvent(View v) {

    }

    /**标题栏   -  左边按钮触发事件*/
    public void onClickTitleLeft(View v) {
        finish();
    }

    /**标题栏   -  右边按钮触发事件*/
    public void onClickTitleRight(View v) {
        showToast("ibtn_right");
    }

    @Override
    public void onTitleBarClick(View view) {
        switch (view.getId()) {
            default:
                break;
        }
    }

    public void onTabClick(View view) {
        switch (view.getId()) {
            case TabBarFragment.ID_TAB_BASECODE:
                if(mFragment ==null){
                    mFragment = new HomeFragment();
                }
                showFragment(mFragment);
                break;
        }
    }

    private void showFragment(Fragment targetFragment){
        if (targetFragment!=null&&targetFragment!= oldFragment) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();//开启Fragment事务
            if(oldFragment !=null){
                transaction.hide(oldFragment);
            }
            if (!targetFragment.isAdded()) {
                transaction.add(R.id.fragment_content, targetFragment);
            } else {
                transaction.show(targetFragment);
            }
            oldFragment = targetFragment;
            transaction.commit();
        }
    }

    private void setTabBarVisiable(boolean isShow){
        if(isSupportTabBar()){
            FragmentTransaction transaction = getFragmentManager().beginTransaction();//开启Fragment事务
            if(isShow){
                if (!tabBar.isAdded()) { // 隐藏当前的fragment，add下一个到Activity中
                    transaction.add(R.id.fragment_content, tabBar);
                }
                transaction.show(tabBar);
            }else{
                transaction.hide(tabBar);
            }
            transaction.commit();
        }
    }

    @Override
    public void receive(String tag, Object obj) {
        if(obj instanceof String){
            showToast(obj.toString());
        }else{
            showToast(obj.toString());
        }
    }

    @Override
    public void btnCallBack(String tag, int type) {
        switch (type){
            case IDialogCallBack.DIALOGCALLBACK_BTN_LEFT:
                if("AlertDialogFragment".equals(tag)){
                    showToast("DIALOGCALLBACK_BTN_LEFT");
                }
                break;
            default:
                break;
        }
    }
}