package com.frame.fastframe.view.popwinimpl;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import com.frame.fastframe.R;
import com.frame.fastframelibrary.aosp.basepopwindow.BasePopupWindow;

/** 从底部滑上来的popup */
public class PopWin4SlideFromBottom extends BasePopupWindow implements View.OnClickListener{

    private View popupView;
    public PopWin4SlideFromBottom(Activity context) {
        super(context);
        bindEvent();
    }

    @Override
    public Animation getShowAnimation() {
        return getTranslateAnimation(250*2,0,300);
    }

    @Override
    public View getClickToDismissView() {
        return popupView.findViewById(R.id.click_to_dismiss);
    }


    @Override
    public View getInputView() {
        return null;
    }


    @Override
    public View getPopupView() {
        popupView= LayoutInflater.from(mContext).inflate(R.layout.popwin_slide_from_bottom,null);
        return popupView;
    }

    @Override
    public View getAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    private void bindEvent() {
        if (popupView!=null){
            popupView.findViewById(R.id.tx_1).setOnClickListener(this);
            popupView.findViewById(R.id.tx_2).setOnClickListener(this);
            popupView.findViewById(R.id.tx_3).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tx_1:

                break;
            case R.id.tx_2:

                break;
            case R.id.tx_3:

                break;
            default:
                break;
        }
    }
}