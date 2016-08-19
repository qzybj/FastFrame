package brady.com.appframe.common.ui.fragment.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;
import brady.com.appframe.R;
import brady.com.appframe.common.ui.fragment.recyclerview.adapter.decoration.GridDecoration;

public class QuickAdapter extends BaseQuickAdapter<String> implements
        BaseQuickAdapter.OnRecyclerViewItemClickListener,BaseQuickAdapter.OnRecyclerViewItemChildClickListener {

    public QuickAdapter(RecyclerView recyclerView,List data) {
        super(R.layout.item_recycleview,data);
        initAdapter(recyclerView);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
//        helper.setText(R.id.tweetName, item.getUserName())
//                .setVisible(R.id.tweetRT, item.isRetweet())
//                .setOnClickListener(R.id.tweetAvatar, new OnItemChildClickListener())
//                .linkify(R.id.tweetText);

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
}