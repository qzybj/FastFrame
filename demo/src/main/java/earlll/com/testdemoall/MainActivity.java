package earlll.com.testdemoall;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import com.frame.fastframelibrary.aosp.baseadapterhelper.BaseAdapterHelper;
import com.frame.fastframelibrary.aosp.baseadapterhelper.QuickAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.ui.GroupStyleAdapterActivity;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.ui.ItemDragAndSwipeUseActivity;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.ui.ItemDragSmallIconActivity;
import earlll.com.testdemoall.module.annotationdemo.ui.SimpleAnnotationActivity;
import earlll.com.testdemoall.module.demo.ui.FileOperationSimpleActivity;
import earlll.com.testdemoall.module.demo.ui.SimpleBaseAdapterActivity;
import earlll.com.testdemoall.module.demo.ui.MultiFragmentActivity;
import earlll.com.testdemoall.module.demo.bean.TestBean;
import earlll.com.testdemoall.core.ui.base.BaseActivity;
import earlll.com.testdemoall.core.utils.TestData4Demo;
import earlll.com.testdemoall.module.demo.ui.TestDataSimpleActivity;
import earlll.com.testdemoall.module.viewdemo.ui.SimpleLayoutActivity;
import earlll.com.testdemoall.module.webviewdemo.ui.InterceptUrlActivity;
import earlll.com.testdemoall.module.zxing.ui.ZXingActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.listView)
    public ListView listView;

    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    @BindView(R.id.fab)
    public FloatingActionButton mFab;

    protected QuickAdapter<TestBean> adapter;

    @Override
    public int getLayoutResId() {
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
            adapter = new QuickAdapter<TestBean>(getBaseActivity(), R.layout.listview_item) {
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
        ArrayList<TestBean> list = new ArrayList<>();
//        list.add(TestData4Demo.getTestBean("示例 - 单个item展示",SimpleBaseAdapterActivity.class.getUserName()));
//        list.add(TestData4Demo.getTestBean("示例 - 横向ListView",SimpleHorizontalListViewActivity.class.getUserName()));
//        list.add(TestData4Demo.getTestBean("示例 - fragment使用示例",SimpleFragmentActivity.class.getUserName()));
//        list.add(TestData4Demo.getTestBean("示例 - eventBus使用示例",EventBusReciveActivity.class.getUserName()));
//        list.add(TestData4Demo.getTestBean("示例 - 下拉刷新SwipeRefreshLayout中放置WebView展示",SimpleSwipeRefreshLayoutActivity.class.getUserName(),
//                IntentUtils.setBundleStr(null, ConstantsCommonKey.KEY_URL,"http://m.yintai.com/category/miaoindex?")));
//        list.add(TestData4Demo.getTestBean("示例 - 下拉刷新 PullRefreshLayout",SimplePullRefreshLayoutActivity.class.getUserName()));
//        list.add(TestData4Demo.getTestBean("示例 - TestDataSimple - 测试数据生成器使用示例",TestDataSimpleActivity.class.getUserName()));
//        list.add(TestData4Demo.getTestBean("示例 - FileOperationSimple - File操作使用示例",FileOperationSimpleActivity.class.getUserName()));

//        list.add(TestData4Demo.getTestBean("示例 - xrecyclerview - 收缩项部ToolBar",CollapsingToolbarActivity.class.getUserName()));
//        list.add(TestData4Demo.getTestBean("示例 - xrecyclerview - 多Hearder",MultiHeaderActivity.class.getUserName()));
//        list.add(TestData4Demo.getTestBean("示例 - xrecyclerview - 交错",StaggeredGridActivity.class.getUserName()));

//        list.add(TestData4Demo.getTestBean("示例 - recyclerviewAdatper - 动画",AnimationAdatperActivity.class.getUserName()));
        list.add(TestData4Demo.getTestBean("示例 - xrecyclerviewAdatper - group分组",GroupStyleAdapterActivity.class.getName()));
        list.add(TestData4Demo.getTestBean("示例 - xrecyclerviewAdatper - 拖拽ItemView及滑动删除",ItemDragAndSwipeUseActivity.class.getName()));
        list.add(TestData4Demo.getTestBean("示例 - xrecyclerviewAdatper - 拖拽small Icon",ItemDragSmallIconActivity.class.getName()));
//        list.add(TestData4Demo.getTestBean("示例 - xrecyclerviewAdatper - 多类型",MultipleTypeAdapterActivity.class.getUserName()));
//        list.add(TestData4Demo.getTestBean("示例 - xrecyclerviewAdatper - 下拉刷新",PullToRefreshAdapterActivity.class.getUserName()));
//        list.add(TestData4Demo.getTestBean("示例 - xrecyclerviewAdatper - 项部收缩",CollapsingAdapterActivity.class.getUserName()));

//        list.add(TestData4Demo.getTestBean("示例 - BaseListViewActivity - 展示示例", SimpleBaseAdapterActivity.class.getUserName()));
        list.add(TestData4Demo.getTestBean("示例 - MultiFragmentActivity - 多个Fragment界面的展示示例", MultiFragmentActivity.class.getName()));
        list.add(TestData4Demo.getTestBean("示例 - SimpleAnnotationActivity - 注解使用示例", SimpleAnnotationActivity.class.getName()));
        list.add(TestData4Demo.getTestBean("示例 - SimpleAnnotationActivity - 各种布局 ViewStub，merge，include 使用示例", SimpleLayoutActivity.class.getName()));
        list.add(TestData4Demo.getTestBean("示例 - InterceptUrlActivity - html页面打开时，针对http劫持逻辑的处理", InterceptUrlActivity.class.getName()));
//        list.add(TestData4Demo.getTestBean("示例 - ZXingActivity - 二维码扫码处理", ZXingActivity.class.getUserName()));

        return list;
    }
}
