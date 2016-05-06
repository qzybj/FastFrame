package com.frame.fastframelibrary.core.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**用来嵌套在ScrollView当中的GridView*/
public class InnerGridView extends GridView {

    public InnerGridView(Context context) {
        super(context);
    }

    public InnerGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InnerGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }
    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec =
                MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}