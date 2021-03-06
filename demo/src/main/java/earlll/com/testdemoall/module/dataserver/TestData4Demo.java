package earlll.com.testdemoall.module.dataserver;


import android.os.Bundle;
import com.frame.fastframelibrary.utils.dataprocess.IntentUtils;
import com.frame.fastframelibrary.utils.dataprocess.RandomUtils;
import com.frame.fastframelibrary.utils.jump.JumpBaseUtils;
import java.util.ArrayList;
import java.util.Date;
import earlll.com.testdemoall.core.ui.reciverui.bean.MainItemBean;
import earlll.com.testdemoall.core.ui.reciverui.ui.ContainerActivity;
import earlll.com.testdemoall.core.utils.JumpUtils;
import earlll.com.testdemoall.module.demo.bean.SingleTypeBean;
import earlll.com.testdemoall.module.demo.bean.TestBean;

public class TestData4Demo {

    public static final String[] testDataStr = {
            "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale"};

    public static final String[] imageUrlsAll = {
            "http://yrs.yintai.com/rs/img/AppCMS/images/0c9b045b-cfa0-46ec-8a9a-751afe0ad62c.jpg",
            "http://yrs.yintai.com/rs/img/AppCMS/images/25739eb4-1f05-4bf3-b04f-b557690ae829.jpg",
            "http://yrs.yintai.com/rs/img/AppCMS/images/dced0440-344e-4928-b58c-07552786944d.jpg",
            "http://yrs.yintai.com/rs/img/AppCMS/images/f5d5e708-ff52-47c3-92bd-44bb99043f1a.jpg",
            "http://yrs.yintai.com/rs/img/AppCMS/images/1186f052-21cb-4f0c-bd7d-4e379efedf37.png",
            "http://yrs.yintai.com/rs/img/AppCMS/images/4b11918e-0927-4cec-a002-5b59d619109b.png",
            "http://yrs.yintai.com/rs/img/AppCMS/images/93b5703a-2a60-429d-b130-c8865523f053.png",
            "http://yrs.yintai.com/rs/img/AppCMS/images/12333f18-acdf-489d-a831-3421f6721e96.png"};

    public static final String[] imageUrls = {
            "http://yrs.yintai.com/rs/img/AppCMS/images/1186f052-21cb-4f0c-bd7d-4e379efedf37.png",
            "http://yrs.yintai.com/rs/img/AppCMS/images/4b11918e-0927-4cec-a002-5b59d619109b.png",
            "http://yrs.yintai.com/rs/img/AppCMS/images/93b5703a-2a60-429d-b130-c8865523f053.png",
            "http://yrs.yintai.com/rs/img/AppCMS/images/12333f18-acdf-489d-a831-3421f6721e96.png"};

    public  static  ArrayList<SingleTypeBean> getSingleTypeBeanList() {
        ArrayList<SingleTypeBean> list = new ArrayList<SingleTypeBean>();
        for (int i = 0; i < 10; i++) {
            list.add(new SingleTypeBean("name"+i, "text"+i, "10086"+i,"20130240122"+i ));
        }
        return list;
    }

    public  static  String getImageUrl() {
        return imageUrls[RandomUtils.getRandom(imageUrls.length-1)];
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
    private static TestBean getTestBean(String describe, String targetActivity, Bundle bundle) {
        TestBean bean = new TestBean();
        bean.setName(describe);
        bean.setText(targetActivity);
        bean.setDate("2016-8-1");
        bean.setImageurl(imageUrls[RandomUtils.getRandom(imageUrls.length-1)]);
        bean.setArgs(bundle);
        return bean;
    }

    /**以Fragment展示的界面*/
    public static MainItemBean getJumpBeanF(Class cls, String describe, Class fragment) {
        Bundle args = IntentUtils.setBString(null, ContainerActivity.KEY_FRAGMENT, fragment.getName());
        return buildJumpBean(new JumpUtils.JumpInfo(cls,describe,args));
    }
    /**以指定Activity展示的界面*/
    public static MainItemBean getJumpBean(Class cls, String describe, Bundle args) {
        return buildJumpBean(new JumpUtils.JumpInfo(cls,describe,args));
    }
    /**以指定Activity展示的界面*/
    public static MainItemBean getJumpBean(String describe,Class cls, Bundle args) {
        return buildJumpBean(new JumpUtils.JumpInfo(cls,describe,args));
    }

    public static MainItemBean buildJumpBean(JumpBaseUtils.IJumpInfo jumpInfo) {
        MainItemBean bean = new MainItemBean();
        bean.setTitle(jumpInfo.getTitle());
        bean.setContent(jumpInfo.getTarget().getSimpleName());
        bean.setDate(new Date().toString());
        bean.setImageUrl(imageUrls[RandomUtils.getRandom(imageUrls.length-1)]);
        bean.setJumpInfo(jumpInfo);
        return bean;
    }
}