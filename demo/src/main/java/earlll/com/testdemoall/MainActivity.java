package earlll.com.testdemoall;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import com.frame.fastframelibrary.aosp.baseadapterhelper.BaseAdapterHelper;
import com.frame.fastframelibrary.aosp.baseadapterhelper.QuickAdapter;
import com.frame.fastframelibrary.core.common.ConstantsCommonKey;
import com.frame.fastframelibrary.utils.IntentUtils;
import org.xutils.view.annotation.ViewInject;
import java.util.ArrayList;
import java.util.List;
import earlll.com.testdemoall.bean.TestBean;
import earlll.com.testdemoall.ui.EventBusReciveActivity;
import earlll.com.testdemoall.ui.SimpleFragmentActivity;
import earlll.com.testdemoall.ui.SimpleHorizontalListViewActivity;
import earlll.com.testdemoall.ui.SimpleBaseAdapterActivity;
import earlll.com.testdemoall.ui.SimplePullRefreshLayoutActivity;
import earlll.com.testdemoall.ui.SimpleSensorActivity;
import earlll.com.testdemoall.ui.SimpleSwipeRefreshLayoutActivity;
import earlll.com.testdemoall.ui.SimpleWebViewPullrefreshActivity;
import earlll.com.testdemoall.ui.base.BaseActivity;
import earlll.com.testdemoall.utils.TestDataBuilder;
import earlll.com.testdemoall.utils.WebViewUtilPlus;

public class MainActivity extends BaseActivity {

    @ViewInject(R.id.listView)
    protected ListView listView;

    @ViewInject(R.id.toolbar)
    protected Toolbar mToolbar;

    @ViewInject(R.id.fab)
    protected FloatingActionButton mFab;

    protected QuickAdapter<TestBean> adapter;

    @Override
    public int getLayoutResouceId() {
        return R.layout.activity_main;
    }

    @Override
    public void initContentView(View view) {
        //setSupportActionBar(mToolbar);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        if (adapter == null){
            adapter = new QuickAdapter<TestBean>(mBaseActivity, R.layout.listview_item) {
                @Override
                protected void convert(BaseAdapterHelper helper, final TestBean data) {
                    helper.setText(R.id.tv_title, data.getName());
                    helper.setText(R.id.tv_content, data.getText().substring(data.getText().lastIndexOf(".")+1));
                    helper.setOnClickListener(R.id.layout,
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    goActivity(data);
                                }
                            });
                    helper.setVisible(R.id.iv_icon_show,false);
                    helper.setVisible(R.id.tweetDate,false);
                    helper.setVisible(R.id.tv_right_date,false);
                    //helper.setRootVisible(R.id.tweetRT, data.isShowFlag());
                    //helper.setText(R.id.tweetDate, data.getDate());
                    //helper.setImageUrl(R.id.tweetAvatar, data.getImageurl());
                    //helper.linkify(R.id.tweetText);
                }
            };
        }
        listView.setAdapter(adapter);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        loadData(getShowBeanList());
    }
    /***/
    protected void loadData(List<TestBean> tweets) {
        // Problem with connection, retry
        if (tweets == null) {
            adapter.notifyDataSetChanged();
            return;
        }
        adapter.addAll(tweets);
    }

    public static ArrayList<TestBean> getShowBeanList() {
        ArrayList<TestBean> list = new ArrayList<TestBean>();
        list.add(TestDataBuilder.getTestBean("单个item展示",SimpleBaseAdapterActivity.class.getName()));
        list.add(TestDataBuilder.getTestBean("横向ListView",SimpleHorizontalListViewActivity.class.getName()));
        list.add(TestDataBuilder.getTestBean("fragment使用示例",SimpleFragmentActivity.class.getName()));
        list.add(TestDataBuilder.getTestBean("eventBus使用示例",EventBusReciveActivity.class.getName()));
        list.add(TestDataBuilder.getTestBean("获取传感器的值使用示例",SimpleSensorActivity.class.getName()));
        list.add(TestDataBuilder.getTestBean("下拉刷新PullrefreshWebView展示",SimpleWebViewPullrefreshActivity.class.getName(),
                IntentUtils.setBundleStr(null, ConstantsCommonKey.KEY_URL,"http://m.yintai.com/category/miaoindex?")));
        list.add(TestDataBuilder.getTestBean("下拉刷新SwipeRefreshLayout中放置WebView展示",SimpleSwipeRefreshLayoutActivity.class.getName(),
                IntentUtils.setBundleStr(null,ConstantsCommonKey.KEY_URL,"http://m.yintai.com/category/miaoindex?")));
        list.add(TestDataBuilder.getTestBean("下拉刷新 PullRefreshLayout 示例",SimplePullRefreshLayoutActivity.class.getName()));
        return list;
    }
}
