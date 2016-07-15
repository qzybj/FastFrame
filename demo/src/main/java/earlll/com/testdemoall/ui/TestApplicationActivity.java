package earlll.com.testdemoall.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import earlll.com.testdemoall.R;
import earlll.com.testdemoall.adapter.SingleTypeAdapter;
import earlll.com.testdemoall.bean.SingleTypeBean;


/**用来测试 application中存放的变量 */
public class TestApplicationActivity extends Activity {

    private ListView mListView;
    private ArrayList<SingleTypeBean> mDatas = new ArrayList<SingleTypeBean>();

    private SingleTypeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testapplication);

        initDatas();


    }

    private void initDatas() {


    }

}
