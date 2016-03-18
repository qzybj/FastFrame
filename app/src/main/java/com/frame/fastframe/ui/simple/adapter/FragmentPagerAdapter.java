package com.frame.fastframe.ui.simple.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.frame.fastframe.MyApplication;
import com.frame.fastframe.R;

import java.util.ArrayList;
import java.util.List;
/**
 * 当前类注释:Fragment，Viewpager的自定义适配器
 */
public class FragmentPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<String> titles;
    private LayoutInflater mInflater;
    public void setTitles(String[] titles) {
        ArrayList<String> list = new ArrayList<String>();
        if (titles != null&&titles.length>0) {
            for(int i=0;i<titles.length;i++){
                list.add(titles[i]);
            }
        }
        this.titles = list;
    }
    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }
    private List<Fragment> fragments;
    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles!= null&&!titles.isEmpty()) {
            return  titles.get(position);
        }
        return "";
    }
    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }
    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment=null;
        try {
            fragment=(Fragment)super.instantiateItem(container,position);
        }catch (Exception e){

        }
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
    //此方法用来显示tab上的名字
//    @Override
//    public CharSequence getPageTitle(int position) {
//
//        return titles[position % titles.length];
//    }
    public List<Fragment> getFragments() {
        return fragments;
    }
    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    /**
     * 添加getTabView的方法，来进行自定义Tab的布局View
     * @param position
     * @return
     */
    public View getTabView(int position){
        mInflater=LayoutInflater.from(MyApplication.getInstance());
        View view=mInflater.inflate(R.layout.tab_item_layout,null);
        TextView tv= (TextView) view.findViewById(R.id.textView);
        tv.setText(titles.get(position));
        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        img.setImageResource(R.mipmap.ic_launcher);
        return view;
    }
}
