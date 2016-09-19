package earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import earlll.com.testdemoall.R;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.adapter.QuickAdapter;
import earlll.com.testdemoall.module.dataserver.DataServer;


public class CollapsingAdapterActivity extends AppCompatActivity implements
        BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView mRecyclerView;
    private QuickAdapter mQuickAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private static final int TOTAL_COUNTER = 18;

    private static final int PAGE_SIZE = 6;

    private int delayMillis = 1000;

    private int mCurrentCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvadapter_collapsingtoolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitleEnabled(false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        //mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setEnabled(false);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        initAdapter(mRecyclerView);
    }

    private void addHeadView() {
        View headView = getLayoutInflater().inflate(R.layout.rvadapter_view_header, (ViewGroup) mRecyclerView.getParent(), false);
        ((TextView)headView.findViewById(R.id.tv)).setText("click use custom loading view");
        final View customLoading = getLayoutInflater().inflate(R.layout.rvadapter_view_loading, (ViewGroup) mRecyclerView.getParent(), false);
        headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuickAdapter.setLoadingView(customLoading);
                mRecyclerView.setAdapter(mQuickAdapter);
                Toast.makeText(CollapsingAdapterActivity.this,"use ok!",Toast.LENGTH_LONG).show();
            }
        });
        mQuickAdapter.addHeaderView(headView);
    }

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
        //addHeadView();
        mQuickAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(CollapsingAdapterActivity.this, Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }
}
