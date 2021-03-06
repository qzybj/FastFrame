package brady.com.appframe.common.ui.fragment.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
 * 弹出登录用的提示框
 */
public class LoginDialogFragment extends DialogFragment {

    @BindView(R.id.et_username)
    EditText tv_userName;
    @BindView(R.id.et_password)
    EditText tv_password;

    @Override
    public void onCreate(Bundle icicle){
        super.onCreate(icicle);
        this.setCancelable(false);// 设置点击屏幕Dialog不消失,方式1
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog_login, null);
        ButterKnife.bind(this, view);
        builder.setView(view)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                if (getActivity() instanceof IFragmentDataPass) {
                                    IFragmentDataPass listener = (IFragmentDataPass) getActivity();
                                    UserManager.AutoLoginInfo bean = new UserManager.AutoLoginInfo();
                                    bean.setAccount(TextViewUtils.getTextViewValue(tv_userName));
                                    bean.setPassword(TextViewUtils.getTextViewValue(tv_password));
                                    listener.receiveFragmentSendData(getTag(),bean);
                                }
                            }
                        })
                .setNegativeButton("Cancel", null);
        Dialog dialog =  builder.create();
        //dialog.setCanceledOnTouchOutside(false);//设置点击屏幕Dialog不消失,方式2
        return dialog;
    }
}