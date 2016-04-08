package com.frame.fastframe.module.home.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.frame.fastframe.R;
import com.frame.fastframe.module.home.adapter.HomeAdapter;
import com.frame.fastframe.module.home.bean.ShowBean;
import com.frame.fastframe.utils.TestDataBuilder;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import java.util.ArrayList;

public class HomeActivity extends Activity
{

	@ViewInject(R.id.listView)
	private ListView mListView;

	private ArrayList<ShowBean> mDatas = new ArrayList<ShowBean>();

	private HomeAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		x.view().inject(this);

		mListView = (ListView) findViewById(R.id.listView);

		initDatas();
		mAdapter = new HomeAdapter(this, mDatas);
//		mAdapter.showIndeterminateProgress(true);
		mListView.setAdapter(mAdapter);// 设置适配器

	}

	private void initDatas()
	{
		mDatas = TestDataBuilder.getShowBeanList();
	}

}
