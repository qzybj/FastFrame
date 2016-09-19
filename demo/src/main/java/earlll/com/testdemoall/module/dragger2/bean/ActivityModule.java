package earlll.com.testdemoall.module.dragger2.bean;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    @Provides
    UserModel provideUserModel() {
        return new UserModel();
    }
}