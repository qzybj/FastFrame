package com.frame.fastframe.ui;

import android.os.Bundle;
import com.frame.fastframe.ui.base.BaseTabMainFragment;
import com.frame.fastframe.R;
import org.xutils.view.annotation.ContentView;

/**
 *  用于页面中Tab栏、ViewPager并且可以滑动切换的Fragment
 */
@ContentView(R.layout.fragment_tabmain_layout)
public class TabMainFragment extends BaseTabMainFragment {
    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }
    protected void initFragment(){
        addFragment("全部",new MainActivityFragmentFrame());
        addFragment("热点",new MainActivityFragmentFrame());
        addFragment("新闻",new MainActivityFragmentFrame());
        addFragment("房产",new MainActivityFragmentFrame());
        addFragment("经济",new MainActivityFragmentFrame());
        addFragment("政策",new MainActivityFragmentFrame());
        addFragment("游戏",new MainActivityFragmentFrame());
        addFragment("体育",new MainActivityFragmentFrame());
    }
    //下面三个回调方法 分别是在ViewPager进行滑动的时候调用
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
