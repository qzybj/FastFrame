package com.frame.fastframelibrary.view;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;

public class ObservableWebView extends WebView {
    private OnScrollChangedCallback mOnScrollChangedCallback;
    public ObservableWebView(Context context) {
        super(context);
        initFrame();
    }
    public ObservableWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFrame();
    }
    public ObservableWebView(Context context, AttributeSet attrs,int defStyle) {
        super(context, attrs, defStyle);
        initFrame();
    }
    void initFrame(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            this.setOnScrollChangeListener(new OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (mOnScrollChangedCallback != null) {
                        mOnScrollChangedCallback.onScroll(scrollX - oldScrollX, scrollY - oldScrollY,"onScrollChange");
                    }
                }
            });
        }
    }


//    @Override
//    protected void onScrollChanged(final int l, final int t, final int oldl,final int oldt) {
//        super.onScrollChanged(l, t, oldl, oldt);
//        if (mOnScrollChangedCallback != null) {
//            mOnScrollChangedCallback.onScroll(l - oldl, t - oldt,"onScrollChanged");
//        }
//    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        //只有到滚动边界的时候才会触发，内部滚动区域也是一样
        if (mOnScrollChangedCallback != null&&!isTouchEvent) {
            mOnScrollChangedCallback.onScroll(scrollX,scrollY,"overScrollBy");
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    public OnScrollChangedCallback getOnScrollChangedCallback() {
        return mOnScrollChangedCallback;
    }

    public void setOnScrollChangedCallback(final OnScrollChangedCallback onScrollChangedCallback) {
        mOnScrollChangedCallback = onScrollChangedCallback;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }


    /**
     * Impliment in the activity/fragment/view that you want to listen to the webview
     */
    public interface OnScrollChangedCallback {
        void onScroll(int dx, int dy, String type);
    }
}