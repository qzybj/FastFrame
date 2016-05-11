package com.frame.fastframe.ui.simple.ui;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.frame.fastframe.R;
import com.frame.fastframe.ui.base.BaseActivity;
import com.frame.fastframelibrary.view.PasswordView;
import org.xutils.view.annotation.ViewInject;

public class SimplePassWordViewActivity extends BaseActivity {

    @ViewInject(R.id.password_til)
    PasswordView password_til;
    @ViewInject(R.id.password_strike)
    PasswordView password_strike;
    @ViewInject(R.id.iv_show)
    ImageView iv_show;
    @ViewInject(R.id.iv_show1)
    ImageView iv_show1;

    @Override
    public int getLayoutResouceId() {
        return R.layout.activity_simple_passwordview;
    }

    @Override
    public void initContentView(View view) {
        Drawable drawable  = getDrawableTest("ic_launcher");
        if(drawable!=null){
            iv_show.setImageDrawable(drawable);
        }
        drawable  = getDrawableTest("ic_launcher","mipmap");
        if(drawable!=null){
            iv_show1.setImageDrawable(drawable);
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    private Drawable getDrawableTest(String drawableName){
       return getDrawableId(drawableName,"drawable",getPackageName());
    }
    private Drawable getDrawableTest(String drawableName,String folderName){
       return getDrawableId(drawableName,folderName,getPackageName());
    }

    private Drawable getDrawableId(String drawableName,String folderName,String packageName){
        Drawable returnDrawable=null;
        Resources resources = getResources();
        int indentify = getResources().getIdentifier(drawableName,folderName, packageName);
        if(indentify>0){
            returnDrawable = resources.getDrawable(indentify);
        }
        return returnDrawable;
    }

}
