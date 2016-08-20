package brady.com.appframe;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.frame.fastframelibrary.utils.reflect.ClassReflectUtils;

import java.util.ArrayList;
import java.util.List;
import brady.com.appframe.common.ui.base.BaseFragmentActivity;
import brady.com.appframe.common.ui.fragment.adapter.CommonFragmentPagerAdapter;
import brady.com.appframe.common.ui.fragment.recyclerview.BaseRecyclerViewFragment;
import brady.com.appframe.common.ui.fragment.recyclerview.DragStyleFragment;
import brady.com.appframe.common.ui.fragment.recyclerview.GroupStyleFragment;
import brady.com.appframe.common.ui.fragment.recyclerview.MultipleStyleFragment;
import brady.com.appframe.common.ui.fragment.recyclerview.QuickFragment;
import brady.com.appframe.common.ui.fragment.recyclerview.SwipeStyleFragment;
import brady.com.appframe.common.ui.fragment.recyclerview.annotation.RecyclerViewStyle;
import brady.com.appframe.common.utils.SnackbarUtils;
import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseFragmentActivity implements
        NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.toolbar)
    public Toolbar mToolbar;
    @BindView(R.id.fab)
    public FloatingActionButton mFloatingActionButton;
    @BindView(R.id.drawer_layout)
    public DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    public NavigationView mNavigationView;

    @BindView(R.id.id_tablayout)
    public TabLayout mTabLayout;
    @BindView(R.id.id_viewpager)
    public ViewPager mViewPager;

    @BindView(R.id.id_coordinatorlayout)
    public CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.id_appbarlayout)
    public AppBarLayout mAppBarLayout;

    private CommonFragmentPagerAdapter mCommonFragmentPagerAdapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initContentView(View view) {
        setSupportActionBar(mToolbar);
        mFloatingActionButton.setOnClickListener(this);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initViewPager();
    }

    @Override
    public void onTitleBarClick(View v) {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String msgString = item.getTitle().toString();
        switch (item.getItemId()) {
            case R.id.nav_camera:
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
        }
        //item.setChecked(true);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        SnackbarUtils.show(mViewPager, msgString, getString(R.string.cancel), false);
        return true;
    }

    @Override
    @OnClick({R.id.fab})
    protected void customClickEvent(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
            default:
                super.customClickEvent(v);
                break;
        }
    }

    private void initViewPager() {
        mCommonFragmentPagerAdapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(),getTestData());
        mViewPager.setAdapter(mCommonFragmentPagerAdapter);
        mViewPager.setOffscreenPageLimit(5);//Set ViewPager max cache page number
        mViewPager.addOnPageChangeListener(this);//Toolbar change title then ViewPager page is changed
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);//Bind TabLayout to ViewPager with scroll
        //mTabLayout.setTabsFromPagerAdapter(mCommonFragmentPagerAdapter);//set Tablayout that tab item show title from ViewPager adatper method getPageTitle
    }

    @Override
    public void onPageSelected(int position) {
        mToolbar.setTitle(mCommonFragmentPagerAdapter.getPageTitle(position));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageScrollStateChanged(int state) {}

    private List<CommonFragmentPagerAdapter.FragmentBean> getTestData() {
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
            //mBundle.putInt(BaseRecyclerViewFragment.STYLE, styles[2]);
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