package earlll.com.testdemoall.module.testnetframe.request;

import com.frame.fastframelibrary.net.core.base.BasicRequest;
import com.frame.fastframelibrary.net.core.config.NetConstants;

public class TestRequest extends BasicRequest {
    public String account ;
    public String password ;

    public TestRequest() {
        setMethod(NetConstants.Method.GET);
        setMethodName("passport.login");
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
