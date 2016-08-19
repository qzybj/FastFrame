package brady.com.appframe.common.ui.fragment.utils;


import java.util.ArrayList;
import brady.com.appframe.R;
import brady.com.appframe.common.ui.fragment.interfaces.ITabItem;

/**
 * Created by ZhangYuanBo on 2016/6/1.<Br>
 * 用来控制底部标题栏控制器
 */
public class TabBottomBarUtils {

    /**获取生成的TabView展示数据*/
    public static ArrayList<ITabItem> getTestTabItemList() {
        ArrayList<ITabItem> list = new ArrayList<ITabItem>();
        list.add(getTabItem(R.drawable.tabbottombar_sel_bg,"111"));
        list.add(getTabItem(R.drawable.tabbottombar_sel_bg,"222"));
        list.add(getTabItem(R.drawable.tabbottombar_sel_bg,"333"));
        list.add(getTabItem(R.drawable.tabbottombar_sel_bg,"444"));
        list.add(getTabItem(R.drawable.tabbottombar_sel_bg,"555"));
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