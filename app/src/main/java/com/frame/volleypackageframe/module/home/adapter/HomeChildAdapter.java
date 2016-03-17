package com.frame.volleypackageframe.module.home.adapter;

import android.content.Context;
import android.view.View;

import com.frame.volleypackageframe.R;
import com.frame.volleypackageframe.baseadapterhelper.BaseAdapterHelper;
import com.frame.volleypackageframe.baseadapterhelper.MultiItemTypeSupport;
import com.frame.volleypackageframe.baseadapterhelper.QuickAdapter;
import com.frame.volleypackageframe.bean.SingleTypeBean;
import com.frame.volleypackageframe.common.util.StringUtils;
import com.frame.volleypackageframe.module.home.bean.ShowBean;
import com.frame.volleypackageframe.view.InnerGridView;

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
