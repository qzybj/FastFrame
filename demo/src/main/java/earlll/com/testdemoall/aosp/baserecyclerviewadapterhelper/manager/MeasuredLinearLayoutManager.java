package earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.manager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**用于处理嵌套在AdapterView中的RecycleView*/
public class MeasuredLinearLayoutManager extends LinearLayoutManager {

    private int maxHeight;

    public MeasuredLinearLayoutManager(Context context) {
        super(context);
    }
    public MeasuredLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context,orientation,reverseLayout);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        if(maxHeight == 0 && getItemCount() > 0) {
            View child = recycler.getViewForPosition(0);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            child.measure(widthSpec, View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            int height = child.getMeasuredHeight() + getPaddingTop() + getPaddingBottom()
                    + params.topMargin + params.bottomMargin;
            if (height > maxHeight) {
                maxHeight = height;
            }
        }
        heightSpec = View.MeasureSpec.makeMeasureSpec(maxHeight, View.MeasureSpec.EXACTLY);
        super.onMeasure(recycler, state, widthSpec, heightSpec);
    }
}