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
	public BasicResponse fromJsonByGson(String data) {
		if(data == null){
			return null;
		}		
		
		// ----------------start--------------------------
		// 1.解决返回的数据没有key对应的json串：比如
		// [{"name":"雅诗兰黛"},{"name":"佛牌"},{"name":"雅漾"}]
		// 解决方案：
		// 获得到该数据时，自动把该数据转换为：
		// {"key":[{"name":"雅诗兰黛"},{"name":"佛牌"},{"name":"雅漾"}]}
		// key的定义有开发人员定义，采用的机制是Annotation
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
        //--------------------end-----------------------	
		return GsonUtils.toObject(data, this.getClass());
	}
}
