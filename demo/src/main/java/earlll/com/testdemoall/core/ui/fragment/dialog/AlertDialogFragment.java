package earlll.com.testdemoall.core.ui.fragment.dialog;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.frame.fastframelibrary.utils.StringUtils;
import com.frame.fastframelibrary.utils.TextViewUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.ui.fragment.interfaces.IDialogCallBack;


/**
 * Created by ZhangYuanBo on 2016/5/27.<br/>
 * 弹出提示框
 */
public class AlertDialogFragment extends DialogFragment {

    @BindView(android.R.id.title)
    TextView tv_title;
    @BindView(android.R.id.content)
    TextView tv_content;
    @BindView(android.R.id.button1)
    Button btn_ok;
    @BindView(android.R.id.button2)
    Button btn_cannel;
    private String title;
    private String content;
    private String btn1;
    private String btn2;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        this.setCancelable(false);// 设置点击屏幕Dialog不消失,方式1
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_dialog_alert, container, false);
        ButterKnife.bind(this, view);
        initViewUI(savedInstanceState);
        return view;
    }

    private void initViewUI(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            TextViewUtils.setTextViewValue(tv_title,savedInstanceState.getString("tv_title"));
            TextViewUtils.setTextViewValue(tv_content,savedInstanceState.getString("tv_content"));
            TextViewUtils.setTextViewValue(btn_ok,savedInstanceState.getString("btn_ok"));
            TextViewUtils.setTextViewValue(btn_cannel,savedInstanceState.getString("btn_cannel"));
        }else{
            TextViewUtils.setTextViewValue(tv_title,title);
            TextViewUtils.setTextViewValue(tv_content,content);
            TextViewUtils.setTextViewValue(btn_ok,btn1);
            TextViewUtils.setTextViewValue(btn_cannel,btn2);
        }
    }

    @OnClick({android.R.id.button1, android.R.id.button2})
    public void onClick(View view) {
        if (getActivity() instanceof IDialogCallBack) {
            IDialogCallBack listener = (IDialogCallBack) getActivity();
            switch (view.getId()) {
                case android.R.id.button1:
                    listener.btnCallBack(getTag(),IDialogCallBack.DIALOGCALLBACK_BTN_LEFT);
                    break;
                case android.R.id.button2:
                    listener.btnCallBack(getTag(),IDialogCallBack.DIALOGCALLBACK_BTN_RIGHT);
                    break;
                default:
                    listener.btnCallBack(getTag(),view.getId());
                    break;
            }
        }
        dismiss();
    }

    public void setTitle(String titleStr) {
        title = StringUtils.isNotEmpty(titleStr)?titleStr:title;
    }
    public void setContent(String contentStr) {
        content = StringUtils.isNotEmpty(contentStr)?contentStr:content;
    }
    public void setBtn(String btnLeft,String btnRight) {
        btn1 = StringUtils.isNotEmpty(btnLeft)?btnLeft:btn1;
        btn2 = StringUtils.isNotEmpty(btnRight)?btnRight:btn2;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(outState!=null){
            outState.putCharSequence("tv_title",TextViewUtils.getTextViewValue(tv_title));
            outState.putCharSequence("tv_content",TextViewUtils.getTextViewValue(tv_content));
            outState.putCharSequence("btn_ok",TextViewUtils.getTextViewValue(btn_ok));
            outState.putCharSequence("btn_cannel",TextViewUtils.getTextViewValue(btn_cannel));
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        saveViewValue();
        super.onDismiss(dialog);
    }

    private void saveViewValue() {
        title =TextViewUtils.getTextViewValue(tv_title);
        content=TextViewUtils.getTextViewValue(tv_content);
        btn1=TextViewUtils.getTextViewValue(btn_ok);
        btn2=TextViewUtils.getTextViewValue(btn_cannel);
    }
}