package com.frame.fastframelibrary.core.view;

import android.widget.GridView;

public class InnerGridView extends GridView {
	public InnerGridView(android.content.Context context,
						 android.util.AttributeSet attrs) {
		super(context, attrs);
	}

	/**设置不滚动*/
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = 
				MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}