package earlll.com.testdemoall.core.ui.fragment.bar;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.frame.fastframelibrary.utils.dataprocess.ListUtils;
import com.frame.fastframelibrary.utils.view.TextViewUtils;
import com.frame.fastframelibrary.utils.view.ViewUtils;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.ui.fragment.interfaces.ITabBottomBarClickListener;
import earlll.com.testdemoall.core.ui.fragment.interfaces.ITabItem;
import earlll.com.testdemoall.module.loadimage.ImageLoadUtils;
import earlll.com.testdemoall.module.loadimage.interfaces.IImageLoadCommon;


/**
 * Created by ZhangYuanBo on 2016/5/27.<br/>
 * TabBottomBar 模块：底部页面选择栏的实现(该Fragment为动态实现的)<br/>
 * 集成的activity要实现tabBottomBarClickListener接口，接收点击处理回调<br/>
 */
public class DynamicTabBottomBarFragment extends Fragment implements View.OnClickListener {

    public static final int BASE_TAB_BOTTOM_ID = 31000;

    @BindView(R.id.tab_bottom_container)
    LinearLayout tabLayout_container;

    //是否开户自定义字体颜色
    private boolean customTextColor = false;
    //默认的自定义字体颜色
    int textColor_select = android.R.color.white;
    int textColor_no_select = android.R.color.black;

    ArrayList<ITabItem> mList;
    int lastSelectViewId = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dynamic_tab_bottombar, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 外部调用tabView的初始化方法
     * @param list
     */
    public void initTabView(ArrayList<ITabItem> list) {
        if(tabLayout_container!=null){
            if(ListUtils.isNotEmpty(list)){
                mList = list;
                tabLayout_container.removeAllViews();
                for (int i = 0; i < list.size(); i++) {
                    View view = createTabView(i,list.get(i));
                    if(view!=null){
                        tabLayout_container.addView(view);
                    }
                }
            }
        }
    }

    /**生成TabView*/
    private View createTabView(int viewId,ITabItem tabItem) {
        View view = ViewUtils.inflateView(getActivity(),R.layout.fragment_tab_bottombar_item);
        if (view != null && tabItem != null) {
            view.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,1));
            view.setOnClickListener(this);
            view.setId(BASE_TAB_BOTTOM_ID + viewId);
            ImageLoadUtils.instance().loadImage((ImageView) view.findViewById(R.id.tab_item_iv), tabItem.getImageResId());
            TextViewUtils.setTextViewValue((TextView) view.findViewById(R.id.tab_item_tv), tabItem.getText());
        }
        return view;
    }

    private void setSelectTabView(View view,boolean select) {
        if(view !=null){
            view.setSelected(select);
            view.findViewById(R.id.tab_item_iv).setSelected(select);
            view.findViewById(R.id.tab_item_tv).setSelected(select);
            if(customTextColor){
                ((TextView)view.findViewById(R.id.tab_item_tv)).setTextColor(select?getResources().getColor(textColor_select):getResources().getColor(textColor_no_select));
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(lastSelectViewId!=-1){
            setSelectTabView(tabLayout_container.findViewById(lastSelectViewId),false);
        }
        setSelectTabView(view,true);
        lastSelectViewId = view.getId();
        if (getActivity() instanceof ITabBottomBarClickListener) {//将点击事件传递到主界面处理
            ITabBottomBarClickListener listener = (ITabBottomBarClickListener) getActivity();
            listener.onTabBottomBarClick(view);
        }
    }
}