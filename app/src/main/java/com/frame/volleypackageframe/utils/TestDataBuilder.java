package com.frame.volleypackageframe.utils;

import com.frame.volleypackageframe.R;
import com.frame.volleypackageframe.bean.MultiTypeBean;
import com.frame.volleypackageframe.bean.SingleTypeBean;
import com.frame.volleypackageframe.bean.TestBean;
import com.frame.volleypackageframe.module.communicate.bean.News;
import com.frame.volleypackageframe.module.home.adapter.HomeAdapter;
import com.frame.volleypackageframe.module.home.bean.ShowBean;
import com.frame.volleypackageframe.module.communicate.bean.Communicate;
import com.frame.volleypackageframe.module.news.adapter.NewsAdapter;
import com.frame.volleypackageframe.module.product.bean.ImageBean;

import java.util.ArrayList;

public class TestDataBuilder {

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
