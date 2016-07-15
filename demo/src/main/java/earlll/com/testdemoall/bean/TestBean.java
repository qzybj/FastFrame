package earlll.com.testdemoall.bean;

import android.os.Bundle;

public class TestBean {
    private String text;
    private String name;
    private String date;
    private String imageurl;
    private Bundle args;
    private boolean isShowFlag;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public boolean isShowFlag() {
        return isShowFlag;
    }

    public void setShowFlag(boolean showFlag) {
        isShowFlag = showFlag;
    }

    public Bundle getArgs() {
        return args;
    }

    public void setArgs(Bundle args) {
        this.args = args;
    }
}
