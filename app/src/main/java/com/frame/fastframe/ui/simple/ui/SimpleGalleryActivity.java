package com.frame.fastframe.ui.simple.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.frame.fastframe.R;
import com.frame.fastframe.module.aosp.touchgallery.gallerywidget.BasePagerAdapter;
import com.frame.fastframe.module.aosp.touchgallery.gallerywidget.GalleryViewPager;
import com.frame.fastframe.module.aosp.touchgallery.gallerywidget.UrlPagerAdapter;
import com.frame.fastframe.ui.base.BaseActivity;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**画廊调用示例*/
public class SimpleGalleryActivity extends BaseActivity {

    String[] mUrls = {
            "http://cs407831.userapi.com/v407831207/18f6/jBaVZFDhXRA.jpg",
            "http://cs407831.userapi.com/v4078f31207/18fe/4Tz8av5Hlvo.jpg",
            "http://cs407831.userapi.com/v407831207/1906/oxoP6URjFtA.jpg",
            "http://cs407831.userapi.com/v407831207/190e/2Sz9A774hUc.jpg",
            "http://cs407831.userapi.com/v407831207/1916/Ua52RjnKqjk.jpg",
            "http://cs407831.userapi.com/v407831207/191e/QEQE83Ok0lQ.jpg"
    };
    @ViewInject(R.id.gv_show)
    private GalleryViewPager gv_show;

    @Override
    public int getLayoutResouceId() {
        return R.layout.activity_simple_gallery;
    }

    @Override
    public void initContentView(View view) {
        initGallery(mUrls);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
    }

    public void initGallery(String[] urls) {
        List<String> items = new ArrayList<String>();
        Collections.addAll(items, urls);
        UrlPagerAdapter pagerAdapter = new UrlPagerAdapter(this, items);
        pagerAdapter.setOnItemChangeListener(new BasePagerAdapter.OnItemChangeListener(){
            @Override
            public void onItemChange(int currentPosition){
                Toast.makeText(SimpleGalleryActivity.this, "Current item is " + currentPosition, Toast.LENGTH_SHORT).show();
            }
        });
        gv_show.setOffscreenPageLimit(3);
        gv_show.setAdapter(pagerAdapter);
    }
}