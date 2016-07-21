package earlll.com.testdemoall.interfaces;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import earlll.com.testdemoall.ui.adapter.RecyclerViewAdapter;

/**
 * 用于BaseListView的必要实现Method
 */
 public interface IListViewActivity<T extends ViewGroup> {
    /**View - get ListView */
    T getListView();
    /**View - get Adapter for ListView */
    RecyclerView.Adapter getAdapter();
    /**View - add header to ListView(set header before setAdapter) */
    void addHeader(View v);
    /**View - set footerview to ListView(set header before setAdapter) */
    void setFootView(View v);


    /**Event - enable pull to refresh */
    void setPullRefreshEnable(boolean enable);
    /**Event - enable load more data */
    void setLoadMoreEnable(boolean enable);

    /**Event - pull to refresh */
    void onBindRefresh(View v);
    /**Event - load more data */
    void onBindLoadMore(View v);
}