package earlll.com.testdemoall.core.ui.fragment.bar;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.frame.fastframelibrary.utils.view.TextViewUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.ui.fragment.interfaces.ITabBarClickListener;
import earlll.com.testdemoall.core.ui.fragment.interfaces.ITabItem;
import com.frame.fastframelibrary.module.loadimage.LoadImageManager;


/**
 * Created by ZhangYuanBo on 2016/5/27.
 * TabBottomBar 模块：底部页面选择栏的实现，该方法为布局实现，非动态的。
 */
public class TabBottomBarFragment extends Fragment {

    @BindView(R.id.tab_bottom_layout_home)
    LinearLayout tabLayout_home;
    @BindView(R.id.tab_bottom_layout_biz)
    LinearLayout tabLayout_biz;
    @BindView(R.id.tab_bottom_layout_bbs)
    LinearLayout tabLayout_bbs;
    @BindView(R.id.tab_bottom_layout_article)
    LinearLayout tabLayout_article;
    @BindView(R.id.tab_bottom_layout_more)
    LinearLayout tabLayout_more;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_bottombar, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.tab_bottom_layout_home, R.id.tab_bottom_layout_biz, R.id.tab_bottom_layout_bbs, R.id.tab_bottom_layout_article, R.id.tab_bottom_layout_more})
    public void onClick(View view) {//将点击事件传递到主界面处理
        if (getActivity() instanceof ITabBarClickListener) {
            ITabBarClickListener listener = (ITabBarClickListener) getActivity();
            listener.onTabClick(view);
        }
    }

    public void setTabItem(int tabid,ITabItem tabItem){
        switch (tabid){
            case R.id.tab_bottom_layout_home:

                break;
            case R.id.tab_bottom_layout_biz:

                break;
            case R.id.tab_bottom_layout_bbs:

                break;
            case R.id.tab_bottom_layout_article:

                break;
            case R.id.tab_bottom_layout_more:

                break;
        }
    }
    public void setTabItem(View view,ITabItem tabItem){
        if(view!=null&&tabItem!=null){
            LoadImageManager.instance().loadImage((ImageView)view.findViewById(R.id.tab_item_iv),tabItem.getImageResId());
            TextViewUtils.setTextViewValue((TextView)view.findViewById(R.id.tab_item_tv),tabItem.getText());
        }
    }
}