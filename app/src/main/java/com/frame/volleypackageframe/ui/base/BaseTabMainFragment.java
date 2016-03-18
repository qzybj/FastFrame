package com.frame.volleypackageframe.ui.base;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.frame.volleypackageframe.R;
import com.frame.volleypackageframe.module.common.util.StringUtils;
import com.frame.volleypackageframe.ui.simple.adapter.FragmentPagerAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 *  用于页面中Tab栏、ViewPager并且可以滑动切换的Fragment
 */
@ContentView(R.layout.fragment_tabmain_layout)
public class BaseTabMainFragment extends BaseFragment implements ViewPager.OnPageChangeListener{

    /*** 导航的Tab容器*/
    @ViewInject(R.id.tabs)
    private TabLayout mTabLayout;

    /*** 展示内容的ViewPager的容器*/
    @ViewInject(R.id.container_viewpager)
    private ViewPager mViewPager;

    private FragmentPagerAdapter mPagerAdater;
    protected List<Fragment> fragments = new ArrayList<Fragment>();
    /**Tab标题*/
    protected  ArrayList<String> tabTitleList =new ArrayList<String>();
    /** 当前选择的分类*/
    private int mCurClassIndex=0;

    @Override
    protected void initView() {
        super.initView();
        initFragment();
        initBodyView();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    /**添加显示的Fragment*/
    public void addFragment(String title,Fragment fragment) {
        if(tabTitleList!=null&&fragments!= null){
            if (!StringUtils.isEmpty(title)&&fragment!=null) {
                tabTitleList.add(title);
                Bundle bundle=new Bundle();
                bundle.putString(BaseFragment.KEY_TITLE, title);
                fragment.setArguments(bundle);
                fragments.add(fragment);
            }
        }
    }
    /**初始化显示的Fragment*/
    protected void initFragment(){
       // addFragment("全部",new MainActivityFragment());

    }

    private void initBodyView(){
        if(tabTitleList!=null&&!tabTitleList.isEmpty()&&fragments!= null){
                mPagerAdater=new FragmentPagerAdapter(getChildFragmentManager());
                mPagerAdater.setTitles(tabTitleList);
                mPagerAdater.setFragments(fragments);
                mViewPager.setAdapter(mPagerAdater);
                mViewPager.setOnPageChangeListener(this);
                initTabView();
        }
    }
    /**
     * 初始化顶部Tab滑动的标签
     */
    private void initTabView(){
        if(tabTitleList!=null&&!tabTitleList.isEmpty()){
            //mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//设置tab模式，当前为系统默认模式
            for(int i=0;i<tabTitleList.size();i++){
                mTabLayout.addTab(mTabLayout.newTab().setText(tabTitleList.get(i)));//添加tab选项卡
            }
            mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
            mTabLayout.setTabsFromPagerAdapter(mPagerAdater);//给Tabs设置适配器
        }
    }
    //下面三个回调方法 分别是在ViewPager进行滑动的时候调用
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurClassIndex=position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
