package earlll.com.testdemoall.module.demo.adapter;

import android.content.Context;

import com.frame.fastframelibrary.aosp.baseadapterhelper.BaseAdapterHelper;
import com.frame.fastframelibrary.aosp.baseadapterhelper.QuickAdapter;

import java.util.ArrayList;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.module.demo.bean.SingleTypeBean;


public class SingleTypeAdapter extends QuickAdapter<SingleTypeBean> {
    public SingleTypeAdapter(Context context, int layoutResId, ArrayList<SingleTypeBean> data){
        super(context,layoutResId,data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, SingleTypeBean item){
        helper.setText(R.id.tv_content, item.getDesc());
        helper.setVisible(R.id.iv_right, false);
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_date, item.getTime());
        //helper.setImageUrl(R.id.tweetAvatar, data.getImageUrl());
        helper.linkify(R.id.tv_content);
    }
}
