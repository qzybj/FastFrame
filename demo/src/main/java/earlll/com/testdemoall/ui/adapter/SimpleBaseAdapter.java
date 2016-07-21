package earlll.com.testdemoall.ui.adapter;

import android.animation.Animator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import earlll.com.testdemoall.R;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.entity.Status;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.transform.GlideCircleTransform;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.utils.DataServer;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class SimpleBaseAdapter extends BaseQuickAdapter<Status> {
    private XRecyclerView mRecyclerView;

    public SimpleBaseAdapter() {
        super( R.layout.rvadatper_quick_item, DataServer.getSampleData(100));
    }

    public SimpleBaseAdapter(XRecyclerView adapterView, int dataSize) {
        super( R.layout.rvadatper_quick_item, DataServer.getSampleData(dataSize));
        this.mRecyclerView = adapterView;
    }
    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index < 5){
            anim.setStartDelay(index * 150);
        }
    }

    @Override
    public int getHeaderViewsCount() {
        return super.getHeaderViewsCount()+1;
    }


    @Override
    protected void convert(BaseViewHolder helper, Status item) {
        helper.setText(R.id.tweetName, item.getUserName())
                .setText(R.id.tweetText, item.getText())
                .setText(R.id.tweetDate, item.getCreatedAt())
                .setVisible(R.id.tweetRT, item.isRetweet())
                .setOnClickListener(R.id.tweetAvatar, new OnItemChildClickListener())
                .setOnClickListener(R.id.tweetName, new OnItemChildClickListener())
                .linkify(R.id.tweetText);
        Glide.with(mContext).load(item.getUserAvatar()).crossFade().placeholder(R.mipmap.def_head).
                transform(new GlideCircleTransform(mContext)).into((ImageView) helper.getView(R.id.tweetAvatar));
    }
}
