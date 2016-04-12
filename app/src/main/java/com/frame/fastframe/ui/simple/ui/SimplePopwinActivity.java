package com.frame.fastframe.ui.simple.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.frame.fastframe.R;
import com.frame.fastframe.bean.SingleTypeBean;
import com.frame.fastframe.ui.base.BaseActivity;
import com.frame.fastframe.ui.simple.adapter.SingleTypeAdapter;
import com.frame.fastframe.view.popwinimpl.PopWin4List;
import com.frame.fastframelibrary.utils.ToastUtils;

import org.xutils.view.annotation.Event;

import java.util.ArrayList;

public class SimplePopwinActivity extends BaseActivity {
    public static final int TAG_CREATE=0x01;
    public static final int TAG_DELETE=0x02;
    public static final int TAG_MODIFY=0x03;

    @Event(value = R.id.btn_popwin1, type = View.OnClickListener.class)
    private void clickBtn1(View view) {

    }
    @Event(value = R.id.btn_popwin2, type = View.OnClickListener.class)
    private void clickBtn2(View view) {

    }
    @Event(value = R.id.btn_popwin3, type = View.OnClickListener.class)
    private void clickBtn3(View view) {

    }
    @Event(value = R.id.btn_popwin4, type = View.OnClickListener.class)
    private void clickBtn4(View view) {

    }

    private ListView mListView;
    private ArrayList<SingleTypeBean> mDatas = new ArrayList<SingleTypeBean>();

    private SingleTypeAdapter mAdapter;


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

    public void bindEvent() {
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

    }




}
