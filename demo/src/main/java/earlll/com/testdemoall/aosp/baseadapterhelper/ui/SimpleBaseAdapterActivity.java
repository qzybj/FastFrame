package earlll.com.testdemoall.aosp.baseadapterhelper.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.utils.TestData4Demo;
import earlll.com.testdemoall.module.demo.adapter.SingleTypeAdapter;
import earlll.com.testdemoall.module.demo.bean.SingleTypeBean;
import earlll.com.testdemoall.core.ui.base.BaseActivity;

public class SimpleBaseAdapterActivity extends BaseActivity {

    private ListView mListView;
    private ArrayList<SingleTypeBean> mDatas = new ArrayList<SingleTypeBean>();
    private SingleTypeAdapter mAdapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_baseadapter;
    }

    @Override
    public void initContentView(View view) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mDatas = TestData4Demo.getSingleTypeBeanList();
        mListView = (ListView) findViewById(R.id.listView);
        mAdapter = new SingleTypeAdapter(SimpleBaseAdapterActivity.this, R.layout.listview_item, mDatas);
//		mAdapter.showIndeterminateProgress(true);
        // 设置适配器
        mListView.setAdapter(mAdapter);
    }

}
