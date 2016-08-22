package brady.com.appframe.common.utils;

import android.os.Bundle;

import com.frame.fastframelibrary.utils.reflect.ClassReflectUtils;
import com.frame.fastframelibrary.utils.test.TestDataBuilder;

import java.util.ArrayList;
import java.util.List;

import brady.com.appframe.common.ui.fragment.adapter.CommonFragmentPagerAdapter;
import brady.com.appframe.common.ui.fragment.recyclerview.BaseRecyclerViewFragment;
import brady.com.appframe.common.ui.fragment.recyclerview.DragStyleFragment;
import brady.com.appframe.common.ui.fragment.recyclerview.GroupStyleFragment;
import brady.com.appframe.common.ui.fragment.recyclerview.MultipleStyleFragment;
import brady.com.appframe.common.ui.fragment.recyclerview.QuickFragment;
import brady.com.appframe.common.ui.fragment.recyclerview.SwipeStyleFragment;
import brady.com.appframe.common.ui.fragment.recyclerview.annotation.RecyclerViewStyle;

/**
 * Created by ZhangYuanBo on 2016/8/20.
 */
public class CustomTestDataBuilder extends TestDataBuilder {

    @Override
    protected void init() {

    }

    public static List<CommonFragmentPagerAdapter.FragmentBean> getMainFragmentBeans() {
        ArrayList<Class<? extends BaseRecyclerViewFragment>> fragments = new ArrayList<>();
        fragments.add(QuickFragment.class);
        fragments.add(GroupStyleFragment.class);
        fragments.add(MultipleStyleFragment.class);
        fragments.add(SwipeStyleFragment.class);
        fragments.add(DragStyleFragment.class);

        int[] styles = {RecyclerViewStyle.VERTICAL_LIST, RecyclerViewStyle.HORIZONTAL_LIST, RecyclerViewStyle.VERTICAL_GRID,
                RecyclerViewStyle.HORIZONTAL_GRID, RecyclerViewStyle.STAGGERED_GRID};

        List<CommonFragmentPagerAdapter.FragmentBean> mFragments = new ArrayList<>();
        for (int i = 0; i < fragments.size(); i++) {
            CommonFragmentPagerAdapter.FragmentBean bean = new CommonFragmentPagerAdapter.FragmentBean();
            Bundle mBundle = new Bundle();
            //mBundle.putInt(BaseRecyclerViewFragment.STYLE, styles[2]);//change style
            BaseRecyclerViewFragment mFragment = ClassReflectUtils.getClassInstance(fragments.get(i));
            if(mFragment!=null){
                mFragment.setArguments(mBundle);
                bean.setTitle(mFragment.getClass().getSimpleName());
                bean.setFragment(mFragment);
                mFragments.add(bean);
            }
        }
        return mFragments;
    }

}
