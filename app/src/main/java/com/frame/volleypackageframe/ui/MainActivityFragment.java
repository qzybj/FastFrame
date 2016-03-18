package com.frame.volleypackageframe.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.frame.volleypackageframe.R;
import com.frame.volleypackageframe.module.aosp.baseadapterhelper.BaseAdapterHelper;
import com.frame.volleypackageframe.module.aosp.baseadapterhelper.QuickAdapter;
import com.frame.volleypackageframe.bean.TestBean;
import com.frame.volleypackageframe.module.home.ui.HomeActivity;
import com.frame.volleypackageframe.module.news.ui.NewsActivity;
import com.frame.volleypackageframe.module.product.ui.ProductActivity;
import com.frame.volleypackageframe.ui.base.BaseFragment;
import com.frame.volleypackageframe.ui.simple.ui.SimpleBaseAdapterActivity;
import com.frame.volleypackageframe.ui.simple.ui.SimpleMultiBaseAdapterActivity;
import com.frame.volleypackageframe.view.ExtendedListView;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import static java.text.DateFormat.SHORT;
import static java.text.DateFormat.getDateInstance;

/**
 * A placeholder fragment containing a simple view.
 */
@ContentView(R.layout.fragment_one)
public class MainActivityFragment extends BaseFragment implements ExtendedListView.OnEndOfListListener<TestBean> {
    private static final DateFormat dateFormat = getDateInstance(SHORT);

    @ViewInject(R.id.listView)
    protected ExtendedListView listView;

    protected QuickAdapter<TestBean> adapter;

    public MainActivityFragment() {}

    @Override
    protected void initView() {
        listView.setOnEndOfListListener(this);
        if (adapter == null)
            adapter = new QuickAdapter<TestBean>(getActivity(), R.layout.listview_item_click) {
                @Override
                protected void convert(BaseAdapterHelper helper, final TestBean data) {
                    helper.setText(R.id.tweetName, data.getName());
                    helper.setText(R.id.tweetText, data.getText().substring(data.getText().lastIndexOf(".")+1));
                    helper.setOnClickListener(
                            R.id.layout,
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    goActivity(data.getText());
                                }
                            });
                    helper.setVisible(R.id.tweetAvatar,false);
                    helper.setVisible(R.id.tweetDate,false);
                    helper.setVisible(R.id.tweetRT,false);
                    //helper.setRootVisible(R.id.tweetRT, data.isShowFlag());
                    //helper.setText(R.id.tweetDate, data.getDate());
                    //helper.setImageUrl(R.id.tweetAvatar, data.getImageurl());
                    //helper.linkify(R.id.tweetText);
                }
            };
        listView.setAdapter(adapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        loadData(getTestBeanList());
    }

    /***/
    protected void loadData(List<TestBean> tweets) {
        // Problem with connection, retry
        if (tweets == null) {
            adapter.notifyDataSetChanged();
            return;
        }
        // No more tweets
        if (tweets.isEmpty()) {
            listView.setOnEndOfListListener(null);
        }
        adapter.addAll(tweets);
    }

    @Override
    public void onEndOfList(TestBean lastItem) {
       //最后一条数据
    }
    private void  goActivity(String className){
        Intent intent = new Intent();
        intent.setClassName(mParentActivity,className);
        startActivity(intent);
    }

    public  static ArrayList<TestBean> getTestBeanList() {
        ArrayList<TestBean> list = new ArrayList<TestBean>();
        list.add(getTestBean("单个item展示",SimpleBaseAdapterActivity.class.getName()));
        list.add(getTestBean("多个item展示",SimpleMultiBaseAdapterActivity.class.getName()));
        list.add(getTestBean("样式1",HomeActivity.class.getName()));
        list.add(getTestBean("样式2",NewsActivity.class.getName()));
        list.add(getTestBean("添加信息通用样式",ProductActivity.class.getName()));
        return list;
    }
    public  static TestBean getTestBean(String describe,String targetActivity) {
        TestBean bean = new TestBean();
        bean.setName(describe);
        bean.setText(targetActivity);
        bean.setDate("2015-12-01");
        bean.setImageurl("http://p10.ytrss.com/product/20/647/7390/ViewImage/3490.jpg");
        return bean;
    }
}
