package earlll.com.testdemoall.ui.fragment.interfaces;

/**
 * Created by ZhangYuanBo on 2016/5/30.
 *  用于DialogFragment与activity的数据
 */
public interface IDialogCallBack {
    /**requestcode 基本值*/
    int DIALOGCALLBACK_BTN_LEFT = 11001;
    int DIALOGCALLBACK_BTN_RIGHT = 11002;

    /**
     * 接收Fragment发送的数据
     * @param tag Fragment tag
     * @param type
     */
    void btnCallBack(String tag, int type);
}
