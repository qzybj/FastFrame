package com.frame.fastframe.module.communicate.adapter;

import android.content.Context;

import com.frame.fastframe.R;
import com.frame.fastframelibrary.aosp.baseadapterhelper.BaseAdapterHelper;
import com.frame.fastframelibrary.aosp.baseadapterhelper.QuickAdapter;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;

import java.util.ArrayList;


public class CommunicateChildAdapter extends QuickAdapter<String> {
    public CommunicateChildAdapter(Context context, int layoutResId, ArrayList<String> data){
        super(context,layoutResId,data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, String imgUrl){
        if (!StringUtils.isEmpty(imgUrl)) {
            helper.setImageUrl(R.id.iv_show, imgUrl);
        }
    }
}
