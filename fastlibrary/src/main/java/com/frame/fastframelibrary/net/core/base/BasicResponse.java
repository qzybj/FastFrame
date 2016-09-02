package com.frame.fastframelibrary.net.core.base;

import java.lang.reflect.Field;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import com.frame.fastframelibrary.net.core.annotation.JSonParseAnn;
import com.frame.fastframelibrary.utils.json.GsonUtils;

public class BasicResponse {
	private int mErrCode = 0;
	private String mErrMsg=null;
	private boolean fromNet = true;
	
	public BasicResponse setErrCode(int errCode) {
		mErrCode = errCode;
		return this;
	}
	
	public BasicResponse setErrMsg(String errMsg) {
		mErrMsg = errMsg;
		return this;
	}
	
	public int getErrCode() {		
		return mErrCode;
	}
	
	public String getErrMsg() {		
		return mErrMsg;
	}
	
	public void setFromNet(boolean fromNet){
		this.fromNet = fromNet;
	}
	
	public boolean getFromNet(){
		return fromNet;
	}
	
	@Override
	public String toString() {		
		return "mErrCode="+mErrCode+";mErrMsg="+mErrMsg;		
	}

	/**
	 * 用Gson解析json数据
	 * @param data
	 * @return
	 */
	public BasicResponse toJson(String data) {
		if(data == null){
			return null;
		}
		Field[] fields = this.getClass().getDeclaredFields();
		if (fields.length == 1) {
			JSonParseAnn t_LFJSonParseAnn = null;
			Field t_field = null;
			t_field = fields[0];
			t_LFJSonParseAnn = t_field.getAnnotation(JSonParseAnn.class);
			if (t_LFJSonParseAnn != null) {
				JSONTokener readFrom = new JSONTokener(data);
				JSONObject json = new JSONObject();
				try {
					json.put(t_LFJSonParseAnn.jsonType(), readFrom.nextValue());
					data = json.toString();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		return GsonUtils.toObject(data, this.getClass());
	}
}
