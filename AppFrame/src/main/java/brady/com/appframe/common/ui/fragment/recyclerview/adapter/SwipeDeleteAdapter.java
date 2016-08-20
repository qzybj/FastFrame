package brady.com.appframe.common.ui.fragment.recyclerview.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;

import java.util.List;

import brady.com.appframe.CApplication;
import brady.com.appframe.R;
import brady.com.appframe.common.ui.fragment.recyclerview.adapter.decoration.GridDecoration;


/**
 * Created by ZhangYuanBo on 2016/7/30.
 * Item Draggable Adapter easy implement
 */
public class SwipeDeleteAdapter extends BaseItemDraggableAdapter<String> implements
        OnItemSwipeListener,BaseQuickAdapter.OnRecyclerViewItemClickListener {
    Paint swipePaint;

    public SwipeDeleteAdapter(RecyclerView recyclerView, List data) {
        super(R.layout.item_recycleview,data);
        initAdapter(recyclerView);
    }
    @Override
    protected void convert(BaseViewHolder helper, String str) {
        if (StringUtils.isNotEmpty(str)) {
            helper.setText(R.id.tv, str);
        }
    }


    @Override
    public void onItemClick(View view, int i) {

    }

    @Override
    public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
        Log.d(TAG, "view swiped start: " + pos);
        BaseViewHolder holder = ((BaseViewHolder)viewHolder);
        holder.setTextColor(R.id.tv, Color.WHITE);
        ((CardView)viewHolder.itemView).setCardBackgroundColor(Color.YELLOW);
    }

    @Override
    public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
        Log.d(TAG, "View reset: " + pos);
        BaseViewHolder holder = ((BaseViewHolder)viewHolder);
        holder.setTextColor(R.id.tv, Color.BLACK);
        ((CardView)viewHolder.itemView).setCardBackgroundColor(Color.WHITE);
    }

    @Override
    public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
        LogUtils.d(TAG, "View Swiped: " + pos);
    }

    @Override
    public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
        canvas.drawColor(ContextCompat.getColor(getOptimizeContext(), R.color.color_20cdff));
        canvas.drawText("Just some text", 0, 40, getPaint());
    }

    private Context getOptimizeContext(){
        return CApplication.instance();
    }

    private void initAdapter(RecyclerView recyclerView){
        //recyclerView.setLayoutManager(new GridLayoutManager(CApplication.instance(), 4));//设定样式 new LinearLayoutManager(mContext)
        recyclerView.addItemDecoration(new GridDecoration(getOptimizeContext(), GridDecoration.STYLE_VERTICAL));//设定分隔线

        ItemDragAndSwipeCallback mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(this);
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);

        //mItemDragAndSwipeCallback.setDragMoveFlags(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN);
        mItemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.START | ItemTouchHelper.END);

        enableSwipeItem();
        setOnItemSwipeListener(this);
        setOnRecyclerViewItemClickListener(this);
        //openLoadAnimation();


    }
    private Paint getPaint(){
        if(swipePaint==null){
            swipePaint = new Paint();
            swipePaint.setAntiAlias(true);
            swipePaint.setTextSize(20);
            swipePaint.setColor(Color.BLACK);
        }
        return swipePaint;
    }
}
