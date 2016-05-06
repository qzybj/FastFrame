package com.frame.fastframelibrary.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	/**
     * TimeUtils
     *
     * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-8-24
     */
    public static class TimeUtils {

        public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        public static final SimpleDateFormat DATE_FORMAT_DATE    = new SimpleDateFormat("yyyy-MM-dd");

        private TimeUtils() {
            throw new AssertionError();
        }

        /**
         * long time to string
         *
         * @param timeInMillis timeInMillis
         * @param dateFormat   dateFormat
         * @return String
         */
        public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
            return dateFormat.format(new Date(timeInMillis));
        }

        /**
         * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
         *
         * @param timeInMillis time
         * @return String
         */
        public static String getTime(long timeInMillis) {
            return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
        }

        /**
         * get current time in milliseconds
         *
         * @return long
         */
        public static long getCurrentTimeInLong() {
            return System.currentTimeMillis();
        }

        /**
         * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
         *
         * @return  String
         */
        public static String getCurrentTimeInString() {
            return getTime(getCurrentTimeInLong());
        }

        /**
         * get current time in milliseconds
         *
         * @param dateFormat    dateFormat
         * @return  String
         */
        public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {

            return getTime(getCurrentTimeInLong(), dateFormat);
        }
    }
}
