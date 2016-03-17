package com.frame.volleypackageframe.module.communicate.adapter;

import android.content.Context;

import com.frame.volleypackageframe.R;
import com.frame.volleypackageframe.baseadapterhelper.BaseAdapterHelper;
import com.frame.volleypackageframe.baseadapterhelper.QuickAdapter;
import com.frame.volleypackageframe.common.util.StringUtils;

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
