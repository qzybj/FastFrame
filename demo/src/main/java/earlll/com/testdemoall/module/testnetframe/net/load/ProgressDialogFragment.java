package earlll.com.testdemoall.module.testnetframe.net.load;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;

import com.frame.fastframelibrary.R;

/**
 * Created by ZhangYuanBo on 2016/5/27.<br/>
 * 弹出加载用的提示框
 */
public class ProgressDialogFragment extends DialogFragment {

    public static ProgressDialogFragment newInstance() {
        ProgressDialogFragment frag = new ProgressDialogFragment();
        return frag;
    }

    @Override
    public void onCreate(Bundle icicle){
        super.onCreate(icicle);
        //this.setCancelable(false);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog dialog = new ProgressDialog(getActivity(), getTheme());
        dialog.setTitle(getString(R.string.reminder));
        dialog.setMessage(getString(R.string.loading_prompt));
        dialog.setIndeterminate(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return dialog;
    }
}