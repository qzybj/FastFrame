
package com.frame.fastframe.module.previewimage.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.frame.fastframe.R;
import com.frame.fastframe.module.common.util.DensityUtil;
import com.frame.fastframe.module.previewimage.view.HackyViewPager;
import com.frame.fastframe.ui.base.BaseActivity;
import com.frame.fastframelibrary.utils.DeviceUtils;
import com.frame.fastframelibrary.utils.NetUtils;
import com.squareup.picasso.Picasso;
import org.xutils.view.annotation.ViewInject;
import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;


/**
 * 描述：大图页面
 */
public class DisplayImageActivity extends BaseActivity {

    private final int MARGIN_DOT_DP = 6 ;

    /** Intent传递数据Key 图片集合 */
    public static final String INTENT_KEY_IMG="INTENT_KEY_IMG";
    /** Intent传递数据Key 当前图片索引 */
    public static final String INTENT_KEY_IMG_INDEX="INTENT_KEY_IMG_INDEX";

	/** 当前显示的图片索引值 */
	private int mItemIndex=0;
    /** 图片数据集合 */
    private ArrayList<String> mBigUrlList=null;
    /** 画廊适配器 */
    private ViewPagerAdapter mImageAdapter =null;

    /** 屏幕宽度 */
    public static int screenWidth;
    /** 屏幕高度 */
    public static int screenHeight;
    //轮播图原点的资源图片,通过ImageView的selected状态来显示不同的图片
    private int docRes = - 1;

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
        screenWidth= DeviceUtils.getScreenWidth(this);
        screenHeight=DeviceUtils.getScreenHeight(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        //"图片浏览"
        if (extras != null) {
            mBigUrlList = (ArrayList<String>) extras.getSerializable(INTENT_KEY_IMG);
            mItemIndex = extras.getInt(INTENT_KEY_IMG_INDEX);
        }
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
        mImageAdapter = new ViewPagerAdapter(this);
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
        docRes=R.drawable.sel_homebanner_dot;
        mLLDot.removeAllViews();
        //添加点
		int rightMargin = DensityUtil.dip2px(this, MARGIN_DOT_DP);
		android.widget.LinearLayout.LayoutParams lp = new android.widget.LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.rightMargin = rightMargin;
		for (int i = 0; i < mBigUrlList.size(); i++) {
			// 添加白点
			mLLDot.addView(createDotIV(), lp);
		}
		if (mBigUrlList.size() > 0) {
			// 默认选中第一个
			showSelectDot(mItemIndex);
		}
	}

    /**
    *
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
	 *
	 * @return
	 */
	private ImageView createDotIV() {
		ImageView iv = new ImageView(this);
		iv.setImageResource(docRes);
		iv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		return iv;
	}


    static class ViewPagerAdapter extends PagerAdapter {
        private Context context;
        private ArrayList<String> mImgList;
        public ViewPagerAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return mImgList.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            Picasso.with(context).load(mImgList.get(position)).into(photoView);
            // Now just add PhotoView to ViewPager and return it
            container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
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
