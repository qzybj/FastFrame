package com.frame.fastframe.module.news.adapter;

import android.content.Context;

import com.frame.fastframe.module.news.bean.News;
import com.frame.fastframe.R;
import com.frame.fastframelibrary.aosp.baseadapterhelper.BaseAdapterHelper;
import com.frame.fastframelibrary.aosp.baseadapterhelper.QuickAdapter;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;

import java.util.ArrayList;


public class NewsChildAdapter extends QuickAdapter<News> {
    public NewsChildAdapter(Context context, int layoutResId, ArrayList<News> data){
        super(context,layoutResId,data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, News item){
        if (!StringUtils.isEmpty(item.getImgUrl())) {
            helper.setImageUrl(R.id.iv_show, item.getImgUrl());
        }
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_content, item.getContent());
    }
}
