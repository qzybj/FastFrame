package com.frame.fastframe.module.previewimage.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import com.frame.fastframe.R;
import com.frame.fastframe.module.previewimage.loader.CustomImageLoader;
import com.frame.fastframe.ui.base.BaseActivity;
import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.ScrollGalleryView;
import org.xutils.view.annotation.ViewInject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PreviewImageActivity extends BaseActivity {

    private static final ArrayList<String> images = new ArrayList<String>(Arrays.asList(
            "http://img1.goodfon.ru/original/1920x1080/d/f5/aircraft-jet-su-47-berkut.jpg",
            "http://www.dishmodels.ru/picture/glr/13/13312/g13312_7657277.jpg",
            "http://img2.goodfon.ru/original/1920x1080/b/c9/su-47-berkut-c-37-firkin.jpg"
    ));
    private static final String movieUrl = "http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4";


    @ViewInject(R.id.scroll_gallery_view)
    ScrollGalleryView scrollGalleryView;

    @Override
    public int getLayoutResouceId() {
        return R.layout.activity_browseimage;
    }

    @Override
    public void initContentView(View view) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        List<MediaInfo> infos = new ArrayList<>(images.size());
        for (String url : images) {
            infos.add(MediaInfo.mediaLoader(new CustomImageLoader(url)));
        }
        scrollGalleryView
                .setThumbnailSize(100)
                .setZoom(true)
                .setFragmentManager(getSupportFragmentManager())
                .addMedia(infos);
    }

}