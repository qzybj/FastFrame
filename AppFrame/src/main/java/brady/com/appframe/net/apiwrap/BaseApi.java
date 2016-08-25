package brady.com.appframe.net.apiwrap;

import java.util.HashMap;
import java.util.Map;

import brady.com.appframe.CApplication;
import brady.com.appframe.config.ApiConstant;
import netcore.api.ApiClient;

/**
 * Base api，其他的api集成于它
 */
public class BaseApi {

    protected ApiClient apiClient;

    private static Map<String, String> baseParams = null;

    public BaseApi() {
        apiClient = new ApiClient(ApiConstant.API_BASE_URL, ApiConstant.APP_KEY, ApiConstant.APP_SECRET);
        apiClient.setContext(CApplication.instance());
        if(baseParams == null) {
            baseParams = new HashMap<String, String>();
            baseParams.put("client_type", ApiConstant.APP_CLIENT_TYPE);
            baseParams.put("v", ApiConstant.API_VERSION);
            baseParams.put("is_debug", "1");
        }
        //设置基础参数
        apiClient.setBaseParams(baseParams);
        //设置请求方式
        apiClient.setRequestMethod(ApiConstant.REQUEST_METHOD);
    }
}
