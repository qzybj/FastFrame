package com.frame.volleypackageframe.module.communicate.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.frame.volleypackageframe.R;
import com.frame.volleypackageframe.module.communicate.adapter.CommunicateAdapter;
import com.frame.volleypackageframe.module.communicate.bean.Communicate;
import com.frame.volleypackageframe.utils.TestDataBuilder;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

public class CommunicateActivity extends Activity
{
	@ViewInject(R.id.listView)
	private ListView mListView;
	private ArrayList<Communicate> mDatas = new ArrayList<Communicate>();

	private CommunicateAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		x.view().inject(this);

		initListViewDatas();

	}

	/**
	 * 初始化ListView
	 */
	private void initListViewDatas()
	{
		mDatas = TestDataBuilder.getCommunicateList();
		mAdapter = new CommunicateAdapter(this,R.layout.listview_item_communicate, mDatas);
//		mAdapter.showIndeterminateProgress(true);
		mListView.setAdapter(mAdapter);// 设置适配器
	}

}