package com.frame.fastframe.ui.simple.adapter;

import android.content.Context;

import com.frame.fastframe.R;
import com.frame.fastframe.module.aosp.baseadapterhelper.BaseAdapterHelper;
import com.frame.fastframe.module.aosp.baseadapterhelper.MultiItemTypeSupport;
import com.frame.fastframe.module.aosp.baseadapterhelper.QuickAdapter;
import com.frame.fastframe.bean.MultiTypeBean;

import java.util.ArrayList;


public class MultiTypeAdapter extends QuickAdapter<MultiTypeBean> implements MultiItemTypeSupport<MultiTypeBean> {


    public MultiTypeAdapter(Context con, ArrayList<MultiTypeBean> mDatas) {
        super(con, mDatas, null);
        mMultiItemSupport = this;
    }

    @Override
    protected void convert(BaseAdapterHelper helper, MultiTypeBean item) {
        switch (helper.layoutId) {
            case R.layout.listview_item_chat_type1:
                helper.setText(R.id.chat_from_content, item.getContent());
                helper.setText(R.id.chat_from_name, item.getName());
                helper.setImageResource(R.id.chat_from_icon, item.getIcon());
                break;
            case R.layout.listview_item_chat_type2:
                helper.setText(R.id.chat_send_content, item.getContent());
                helper.setText(R.id.chat_send_name, item.getName());
                helper.setImageResource(R.id.chat_send_icon, item.getIcon());
                break;
        }
    }

    @Override
    public int getLayoutId(int position, MultiTypeBean msg) {
        if (msg.isComMeg()) {
            return R.layout.listview_item_chat_type1;
        }
        return R.layout.listview_item_chat_type2;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int postion, MultiTypeBean msg) {
        if (msg.isComMeg()) {
            return MultiTypeBean.RECIEVE_MSG;
        }
        return MultiTypeBean.SEND_MSG;
    }
}
