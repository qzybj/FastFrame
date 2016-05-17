package com.frame.fastframe.ui.simple.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.frame.fastframe.R;
import com.frame.fastframe.bean.SingleTypeBean;
import com.frame.fastframe.ui.base.BaseActivity;
import com.frame.fastframe.ui.simple.adapter.SingleTypeAdapter;
import com.frame.fastframe.ui.simple.bean.GroupChildModel;
import com.frame.fastframe.ui.simple.bean.GroupModel;
import com.frame.fastframe.view.popwinimpl.PopWin4List;
import com.frame.fastframe.view.popwinimpl.PopWin4SlideFromBottom;
import com.frame.fastframe.view.popwinimpl.PopWin4SlideFromBottomPlus;
import com.frame.fastframe.view.popwinimpl.adapter.GroupAdapter;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.ToastUtils;
import org.xutils.view.annotation.Event;

import java.util.ArrayList;

public class SimplePopwinActivity extends BaseActivity {
    public static final int TAG_CREATE=0x01;
    public static final int TAG_DELETE=0x02;
    public static final int TAG_MODIFY=0x03;



    @Event(value = R.id.btn_popwin1, type = View.OnClickListener.class)
    private void clickBtn1(View view) {
        popStyle4List();
    }
    @Event(value = R.id.btn_popwin2, type = View.OnClickListener.class)
    private void clickBtn2(View view) {
        popStyle4BottomMenu();
    }
    @Event(value = R.id.btn_popwin3, type = View.OnClickListener.class)
    private void clickBtn3(View view) {
        popStyle4BottomMenu();
    }
    @Event(value = R.id.btn_popwin4, type = View.OnClickListener.class)
    private void clickBtn4(View view) {
        popStyle4BottomMenuPlus();
    }

    @Override
    public int getLayoutResouceId() {
        return R.layout.activity_simple_popwin;
    }

    @Override
    public void initContentView(View view) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    public void popStyle4List() {
        PopWin4List.Builder builder=new PopWin4List.Builder(getBaseActivity());
        builder.addItem(TAG_CREATE,"Create-01");
        builder.addItem(TAG_MODIFY,"Modify-01");
        builder.addItem(TAG_CREATE,"Create-02");
        builder.addItem(TAG_DELETE,"Delete-01");
        builder.addItem(TAG_MODIFY,"Modify-02");
        builder.addItem(TAG_CREATE,"Create-03");
        builder.addItem(TAG_DELETE,"Delete-02");
        builder.addItem(TAG_MODIFY,"Modify-03");
        builder.addItem(TAG_DELETE,"Delete-03");
        builder.addItem(TAG_MODIFY,"Modify-04");
        builder.addItem(TAG_DELETE,"Delete-04");
        builder.addItem(TAG_CREATE,"Create-04");
        PopWin4List mListPopup=builder.build();

        mListPopup.setOnListPopupItemClickListener(new PopWin4List.OnListPopupItemClickListener() {
            @Override
            public void onItemClick(int what) {
                switch (what){
                    case TAG_CREATE:
                        ToastUtils.showToast(getBaseActivity(),"click TAG_CREATE");
                        break;
                    case TAG_DELETE:
                        ToastUtils.showToast(getBaseActivity(),"click TAG_DELETE");
                        break;
                    case TAG_MODIFY:
                        ToastUtils.showToast(getBaseActivity(),"click TAG_MODIFY");
                        break;
                    default:
                        break;
                }
            }
        });
        mListPopup.showPopupWindow();
    }

    public void popStyle4BottomMenu() {
        PopWin4SlideFromBottom popwin = new PopWin4SlideFromBottom(getBaseActivity());
        popwin.showPopupWindow();
    }
    PopWin4SlideFromBottomPlus popwin;
    public void popStyle4BottomMenuPlus() {
        PopWin4SlideFromBottomPlus popwin = new PopWin4SlideFromBottomPlus(getBaseActivity());
        ArrayList<GroupAdapter.IGroupModel> groups = new ArrayList<GroupAdapter.IGroupModel>();
        for (int i = 0; i <3 ; i++) {
            GroupModel group = new GroupModel();
            group.setId(i+"");
            group.setName(i+"groupname");
            ArrayList<GroupAdapter.IGroupChildModel> childs = new ArrayList<GroupAdapter.IGroupChildModel>();
            for (int j = 0; j < 3; j++) {
                GroupChildModel child = new GroupChildModel();
                child.setId(j+"");
                child.setName("name"+j);
                childs.add(child);
            }
            group.setChilds(childs);
            groups.add(group);
        }
        popwin.initListView(groups, new PopWin4SlideFromBottomPlus.OnPopupItemClickListener() {
            @Override
            public void onItemClick(GroupAdapter.IGroupChildModel child, int postion) {
                LogUtils.d(child.getId()+child.getName());
            }
        });
        popwin.showPopupWindow();
    }
}
