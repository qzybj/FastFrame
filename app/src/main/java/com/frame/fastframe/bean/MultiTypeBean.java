package com.frame.fastframe.bean;

/**
 * 多个Item类型示例
 */
public class MultiTypeBean
{
	private int icon;
	private String name;
	private String content;
	private String createDate;
	private boolean isComMeg;

	/**类型1*/
	public final static int SEND_MSG = 1;
	/**类型2*/
	public final static int RECIEVE_MSG = 2;

	public MultiTypeBean(int icon, String name, String content,
						 String createDate, boolean isComMeg)
	{
		this.icon = icon;
		this.name = name;
		this.content = content;
		this.createDate = createDate;
		this.isComMeg = isComMeg;
	}

	public boolean isComMeg()
	{
		return isComMeg;
	}

	public void setComMeg(boolean isComMeg)
	{
		this.isComMeg = isComMeg;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public int getIcon()
	{
		return icon;
	}

	public void setIcon(int icon)
	{
		this.icon = icon;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(String createDate)
	{
		this.createDate = createDate;
	}

	@Override
	public String toString()
	{
		return "Communicate [icon=" + icon + ", name=" + name + ", content="
				+ content + ", createDate=" + createDate + "]";
	}

}