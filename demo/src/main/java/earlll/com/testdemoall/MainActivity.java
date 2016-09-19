package earlll.com.testdemoall;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.frame.fastframelibrary.utils.dataprocess.ListUtils;
import com.frame.fastframelibrary.utils.jump.JumpBaseUtils;
import java.util.ArrayList;
import butterknife.BindView;
import earlll.com.testdemoall.module.dataserver.DataServer;
import earlll.com.testdemoall.core.ui.base.BaseActivity;
import earlll.com.testdemoall.core.ui.reciverui.bean.MainItemBean;
import earlll.com.testdemoall.core.ui.reciverui.adapter.MainAdapter;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener,BaseQuickAdapter.OnRecyclerViewItemClickListener{

    @BindView(R.id.srlayout_common)
    public SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.rv_common)
    public RecyclerView mRecyclerView;

    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    @BindView(R.id.fab)
    public FloatingActionButton mFab;

    protected MainAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initContentView(View view) {
        setSupportActionBar(mToolbar);
        initSwipeRefreshLayout();
        initRecyclerViewStyle();
        mFab.setVisibility(View.GONE);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        loadData(DataServer.getMainData());
    }

    protected void loadData(ArrayList<MainItemBean> list) {
        if(ListUtils.isNotEmpty(list)){
            adapter.addData(list);
        }
        adapter.notifyDataSetChanged();
    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.color_ffffff, R.color.color_33a6ff,R.color.color_ff0000);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setEnabled(true);
    }

    /**init RecyclerView:get show UI type,and generate the corresponding adapter */
    private void initRecyclerViewStyle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.instance(), LinearLayoutManager.VERTICAL, false));
        adapter = new MainAdapter(new ArrayList<>());
        adapter.setOnRecyclerViewItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(() -> {
            mSwipeRefreshLayout.setRefreshing(false);
            onRefreshData();
        }, 1000);
    }
    public void onRefreshData() {
        //adapter.setNewData(getTestData());
        //adapter.notifyDataSetChanged();
    }
    @Override
    public void onLoadMoreRequested() {

    }
    @Override
    public void onItemClick(View view, int position) {
        if(adapter!=null){
            MainItemBean item = adapter.getItem(position);
            JumpBaseUtils.goActivity(this,item.getJumpInfo());
        }
    }
}