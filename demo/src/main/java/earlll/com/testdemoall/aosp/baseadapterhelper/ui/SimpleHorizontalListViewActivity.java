package earlll.com.testdemoall.aosp.baseadapterhelper.ui;

import android.os.Bundle;
import android.view.View;
import com.frame.fastframelibrary.aosp.baseadapterhelper.BaseAdapterHelper;
import com.frame.fastframelibrary.aosp.baseadapterhelper.QuickAdapter;
import com.frame.fastframelibrary.core.config.ConstantsCommonKey;
import com.frame.fastframelibrary.core.view.HorizontalListView;
import com.frame.fastframelibrary.utils.dataprocess.IntentUtils;
import org.xutils.view.annotation.ViewInject;
import java.util.ArrayList;
import java.util.List;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.module.demo.bean.TestBean;
import earlll.com.testdemoall.core.ui.base.BaseActivity;
import earlll.com.testdemoall.aosp.pullrefreshlayout.ui.SimpleSwipeRefreshLayoutActivity;
import earlll.com.testdemoall.core.utils.TestDataBuilder;

public class SimpleHorizontalListViewActivity extends BaseActivity {

    @ViewInject(R.id.listView)
    private HorizontalListView mListView;

    private ArrayList<TestBean> mDatas = new ArrayList<TestBean>();

    private QuickAdapter mAdapter;


    @Override
    public int getLayoutResouceId() {
        return R.layout.activity_baseadapter_horizontal;
    }

    @Override
    public void initContentView(View view) {
        mAdapter = new QuickAdapter<TestBean>(mBaseActivity, R.layout.listview_horizontal_item,mDatas) {
            @Override
            protected void convert(BaseAdapterHelper helper, final TestBean data) {
                helper.setImageUrl(R.id.iv_show, data.getImageurl());
                helper.setText(R.id.tv_name, data.getDate());
                helper.setOnClickListener(R.id.layout,new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //goActivity(data);
                    }
                });
            }
        };

//		mAdapter.showIndeterminateProgress(true);
        // 设置适配器
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        loadData(getShowBeanList());
    }

    protected void loadData(List<TestBean> tweets) {
        // Problem with connection, retry
        if (tweets == null) {
            mAdapter.notifyDataSetChanged();
            return;
        }
        mAdapter.addAll(tweets);
    }

    public  static ArrayList<TestBean> getShowBeanList() {
        ArrayList<TestBean> list = new ArrayList<TestBean>();
        list.add(TestDataBuilder.getTestBean("单个item展示",SimpleBaseAdapterActivity.class.getName()));
//        TestBean webviewBean  = getJumpBean("下拉刷新WebView展示",SimpleWebViewPullrefreshActivity.class.getName());
//        webviewBean.setArgs(IntentUtils.setBundleStr(null, WebViewUtilPlus.KEY_URL,"http://m.yintai.com/category/miaoindex?"));
//        list.add(webviewBean);
        TestBean webviewBean1  = TestDataBuilder.getTestBean("下拉刷新SwipeRefreshLayout中放置WebView展示",SimpleSwipeRefreshLayoutActivity.class.getName());
        webviewBean1.setArgs(IntentUtils.setBundleStr(null, ConstantsCommonKey.KEY_URL,"http://m.yintai.com/category/miaoindex?"));
        list.add(webviewBean1);
        list.add(TestDataBuilder.getTestBean("横向ListView",SimpleHorizontalListViewActivity.class.getName()));
//        list.add(getJumpBean("样式2",NewsActivity.class.getName()));
//        list.add(getJumpBean("添加信息通用样式",ProductActivity.class.getName()));
//        list.add(getJumpBean("PopWin使用示例",SimplePopwinActivity.class.getName()));
        //list.add(getJumpBean("图片编辑",UCropSampleActivity.class.getName()));
        return list;
    }

}
