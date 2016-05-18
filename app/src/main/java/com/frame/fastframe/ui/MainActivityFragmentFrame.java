package com.frame.fastframe.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.frame.fastframe.bean.TestBean;
import com.frame.fastframe.module.home.ui.HomeActivity;
import com.frame.fastframe.module.news.ui.NewsActivity;
import com.frame.fastframe.module.previewimage.ui.DisplayImageActivity;
import com.frame.fastframe.module.previewimage.ui.PreviewImage1Activity;
import com.frame.fastframe.module.previewimage.ui.PreviewImageActivity;
import com.frame.fastframe.module.product.ui.ProductActivity;
import com.frame.fastframe.ui.simple.ui.SimpleBaseAdapterActivity;
import com.frame.fastframe.ui.simple.ui.SimpleCameraActivity;
import com.frame.fastframe.ui.simple.ui.SimpleGalleryActivity;
import com.frame.fastframe.ui.simple.ui.SimpleMultiBaseAdapterActivity;
import com.frame.fastframe.R;
import com.frame.fastframe.ui.simple.ui.SimplePassWordViewActivity;
import com.frame.fastframe.ui.simple.ui.SimplePopwinActivity;
import com.frame.fastframelibrary.aosp.baseadapterhelper.BaseAdapterHelper;
import com.frame.fastframelibrary.aosp.baseadapterhelper.QuickAdapter;
import com.frame.fastframelibrary.ui.base.FrameBaseFragment;
import com.frame.fastframelibrary.view.ExtendedListView;
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
public class MainActivityFragmentFrame extends FrameBaseFragment implements ExtendedListView.OnEndOfListListener<TestBean> {
    private static final DateFormat dateFormat = getDateInstance(SHORT);

    @ViewInject(R.id.listView)
    protected ExtendedListView listView;

    protected QuickAdapter<TestBean> adapter;

    public MainActivityFragmentFrame() {}

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
        loadData(getShowBeanList());
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
        if(DisplayImageActivity.class.getName().equals(className)){
            intent.putStringArrayListExtra(DisplayImageActivity.INTENT_KEY_IMG, PreviewImage1Activity.images);
            intent.putExtra(DisplayImageActivity.INTENT_KEY_IMG_INDEX,0);
        }
        startActivity(intent);
    }

    public  static ArrayList<TestBean> getShowBeanList() {
        ArrayList<TestBean> list = new ArrayList<TestBean>();
        list.add(getTestBean("单个item展示",SimpleBaseAdapterActivity.class.getName()));
        list.add(getTestBean("多个item展示",SimpleMultiBaseAdapterActivity.class.getName()));
        list.add(getTestBean("样式1",HomeActivity.class.getName()));
        list.add(getTestBean("样式2",NewsActivity.class.getName()));
        list.add(getTestBean("添加信息通用样式",ProductActivity.class.getName()));
        list.add(getTestBean("PopWin使用示例",SimplePopwinActivity.class.getName()));
        list.add(getTestBean("PassWordView使用示例",SimplePassWordViewActivity.class.getName()));
//        list.add(getTestBean("Camera使用示例",SimpleCameraActivity.class.getName()));
//        list.add(getTestBean("Gallery使用示例",SimpleGalleryActivity.class.getName()));
        //list.add(getTestBean("Gallery使用示例",PreviewImageActivity.class.getName()));
        list.add(getTestBean("Gallery使用示例",DisplayImageActivity.class.getName()));
        //list.add(getTestBean("图片编辑",UCropSampleActivity.class.getName()));
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