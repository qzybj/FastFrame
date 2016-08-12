package earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.squareup.picasso.Picasso;
import java.util.List;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.decoration.DividerGridItemDecoration;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.entity.HomeItem;


/**
 * Created by ZhangYuanBo on 2016/7/30.
 */
public class EasyDragItemAdapter extends BaseItemDraggableAdapter<HomeItem> implements OnItemDragListener,BaseQuickAdapter.OnRecyclerViewItemClickListener {
    private Context mContext;

    public EasyDragItemAdapter(Context con, RecyclerView recyclerView,int layoutResId, List data) {
        super(layoutResId, data);
        this.mContext = con;
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext,DividerGridItemDecoration.STYLE_VERTICAL));
        setOnRecyclerViewItemClickListener(this);
//        ItemDragAndSwipeCallback mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(this);
//        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
//        mItemTouchHelper.attachToRecyclerView(recyclerView);
//        enableDragItem(mItemTouchHelper);
//        setOnItemDragListener(this);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeItem item) {
//        if (StringUtils.isNotEmpty(item.title)) {
//            helper.setText(R.id.tv, item.title);
//        }
    }

    @Override
    public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
        BaseViewHolder holder = ((BaseViewHolder) viewHolder);
        holder.setTextColor(R.id.tv, Color.WHITE);
        ((CardView) viewHolder.itemView).setCardBackgroundColor(ContextCompat.getColor(mContext, android.R.color.white));
    }

    @Override
    public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {}

    @Override
    public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
        BaseViewHolder holder = ((BaseViewHolder) viewHolder);
        holder.setTextColor(R.id.tv, Color.BLACK);
        ((CardView) viewHolder.itemView).setCardBackgroundColor(Color.WHITE);
    }

    @Override
    public void onItemClick(View view, int position) {
        HomeItem item = getItem(position);
        if(item!=null){

        }
    }

    public void loadImageUrl(ImageView iv, String imageUrl){
        Picasso.with(mContext).load(imageUrl).into(iv);
    }
}
