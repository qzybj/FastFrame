package com.frame.volleypackageframe.module.news.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.frame.volleypackageframe.R;
import com.frame.volleypackageframe.module.news.adapter.NewsAdapter;
import com.frame.volleypackageframe.module.communicate.bean.News;
import com.frame.volleypackageframe.utils.TestDataBuilder;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import java.util.ArrayList;

public class NewsActivity extends Activity
{
	@ViewInject(R.id.listView)
	private ListView mListView;
	private ArrayList<News> mDatas = new ArrayList<News>();

	private NewsAdapter mAdapter;

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
		mDatas = TestDataBuilder.getNewsList();
		mAdapter = new NewsAdapter(this, mDatas);
//		mAdapter.showIndeterminateProgress(true);
		mListView.setAdapter(mAdapter);// 设置适配器
	}

}