package brady.com.appframe.common.ui.fragment.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;
import brady.com.appframe.CApplication;
import brady.com.appframe.R;
import brady.com.appframe.common.ui.fragment.recyclerview.adapter.bean.BaseSectionItem;
import brady.com.appframe.common.ui.fragment.recyclerview.adapter.decoration.GridDecoration;


public class GroupStyleQuickAdapter extends BaseSectionQuickAdapter<BaseSectionItem> implements
        BaseQuickAdapter.OnRecyclerViewItemClickListener,BaseQuickAdapter.OnRecyclerViewItemChildClickListener{

    public GroupStyleQuickAdapter(RecyclerView recyclerView, List data) {
        super( R.layout.rvadapter_child_item, R.layout.rvadapter_group_item, data);
        initAdapter(recyclerView);
    }

    private void initAdapter(RecyclerView recyclerView){
        //recyclerView.setLayoutManager(new GridLayoutManager(CApplication.instance(), 4));//设定样式 new LinearLayoutManager(mContext)
        recyclerView.addItemDecoration(new GridDecoration(mContext, GridDecoration.STYLE_VERTICAL));//设定分隔线
        setOnRecyclerViewItemClickListener(this);
        setOnRecyclerViewItemChildClickListener(this);
        //openLoadAnimation();
    }
    @Override
    protected void convertHead(BaseViewHolder helper, BaseSectionItem item) {
        helper.setText(R.id.header, item.header);
        helper.setVisible(R.id.more,item.isMroe());
        helper.setOnClickListener(R.id.more,new OnItemChildClickListener());
    }


    @Override
    protected void convert(BaseViewHolder helper, BaseSectionItem item) {
        helper.setText(R.id.tv, item.t);
        //helper.setImageUrl(R.id.iv, video.getImg());
    }

    @Override
    public void onItemClick(View view, int i) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }
    private Context getOptimizeContext(){
        return CApplication.instance();
    }
}
