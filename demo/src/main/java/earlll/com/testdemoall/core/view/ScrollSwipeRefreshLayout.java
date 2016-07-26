package earlll.com.testdemoall.core.view;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class ScrollSwipeRefreshLayout extends SwipeRefreshLayout {
    private ViewGroup viewGroup;

    public ScrollSwipeRefreshLayout(Context context) {
        super(context);
    }
    public ScrollSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (viewGroup != null && viewGroup.getScrollY() > 1) {
            this.setEnabled(false);
            return super.onTouchEvent(arg0);
        } else {
            // 让SwipeRefreshLayout处理本次事件
            return super.onTouchEvent(arg0);
        }
    }

    public ViewGroup getViewGroup() {
        return viewGroup;
    }

    public void setViewGroup(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
        this.viewGroup.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (ScrollSwipeRefreshLayout.this.viewGroup.getScrollY() <= 1) {
                    ScrollSwipeRefreshLayout.this.setEnabled(true);
                }
                return false;
            }
        });
    }

    @Override
    public boolean canChildScrollUp() {//第一处触发
        return super.canChildScrollUp();
    }

}