package brady.com.appframe.net.apiwrap.passport.response;


import java.util.ArrayList;
import brady.com.appframe.net.models.passport.UserinfoBean;
import netcore.api.BaseResponse;

/**
 * Created by tongdesheng on 16/4/26.
 */
public class HotUsersResponse extends BaseResponse {
    public ArrayList<UserinfoBean> data;
}
