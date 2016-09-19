package earlll.com.testdemoall.core.utils;

import android.os.Bundle;
import com.frame.fastframelibrary.utils.jump.JumpBaseUtils;

/**
 * Created by ZhangYuanBo on 2016/9/8.
 */
public class JumpUtils extends JumpBaseUtils {

    public static class JumpInfo implements JumpBaseUtils.IJumpInfo {
        private Class cls;
        private String title;
        private Bundle args;

        public JumpInfo(Class cls, String title, Bundle args) {
            this.cls = cls;
            this.title = title;
            this.args = args;
        }

        @Override
        public Class getTarget() {
            return cls;
        }

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public Bundle getArgs() {
            return args;
        }
    }
    public static IJumpInfo getJumpBean(Class cls,String title,Bundle args) {
        return new JumpInfo(cls,title,args);
    }
}
