package earlll.com.testdemoall.core.ui.fragment.bar;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.frame.fastframelibrary.utils.StringUtils;
import com.frame.fastframelibrary.utils.TextViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.ui.fragment.interfaces.ITitleBarClickListener;


/**
 * Created by ZhangYuanBo on 2016/5/27.
 * titlebar 模块：页面项部标题栏的实现
 */
public class TitleBarFragment extends Fragment {
    @BindView(R.id.titlebar_layout_left)
    LinearLayout layout_leftbtn;
    @BindView(R.id.titlebar_tv_left)
    TextView tv_left;
    @BindView(R.id.titlebar_tv_title)
    TextView tv_title;
    @BindView(R.id.titlebar_layout_right)
    LinearLayout layout_rightbtn;
    @BindView(R.id.titlebar_tv_right)
    TextView tv_right;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_titlebar, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.titlebar_layout_left, R.id.titlebar_layout_right})
    public void onClick(View view) {
        if (getActivity() instanceof ITitleBarClickListener) {
            ITitleBarClickListener listener = (ITitleBarClickListener) getActivity();
            if (layout_leftbtn  == view) {
                listener.onClickTitleLeft(view);
            } else if (layout_rightbtn == view) {
                listener.onClickTitleRight(view);
            } else{
                listener.onTitleBarClick(view);
            }
        }
    }

    //--------------------设置标题栏展示实现方法
    /**
     * 设置title内容
     * @param titleResid
     */
    public final void setTitle(int titleResid) {
        setTitle(getString(titleResid));
    }

    /**
     * 设置title内容
     *
     * @param titleStr
     */
    public final void setTitle(String titleStr) {
        TextViewUtils.setTextViewValue(tv_title,titleStr);
    }

    /**
     * 控制标题栏左边按钮显示或隐藏
     */
    public final void setLeftVisibility(boolean isVisibility) {
        if (layout_leftbtn != null) {
            layout_leftbtn.setVisibility(isVisibility ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 设置title左边按钮的显示内容
     *
     * @param titleResid
     */
    public final void setLeftText(int titleResid) {
        if (titleResid > 0) {
            setLeftText(getString(titleResid));
        }
    }

    /**
     * 设置title左边按钮的显示内容
     *
     * @param titleStr
     */
    public final void setLeftText(String titleStr) {
        if (tv_left != null && StringUtils.isNotEmpty(titleStr)) {
            TextViewUtils.setTextViewValue(tv_left,titleStr);
        }
    }

    /**
     * 设置title左边按钮的显示图片
     * @param drawableResid
     */
    public final void setLeftDrawable(int drawableResid, int padding) {
        if (tv_left != null) {
            if (drawableResid > 0) {
                Drawable drawable = getResources().getDrawable(drawableResid);
                if (drawable != null) {
                    TextViewUtils.setTextViewDrawable(tv_left, drawable, padding);
                }
            } else {
                TextViewUtils.setTextViewDrawable(tv_left, null, padding);
            }
        }
    }


    /**
     * 控制标题栏右边按钮显示或隐藏
     */
    public final void setRightVisibility(boolean isVisibility) {
        if (layout_rightbtn != null) {
            layout_rightbtn.setVisibility(isVisibility ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 设置title右边按钮的显示内容
     *
     * @param titleResid
     */
    public final void setRightText(int titleResid) {
        if (titleResid > 0) {
            setRightText(getString(titleResid));
        }
    }

    /**
     * 设置title右边按钮的显示内容
     *
     * @param title
     */
    public final void setRightText(String title) {
        TextViewUtils.setTextViewValue(tv_right,title);
    }

    /**
     * 设置title右边按钮的显示图片
     * @param drawableResid
     */
    public final void setRightDrawable(int drawableResid, int padding) {
        if (tv_right != null && drawableResid > 0) {
            Drawable drawable = getResources().getDrawable(drawableResid);
            if (drawable != null) {
                TextViewUtils.setTextViewDrawable(tv_right, drawable, padding);
            }
        }
    }
}