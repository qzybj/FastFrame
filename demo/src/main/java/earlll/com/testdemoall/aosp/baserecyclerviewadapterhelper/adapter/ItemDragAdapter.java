package earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.adapter;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;
import earlll.com.testdemoall.R;

/**
 * Created by luoxw on 2016/6/20.
 */
public class ItemDragAdapter extends BaseItemDraggableAdapter<String> {
    public ItemDragAdapter(List data) {
        super(R.layout.rvadapter_item_draggable, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv, item);
    }
}
