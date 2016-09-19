package earlll.com.testdemoall.core.ui.fragment.interfaces;

/**
 * Created by ZhangYuanBo on 2016/5/30.
 *  用于Fragment与activity的数据
 */
public interface ISendData {
    /**
     * 接收Fragment发送的数据
     * @param tag Fragment tag
     * @param obj
     */
    void receive(String tag, Object obj);
}
