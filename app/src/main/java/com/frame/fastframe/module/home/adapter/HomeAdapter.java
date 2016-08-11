package com.frame.fastframe.module.home.adapter;

import android.content.Context;
import android.view.View;
import com.frame.fastframe.R;
import com.frame.fastframe.module.home.bean.ShowBean;
import com.frame.fastframelibrary.aosp.baseadapterhelper.BaseAdapterHelper;
import com.frame.fastframelibrary.aosp.baseadapterhelper.MultiItemTypeSupport;
import com.frame.fastframelibrary.aosp.baseadapterhelper.QuickAdapter;
import com.frame.fastframelibrary.view.InnerGridView;
import java.util.ArrayList;


public class HomeAdapter extends QuickAdapter<ShowBean> implements MultiItemTypeSupport<ShowBean>, View.OnClickListener {
    /**类型1*/
    public final static int SHOW_TYPE_1 = 1;
    /**类型2*/
    public final static int SHOW_TYPE_2 = 2;

    public HomeAdapter(Context con, ArrayList<ShowBean> mDatas){
        super(con,mDatas,null);
        mMultiItemSupport = this;
    }

    @Override
    protected void convert(BaseAdapterHelper helper, ShowBean item){
        switch (helper.layoutId){
            case R.layout.listview_item_home_type1:
                helper.setImageUrl(R.id.iv_show, item.getImgUrl());

                helper.setText(R.id.tv_title, item.getTitle());
                helper.setText(R.id.tv_subtitle, item.getSubTitle());
                helper.setText(R.id.tv_content, item.getContent());
                //多个图片展示
                InnerGridView gridView = helper.getView(R.id.igv_show);
                if (gridView != null) {
                    HomeChildAdapter childAdapter = new HomeChildAdapter(context,R.layout.gridview_item_image,item.getImageList());
                    gridView.setAdapter(childAdapter);
                }

                helper.setText(R.id.tv_type_1, item.getMsgCount_1());
                helper.setOnClickListener(R.id.layout_type_1, this);

                helper.setText(R.id.tv_type_2, item.getMsgCount_2());
                helper.setOnClickListener(R.id.layout_type_2, this);

                helper.setText(R.id.tv_type_3, item.getMsgCount_3());
                helper.setOnClickListener(R.id.layout_type_3, this);

                helper.setText(R.id.tv_type_4, item.getMsgCount_4());
                helper.setOnClickListener(R.id.layout_type_4, this);

                break;
            case R.layout.listview_item_home_type2:
//                helper.setText(R.id.chat_send_content, item.getContent());
//                helper.setText(R.id.chat_send_name, item.getName());
//                helper.setImageResource(R.id.chat_send_icon, item.getIcon());
                break;
        }
    }
    @Override
    public int getLayoutId(int position, ShowBean msg)
    {
        switch(msg.getShowtype()){
            case SHOW_TYPE_1:
                return R.layout.listview_item_home_type1;
            case SHOW_TYPE_2:
            default:
                return R.layout.listview_item_home_type2;
        }
    }

    @Override
    public int getViewTypeCount()
    {//实际类型数+1
        return 2+1;
    }

    @Override
    public int getItemViewType(int postion, ShowBean msg)
    {
        switch(msg.getShowtype()){
            case SHOW_TYPE_1:
            return SHOW_TYPE_1;
            case SHOW_TYPE_2:
            default:
                return SHOW_TYPE_2;
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.layout_type_1:
                break;
            case R.id.layout_type_2:
                break;
            case R.id.layout_type_3:
                break;
            case R.id.layout_type_4:
                break;
        }
    }
}
