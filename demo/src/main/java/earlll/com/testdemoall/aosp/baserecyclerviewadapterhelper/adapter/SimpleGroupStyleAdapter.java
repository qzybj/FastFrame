package earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;

import java.util.List;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.decoration.DividerGridItemDecoration;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.entity.MySection;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.entity.Video;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.ui.GroupStyleAdapterActivity;


public class SimpleGroupStyleAdapter extends BaseSectionQuickAdapter<MySection> implements BaseQuickAdapter.OnRecyclerViewItemClickListener,BaseQuickAdapter.OnRecyclerViewItemChildClickListener  {
    private Context mContext;

    /**
     *
     * Same as QuickAdapter#QuickAdapter(Context,int) but with some initialization data.
     *
     * @param con
     * @param layoutResId The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param recyclerView
     * @param data        A new list is created out of this one to avoid mutable list
     */
    public SimpleGroupStyleAdapter(Context con, int layoutResId, int sectionHeadResId, RecyclerView recyclerView, List data) {
        super( layoutResId, sectionHeadResId, data);
        this.mContext = con;
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));//设定样式 new LinearLayoutManager(mContext)
        recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext, DividerGridItemDecoration.STYLE_VERTICAL));//设定分隔线
        setOnRecyclerViewItemClickListener(this);
        //ItemDragAndSwipeCallback mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(this);
        //openLoadAnimation();
        //setOnLoadMoreListener(this);
        //openLoadMore(page_size, true);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, final MySection item) {
        helper.setText(R.id.header, item.header);
        helper.setVisible(R.id.more,item.isMroe());
        helper.setOnClickListener(R.id.more,new OnItemChildClickListener());
    }


    @Override
    protected void convert(BaseViewHolder helper, MySection item) {
        Video video = (Video) item.t;
        //helper.setImageUrl(R.id.iv, video.getImg());
        helper.setText(R.id.tv, video.getName());
    }
    @Override
    public void onItemClick(View view, int position) {
        MySection mySection = (MySection)getItem(position);
        if (mySection.isHeader)
            Toast.makeText(mContext, mySection.header, Toast.LENGTH_LONG).show();
        else
            Toast.makeText(mContext, mySection.t.getName(), Toast.LENGTH_LONG).show();
    }
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        Toast.makeText(mContext, "onItemChildClick", Toast.LENGTH_LONG).show();
    }
}
