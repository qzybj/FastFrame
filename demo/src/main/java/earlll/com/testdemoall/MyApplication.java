package earlll.com.testdemoall;

import com.frame.fastframelibrary.FastApplication;

import earlll.com.testdemoall.module.demo.bean.AccountBean;

public class MyApplication extends FastApplication {
	/** 用户信息 add by 4.1.0*/
	private static AccountBean mAccountBean =null;
	/**调用之前需要进行isNotEmptyAccountBean()为空判断 */
	public AccountBean getAccountBean() {return mAccountBean;}
	public void setAccountBean(AccountBean accountBean) {
		this.mAccountBean = accountBean;
	}

	public boolean isNotEmptyAccountBean() {return mAccountBean !=null;}

	@Override
	public void onCreate() {
		super.onCreate();
	}
}
