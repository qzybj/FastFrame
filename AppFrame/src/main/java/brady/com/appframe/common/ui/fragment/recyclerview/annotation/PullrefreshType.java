package brady.com.appframe.common.ui.fragment.recyclerview.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by ZhangYuanBo on 2016/8/19.
 */
@IntDef({PullrefreshType.PULLREFRESH_OFF, PullrefreshType.PULLREFRESH_ON})
@Retention(RetentionPolicy.SOURCE)
public @interface PullrefreshType {
    /**Pullrefresh switch - off*/
    int PULLREFRESH_OFF = 0;
    /**Pullrefresh switch - on*/
    int PULLREFRESH_ON = 1;
}
