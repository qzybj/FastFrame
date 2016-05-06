package com.frame.fastframe.view.popwinimpl;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ListView;
import com.frame.fastframe.R;
import com.frame.fastframe.view.popwinimpl.adapter.GroupAdapter;
import com.frame.fastframelibrary.aosp.basepopwindow.BasePopupWindow;
import java.util.ArrayList;

/**
 * 从底部滑上来的popup,子项目为AdapterView<br/>
 * 要调用 initListView()方法来进行数据的初始化
 */
public class PopWin4SlideFromBottomPlus extends BasePopupWindow{

    private View popupView;
    private ListView popup_list;
    public PopWin4SlideFromBottomPlus(Activity context) {
        super(context);
        initCustomView();
    }

    private void initCustomView() {
        if(popupView!=null){
            popup_list = (ListView)popupView.findViewById(R.id.popup_list);
        }
    }

    @Override
    public Animation getShowAnimation() {
        return getTranslateAnimation(250*2,0,300);
    }

    @Override
    public View getClickToDismissView() {
        return popupView.findViewById(R.id.click_to_dismiss);
    }


    @Override
    public View getInputView() {
        return null;
    }


    @Override
    public View getPopupView() {
        popupView= LayoutInflater.from(mContext).inflate(R.layout.popwin_slide_from_bottomplus,null);
        return popupView;
    }

    @Override
    public View getAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    public void initListView(ArrayList<GroupAdapter.IGroupModel> data, OnPopupItemClickListener listener) {
        if(popup_list!=null){
            GroupAdapter adapter = new GroupAdapter(mContext,mPopupWindow,R.layout.popwin_bottomplus_gridview_item,data);
            adapter.setChildClickItemListen(listener);
            popup_list.setAdapter(adapter);
        }
    }
    //=============================================================interface
    public interface OnPopupItemClickListener{
        void onItemClick(GroupAdapter.IGroupChildModel obj, int postion);
    }
}