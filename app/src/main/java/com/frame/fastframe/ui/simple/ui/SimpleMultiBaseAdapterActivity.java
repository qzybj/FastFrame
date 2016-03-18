package com.frame.fastframe.ui.simple.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.frame.fastframe.R;
import com.frame.fastframe.bean.MultiTypeBean;
import com.frame.fastframe.ui.simple.adapter.MultiTypeAdapter;
import com.frame.fastframe.utils.TestDataBuilder;
import java.util.ArrayList;

public class SimpleMultiBaseAdapterActivity extends Activity
{

	private ListView mListView;
	private ArrayList<MultiTypeBean> mDatas = new ArrayList<MultiTypeBean>();

	private MultiTypeAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_baseadapter);

		mListView = (ListView) findViewById(R.id.listView);

		initDatas();
		mAdapter = new MultiTypeAdapter(SimpleMultiBaseAdapterActivity.this, mDatas);
//		mAdapter.showIndeterminateProgress(true);
		mListView.setAdapter(mAdapter);// 设置适配器

	}

	private void initDatas()
	{
		mDatas = TestDataBuilder.getChatMessageList();
	}

}
