package brady.com.appframe.common.ui.fragment.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;

import java.util.List;

import brady.com.appframe.CApplication;
import brady.com.appframe.common.ui.fragment.recyclerview.adapter.bean.BaseMultipleItem;
import brady.com.appframe.common.ui.fragment.recyclerview.adapter.decoration.GridDecoration;

/**
 * Created by ZhangYuanBo on 2016/7/30.
 * Multiple type item adapter easy implement
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<BaseMultipleItem> implements
        BaseQuickAdapter.OnRecyclerViewItemClickListener,BaseQuickAdapter.OnRecyclerViewItemChildClickListener{

    public MultipleItemQuickAdapter(RecyclerView recyclerView, List data) {
        super( data);
        initAdapter(recyclerView);
        initStyle();
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseMultipleItem item) {
        switch (helper.getItemViewType()) {
            case BaseMultipleItem.STYLE_1:

                break;
            case BaseMultipleItem.STYLE_2:

                break;
        }
    }

    private void initStyle(){
//        addItemType(BaseMultipleItem.STYLE_1, R.layout.rvadapter_multiitem_text);
//        addItemType(BaseMultipleItem.STYLE_2, R.layout.rvadapter_multiitem_image);
//        addItemType(BaseMultipleItem.STYLE_3, R.layout.rvadapter_multiitem_images);
    }
    @Override
    public void onItemClick(View view, int i) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }
    private void initAdapter(RecyclerView recyclerView){
        //recyclerView.setLayoutManager(new GridLayoutManager(CApplication.instance(), 4));//设定样式 new LinearLayoutManager(mContext)
        recyclerView.addItemDecoration(new GridDecoration(mContext, GridDecoration.STYLE_VERTICAL));//设定分隔线
        setOnRecyclerViewItemClickListener(this);
        setOnRecyclerViewItemChildClickListener(this);
        //openLoadAnimation();
    }

    private Context getOptimizeContext(){
        return CApplication.instance();
    }
}
