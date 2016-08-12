package earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import com.squareup.picasso.Picasso;
import java.util.List;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.decoration.DividerGridItemDecoration;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.entity.HomeItem;
import earlll.com.testdemoall.core.utils.WebViewUtilPlus;


/**
 * Created by ZhangYuanBo on 2016/7/30.
 * ItemDraggableAdapter修改来的简单实现示例
 */
public  class SimpleDragItemAdapter extends BaseItemDraggableAdapter<HomeItem> implements OnItemDragListener,BaseQuickAdapter.OnRecyclerViewItemClickListener {
    private Context mContext;

    public SimpleDragItemAdapter(Context con,int layoutResId, RecyclerView recyclerView, List data) {
        super(layoutResId,data);
        this.mContext = con;
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));//设定样式
        recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext, DividerGridItemDecoration.STYLE_VERTICAL));//设定分隔线
        ItemDragAndSwipeCallback mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(this);
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        enableDragItem(mItemTouchHelper);
        setOnItemDragListener(this);
        setOnRecyclerViewItemClickListener(this);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeItem item) {
        setText(helper,R.id.tv,item.getTitle());
        loadImageUrl((ImageView)helper.getView(R.id.iv),"");
    }

    @Override
    public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
        BaseViewHolder holder = ((BaseViewHolder) viewHolder);
        holder.setTextColor(R.id.tv, Color.WHITE);
        ((CardView) viewHolder.itemView).setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.color_f1f1f1));
    }

    @Override
    public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
    }

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
            WebViewUtilPlus.goInnerWebView(mContext, item.getTitle(), item.getTitle());
        }
    }

    public void setText(BaseViewHolder helper,int viewId, String content){
        if (StringUtils.isNotEmpty(content)) {
            helper.setText(viewId, content);
        }
    }

    public void loadImageUrl(ImageView iv, String imageUrl){
        if (iv!=null&&StringUtils.isNotEmpty(imageUrl)) {
            Picasso.with(mContext).load(imageUrl).into(iv);
        }
    }
}
