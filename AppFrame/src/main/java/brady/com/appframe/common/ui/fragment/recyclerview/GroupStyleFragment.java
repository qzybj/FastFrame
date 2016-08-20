package brady.com.appframe.common.ui.fragment.recyclerview;

import android.os.Bundle;
import android.os.Handler;
import java.util.ArrayList;
import brady.com.appframe.common.ui.fragment.recyclerview.adapter.GroupAdapter;
import brady.com.appframe.common.ui.fragment.recyclerview.adapter.bean.BaseSectionItem;
import brady.com.appframe.common.ui.fragment.recyclerview.annotation.RecyclerViewStyle;
import brady.com.appframe.common.ui.fragment.recyclerview.interfaces.IRecyclerViewOptions;
import brady.com.appframe.common.ui.fragment.recyclerview.interfaces.impl.RecyclerViewCommon;

public class GroupStyleFragment extends BaseRecyclerViewFragment{
    private GroupAdapter mAdapter;
    protected final int SPAN_COUNT = 2;

    @Override
    protected IRecyclerViewOptions getOption() {
        RecyclerViewCommon option = new RecyclerViewCommon();
        option.setStyle(RecyclerViewStyle.STAGGERED_GRID);
        option.setSpanCount(SPAN_COUNT);
        return option;
    }

    @Override
    protected GroupAdapter getAdapter(){
        if(mAdapter==null){
            mAdapter = new GroupAdapter(getRecycleView(), getTestData());
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

    private ArrayList<BaseSectionItem> getTestData(){
        ArrayList<BaseSectionItem> list = new ArrayList<>();
        for (int i = 0; i <= 50; i++) {
            boolean ishead = i%5==0;
            BaseSectionItem item = ishead?new BaseSectionItem(ishead,"group"+i,ishead):new BaseSectionItem("name"+i);
            list.add(item);
        }
        return list;
    }
}
