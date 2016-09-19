package earlll.com.testdemoall.module.demo.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.core.ui.base.BaseFragment;


public class ThreadFragment extends BaseFragment{


    @BindView(R.id.tv_show)
    TextView tv_content;
    @BindView(R.id.tv_show1)
    TextView tv_content1;
    @BindView(R.id.btn_test)
    Button btn_test;


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_thread;
    }

    @Override
    public void initContentView(View view) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_test})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test:
                test();
                break;
        }
    }

    private void test() {
         new MThread(1,0).start();
         new MThread(100,1).start();
    }

    class MThread  extends Thread{
        private int a = 0;
        private int type = 1;
        MThread(int i,int type){
            a= i;
            this.type =type;
        }

        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a+=2;
                switch (type){
                    case 0:
                        tv_content.setText(a);
                        break;
                    case 1:
                        tv_content1.setText(a);
                        break;
                }

            }
            super.run();
        }
    }

}