package brady.com.appframe.common.ui.fragment.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.frame.fastframelibrary.module.loadimage.LoadImageManager;
import java.util.List;
import brady.com.appframe.CApplication;
import brady.com.appframe.R;
import brady.com.appframe.common.ui.fragment.recyclerview.adapter.bean.BaseSectionItem;
import brady.com.appframe.common.ui.fragment.recyclerview.adapter.decoration.GridDecoration;


public class GroupAdapter extends BaseSectionQuickAdapter<BaseSectionItem> implements
        BaseQuickAdapter.OnRecyclerViewItemClickListener,BaseQuickAdapter.OnRecyclerViewItemChildClickListener{

    public GroupAdapter(RecyclerView recyclerView, List data) {
        super( R.layout.rvadapter_child_item, R.layout.rvadapter_group_item, data);
        initAdapter(recyclerView);
    }

    private void initAdapter(RecyclerView recyclerView){
        //Group style is specific,must to replace this layoutmanager
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new GridDecoration(getOptimizeContext(), GridDecoration.STYLE_VERTICAL));
        setOnRecyclerViewItemClickListener(this);
        setOnRecyclerViewItemChildClickListener(this);
        //openLoadAnimation();
    }
    @Override
    protected void convertHead(BaseViewHolder helper, BaseSectionItem item) {
        helper.setText(R.id.header, item.header);
        helper.setVisible(R.id.more,item.isMore());
        helper.setOnClickListener(R.id.more,new OnItemChildClickListener());
    }


    @Override
    protected void convert(BaseViewHolder helper, BaseSectionItem item) {
        helper.setText(R.id.tv, item.t);
        String imgUrl = "http://yrs.yintai.com/rs/img/AppCMS/images/1186f052-21cb-4f0c-bd7d-4e379efedf37.png";
        LoadImageManager.instance().loadImage((ImageView)helper.getView(R.id.iv), imgUrl);
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
