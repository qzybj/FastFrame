package com.frame.fastframe.aosp.volley.fileupload;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.frame.fastframelibrary.utils.json.GsonUtils;
import com.google.gson.JsonSyntaxException;


public class MultiPartRequest<T> extends Request<T> implements IMultiPartRequest {

    private Class<T> clazz;
	private final Listener<T> mListener;
	private Map<String,File> fileUploads = new HashMap<String,File>();
	private Map<String,String> stringUploads = new HashMap<String,String>();
	
    /**
     * Creates a new request with the given method.
     *
     * @param method the request {@link Method} to use
     * @param url URL to fetch the string at
     * @param listener Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public MultiPartRequest(int method, String url, Class<T> clazz, Listener<T> listener,ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
        this.clazz = clazz;
    }

    public void addFileUpload(String param,File file) {
    	fileUploads.put(param,file);
    }
    
    public void addStringUpload(String param,String content) {
    	stringUploads.put(param,content);
    }
    
    /**
     * 要上传的文件
     */
    public Map<String,File> getFileUploads() {
    	return fileUploads;
    }
    
    /**
     * 要上传的参数
     */
    public Map<String,String> getStringUploads() {
    	return stringUploads;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(GsonUtils.toObject(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

	@Override
	protected void deliverResponse(T response) {
		if(mListener != null) {
			mListener.onResponse(response);
		}
	}
	
	/**
	 * 空表示不上传
	 */
    public String getBodyContentType() {
        return null;
    }
}