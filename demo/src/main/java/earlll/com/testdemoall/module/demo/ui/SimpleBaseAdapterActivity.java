package earlll.com.testdemoall.module.demo.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.entity.Status;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.utils.DataServer;
import earlll.com.testdemoall.module.demo.ui.adapter.SimpleBaseAdapter;
import earlll.com.testdemoall.core.ui.base.BaseListViewActivity;

/**
 * 用于ListView的通用展示示例
 */
public class SimpleBaseAdapterActivity extends BaseListViewActivity {
    @BindView(R.id.rv_list)
    public XRecyclerView mRecyclerView;

    private SimpleBaseAdapter mQuickAdapter;
    private static final int PAGE_SIZE = 6;
    private List<Status> listData = new ArrayList<Status>();

    @Override
    public int getLayoutResouceId() {
        return R.layout.activity_simple_listview;
    }


    @Override
    protected void initAdapterView(View view) {

        getListView().setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        getListView().setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        getListView().setArrowImageView(R.drawable.xrecyclerview_arrow_down_grey);

        View header = LayoutInflater.from(this).inflate(R.layout.recyclerview_view_header, (ViewGroup)findViewById(android.R.id.content),false);
        getListView().addHeaderView(header);
        View header1 = LayoutInflater.from(this).inflate(R.layout.recyclerview_view_header, (ViewGroup)findViewById(android.R.id.content),false);
        header1.setBackgroundColor(0xff556B2F);
        getListView().addHeaderView(header1);

    }

    @Override
    public void initCustomView(View view){

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitlebarLeftDrawable(0, 0);
        setTitlebarLeftText("取消");
        setTitlebarContent("Simple BaseListViewActivity");
        setTitlebarRightVisibility(true);
        setTitlebarRightDrawable(R.mipmap.ic_launcher, 0);
    }

    @Override
    protected void onClickTitleRight(View v) {
        onBindRefresh(getListView());
        super.onClickTitleRight(v);
    }

    @Override
    protected void clickEvent(View v) {}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public XRecyclerView getListView() {
        return mRecyclerView;
    }

 ;
    @Override
    public BaseQuickAdapter getAdapter() {
        if(mQuickAdapter==null){
            //mQuickAdapter = new SimpleBaseAdapter(getListView(),PAGE_SIZE);
            mQuickAdapter = new SimpleBaseAdapter();
            mQuickAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(getBaseActivity(), Integer.toString(position), Toast.LENGTH_LONG).show();
                }
            });
        }
        return mQuickAdapter;
    }

    @Override
    public void onBindRefresh(View v) {
        new Handler().postDelayed(new Runnable(){
            public void run() {
                listData = DataServer.getSampleData(PAGE_SIZE);
                getAdapter().setNewData(listData);
                getAdapter().notifyDataChangedAfterLoadMore(true);
                getListView().refreshComplete();
            }

        }, 1000);
    }

    @Override
    public void onBindLoadMore(View v) {
        new Handler().postDelayed(new Runnable(){
            public void run() {
                listData.addAll(DataServer.getSampleData(PAGE_SIZE));
                getAdapter().setNewData(listData);
                getAdapter().notifyDataChangedAfterLoadMore(true);
                getListView().loadMoreComplete();
            }
        }, 1000);
    }


    //@OnClick(R.id.rv_list)
    public void onClick() {

    }
}