package earlll.com.testdemoall.module.demo.bean;

/**
 * 单个Item类型示例
 */
public class SingleTypeBean
{
	private String title;
	private String desc;
	private String phone;
	private String time;

	public SingleTypeBean()
	{
	}

	public SingleTypeBean(String title, String desc, String phone, String time)
	{
		super();
		this.title = title;
		this.desc = desc;
		this.phone = phone;
		this.time = time;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

}
