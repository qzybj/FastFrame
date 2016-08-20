package brady.com.appframe.common.ui.fragment.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.frame.fastframelibrary.module.loadimage.LoadImageManager;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import java.util.List;
import brady.com.appframe.CApplication;
import brady.com.appframe.R;
import brady.com.appframe.common.ui.fragment.recyclerview.adapter.bean.BaseMultipleItem;
import brady.com.appframe.common.ui.fragment.recyclerview.adapter.decoration.GridDecoration;

/**
 * Created by ZhangYuanBo on 2016/7/30.
 * Multiple type item adapter easy implement
 */
public class MultipleTypeAdapter extends BaseMultiItemQuickAdapter<BaseMultipleItem> implements
        BaseQuickAdapter.OnRecyclerViewItemClickListener,BaseQuickAdapter.OnRecyclerViewItemChildClickListener{

    public MultipleTypeAdapter(RecyclerView recyclerView, List data) {
        super( data);
        initAdapter(recyclerView);
        initStyle();
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseMultipleItem item) {
        switch (helper.getItemViewType()) {
            case BaseMultipleItem.STYLE_1:
                if (StringUtils.isNotEmpty(item.getName())) {
                    helper.setText(R.id.tv, item.getName());
                }
                break;
            case BaseMultipleItem.STYLE_2:
                helper.setText(R.id.tv, item.getName());
                String imgUrl = "http://yrs.yintai.com/rs/img/AppCMS/images/1186f052-21cb-4f0c-bd7d-4e379efedf37.png";
                LoadImageManager.instance().loadImage((ImageView)helper.getView(R.id.iv), imgUrl);
                break;
            default:
                break;
        }
    }

    private void initStyle(){
        addItemType(BaseMultipleItem.STYLE_1, R.layout.item_recycleview);
        addItemType(BaseMultipleItem.STYLE_2, R.layout.rvadapter_child_item);
    }
    @Override
    public void onItemClick(View view, int i) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }
    private void initAdapter(RecyclerView recyclerView){
        //recyclerView.setLayoutManager(new GridLayoutManager(CApplication.instance(), 4));//设定样式 new LinearLayoutManager(mContext)
        recyclerView.addItemDecoration(new GridDecoration(getOptimizeContext(), GridDecoration.STYLE_VERTICAL));//设定分隔线
        setOnRecyclerViewItemClickListener(this);
        setOnRecyclerViewItemChildClickListener(this);
        //openLoadAnimation();
    }

    private Context getOptimizeContext(){
        return CApplication.instance();
    }
}
