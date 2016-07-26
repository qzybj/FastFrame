package earlll.com.testdemoall.core.ui.fragment.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import com.frame.fastframelibrary.utils.TextViewUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.ui.fragment.interfaces.IFragmentDataPass;

/**
 * Created by ZhangYuanBo on 2016/5/27.<br/>
 * 弹出一个简单的文本输入框
 */
public class InputDialogFragment extends DialogFragment {

    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.btn_surename)
    Button btn_surename;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_dialog_input, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_surename)
    public void onClick() {
        if (getActivity() instanceof IFragmentDataPass) {
            IFragmentDataPass listener = (IFragmentDataPass) getActivity();
            listener.receiveFragmentSendData(getTag(),TextViewUtils.getTextViewValue(et_name));
            dismiss();
        }
    }
}