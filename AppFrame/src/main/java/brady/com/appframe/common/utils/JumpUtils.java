package brady.com.appframe.common.utils;

import android.os.Bundle;
import com.frame.fastframelibrary.utils.jump.JumpBaseUtils;

/**
 * Created by ZhangYuanBo on 2016/9/8.
 */
public class JumpUtils extends JumpBaseUtils {

    public static class JumpInfo implements IJumpInfo {
        private Class cls;
        private String title;
        private Bundle args;

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

        public void setTitle(String title) {
            this.title = title;
        }

        public void setCls(Class cls) {
            this.cls = cls;
        }

        public void setArgs(Bundle args) {
            this.args = args;
        }
    }

    public static IJumpInfo getJumpBean(Class cls,String title,Bundle args) {
        JumpInfo bean = new JumpInfo();
        bean.setTitle(title);
        bean.setCls(cls);
        bean.setArgs(args);
        return bean;
    }
}