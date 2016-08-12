package earlll.com.testdemoall.module.demo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.frame.fastframelibrary.module.loadimage.LoadImageManager;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.reflect.ClassReflectUtils;
import com.frame.fastframelibrary.utils.test.TestDataBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.utils.TestData4Demo;
import earlll.com.testdemoall.module.demo.bean.TestBean;

/**
 * 测试数据生成器，使用示例
 */
public class TestDataSimpleActivity extends Activity {
    private static final String TAG = TestDataSimpleActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private List<TestBean> mData;
    private ItemDragSmallIconAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvadapter_common);

        mRecyclerView = (RecyclerView)findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        mData = generateData(50);
        mAdapter = new ItemDragSmallIconAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
    }

    public class ItemDragSmallIconAdapter extends BaseItemDraggableAdapter<TestBean> {
        public ItemDragSmallIconAdapter(List data) {
            super(R.layout.rvadapter_item_dragsmallicon, data);
        }
        @Override
        protected void convert(BaseViewHolder helper, TestBean item) {
            if(item!=null){
                helper.setText(R.id.tv, item.getName());
                ImageView iv = helper.getView(R.id.iv);
                LoadImageManager.instance().loadImage(iv,item.getImageurl());
            }
        }
    }
    private List<TestBean> generateData(int size) {
        return getData4Custom(size);
    }

    private List<TestBean> getData4Default(int size) {
        ArrayList<TestBean> data = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            TestBean bean = TestDataBuilder.getClassInstance(TestBean.class);
            bean.setImageurl(TestData4Demo.getImageUrl());
            data.add(bean);
        }
        return data;
    }

    private List<TestBean> getData4Custom(int size) {
        ArrayList<TestBean> data = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            TestBean instance = new TestBean();//实例化对象
            Map<String, String> valMap = new HashMap<>();
            valMap.put("text", "michael");
            valMap.put("name", "27");
            valMap.put("date", "2010-10-24");
            valMap.put("imageurl", TestData4Demo.getImageUrl());
            valMap.put("args", null);
            valMap.put("isShowFlag", "false");
            try{
                ClassReflectUtils.setFieldValue(instance, valMap);
            }catch(Exception e){
                LogUtils.e(e);
            }
            data.add(instance);
        }
        return data;
    }
}
