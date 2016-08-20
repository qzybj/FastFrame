package brady.com.appframe.common.ui.fragment.recyclerview.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by ZhangYuanBo on 2016/8/19.
 */
@IntDef({RecyclerViewStyle.VERTICAL_LIST, RecyclerViewStyle.HORIZONTAL_LIST, RecyclerViewStyle.VERTICAL_GRID,
        RecyclerViewStyle.HORIZONTAL_GRID, RecyclerViewStyle.STAGGERED_GRID})
@Retention(RetentionPolicy.SOURCE)
public @interface RecyclerViewStyle {
    /**UI style value - looks like a ListView*/
    int VERTICAL_LIST = 1;
    /**UI style value - looks like a horizontal ListView*/
    int HORIZONTAL_LIST = 2;
    /**UI style value - looks like a GridView*/
    int VERTICAL_GRID = 3;
    /**UI style value - looks like a horizontal GridView*/
    int HORIZONTAL_GRID = 4;
    /**UI style value - waterfall gridview */
    int STAGGERED_GRID = 5;
}
