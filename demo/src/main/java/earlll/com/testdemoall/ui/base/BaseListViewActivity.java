package earlll.com.testdemoall.ui.base;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import earlll.com.testdemoall.interfaces.IListViewActivity;

/**
 * 用于ListView类界面继承实现(AdapterView)
 */
public abstract class BaseListViewActivity extends BaseActivity implements IListViewActivity<XRecyclerView>,XRecyclerView.LoadingListener {

    @Override
    public final void initContentView(View view) {
        if(isSupportAdapterView()){
            initAdapterView(view);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            getListView().setLayoutManager(layoutManager);

            getListView().setLoadingListener(this);
            if(getAdapter()!=null){
                getListView().setAdapter(getAdapter());
            }
        }
        initCustomView(view);
    }
    /**只用于AdapterView相关操作，如addheader,setfooter等 */
    protected abstract void initAdapterView(View view);

    /**用于其它UI初始化*/
    protected abstract void initCustomView(View view);

    @Override
    public void addHeader(View v) {
        if(isSupportAdapterView()){
            getListView().addHeaderView(v);
        }
    }

    @Override
    public void setFootView(View v) {
        if(isSupportAdapterView()){
            getListView().setFootView(v);
        }
    }

    @Override
    public void setPullRefreshEnable(boolean enable){
        if(isSupportAdapterView()){
            getListView().setPullRefreshEnabled(enable);//禁止下拉刷新
        }
    }

    @Override
    public void setLoadMoreEnable(boolean enable){
        if(isSupportAdapterView()){
            getListView().setLoadingMoreEnabled(enable);//禁止加载更多
        }
    }

    /**
     * 是否支持AdapterView操作(判空)
     * @return
     */
    public boolean isSupportAdapterView(){
        if(getListView()!=null){
            return true;
        }
        return false;
    }

    @Override
    public void onRefresh() {
        onBindRefresh(getListView());
    }

    @Override
    public void onLoadMore() {
        onBindLoadMore(getListView());
    }


}