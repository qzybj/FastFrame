package com.frame.fastframe.utils;

import com.frame.fastframe.bean.MultiTypeBean;
import com.frame.fastframe.bean.SingleTypeBean;
import com.frame.fastframe.bean.TestBean;
import com.frame.fastframe.module.communicate.bean.Communicate;
import com.frame.fastframe.module.news.adapter.NewsAdapter;
import com.frame.fastframe.module.news.bean.News;
import com.frame.fastframe.module.product.bean.ImageBean;
import com.frame.fastframe.R;
import com.frame.fastframe.module.home.adapter.HomeAdapter;
import com.frame.fastframe.module.home.bean.ShowBean;

import java.util.ArrayList;
import java.util.Arrays;

public class TestDataBuilder {

    public static final String[] imgs =new String[]{
            "http://i1.mopimg.cn/img/tt/2015-06/961/20150601172100739.jpg790x600.jpg",
            "http://i1.17173.itc.cn/2011/news/2011/02/10/11_02101740_08s.jpg",
            "http://www.hers.cn/uploadfile/2011/0213/20110213035902611.jpg",
            "http://i1.mopimg.cn/img/tt-admin-xuan/2014-09/800/20140905142547893.jpg190x140.jpg",
            "http://i1.mopimg.cn/img/tt/2015-06/931/20150602104850579.jpg790x600.jpg",
            "http://yrs.yintai.com/rs/img/AppCMS/images/38852454-7e89-49c4-8196-03b5cbae8c9d.jpg",
            "http://yrs.yintai.com/rs/img/AppCMS/images/3ed1d80b-8d73-404f-8fd7-2006f5ebcfb9.jpg",
            "http://yrs.yintai.com/rs/img/AppCMS/images/5848d0ce-ee6c-41af-b7fb-723e9b1cd573.jpg",
            "http://yrs.yintai.com/rs/img/AppCMS/images/c130b95f-20da-4bfb-9c81-73ba6d87f832.jpg",
            "http://yrs.yintai.com/rs/img/AppCMS/images/8b4b30bc-466c-4a0d-9e14-cd081c863189.jpg",
            "http://10.32.11.74:8018/rs/img/AppCMS/images/15acd1e5-60a0-4c41-a8e9-5433b6ecabd1.jpg",
            "http://10.32.11.74:8018/rs/img/AppCMS/images/1986b5dd-239b-45b4-922b-c3f7b154ec16.jpg",
            "http://10.32.11.74:8018/rs/img/AppCMS/images/35017bb9-fd0b-41ac-a8cb-d2d93cd5bba1.jpg"
    };

    public static final ArrayList<String> imageList = new ArrayList<String>(Arrays.asList(imgs));

    public static final String[] testDataStr = {
            "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale"};

    public  static  ArrayList<ImageBean> getImageBeanList() {
        ArrayList<ImageBean> list = new ArrayList<ImageBean>();
        for (int i = 0; i < testDataStr.length; i++) {
            ImageBean bean = new ImageBean();
            bean.setDescribe(testDataStr[i]);
            bean.setImageUrl("http://p10.ytrss.com/product/20/647/7390/ViewImage/3490.jpg");
            list.add(bean);
        }
        return list;
    }

    public  static  ArrayList<TestBean> getTestBeanList() {
        ArrayList<TestBean> list = new ArrayList<TestBean>();
        for (int i = 0; i < 10; i++) {
            TestBean bean = new TestBean();
            bean.setName("name"+i);
            bean.setText("text"+i);
            bean.setDate("2015-12-01");
            bean.setImageurl("http://p10.ytrss.com/product/20/647/7390/ViewImage/3490.jpg");
            list.add(bean);
        }
        return list;
    }


     public  static  ArrayList<MultiTypeBean> getChatMessageList() {
         ArrayList<MultiTypeBean> list = new ArrayList<MultiTypeBean>();
         for (int i = 0; i < 10; i++) {
             list.add(new MultiTypeBean(R.mipmap.ic_launcher, "name"+i, "text"+i,null, i%2==1?true:false));
         }
         return list;
    }
     public  static  ArrayList<SingleTypeBean> getSingleTypeBeanList() {
         ArrayList<SingleTypeBean> list = new ArrayList<SingleTypeBean>();
         for (int i = 0; i < 10; i++) {
             list.add(new SingleTypeBean("name"+i, "text"+i, "10086"+i,"20130240122"+i ));
         }
         return list;
    }


    public  static  ArrayList<ShowBean> getShowBeanList() {
        ArrayList<ShowBean> list = new ArrayList<ShowBean>();
        for (int i = 0; i < 10; i++) {
            ShowBean bean = new ShowBean();
            bean.setShowtype(HomeAdapter.SHOW_TYPE_1);
            bean.setTitle("name"+i);
            bean.setSubTitle("text"+i);
            bean.setContent("this is content "+i);
            bean.setImgUrl(getImageUrl());
            bean.setImageList(getImageUrlList());
            bean.setMsgCount_1(i%2+"");
            bean.setMsgCount_2(i%3+"");
            bean.setMsgCount_3(i%4+"");
            bean.setMsgCount_4(i%5+"");
            list.add(bean);
        }
        return list;
    }
    public  static  ArrayList<Communicate> getCommunicateList() {
        ArrayList<Communicate> list = new ArrayList<Communicate>();
        for (int i = 0; i < 10; i++) {
            Communicate bean = new Communicate();
            bean.setShowtype(HomeAdapter.SHOW_TYPE_1);
            bean.setTitle("name"+i);
            bean.setSubTitle("text"+i);
            bean.setContentTitle("this is content title "+i);
            bean.setContent("this is content "+i);
            bean.setImgUrl(getImageUrl());
            bean.setImageList(getImageUrlList());
            bean.setMsgCount_1(i%2+"");
            bean.setMsgCount_2(i%3+"");
            list.add(bean);
        }
        return list;
    }
    public  static  ArrayList<News> getNewsList() {
        ArrayList<News> list = new ArrayList<News>();
        for (int i = 0; i < 10; i++) {
            News bean = new News();
            switch (i%3){
                case 1:
                    bean.setShowtype(NewsAdapter.SHOW_TYPE_2);
                    break;
                case 2:
                    bean.setShowtype(NewsAdapter.SHOW_TYPE_3);
                    break;
                case 0:
                default:
                    bean.setShowtype(NewsAdapter.SHOW_TYPE_1);
                    break;
            }
            bean.setTitle("name"+i);
            bean.setSubTitle("text"+i);
            bean.setContentTitle("this is content title "+i);
            bean.setContent("this is content "+i);
            bean.setImgUrl(getImageUrl());
            bean.setImageList(getImageUrlList());
            bean.setMsgCount_1(i%2+"");
            bean.setMsgCount_2(i%3+"");
            list.add(bean);
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
}
