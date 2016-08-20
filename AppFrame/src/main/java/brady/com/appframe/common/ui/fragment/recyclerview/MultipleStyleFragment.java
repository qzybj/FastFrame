package brady.com.appframe.common.ui.fragment.recyclerview;

import android.os.Bundle;
import android.os.Handler;
import java.util.ArrayList;
import brady.com.appframe.common.ui.fragment.recyclerview.adapter.MultipleTypeAdapter;
import brady.com.appframe.common.ui.fragment.recyclerview.adapter.bean.BaseMultipleItem;
import brady.com.appframe.common.ui.fragment.recyclerview.annotation.RecyclerViewStyle;
import brady.com.appframe.common.ui.fragment.recyclerview.interfaces.IRecyclerViewOptions;
import brady.com.appframe.common.ui.fragment.recyclerview.interfaces.impl.RecyclerViewCommon;

public class MultipleStyleFragment extends BaseRecyclerViewFragment{
    private MultipleTypeAdapter mAdapter;
    protected final int SPAN_COUNT = 2;

    @Override
    protected IRecyclerViewOptions getOption() {
        RecyclerViewCommon option = new RecyclerViewCommon();
        option.setStyle(RecyclerViewStyle.VERTICAL_GRID);
        option.setSpanCount(SPAN_COUNT);
        return option;
    }

    @Override
    protected MultipleTypeAdapter getAdapter(){
        if(mAdapter==null){
            mAdapter = new MultipleTypeAdapter(getRecycleView(), getTestData());
        }
        return mAdapter;
    }

    @Override
    protected void customInitData(Bundle savedInstanceState) {

    }
    @Override
    protected void initRecycleView() {
//        getAdapter().setOnLoadMoreListener(this);
//        getAdapter().openLoadMore(PAGE_SIZE, true);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
                getAdapter().setNewData(getTestData());
                getAdapter().notifyDataSetChanged();
            }
        }, 1000);
    }

    @Override
    public void onLoadMoreRequested() {

    }

    private ArrayList<BaseMultipleItem> getTestData(){
        ArrayList<BaseMultipleItem> list = new ArrayList<>();
        boolean styleFlag = false;
        for (int i = 'A'; i <= 'z'; i++) {
            BaseMultipleItem item = new BaseMultipleItem();
            item.setName((char)i+"");
            item.setItemType(styleFlag?BaseMultipleItem.STYLE_1:BaseMultipleItem.STYLE_2);
            styleFlag=!styleFlag;
            list.add(item);
        }
        return list;
    }
}
