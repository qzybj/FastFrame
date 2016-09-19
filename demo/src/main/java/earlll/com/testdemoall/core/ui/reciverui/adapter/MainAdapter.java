package earlll.com.testdemoall.core.ui.reciverui.adapter;

import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.frame.fastframelibrary.module.loadimage.LoadImageManager;
import java.util.ArrayList;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.ui.reciverui.bean.MainItemBean;

public class MainAdapter extends BaseQuickAdapter<MainItemBean> {

    public MainAdapter(ArrayList<MainItemBean> list) {
        super( R.layout.rvadatper_main_item, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainItemBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_content, item.getContent());
        helper.setVisible(R.id.tv_date,false);
//        helper.setText(R.id.tv_date, item.getDate());
        loadImg(helper.getView(R.id.iv_icon),item.getImageUrl());
    }
    private void loadImg(ImageView iv,String imgUrl){
        LoadImageManager.instance().loadImage(iv,imgUrl);
//        Glide.with(mContext).load(imgUrl).crossFade().placeholder(R.mipmap.def_head).
//                transform(new GlideCircleTransform(mContext)).into(iv);
    }
}