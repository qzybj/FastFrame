package com.frame.volleypackageframe.module.product.adapter;

/**
 * Author: alex askerov
 * Date: 9/9/13
 * Time: 10:52 PM
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.frame.volleypackageframe.R;
import com.frame.volleypackageframe.module.product.bean.ImageBean;
import com.frame.volleypackageframe.view.dynamicgridview.BaseDynamicGridAdapter;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class GridViewPictureAdapter extends BaseDynamicGridAdapter {
    /**编辑模式*/
    public final static int TYPE_SHOW_EDIT = 0x02001;
    /**展示模式*/
    public final static int TYPE_SHOW_NO_EDIT = 0x02002;

    private int mShowType = TYPE_SHOW_EDIT;
    private Context con;
    public GridViewPictureAdapter(Context context, ArrayList<ImageBean> items, int columnCount) {
        super(context, items, columnCount);
        con = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CheeseViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.gridview_item_uploadpictures, null);
            holder = new CheeseViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (CheeseViewHolder) convertView.getTag();
        }

        Object bean  =  getItem(position);
        if (bean instanceof ImageBean) {
            if ((getCount()-1)==position&&mShowType==TYPE_SHOW_EDIT) {//添加图片改为最后一个显示
                holder.iv_show.setVisibility(View.GONE);
                holder.iv_delpicture.setVisibility(View.GONE);
                holder.def_layout.setVisibility(View.VISIBLE);
            }else {
                holder.iv_show.setVisibility(View.VISIBLE);
                holder.iv_delpicture.setVisibility(mShowType == TYPE_SHOW_EDIT ? View.VISIBLE : View.GONE);
                holder.def_layout.setVisibility(View.GONE);
            }
            holder.build((ImageBean)bean);
        }
        return convertView;
    }

    private class CheeseViewHolder {
        public TextView tv_describe;
        public ImageView iv_show;
        public ImageView iv_delpicture;
        public RelativeLayout def_layout;

        private CheeseViewHolder(View view) {
            tv_describe = (TextView) view.findViewById(R.id.tv_describe);
            iv_show = (ImageView) view.findViewById(R.id.iv_show);
            iv_delpicture = (ImageView) view.findViewById(R.id.iv_delpicture);
            def_layout = (RelativeLayout) view.findViewById(R.id.item_def_layout);
        }

        void build(ImageBean bean) {
            if (tv_describe!= null) {
                tv_describe.setText(bean.getDescribe());
            }
            if (iv_show!= null) {
                Picasso.with(con).load(bean.getImageUrl()).into(iv_show);
            }
        }
    }
}