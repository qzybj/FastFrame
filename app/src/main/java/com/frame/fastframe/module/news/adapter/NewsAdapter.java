package com.frame.fastframe.module.news.adapter;

import android.content.Context;
import android.view.View;
import com.frame.fastframe.R;
import com.frame.fastframe.module.news.bean.News;
import com.frame.fastframe.utils.TestDataBuilder;
import com.frame.fastframelibrary.aosp.baseadapterhelper.BaseAdapterHelper;
import com.frame.fastframelibrary.aosp.baseadapterhelper.MultiItemTypeSupport;
import com.frame.fastframelibrary.aosp.baseadapterhelper.QuickAdapter;
import com.frame.fastframelibrary.core.view.InnerListView;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;

import java.util.ArrayList;


public class NewsAdapter extends QuickAdapter<News> implements MultiItemTypeSupport<News>, View.OnClickListener {
    /**类型1*/
    public final static int SHOW_TYPE_1 = 1;
    /**类型2*/
    public final static int SHOW_TYPE_2 = 2;
    /**类型3*/
    public final static int SHOW_TYPE_3 = 3;

    public NewsAdapter(Context con, ArrayList<News> mDatas){
        super(con,mDatas,null);
        mMultiItemSupport = this;
    }

    @Override
    protected void convert(BaseAdapterHelper helper, News item){
        switch (helper.layoutId){
            case R.layout.listview_item_news_type1:
                helper.setImageUrl(R.id.iv_show, item.getImgUrl());
                helper.setText(R.id.tv_title, item.getTitle());
                helper.setText(R.id.tv_content, item.getContent());
                helper.setText(R.id.tv_type, "type");
                break;
            case R.layout.listview_item_news_type2:
                helper.setText(R.id.tv_title, item.getTitle());
                helper.setText(R.id.tv_type, item.getTitle());
                InnerListView adapterView = helper.getView(R.id.ilv_show);
                if (adapterView != null) {
                    NewsChildAdapter childAdapter = new NewsChildAdapter(context,R.layout.listview_item_news_child, TestDataBuilder.getNewsList());
                    adapterView.setAdapter(childAdapter);
                }
                break;
            case R.layout.listview_item_news_child:
            if (!StringUtils.isEmpty(item.getImgUrl())) {
                helper.setImageUrl(R.id.iv_show, item.getImgUrl());
            }
            helper.setText(R.id.tv_title, item.getTitle());
            helper.setText(R.id.tv_content, item.getContent());
                break;
        }
    }
    @Override
    public int getLayoutId(int position, News msg)
    {
        switch(msg.getShowtype()){
            case SHOW_TYPE_2:
                return R.layout.listview_item_news_type2;
            case SHOW_TYPE_3:
                return R.layout.listview_item_news_child;
            case SHOW_TYPE_1:
            default:
            return R.layout.listview_item_news_type1;
        }
    }

    @Override
    public int getViewTypeCount()
    {//实际类型数+1
        return 3+1;
    }

    @Override
    public int getItemViewType(int postion, News msg)
    {
        switch(msg.getShowtype()){
            case SHOW_TYPE_2:
                return SHOW_TYPE_2;
            case SHOW_TYPE_3:
                return SHOW_TYPE_3;
            case SHOW_TYPE_1:
            default:
                return SHOW_TYPE_1;
        }
    }
    @Override
    public void onClick(View v) {
    }
}
