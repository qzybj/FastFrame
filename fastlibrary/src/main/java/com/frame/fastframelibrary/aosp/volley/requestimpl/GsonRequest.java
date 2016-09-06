/**
 * Copyright 2013 Mani Selvaraj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.frame.fastframelibrary.aosp.volley.requestimpl;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.frame.fastframelibrary.net.core.bean.NetResponse;
import com.frame.fastframelibrary.net.core.bean.ResultBean;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Custom implementation of Request<T> class which converts the HttpResponse obtained to Java class objects.
 * Uses GSON library, to parse the response obtained.
 * Ref - JsonRequest<T>
 * @author Mani Selvaraj
 */

public  class GsonRequest<T extends ResultBean> extends Request<T>{

    /** Charset for request. */
    private static final String PROTOCOL_CHARSET = "utf-8";

    /** Content type for request. */
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    private final Listener<T> mListener;

    private final String mRequestBody;
    
    private Gson mGson;
    private Class<T> mJavaClass;

    private Map<String, String> headers ;
    private Map<String, String> params;

    public GsonRequest(int method, String url, Class<T> cls,String requestBody, Listener<T> listener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mGson = new Gson();
        this.mJavaClass = cls;
        this.mListener = listener;
        this.mRequestBody = requestBody;
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
    
	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
        if(headers==null){
            headers  = new HashMap<String, String>();
        }
		return headers;
	}
    public void setHeaders(Map<String, String> headers) throws AuthFailureError {
        getHeaders().putAll(headers);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if(params==null){
            params = new HashMap<String, String>();
        }
        return params;
    }
    public void setParams(Map<String, String> params) throws AuthFailureError {
        getParams().putAll(params);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Type type = mJavaClass.newInstance().getType();//new TypeToken<NetResponse<T>>() {}.getType()
            NetResponse<T> parsedParentGSON = mGson.fromJson(jsonString,type);
            return Response.success(parsedParentGSON.result,HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException je) {
            return Response.error(new ParseError(je));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }

    }

    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    @Override
    public byte[] getBody() {
        try {
            return mRequestBody == null ? null : mRequestBody.getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException uee) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",mRequestBody, PROTOCOL_CHARSET);
            return null;
        }
    }
}