package com.frame.fastframe.view.popwinimpl.adapter;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

import com.frame.fastframe.R;
import com.frame.fastframe.ui.simple.bean.GroupChildModel;
import com.frame.fastframe.view.popwinimpl.PopWin4SlideFromBottomPlus;
import com.frame.fastframelibrary.aosp.baseadapterhelper.BaseAdapterHelper;
import com.frame.fastframelibrary.aosp.baseadapterhelper.QuickAdapter;
import com.frame.fastframelibrary.core.view.InnerGridView;
import java.util.ArrayList;

/**Popwin - 用于分组展示数据*/
public class GroupAdapter extends QuickAdapter<GroupAdapter.IGroupModel> {
    private PopWin4SlideFromBottomPlus.OnPopupItemClickListener mListener;
    private PopupWindow mPopupWindow;
    public GroupAdapter(Context context, PopupWindow popupView, int layoutResId, ArrayList<IGroupModel> data){
        super(context,layoutResId,data);
        this.mPopupWindow = popupView;
    }
    @Override
    protected void convert(BaseAdapterHelper helper, IGroupModel item){
        helper.setText(R.id.tv_name, item.getName());
        InnerGridView popup_grid= helper.getView(R.id.popup_igv);
        if(popup_grid!=null){
            final GroupChildItemAdapter childItemAdapter =
                    new GroupChildItemAdapter(context,R.layout.popwin_bottomplus_gridview_item_child,item.getChilds());
            popup_grid.setAdapter(childItemAdapter);
//            popup_grid.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                }
//            });
        }
    }
    public void setChildClickItemListen(PopWin4SlideFromBottomPlus.OnPopupItemClickListener listener){
        mListener = listener;
    }

    public class GroupChildItemAdapter extends QuickAdapter<IGroupChildModel> {
        public GroupChildItemAdapter(Context context, int layoutResId, ArrayList<IGroupChildModel> data){
            super(context,layoutResId,data);
        }
        @Override
        protected void convert(final BaseAdapterHelper helper, IGroupChildModel item){
            helper.setText(R.id.tv_name, item.getName());
            helper.setOnClickListener(R.id.layout_clickarea, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        mListener.onItemClick( getItem(helper.getPosition()),helper.getPosition());
                        if(mPopupWindow!=null){
                            mPopupWindow.dismiss();
                        }
                    }
                }
            });
        }
    }

    //=============================================================interface
    public interface IGroupModel {
        String getName();
        String getId();
        ArrayList<IGroupChildModel> getChilds();
    }
    public interface IGroupChildModel {
        String getName();
        String getId();
    }
}