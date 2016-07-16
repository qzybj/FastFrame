package earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.adapter;

import android.content.Context;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import earlll.com.testdemoall.R;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.entity.MultipleItem;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem> {

    public MultipleItemQuickAdapter(Context context, List data) {
        super( data);
        addItemType(MultipleItem.TEXT, R.layout.rvadapter_multiitem_text);
        addItemType(MultipleItem.IMG, R.layout.rvadapter_multiitem_image);
        addItemType(MultipleItem.IMGS, R.layout.rvadapter_multiitem_images);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.TEXT:
                helper.setText(R.id.tv, item.getContent());
                break;
            case MultipleItem.IMG:
                // set img data
                break;
            case MultipleItem.IMGS:
                // set imgs data
                break;
        }
    }
}
