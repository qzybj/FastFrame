package brady.com.appframe.common.ui.base;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;

import com.frame.fastframelibrary.ui.base.FrameBaseActivity;
import com.frame.fastframelibrary.utils.app.ActivityStack;
import com.frame.fastframelibrary.utils.view.ToastUtils;


/**
 * Activity的base基类
 */
public abstract class AbstractBaseActivity extends FrameBaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityStack.getInstance().addActivity(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityStack.getInstance().removeActivity(this);
	}

	/**
	 * 点击事件处理<BR>
	 * @param v
	 */
	abstract void clickEvent(View v);

	@Override
	final public void onClick(View v) {
		clickEvent(v);
	}

	/**
	 * 展示Fragment
	 * @param targetFragment 目标fragment
	 */
	protected void showDialogFragment(DialogFragment targetFragment){
		showDialogFragment(targetFragment,targetFragment.getTag());
	}

	/**
	 * 展示Fragment
	 * @param targetFragment 目标fragment
	 * @param tag
	 */
	protected void showDialogFragment(DialogFragment targetFragment,String tag){
		if(!isFinishing()&&targetFragment!=null){
			targetFragment.show(getFragmentManager(), tag);
		}
	}
	public final void showToast(int res) {
		showToast(getString(res));
	}

	public final void showToast(final String msg) {
		ToastUtils.showToast(this, msg, false);
	}
}