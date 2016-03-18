package com.frame.fastframe.ui;

import android.os.Bundle;
import com.frame.fastframe.R;
import com.frame.fastframe.ui.base.BaseTabMainFragment;
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
        addFragment("全部",new MainActivityFragment());
        addFragment("热点",new MainActivityFragment());
        addFragment("新闻",new MainActivityFragment());
        addFragment("房产",new MainActivityFragment());
        addFragment("经济",new MainActivityFragment());
        addFragment("政策",new MainActivityFragment());
        addFragment("游戏",new MainActivityFragment());
        addFragment("体育",new MainActivityFragment());
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
