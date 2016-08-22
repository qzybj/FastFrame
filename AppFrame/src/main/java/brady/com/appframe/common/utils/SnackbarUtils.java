package brady.com.appframe.common.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

public class SnackbarUtils {
	private static Snackbar mSnackbar;

	public static void show(View view, String msg,String closeBtnText,boolean isLong) {
		show(view,msg,closeBtnText,isLong,null);
	}
	public static void show(View view, String msg,String actionText,boolean isLong,final View.OnClickListener listener) {
		if(mSnackbar==null){
			mSnackbar = Snackbar.make(view, msg,isLong?Snackbar.LENGTH_LONG:Snackbar.LENGTH_SHORT);
		}
		mSnackbar.setText(msg)
				.setAction(actionText,new View.OnClickListener() {
					@Override public void onClick(View v) {
						mSnackbar.dismiss();
						listener.onClick(v);
					}
				})
				.show();
	}
}
