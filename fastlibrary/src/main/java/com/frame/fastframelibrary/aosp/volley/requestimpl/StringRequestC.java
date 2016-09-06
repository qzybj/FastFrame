package com.frame.fastframelibrary.aosp.volley.requestimpl;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;


public class StringRequestC extends StringRequest {

    private Map<String, String> headers ;
    private Map<String, String> params;

    public StringRequestC(int method, String url,Listener<String> listener, ErrorListener errorListener) {
        super(method, url, listener,errorListener);
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
}