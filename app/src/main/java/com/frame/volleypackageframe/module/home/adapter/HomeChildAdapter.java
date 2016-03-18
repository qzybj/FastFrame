package com.frame.volleypackageframe.module.home.adapter;

import android.content.Context;

import com.frame.volleypackageframe.R;
import com.frame.volleypackageframe.module.aosp.baseadapterhelper.BaseAdapterHelper;
import com.frame.volleypackageframe.module.aosp.baseadapterhelper.QuickAdapter;
import com.frame.volleypackageframe.module.common.util.StringUtils;

import java.util.ArrayList;


public class HomeChildAdapter extends QuickAdapter<String> {
    public HomeChildAdapter(Context context, int layoutResId, ArrayList<String> data){
        super(context,layoutResId,data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, String imgUrl){
        if (!StringUtils.isEmpty(imgUrl)) {
            helper.setImageUrl(R.id.iv_show, imgUrl);
        }
    }
}
