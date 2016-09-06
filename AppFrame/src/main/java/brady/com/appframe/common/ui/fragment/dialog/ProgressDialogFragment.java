package brady.com.appframe.common.ui.fragment.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.frame.fastframelibrary.utils.view.TextViewUtils;

import brady.com.appframe.R;
import brady.com.appframe.common.ui.fragment.interfaces.IFragmentDataPass;
import brady.com.appframe.common.utils.UserManager;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by ZhangYuanBo on 2016/5/27.<br/>
 * 弹出加载用的提示框
 */
public class ProgressDialogFragment extends DialogFragment {

    public static ProgressDialogFragment newInstance() {
        ProgressDialogFragment frag = new ProgressDialogFragment ();
        return frag;
    }

    @Override
    public void onCreate(Bundle icicle){
        super.onCreate(icicle);
        this.setCancelable(false);// 设置点击屏幕Dialog不消失,方式1
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog dialog = new ProgressDialog(getActivity(), getTheme());
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//        View view = inflater.inflate(R.layout.fragment_dialog_login, null);
//        ButterKnife.bind(this, view);
        dialog.setTitle(getString(R.string.reminder));
        dialog.setMessage(getString(R.string.loading_prompt));
        dialog.setIndeterminate(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return dialog;
    }
}