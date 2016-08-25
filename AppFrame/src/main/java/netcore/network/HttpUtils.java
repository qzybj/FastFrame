package netcore.network;

import android.content.Context;
import android.net.Uri;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.File;
import java.util.Map;

/**
 * 对volley进行封装的http请求工具
 * Created by tongdesheng on 16/1/28.
 */
public class HttpUtils {

    private static HttpUtils instance;

    //通用请求队列
    private RequestQueue commonReqQueue;

    //指定请求队列
    private RequestQueue specialReqQueue;

    /**
     * tag，可用于批量取消请求
     */
    public final static String TAG = HttpUtils.class.getSimpleName();


    public static HttpUtils getInstance() {
        if (instance == null) {
            instance = new HttpUtils();
        }
        return instance;
    }



    /**
     * 初始化队列
     * @param context
     */
    public HttpUtils init(Context context) {
        if(commonReqQueue == null) {
            commonReqQueue = Volley.newRequestQueue(context);
        }
        return instance;
    }


    /**
     * 初始化指定stack的队列
     * @param context
     * @param httpStack
     */
    public HttpUtils init(Context context, HttpStack httpStack) {
        if(specialReqQueue == null) {
            specialReqQueue = Volley.newRequestQueue(context, httpStack);
        }
        return instance;
    }

    /**
     * 构建请求URL
     */
    private String buildReqUrl(String baseUrl, Map<String, String> queryParameter) {
        Uri.Builder builder = Uri.parse(baseUrl).buildUpon();
        if (queryParameter != null && queryParameter.size() > 0) {
            for (Map.Entry<String, String> element : queryParameter.entrySet()) {
                builder.appendQueryParameter(element.getKey(), element.getValue().toString());
            }
        }
        return builder.toString();
    }

    /**
     * gson请求
     * @param method
     * @param url
     * @param reqParameter
     * @param cls
     * @param successListener
     * @param errorListener
     * @param <T>
     */
    public <T> void objectRequest(int method, String url, final Map<String, String> reqParameter, Class<T> cls, final HttpSuccessListener<T> successListener, final HttpErrorListener errorListener) {
        GsonRequest<T> gsonRequest = new GsonRequest<T>(
                method,
                url,
                cls,
                reqParameter,
                new Response.Listener<T>() {
                    @Override
                    public void onResponse(T response) {
                        successListener.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errorListener.onError(error);
                    }
                }

            ){};
        commonReqQueue.add(gsonRequest);
    //    commonReqQueue.start();
    }


    /**
     * 字符串请求
     * @param method
     * @param url
     * @param reqParameter
     * @param successListener
     * @param errorListener
     */
    public void stringRequest(int method, String url, final Map<String, String> reqParameter, final HttpSuccessListener<String> successListener, final HttpErrorListener errorListener) {
        StringRequest strReq = new StringRequest(
                method,
                url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        successListener.onSuccess(response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errorListener.onError(error);
                    }

                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError{
                        return reqParameter;
                    }

                };
        commonReqQueue.add(strReq);
 //       commonReqQueue.start();
    }

    /**
     * jsonobject请求
     */
    public void jsonObjectRequest(int method, String url, final Map<String, String> reqParameter, final HttpSuccessListener<JSONObject> successListener, final HttpErrorListener errorListener) {

        url  = buildReqUrl(url, reqParameter);
        JsonObjectRequest jsonReq = new JsonObjectRequest(
                method,
                url,
                null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        successListener.onSuccess(response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errorListener.onError(error);
                    }

                }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
            }

        };
        commonReqQueue.add(jsonReq);
  //      commonReqQueue.start();
    }

    /**
     * 文件上传
     * @param method
     * @param url
     * @param reqParameter
     * @param files
     * @param successListener
     * @param errorListener
     */
    public <T> void uploadRequest(int method, String url, final Map<String, String> reqParameter, Class<T> cls, final Map<String, File> files,  final HttpSuccessListener<T> successListener, final HttpErrorListener errorListener ) {

        MultiPartGsonRequest multiPartRequest =
                new MultiPartGsonRequest(
                        method,
                        url,
                        cls,
                        new Response.Listener<T>(){
                            @Override
                            public void onResponse(T response) {
                                successListener.onSuccess(response);
                            }
                         },
                        new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                errorListener.onError(error);
                        }

                }) {
                    @Override
                    public Map<String, File> getFileUploads() {
                        return files;
                    }
                    @Override
                    public Map<String, String> getStringUploads() {
                        return reqParameter;
                    }
                };

       specialReqQueue.add(multiPartRequest);
 //      specialReqQueue.start();

    }




}
