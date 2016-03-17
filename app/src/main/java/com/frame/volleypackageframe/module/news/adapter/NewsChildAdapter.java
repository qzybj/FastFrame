package com.frame.volleypackageframe.module.communicate.adapter;

import android.content.Context;
import com.frame.volleypackageframe.R;
import com.frame.volleypackageframe.baseadapterhelper.BaseAdapterHelper;
import com.frame.volleypackageframe.baseadapterhelper.QuickAdapter;
import com.frame.volleypackageframe.common.util.StringUtils;
import com.frame.volleypackageframe.module.communicate.bean.News;
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
