package brady.com.appframe;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import brady.com.appframe.common.ui.base.BaseActivity;
import brady.com.appframe.common.ui.fragment.adapter.CommonFragmentPagerAdapter;
import brady.com.appframe.common.utils.CustomTestDataBuilder;
import brady.com.appframe.common.utils.SnackbarUtils;
import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        TabLayout.OnTabSelectedListener {

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
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
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
    protected void clickEvent(View v) {
        switch (v.getId()) {
            case R.id.fab:
                SnackbarUtils.show(v, "Replace with your own action", getString(R.string.cancel), false);
                break;
            default:
                super.clickEvent(v);
                break;
        }
    }

    private void initViewPager() {
        mCommonFragmentPagerAdapter =
                new CommonFragmentPagerAdapter(getSupportFragmentManager(), CustomTestDataBuilder.getMainFragmentBeans());
        mViewPager.setAdapter(mCommonFragmentPagerAdapter);
        mViewPager.setOffscreenPageLimit(5);//Set ViewPager max cache page number
        mTabLayout.addOnTabSelectedListener(this);;//Toolbar change title then ViewPager page is changed
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);//Bind TabLayout to ViewPager with scroll
        //mTabLayout.setTabsFromPagerAdapter(mCommonFragmentPagerAdapter);//set Tablayout that tab item show title from ViewPager adatper method getPageTitle
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mToolbar.setTitle(tab.getText());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}