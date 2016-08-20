package brady.com.appframe.common.ui.fragment.recyclerview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import java.util.List;
import brady.com.appframe.CApplication;
import brady.com.appframe.R;
import brady.com.appframe.common.ui.fragment.recyclerview.adapter.decoration.GridDecoration;


/**
 * Created by ZhangYuanBo on 2016/7/30.
 * Item Draggable Adapter easy implement
 */
public class DragAdapter extends BaseItemDraggableAdapter<String> implements
        OnItemDragListener,BaseQuickAdapter.OnRecyclerViewItemClickListener {

    public DragAdapter(RecyclerView recyclerView, List data) {
        super(R.layout.item_recycleview,data);
        initAdapter(recyclerView);
    }
    @Override
    protected void convert(BaseViewHolder helper, String str) {
        if (StringUtils.isNotEmpty(str)) {
            helper.setText(R.id.tv, str);
        }
    }

    private void initAdapter(RecyclerView recyclerView){
        //recyclerView.setLayoutManager(new GridLayoutManager(CApplication.instance(), 4));//设定样式 new LinearLayoutManager(mContext)
        recyclerView.addItemDecoration(new GridDecoration(getOptimizeContext(), GridDecoration.STYLE_VERTICAL));//设定分隔线
        ItemDragAndSwipeCallback mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(this);
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        enableDragItem(mItemTouchHelper);
        setOnItemDragListener(this);
        setOnRecyclerViewItemClickListener(this);
        //openLoadAnimation();
    }

    @Override
    public void onItemClick(View view, int i) {

    }

    @Override
    public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
        BaseViewHolder holder = ((BaseViewHolder) viewHolder);
        holder.setTextColor(R.id.tv, Color.WHITE);
        ((CardView) viewHolder.itemView).setCardBackgroundColor(ContextCompat.getColor(getOptimizeContext(), R.color.color_f1f1f1));
    }

    @Override
    public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
        BaseViewHolder holder = ((BaseViewHolder) viewHolder);
        holder.setTextColor(R.id.tv, Color.BLACK);
        ((CardView) viewHolder.itemView).setCardBackgroundColor(Color.WHITE);
    }

    @Override
    public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {}

    private Context getOptimizeContext(){
        return CApplication.instance();
    }
}
