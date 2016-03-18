package com.frame.fastframe.ui.simple.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.frame.fastframe.R;
import com.frame.fastframe.bean.SingleTypeBean;
import com.frame.fastframe.ui.simple.adapter.SingleTypeAdapter;
import com.frame.fastframe.utils.TestDataBuilder;

import java.util.ArrayList;

public class SimpleBaseAdapterActivity extends Activity
{

	private ListView mListView;
	private ArrayList<SingleTypeBean> mDatas = new ArrayList<SingleTypeBean>();

	private SingleTypeAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_baseadapter);

		initDatas();

		mListView = (ListView) findViewById(R.id.listView);
		mAdapter = new SingleTypeAdapter(SimpleBaseAdapterActivity.this, R.layout.listview_item, mDatas);
//		mAdapter.showIndeterminateProgress(true);
		// 设置适配器
		mListView.setAdapter(mAdapter);
	}

	private void initDatas()
	{
		mDatas = TestDataBuilder.getSingleTypeBeanList();
	}

}
