package earlll.com.testdemoall.module.demo.ui.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.frame.fastframelibrary.utils.dataprocess.ListUtils;
import com.frame.fastframelibrary.utils.view.TextViewUtils;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.ui.fragment.interfaces.ITabBarClickListener;
import earlll.com.testdemoall.core.ui.fragment.interfaces.ITabItem;


/**
 * Created by ZhangYuanBo on 2016/5/27.<br/>
 * TabBottomBar 模块：底部页面选择栏的实现(该Fragment为动态实现的)<br/>
 * 集成的activity要实现tabBottomBarClickListener接口，接收点击处理回调<br/>
 */
public class HomeTopFragment extends Fragment implements View.OnClickListener {

    public static final int BASE_TAB_BOTTOM_ID = 31000;

    ArrayList<ITabItem> mList;
    @BindView(R.id.layout_funcarea_consult)
    RelativeLayout btn_consult;
    @BindView(R.id.layout_funcarea_marketing)
    RelativeLayout btn_marketing;
    @BindView(R.id.layout_funcarea_manageshop)
    RelativeLayout btn_manageshop;
    @BindView(R.id.layout_funcarea_managegoods)
    RelativeLayout btn_managegoods;

    @BindView(R.id.tv_funcarea_consult)
    TextView tvFuncareaConsult;
    @BindView(R.id.tv_funcarea_consult_msgcount)
    TextView tvFuncareaConsultMsgcount;
    @BindView(R.id.tv_funcarea_marketing)
    TextView tvFuncareaMarketing;
    @BindView(R.id.tv_funcarea_manageshop)
    TextView tvFuncareaManageshop;
    @BindView(R.id.tv_funcarea_managegoods)
    TextView tvFuncareaManagegoods;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_top, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 外部调用tabView的初始化方法
     *
     * @param list
     */
    public void initView(ArrayList<String> list) {
        TextViewUtils.setTextViewValue(tvFuncareaConsult, ListUtils.getData(list,0));
        TextViewUtils.setTextViewValue(tvFuncareaMarketing, ListUtils.getData(list,1));
        TextViewUtils.setTextViewValue(tvFuncareaManageshop, ListUtils.getData(list,2));
        TextViewUtils.setTextViewValue(tvFuncareaManagegoods, ListUtils.getData(list,3));
    }

    @Override
    @OnClick({R.id.layout_funcarea_consult, R.id.layout_funcarea_marketing, R.id.layout_funcarea_manageshop, R.id.layout_funcarea_managegoods})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_funcarea_consult:
                tvFuncareaConsult.setText(tvFuncareaConsult.getText()+"1");
                break;
            case R.id.layout_funcarea_marketing:
                tvFuncareaMarketing.setText(tvFuncareaConsult.getText()+"2");

                break;
            case R.id.layout_funcarea_manageshop:
                tvFuncareaManageshop.setText(tvFuncareaConsult.getText()+"3");

                break;
            case R.id.layout_funcarea_managegoods:
                tvFuncareaManagegoods.setText(tvFuncareaConsult.getText()+"4");
                break;
            default:
                if (getActivity() instanceof ITabBarClickListener) {//将点击事件传递到主界面处理
                    ITabBarClickListener listener = (ITabBarClickListener) getActivity();
                    listener.onTabClick(view);
                }
                break;
        }
    }
}