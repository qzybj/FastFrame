package earlll.com.testdemoall.module.dragger2.interfaces;

import dagger.Component;
import earlll.com.testdemoall.module.dragger2.bean.ActivityModule;
import earlll.com.testdemoall.module.dragger2.ui.DraggerActivity;

/**
 * Created by ZhangYuanBo on 2016/9/6.
 */
@Component(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(DraggerActivity activity);
}