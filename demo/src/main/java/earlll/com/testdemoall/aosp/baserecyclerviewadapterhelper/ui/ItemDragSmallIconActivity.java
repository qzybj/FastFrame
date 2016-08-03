package earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import java.util.ArrayList;
import java.util.List;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.utils.TestDataBuilder;
import earlll.com.testdemoall.module.demo.bean.TestBean;
import earlll.com.testdemoall.module.loadimage.ImageLoadUtils;

/**
 * 小Icon的拖拽的Adapter的使用示例样式
 */
public class ItemDragSmallIconActivity extends Activity {
    private static final String TAG = ItemDragSmallIconActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private List<TestBean> mData;
    private ItemDragSmallIconAdapter mAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private ItemDragAndSwipeCallback mItemDragAndSwipeCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvadapter_common);

        mRecyclerView = (RecyclerView)findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        mData = generateData(50);
        //拖拽监听
        OnItemDragListener listener = new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d(TAG, "drag start");
                BaseViewHolder holder = ((BaseViewHolder)viewHolder);
                holder.setTextColor(R.id.tv, Color.WHITE);
                ((CardView)viewHolder.itemView).setCardBackgroundColor(ContextCompat.getColor(ItemDragSmallIconActivity.this, R.color.color_20cdff));
            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
                Log.d(TAG, "move from: " + source.getAdapterPosition() + " to: " + target.getAdapterPosition());
            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d(TAG, "drag end");
                BaseViewHolder holder = ((BaseViewHolder)viewHolder);
                holder.setTextColor(R.id.tv, Color.BLACK);
                ((CardView)viewHolder.itemView).setCardBackgroundColor(Color.WHITE);
            }
        };

        mAdapter = new ItemDragSmallIconAdapter(mData);
        mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

        mAdapter.enableDragItem(mItemTouchHelper);
        mAdapter.setOnItemDragListener(listener);
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<TestBean> generateData(int size) {
        ArrayList<TestBean> data = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            data.add(TestDataBuilder.getTestBean("item " + i,"item " + i));
        }
        return data;
    }

    public class ItemDragSmallIconAdapter extends BaseItemDraggableAdapter<TestBean> {
        public ItemDragSmallIconAdapter(List data) {
            super(R.layout.rvadapter_item_dragsmallicon, data);
        }
        @Override
        protected void convert(BaseViewHolder helper, TestBean item) {
            helper.setText(R.id.tv, item.getName());
            ImageView iv = helper.getView(R.id.iv);
            ImageLoadUtils.instance().loadImage(iv,item.getImageurl());
        }
    }
}
