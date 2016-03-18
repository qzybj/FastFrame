package com.frame.fastframe.module.home.bean;

import java.util.ArrayList;

/**
 * 多个Item类型示例
 */
public class ShowBean
{

	/**展示类型(对应的具体类型请参数Adapter)*/
	private int showtype;
	/**图片Url*/
	private String imgUrl;
	private String title;
	private String subTitle;
	private String content;
	private ArrayList<String> imageList;
	private String msgCount_1;
	private String msgCount_2;
	private String msgCount_3;
	private String msgCount_4;

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

	public String getMsgCount_3() {
		return msgCount_3;
	}

	public void setMsgCount_3(String msgCount_3) {
		this.msgCount_3 = msgCount_3;
	}

	public String getMsgCount_4() {
		return msgCount_4;
	}

	public void setMsgCount_4(String msgCount_4) {
		this.msgCount_4 = msgCount_4;
	}
}
