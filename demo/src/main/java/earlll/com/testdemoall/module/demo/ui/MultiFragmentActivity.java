package earlll.com.testdemoall.module.demo.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.frame.fastframelibrary.utils.dataprocess.ListUtils;
import butterknife.BindView;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.adapter.QuickAdapter;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.utils.DataServer;
import earlll.com.testdemoall.module.demo.bean.LoginBean;
import earlll.com.testdemoall.core.ui.base.BaseFragmentActivity;
import earlll.com.testdemoall.module.demo.ui.fragment.HomeTopFragment;
import earlll.com.testdemoall.core.ui.fragment.interfaces.IFragmentDataPass;


public class MultiFragmentActivity extends BaseFragmentActivity implements IFragmentDataPass,
        BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerview)
    public RecyclerView mRecyclerView;
    private QuickAdapter mQuickAdapter;

    @BindView(R.id.swipeLayout)
    public SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    @BindView(R.id.collapsing_toolbar)
    public CollapsingToolbarLayout collapsingToolbar;

    private HomeTopFragment mHomeTopFragment;

    private static final int TOTAL_COUNTER = 18;
    private static final int PAGE_SIZE = 6;

    private int delayMillis = 1000;

    private int mCurrentCounter = 0;

    @Override
    public int getLayoutResouceId() {
        return R.layout.activity_multifragment;
    }

    @Override
    public void initContentView(View view) {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar.setTitleEnabled(false);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setEnabled(false);
        initFragment();
        initAdapter(mRecyclerView);
    }

    @Override
    public void initData(Bundle savedInstanceState) {}

    @Override
    public void onLoadMoreRequested() {
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                if (mCurrentCounter >= TOTAL_COUNTER) {
                    mQuickAdapter.notifyDataChangedAfterLoadMore(false);
                    View view = getLayoutInflater().inflate(R.layout.rvadapter_nomore_data, (ViewGroup) mRecyclerView.getParent(), false);
                    mQuickAdapter.addFooterView(view);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mQuickAdapter.notifyDataChangedAfterLoadMore(DataServer.getSampleData(PAGE_SIZE), true);
                            mCurrentCounter = mQuickAdapter.getData().size();
                        }
                    }, delayMillis);
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mQuickAdapter.setNewData(DataServer.getSampleData(PAGE_SIZE));
                mQuickAdapter.openLoadMore(PAGE_SIZE, true);
                mCurrentCounter = PAGE_SIZE;
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, delayMillis);
    }

    private void initAdapter(RecyclerView view) {
        view.setLayoutManager(new LinearLayoutManager(this));
        mQuickAdapter = new QuickAdapter(PAGE_SIZE);
        mQuickAdapter.openLoadAnimation();
        view.setAdapter(mQuickAdapter);
        mCurrentCounter = mQuickAdapter.getData().size();
        mQuickAdapter.setOnLoadMoreListener(this);
        mQuickAdapter.openLoadMore(PAGE_SIZE, true);//or call mQuickAdapter.setPageSize(PAGE_SIZE);  mQuickAdapter.openLoadMore(true);
        mQuickAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MultiFragmentActivity.this, Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initFragment() {
        mHomeTopFragment =  (HomeTopFragment)getFragmentManager().findFragmentById(R.id.fragment_hometop);
        if(mHomeTopFragment!=null){
            mHomeTopFragment.initView(ListUtils.buildList("a","b","c","d"));
        }
    }
    @Override
    public void receiveFragmentSendData(String tag, Object obj) {
        if(obj instanceof LoginBean){
            LoginBean bean= (LoginBean)obj;
            showToast(bean.getUserName()+bean.getPassWord());
        }
    }

    @Override
    public void onTitleBarClick(View v) {

    }
}
