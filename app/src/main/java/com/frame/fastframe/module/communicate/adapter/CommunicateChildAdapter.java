package com.frame.fastframe.module.communicate.adapter;

import android.content.Context;

import com.frame.fastframe.R;
import com.frame.fastframe.module.aosp.baseadapterhelper.BaseAdapterHelper;
import com.frame.fastframe.module.aosp.baseadapterhelper.QuickAdapter;
import com.frame.fastframe.module.common.util.StringUtils;

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
