package brady.com.appframe.common.ui.fragment.recyclerview.interfaces.impl;

import brady.com.appframe.common.ui.fragment.recyclerview.annotation.PullrefreshType;
import brady.com.appframe.common.ui.fragment.recyclerview.annotation.RecyclerViewStyle;
import brady.com.appframe.common.ui.fragment.recyclerview.interfaces.IRecyclerViewOptions;

/**
 * Created by ZhangYuanBo on 2016/8/19.
 */
public class RecyclerViewCommon implements IRecyclerViewOptions {

    @RecyclerViewStyle
    private int uiStyle = RecyclerViewStyle.VERTICAL_LIST;

    @PullrefreshType
    private int pullrefreshType = PullrefreshType.PULLREFRESH_OFF;

    private int spanCount=1;

    @Override
    public int getStyle() {
        return uiStyle;
    }

    @Override
    public int getPullrefreshType() {
        return pullrefreshType;
    }

    @Override
    public int getSpanCount() {
        return spanCount;
    }

    @RecyclerViewStyle
    public void setStyle(int value){
        this.uiStyle = value;
    }

    @PullrefreshType
    public void setPullrefreshType(int value) {
        this.pullrefreshType = value;
    }

    public void setSpanCount(int value) {
        this.spanCount = value;
    }
}