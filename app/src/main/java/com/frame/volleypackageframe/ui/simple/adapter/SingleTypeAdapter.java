package com.frame.volleypackageframe.ui.simple.adapter;

import android.content.Context;

import com.frame.volleypackageframe.R;
import com.frame.volleypackageframe.baseadapterhelper.BaseAdapterHelper;
import com.frame.volleypackageframe.baseadapterhelper.QuickAdapter;
import com.frame.volleypackageframe.bean.SingleTypeBean;

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
