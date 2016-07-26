package earlll.com.testdemoall.core.utils;


import android.os.Bundle;
import java.util.ArrayList;
import earlll.com.testdemoall.module.demo.bean.SingleTypeBean;
import earlll.com.testdemoall.module.demo.bean.TestBean;

public class TestDataBuilder {

    public static final String[] testDataStr = {
            "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale"};

    public  static  ArrayList<SingleTypeBean> getSingleTypeBeanList() {
        ArrayList<SingleTypeBean> list = new ArrayList<SingleTypeBean>();
        for (int i = 0; i < 10; i++) {
            list.add(new SingleTypeBean("name"+i, "text"+i, "10086"+i,"20130240122"+i ));
        }
        return list;
    }

    public  static  String getImageUrl() {
        return "http://p10.ytrss.com/product/20/647/7390/ViewImage/3490.jpg";
    }
    public  static  ArrayList<String> getImageUrlList() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i <6 ; i++) {
            list.add(getImageUrl());
        }
        return list;
    }

    public static TestBean getTestBean(String describe, String targetActivity) {
        return getTestBean(describe,targetActivity,null);
    }

    public static TestBean getTestBean(String describe,String targetActivity,Bundle bundle) {
        TestBean bean = new TestBean();
        bean.setName(describe);
        bean.setText(targetActivity);
        bean.setDate("2015-12-01");
        bean.setImageurl("http://p10.ytrss.com/product/20/647/7390/ViewImage/3490.jpg");
        bean.setArgs(bundle);
        return bean;
    }
}
