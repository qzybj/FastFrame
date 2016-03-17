package com.frame.volleypackageframe.module.communicate.bean;

import java.util.ArrayList;

/**
 * 多个Item类型示例
 */
public class News {

    /**
     * 展示类型(对应的具体类型请参数Adapter)
     */
    private int showtype;
    /**
     * 图片Url
     */
    private String imgUrl;
    private String title;
    private String subTitle;
    private String contentTitle;
    private String content;
    private ArrayList<String> imageList;
    private String msgCount_1;
    private String msgCount_2;

    public int getShowtype() {
        return showtype;
    }

    public void setShowtype(int showtype) {
        this.showtype = showtype;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<String> imageList) {
        this.imageList = imageList;
    }

    public String getMsgCount_1() {
        return msgCount_1;
    }

    public void setMsgCount_1(String msgCount_1) {
        this.msgCount_1 = msgCount_1;
    }

    public String getMsgCount_2() {
        return msgCount_2;
    }

    public void setMsgCount_2(String msgCount_2) {
        this.msgCount_2 = msgCount_2;
    }

}
