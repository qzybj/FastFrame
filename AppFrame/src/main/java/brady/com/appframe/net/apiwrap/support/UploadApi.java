package brady.com.appframe.net.apiwrap.support;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import brady.com.appframe.net.apiwrap.BaseApi;
import brady.com.appframe.net.apiwrap.MyApiDelegate;
import brady.com.appframe.net.apiwrap.support.response.UploadResponse;

/**
 * Created by tongdesheng on 16/4/16.
 */
public class UploadApi extends BaseApi {
    public final static int FILE = 0;
    public final static int IMG = 1;

    /**
     * 上传图片
     * @param userToken
     * @param filePath
     * @param prjType
     * @param apiDelegate
     */
    public void uploadFile(String userToken, String filePath, String prjType, MyApiDelegate<UploadResponse> apiDelegate) {
        File file = new File(filePath);
        Map<String, File> files = new HashMap<>();
        files.put("file_data", file);
        apiClient.setApiMethod("putfile.req");
        apiClient.setParam("user_token", userToken);
        apiClient.setParam("upload_project", prjType);
        apiClient.uploadFile(files, apiDelegate, UploadResponse.class);
    }
}