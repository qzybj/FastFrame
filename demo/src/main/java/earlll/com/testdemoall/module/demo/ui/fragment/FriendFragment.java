package earlll.com.testdemoall.module.demo.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import earlll.com.testdemoall.R;


/**
 * Created by ZhangYuanBo on 2016/5/27.
 */
public class FriendFragment extends Fragment {

    @BindView(R.id.tv_fragment_content)
    TextView tv_content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frends, container, false);
        ButterKnife.bind(this, view);
        tv_content.setText("this is "+this.getClass().getName());
        return view;
    }
}