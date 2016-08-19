package brady.com.appframe.common.ui.fragment.recyclerview.interfaces;

import brady.com.appframe.common.ui.fragment.recyclerview.annotation.PullrefreshType;
import brady.com.appframe.common.ui.fragment.recyclerview.annotation.RecyclerViewStyle;

/**
 * Created by ZhangYuanBo on 2016/8/19.
 * RecycleView UI cofig options must implement
 */
public interface IRecyclerViewOptions {

    @RecyclerViewStyle
    int getStyle();

    @PullrefreshType
    int getPullrefreshType();

    int getSpanCount();

}

