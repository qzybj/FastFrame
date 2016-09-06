package com.frame.fastframelibrary.net.core;

import com.frame.fastframelibrary.FastApplication;
import com.frame.fastframelibrary.R;
import com.frame.fastframelibrary.net.core.annotation.NoRequestArgs;
import com.frame.fastframelibrary.net.core.annotation.RequestArgs;
import com.frame.fastframelibrary.net.core.bean.NetResponse;
import com.frame.fastframelibrary.net.core.config.NetConstants;
import com.frame.fastframelibrary.net.core.interfaces.IErrorInfo;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.dataprocess.MapUtils;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import com.frame.fastframelibrary.utils.reflect.ClassReflectUtils;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NetDataServerUtils {
    private static HashMap<String, String> headers;
    private static HashMap<String, String> params;

    public static HashMap<String, String> getNetHeader() {
       if(headers==null){
           headers = new HashMap<String, String>();
           //headers.put("userid", UserManager.getUid() == null ? "NaN" : UserManager.getUid());
            //		headers.put("platform", "android");
            //		headers.put("appVersion", AppUtils.getVersionNameInManifest());
            //		headers.put("phoneModel", DeviceUtils.getPhoneModel());
            //		headers.put("screenWidth", DeviceUtils.getScreenWidth() + "");
            //		headers.put("screenHeigh", DeviceUtils.getScreenHeight() + "");
            //		headers.put("systemVersion", DeviceUtils.getAndroidSDKVersion());
            //		headers.put("appName", FastApplication.instance().getString(R.string.app_name));
            //		headers.put("channelNum", "17");
            //		headers.put("channelid", "");
       }
        return headers;
    }

    public static HashMap<String, String> getParams() {
        if(params==null){
            params = new HashMap<String, String>();
            params.put("client_type", NetConstants.APP_CLIENT_TYPE);
            params.put("v", NetConstants.API_VERSION);
            params.put("is_debug", "1");
            params.put("app_key", NetConstants.APP_KEY);
        }
        return params;
    }

    public static HashMap<String, String> getFieldsMap(Object obj) {
        if(obj!=null){
            HashMap<String, String> fieldsMap = new HashMap<String, String>();
            //Field[] fields = obj.getClass().getFields();
            Map<String, String> tmpFieldsMap = ClassReflectUtils.getFieldValueMap(obj);

            Field[] childFields = obj.getClass().getDeclaredFields();
            Field[] supperFields = obj.getClass().getSuperclass().getDeclaredFields();
            Field[] fields = concat(childFields,supperFields);
            Field.setAccessible(childFields, true);
            Field.setAccessible(supperFields, true);

            Field t_field = null;
            String key = null;
            Object value = null;
            RequestArgs jsonProperty = null;
            NoRequestArgs noJsonProperty = null;
            for (int i = 0; i < fields.length; i++) {
                t_field = fields[i];
                key = null;
                value = null;
                noJsonProperty = t_field.getAnnotation(NoRequestArgs.class);
                if(noJsonProperty!=null){
                    continue;
                }
                jsonProperty = t_field.getAnnotation(RequestArgs.class);
                if(jsonProperty!=null){
                    key = jsonProperty.value();
                }
                if(StringUtils.isEmpty(key)){
                    key = t_field.getName();
                }
                try {
                    value = t_field.get(obj);
                } catch (IllegalAccessException e) {
                    LogUtils.e(e);
                }
                if (value != null) {
                    fieldsMap.put(key, value.toString());
                }
            }
            return fieldsMap;
        }
        return null;
    }

    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    /**
     * 构建请求参数，比如加密签名等
     * @return
     */
    public static HashMap<String, String> encodeReqParams(HashMap<String, String> params,String mAppSecret) {
        if(MapUtils.isNotEmpty(params)){
            String[] keys = params.keySet().toArray(new String[0]);
            Arrays.sort(keys);
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < keys.length; i ++) {
                String key = keys[i];
                String value = params.get(key);
                if(StringUtils.isEmpty(value)) {
                    continue;
                }
                if(str.toString() != "") {
                    str.append("&");
                }
                str.append(key + "=" + value);
            }
            params.put("sign", StringUtils.md5(mAppSecret + str.toString() + mAppSecret).toUpperCase());
        }
        return params;
    }

    public static IErrorInfo getErrorInfo(int code){
        String message;
        switch (code){
            case NetConstants.NetStatusCode.CODE_NO_JSON:
                message = FastApplication.instance().getResources().getString(R.string.parse_json_err);
                break;
            case NetConstants.NetStatusCode.CODE_NO_NET:
                message = FastApplication.instance().getResources().getString(R.string.not_net_connect_err);
                break;
            case NetConstants.NetStatusCode.CODE_NO_READ:
            default:
                message = FastApplication.instance().getResources().getString(R.string.request_net_err);
        }
        return getErrorInfo(code,message);
    }

    public static IErrorInfo getErrorInfo(int code,String message){
        switch (code){
            case NetConstants.NetStatusCode.CODE_NO_JSON:
                return NetResponse.errorInfo(code,message);
            case NetConstants.NetStatusCode.CODE_NO_NET:
                return NetResponse.errorInfo(code,message);
            case NetConstants.NetStatusCode.CODE_NO_READ:
            default:
                return NetResponse.errorInfo(code,message);
        }
    }
}