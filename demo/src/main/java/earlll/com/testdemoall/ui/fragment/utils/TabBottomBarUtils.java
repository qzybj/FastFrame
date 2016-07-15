package earlll.com.testdemoall.ui.fragment.utils;

import java.util.ArrayList;

import earlll.com.testdemoall.R;
import earlll.com.testdemoall.ui.fragment.bar.DynamicTabBottomBarFragment;
import earlll.com.testdemoall.ui.fragment.interfaces.ITabItem;

/**
 * Created by ZhangYuanBo on 2016/6/1.<Br>
 * 用来控制底部标题栏控制器
 */
public class TabBottomBarUtils {

    /**获取生成的TabView展示数据*/
    public static  ArrayList<ITabItem> getTestTabItemList() {
        ArrayList<ITabItem> list = new ArrayList<ITabItem>();
        list.add(getTabItem(R.drawable.tabbottombar_sel_tab_1,"111"));
        list.add(getTabItem(R.drawable.tabbottombar_sel_tab_2,"222"));
        list.add(getTabItem(R.drawable.tabbottombar_sel_tab_3,"333"));
        list.add(getTabItem(R.drawable.tabbottombar_sel_tab_4,"444"));
        list.add(getTabItem(R.drawable.tabbottombar_sel_tab_5,"555"));
        return list;
    }

    public static ITabItem getTabItem(final int imageResId,final String text) {
        return new ITabItem(){
            @Override
            public int getImageResId() {
                return imageResId;
            }

            @Override
            public String getText() {
                return text;
            }
        };
    }
}