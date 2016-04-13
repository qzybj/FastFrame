package com.frame.fastframelibrary.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

/**
 * Gson 工具类
 */
public class GsonUtils {
	private static Gson gson;
	static {
		 gson = new GsonBuilder()
		 .enableComplexMapKeySerialization() //支持Map的key为复杂对象的形式  
		 //.excludeFieldsWithoutExposeAnnotation() //不导出实体中没有用@Expose注解的属性  
		 //.serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")//时间转化为特定格式    
		 // .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)//会把字段首字母大写,注:对于实体上使用了@SerializedName注解的不会生效.  
		 //.setPrettyPrinting() //对json结果格式化. 该方法不能够放开，因为BI统计不允许JSON 数据格式化
		 .create();	
	}
	
	
	public static String toJson(Object object){
		return gson.toJson(object);
	}
	

	public static <T> T toObject(String json, Class<T> type){
		if(json == null){
			return null;
		}
		JsonReader reader = new JsonReader(new StringReader(json));
		reader.setLenient(true);		
		return (T)gson.fromJson(reader, TypeToken.get(type).getType());
	}

	public static JsonObject toJsonObject(String strJson){
		try {
			return new JsonParser().parse(strJson).getAsJsonObject();
		} catch (JsonSyntaxException e) {
			LogUtils.e(e);
		}
		return new JsonObject();
	}
}
