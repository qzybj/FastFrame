package brady.com.appframe.common.ui.fragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.frame.fastframelibrary.utils.dataprocess.ListUtils;
import java.util.List;

public class CommonFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<FragmentBean> list;

    public CommonFragmentPagerAdapter(FragmentManager fm, List<FragmentBean> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        FragmentBean bean = getItemBean(position);
        return bean==null?null:bean.getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        FragmentBean bean = getItemBean(position);
        return bean==null?null:bean.getFragment();
    }

    @Override
    public int getCount() {
        return ListUtils.isEmpty(list)?0:list.size();
    }

    public FragmentBean getItemBean(int position) {
        return ListUtils.isEmpty(list)?null:list.get(position);
    }

    public static class FragmentBean {
        private String title;
        private Fragment fragment;

        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public Fragment getFragment() {
            return fragment;
        }
        public void setFragment(Fragment fragment) {
            this.fragment = fragment;
        }
    }
}
