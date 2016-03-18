package com.frame.fastframe.module.communicate.adapter;

import android.content.Context;

import com.frame.fastframe.R;
import com.frame.fastframe.module.aosp.baseadapterhelper.BaseAdapterHelper;
import com.frame.fastframe.module.aosp.baseadapterhelper.QuickAdapter;
import com.frame.fastframe.module.communicate.bean.Communicate;
import com.frame.fastframe.view.InnerGridView;

import java.util.ArrayList;


public class CommunicateAdapter extends QuickAdapter<Communicate> {
        public CommunicateAdapter(Context context, int layoutResId, ArrayList<Communicate> data){
            super(context,layoutResId,data);
        }

    @Override
    protected void convert(BaseAdapterHelper helper, Communicate item){
        helper.setImageUrl(R.id.iv_show, item.getImgUrl());
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_subtitle, item.getSubTitle());
        helper.setText(R.id.tv_content_title, item.getContentTitle());
        helper.setText(R.id.tv_content, item.getContent());
        helper.setText(R.id.tv_type_1, item.getMsgCount_1());
        helper.setText(R.id.tv_type_2, item.getMsgCount_2());
        //多个图片展示
        InnerGridView gridView = helper.getView(R.id.igv_show);
        if (gridView != null) {
            CommunicateChildAdapter childAdapter = new CommunicateChildAdapter(context,R.layout.gridview_item_image,item.getImageList());
            gridView.setAdapter(childAdapter);
        }
    }
}
