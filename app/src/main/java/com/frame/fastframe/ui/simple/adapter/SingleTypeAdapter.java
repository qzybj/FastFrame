package com.frame.fastframe.ui.simple.adapter;

import android.content.Context;

import com.frame.fastframe.R;
import com.frame.fastframe.module.aosp.baseadapterhelper.BaseAdapterHelper;
import com.frame.fastframe.module.aosp.baseadapterhelper.QuickAdapter;
import com.frame.fastframe.bean.SingleTypeBean;

import java.util.ArrayList;


public class SingleTypeAdapter extends QuickAdapter<SingleTypeBean> {
    public SingleTypeAdapter(Context context, int layoutResId, ArrayList<SingleTypeBean> data){
        super(context,layoutResId,data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, SingleTypeBean item){
        helper.setText(R.id.tweetText, item.getDesc());
        helper.setVisible(R.id.tweetRT, false);
        helper.setText(R.id.tweetName, item.getTitle());
        helper.setText(R.id.tweetDate, item.getTime());
        //helper.setImageUrl(R.id.tweetAvatar, data.getImageurl());
        helper.linkify(R.id.tweetText);
    }

}
