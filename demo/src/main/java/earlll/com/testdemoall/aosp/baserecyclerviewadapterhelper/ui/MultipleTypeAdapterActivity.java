package earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import earlll.com.testdemoall.R;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.adapter.MultipleItemQuickAdapter;
import earlll.com.testdemoall.module.dataserver.DataServer;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MultipleTypeAdapterActivity extends Activity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvadapter_common);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MultipleItemQuickAdapter multipleItemAdapter = new MultipleItemQuickAdapter(this, DataServer.getMultipleItemData());
        multipleItemAdapter.addHeaderView(getHeaderView());
        multipleItemAdapter.addHeaderView(getHeaderView());
        multipleItemAdapter.addFooterView(getHeaderView());
        mRecyclerView.setAdapter(multipleItemAdapter);
    }

    private View getHeaderView() {
        View view = getLayoutInflater().inflate(R.layout.rvadapter_view_header, null);
        view.findViewById(R.id.tv).setVisibility(View.GONE);
        view.setLayoutParams(new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MultipleTypeAdapterActivity.this, "click View", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
