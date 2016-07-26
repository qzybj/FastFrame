package earlll.com.testdemoall.aosp.pullrefreshlayout.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.baoyz.widget.PullRefreshLayout;
import com.frame.fastframelibrary.aosp.baseadapterhelper.BaseAdapterHelper;
import com.frame.fastframelibrary.aosp.baseadapterhelper.QuickAdapter;
import com.frame.fastframelibrary.utils.ListUtils;
import org.xutils.view.annotation.ViewInject;
import java.util.ArrayList;
import java.util.List;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.module.demo.bean.TestBean;
import earlll.com.testdemoall.core.ui.base.BaseActivity;
import earlll.com.testdemoall.core.utils.TestDataBuilder;

/**
 * 用于下拉刷新展示解决
 */
public class SimplePullRefreshLayoutActivity extends BaseActivity {

    @ViewInject(R.id.prlayout_refresh)
    PullRefreshLayout prlayout_refresh;

    @ViewInject(R.id.lv_show)
    ListView lv_show;

    protected QuickAdapter<TestBean> adapter;

    @ViewInject(R.id.progressbar)
    protected ProgressBar mProgressBar;

    @Override
    public int getLayoutResouceId() {
        return R.layout.activity_pullrefreshlayout;
    }

    @Override
    public void initContentView(View view) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitlebarLeftDrawable(0,0);
        setTitlebarLeftText("取消");
        setTitlebarContent(R.string.app_name);
//      setRightVisibility(true);
//      setRightDrawable(R.mipmap.ic_launcher,0);
        initListView();
        prlayout_refresh.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                prlayout_refresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        prlayout_refresh.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        loadData(getShowBeanList());
    }

    @Override
    protected void clickEvent(View v) {
        super.clickEvent(v);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initListView(){
        if (adapter == null){
            adapter = new QuickAdapter<TestBean>(mBaseActivity, R.layout.listview_item) {
                @Override
                protected void convert(BaseAdapterHelper helper, final TestBean data) {
                    helper.setText(R.id.tv_title, data.getName());
                    helper.setText(R.id.tv_content, data.getText().substring(data.getText().lastIndexOf(".")+1));
                    helper.setVisible(R.id.iv_icon_show,false);
                    helper.setVisible(R.id.tweetDate,false);
                    helper.setVisible(R.id.tv_right_date,false);
//                    helper.setOnClickListener(R.id.layout,
//                            new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    goActivity(data);
//                                }
//                            });
                    //helper.setRootVisible(R.id.tweetRT, data.isShowFlag());
                    //helper.setText(R.id.tweetDate, data.getDate());
                    //helper.setImageUrl(R.id.tweetAvatar, data.getImageurl());
                    //helper.linkify(R.id.tweetText);
                }
            };
        }
        lv_show.setAdapter(adapter);
    }

    protected void loadData(List<TestBean> list) {
        if (ListUtils.isNotEmpty(list)) {
            adapter.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    public static ArrayList<TestBean> getShowBeanList() {
        ArrayList<TestBean> list = new ArrayList<TestBean>();
        list.add(TestDataBuilder.getTestBean("单个item展示",""));
        list.add(TestDataBuilder.getTestBean("横向ListView",""));
        list.add(TestDataBuilder.getTestBean("fragment使用示例",""));
        list.add(TestDataBuilder.getTestBean("eventBus使用示例",""));
        list.add(TestDataBuilder.getTestBean("使用示例1",""));
        list.add(TestDataBuilder.getTestBean("使用示例2",""));
        list.add(TestDataBuilder.getTestBean("使用示例3",""));
        return list;
    }

    protected final void showProgressBar(final int progress) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mProgressBar.getVisibility() != View.VISIBLE) {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
                mProgressBar.setProgress(progress);
            }
        });
    }

    protected final void dissProgressBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mProgressBar.getVisibility() == View.VISIBLE) {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}