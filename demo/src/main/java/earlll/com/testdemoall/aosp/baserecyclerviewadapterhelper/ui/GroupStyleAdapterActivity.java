package earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseQuickAdapter;
import java.util.List;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.adapter.GroupStyleAdapter;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.entity.MySection;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.utils.DataServer;

/**
 * 分组样式，一个groupView,其它的为ItemView
 */
public class GroupStyleAdapterActivity extends Activity implements BaseQuickAdapter.OnRecyclerViewItemClickListener {
    private RecyclerView mRecyclerView;
    private List<MySection> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvadapter_common);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mData = DataServer.getSampleData();
        GroupStyleAdapter sectionAdapter = new GroupStyleAdapter(R.layout.rvadapter_child_item, R.layout.rvadapter_group_item, mData);
        sectionAdapter.setOnRecyclerViewItemClickListener(this);
        sectionAdapter.setOnRecyclerViewItemChildClickListener(
                new BaseQuickAdapter.OnRecyclerViewItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(GroupStyleAdapterActivity.this, "onItemChildClick", Toast.LENGTH_LONG).show();
            }
        });
        mRecyclerView.setAdapter(sectionAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        MySection mySection = mData.get(position);
        if (mySection.isHeader)
            Toast.makeText(this, mySection.header, Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, mySection.t.getName(), Toast.LENGTH_LONG).show();
    }
}
