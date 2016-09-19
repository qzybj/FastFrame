package earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.adapter;

import android.animation.Animator;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.entity.Status;
import earlll.com.testdemoall.aosp.glide.transform.GlideCircleTransform;
import earlll.com.testdemoall.module.dataserver.DataServer;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class QuickAdapter extends BaseQuickAdapter<Status> {
    public QuickAdapter() {
        super( R.layout.rvadatper_quick_item, DataServer.getSampleData(100));
    }

    public QuickAdapter(int dataSize) {
        super( R.layout.rvadatper_quick_item, DataServer.getSampleData(dataSize));
    }
    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index < 5)
            anim.setStartDelay(index * 150);
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
        helper.setText(R.id.tv_title, item.getUserName())
                .setText(R.id.tv_content, item.getText())
                .setText(R.id.tv_date, item.getCreatedAt())
                .setVisible(R.id.iv_right, item.isRetweet())
                .setOnClickListener(R.id.iv_icon, new OnItemChildClickListener())
                .setOnClickListener(R.id.tv_title, new OnItemChildClickListener())
                .linkify(R.id.tv_content);

        Glide.with(mContext).load(item.getUserAvatar()).crossFade().placeholder(R.mipmap.def_head).
                transform(new GlideCircleTransform(mContext)).into((ImageView) helper.getView(R.id.iv_icon));
    }
}
