package com.frame.fastframe.module.previewimage.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bm.library.PhotoView;
import com.frame.fastframe.R;
import com.frame.fastframe.module.common.constant.ConstantsCommonKey;
import com.frame.fastframe.module.common.util.DensityUtil;
import com.frame.fastframe.module.previewimage.view.HackyViewPager;
import com.frame.fastframe.ui.base.BaseActivity;
import com.frame.fastframe.utils.TestDataBuilder;
import com.frame.fastframelibrary.utils.device.NetUtils;
import com.squareup.picasso.Picasso;
import org.xutils.view.annotation.ViewInject;
import java.util.ArrayList;


/** 图片浏览加大图缩放*/
public class PreviewImagesActivity extends BaseActivity {
    private final int MARGIN_DOT_DP = 6 ;

    /** 图片集合 */
    public static final String KEY_IMGURLS = ConstantsCommonKey.KEY_IMGURLS;
    /** 当前图片索引 */
    public static final String KEY_IMG_INDEX = ConstantsCommonKey.KEY_IMG_INDEX;

	/** 当前显示的图片索引值 */
	private int mItemIndex=0;
    /** 图片数据集合 */
    private ArrayList<String> mBigUrlList=null;
    /** 画廊适配器 */
    private ViewPagerAdapter mImageAdapter =null;

    /** 画廊 */
    @ViewInject(R.id.hv_pager)
    private HackyViewPager mViewPager;

    /** 底部提示点 布局 */
    @ViewInject(R.id.banner_dotlayout)
    private LinearLayout mLLDot;

    @Override
    public int getLayoutResouceId() {
        return R.layout.activity_displayimage;
    }

    @Override
    public void initContentView(View view) {
        setTitle("图片浏览");
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if(getIntent()!=null&&getIntent().getExtras()!=null){
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                mBigUrlList = extras.getStringArrayList(KEY_IMGURLS);
                mItemIndex = extras.getInt(KEY_IMG_INDEX);
            }
        }
        mBigUrlList = TestDataBuilder.imageList;//测试数据
        initPointToDo();
        initViewPager();
    }

    /**
     * 刷新GalleryUI
     */
    private void initViewPager(){
        if(!NetUtils.isNetConnected(this)){
            return ;
        }
        mImageAdapter = new ViewPagerAdapter(this,mBigUrlList);
        mViewPager.setAdapter(mImageAdapter);
        mViewPager.setCurrentItem(mItemIndex);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                mItemIndex = position;
                showSelectDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    /**
     * 给提示布局里 添加白点
     */
	private void initPointToDo() {
        //清空无用view
        mLLDot.removeAllViews();
        //添加点
		int rightMargin = DensityUtil.dip2px(this, MARGIN_DOT_DP);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.rightMargin = rightMargin;
		for (int i = 0; i < mBigUrlList.size(); i++) {// 添加白点
			mLLDot.addView(createDotIV(), lp);
		}
		if (mBigUrlList.size() > 0) {// 默认选中第一个
			showSelectDot(mItemIndex);
		}
	}

    /**
    * 将指定下标的点选中
    * @param index
    */
   private void showSelectDot(int index){
       for(int i = 0; i < mLLDot.getChildCount(); i ++){
    	   mLLDot.getChildAt(i).setSelected(index == i);
       }
   }

	/**
	 * 创建banner下的小点
	 * @return
	 */
	private ImageView createDotIV() {
		ImageView iv = new ImageView(this);
		iv.setImageResource(R.drawable.previewimage_dot_selector);//轮播图原点的资源图片,通过ImageView的selected状态来显示不同的图片
		iv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		return iv;
	}


    static class ViewPagerAdapter extends PagerAdapter {
        private Context context;
        private ArrayList<String> mImgList;
        public ViewPagerAdapter(Context context,ArrayList<String> imgUrlList) {
            this.context = context;
            this.mImgList = imgUrlList;
        }

        @Override
        public int getCount() {
            return mImgList.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            photoView.enable();// 启用图片缩放功能
            photoView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            //photoView.setMaxScale(3);//设置 最大缩放倍数
            //photoView.setAnimaDuring(1);//设置 动画持续时间
            Picasso.with(context).load(mImgList.get(position)).into(photoView);
            // Now just add PhotoView to ViewPager and return it
            container.addView(photoView);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
