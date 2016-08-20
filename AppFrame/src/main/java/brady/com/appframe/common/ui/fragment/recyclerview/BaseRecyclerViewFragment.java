package brady.com.appframe.common.ui.fragment.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.frame.fastframelibrary.config.ConstantsCommonKey;
import com.frame.fastframelibrary.ui.base.FrameBaseFragment;
import brady.com.appframe.CApplication;
import brady.com.appframe.R;
import brady.com.appframe.common.ui.fragment.recyclerview.adapter.interfaces.IAdapterFragment;
import brady.com.appframe.common.ui.fragment.recyclerview.adapter.utils.AdapterUtils;
import brady.com.appframe.common.ui.fragment.recyclerview.annotation.PullrefreshType;
import brady.com.appframe.common.ui.fragment.recyclerview.interfaces.IRecyclerViewOptions;
import butterknife.BindView;

/**Base RecycleView fragment*/
public abstract  class BaseRecyclerViewFragment<T extends BaseQuickAdapter> extends FrameBaseFragment implements
        IAdapterFragment,SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener {

    /**UI style key*/
    public static final String STYLE = ConstantsCommonKey.KEY_TYPE;
    /**pullrefresh switch*/
    public static final String PULLREFRESH_TYPE = ConstantsCommonKey.KEY_PULLREFRESH_TYPE;

    protected final int DEF_PAGE_SIZE = 10;

    @BindView(R.id.srlayout_common)
    public SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_common)
    public RecyclerView mRecyclerView;

    private int currStyle = -1;
    private int currpPullrefreshType = PullrefreshType.PULLREFRESH_OFF;

    @Override
    public void initConstant(Bundle savedInstanceState) {}

    @Override
    public int getLayoutResId() {
        return R.layout.content_recycleview;
    }

    @Override
    public void initContentView(View view) {}

    @Override
    public final void initData(Bundle savedInstanceState) {
        Bundle args = getArguments();
        if(args!=null){
            currStyle = args.getInt(STYLE);
            currpPullrefreshType = args.getInt(PULLREFRESH_TYPE);
        }
        initUI();
        customInitData(savedInstanceState);
    }
    protected Context getOptimizeContext(){
        return CApplication.instance();
    }

    /** Get this fragment init options*/
    protected abstract IRecyclerViewOptions getOption();
    /** Get this fragment init options*/
    protected abstract void customInitData(Bundle savedInstanceState);
    /** Get this fragment init options*/
    protected abstract T getAdapter();
    /** Init recycleview listener*/
    protected abstract void initRecycleView();
    protected RecyclerView getRecycleView(){
        return mRecyclerView;
    }

    protected void initUI(){
        if(getOption()!=null){
            initSwipeRefreshLayout();
            initRecyclerViewStyle();
        }
    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.color_ffffff, R.color.color_33a6ff,R.color.color_ff0000);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        currpPullrefreshType = getOption().getPullrefreshType();
        boolean isRefresh = (currpPullrefreshType==PullrefreshType.PULLREFRESH_ON);
        mSwipeRefreshLayout.setEnabled(isRefresh);
    }

    /**init RecyclerView:get show UI type,and generate the corresponding adapter */
    private void initRecyclerViewStyle() {
        if(getAdapter()==null){
            return ;
        }
        currStyle = (currStyle<=0?getOption().getStyle():currStyle);
        mRecyclerView.setLayoutManager(AdapterUtils.getRecyclerViewManager(currStyle,getOption().getSpanCount()));
        mRecyclerView.setAdapter(getAdapter());//Some adapter must replace custom layoutmanager,so method setAdapter() after RecyclerView.setLayoutManager()
        initRecycleView();
    }
}