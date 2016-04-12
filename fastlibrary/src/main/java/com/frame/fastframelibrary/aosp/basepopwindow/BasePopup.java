package com.frame.fastframelibrary.aosp.basepopwindow;

import android.view.View;

/**
 * Created by 大灯泡 on 2016/1/14.
 */
public interface BasePopup {
    /**
     * 得到popupwindow的主体，一般是在xml文件写好然后inflate出来并返回
     * @return
     */
     View getPopupView();
     /**
      * 得到用于展示动画的view，一般为了做到蒙层效果，我们的xml都会给一个灰色半透明的底层然后才是给定展示的popup
      * @return
      */
     View getAnimaView();
}