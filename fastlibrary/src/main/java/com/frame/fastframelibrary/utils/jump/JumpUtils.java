package com.frame.fastframelibrary.utils.jump;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.frame.fastframelibrary.utils.dataprocess.IntentUtils;

/**
 * Created by ZhangYuanBo on 2016/6/2.
 */
public class JumpUtils {

    public static void goActivity(Context con,JumpBean bean){
        if(con!=null&&bean!=null){
            Intent intent = new Intent();
            intent.setClassName(con,bean.getClassName());
            if(!IntentUtils.isEmpty(bean.getArgs())){
                intent.putExtras(bean.getArgs());
            }
            con.startActivity(intent);
        }
    }

    public static JumpBean getJumpBean(String targetActivityName,String title) {
        JumpBean bean = new JumpBean();
        bean.setTitle(title);
        bean.setClassName(targetActivityName);
        bean.setImgUrls(new String[]{"http://p10.ytrss.com/product/20/647/7390/ViewImage/3490.jpg"});
        return bean;
    }

    public static class JumpBean {
        private String title;
        private String className;
        private String[] imgUrls;
        private Bundle args;

        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
        public String[] getImgUrls() {
            return imgUrls;
        }

        public void setImgUrls(String[] imgUrls) {
            this.imgUrls = imgUrls;
        }

        public Bundle getArgs() {
            return args;
        }

        public void setArgs(Bundle args) {
            this.args = args;
        }
    }
}