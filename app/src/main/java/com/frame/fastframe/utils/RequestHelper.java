package com.frame.fastframe.utils;

import android.content.Context;
import android.os.Environment;
import com.frame.fastframelibrary.FastApplication;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.NetUtils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

/** 用来监听请求时间的辅助类(当前只简单写，一个方法名只能存在一个，待改进)  */
public class RequestHelper {
	static final boolean isDevMode = false;

	public final static String FOLDER_NAME= "requestrecord";
	public final static String FOLDER_JSON_NAME= "requestjson";
	public final static String NEWLINE= "\n";
	private static RequestHelper instance;
	private static HashMap<String,RecordBean> map = new HashMap<String,RecordBean>();
	
	
	private RequestHelper() {
		super();
	}
	public static RequestHelper getInstance(){
		if (instance==null) {
			instance = new RequestHelper();
		}
		return instance;
	}

//	public void RecordStart(BasicRequest req){
//		if (req!=null) {
//			map.put(req.interfaceName, getRecordBean(req.interfaceName));
//		}
//	}
//
//	public void RecordEnd(BasicRequest req){
//		if (req!=null) {
//			RecordBean bean = map.get(req.interfaceName);
//			if (bean!=null) {
//				bean.endRecord();
//				write2Disk(bean);
//			}
//		}
//	}
	
	
	public RecordBean getRecordBean(String methodName){
		return new RequestHelper.RecordBean(methodName);
	}
	
	public class RecordBean{
		private String methodName="";
		private long request_start_time =0;
		private long request_end_time =0;
		
		public RecordBean() {
			super();
		}
		
		public RecordBean(String methodName) {
			super();
			this.methodName = methodName;
			this.request_start_time = System.currentTimeMillis();
		}

		public String getMethodName() {
			return methodName;
		}
		public void setMethodName(String methodName) {
			this.methodName = methodName;
		}
		public long getRequest_start_time() {
			return request_start_time;
		}
		public void setRequest_start_time(long request_start_time) {
			this.request_start_time = request_start_time;
		}
		public long getRequest_end_time() {
			return request_end_time;
		}
		public void setRequest_end_time(long request_end_time) {
			this.request_end_time = request_end_time;
		}
		/** 记录结束时间*/
		public void endRecord(){
			setRequest_end_time(System.currentTimeMillis());
		}

		@Override
		public String toString() {
			return "[methodName=" + methodName
					+ ", request_start_time=" + request_start_time
					+ ", request_end_time=" + request_end_time + "]"
					+ NetUtils.getCurrentNetworkType(FastApplication.getInstance())+
					"下耗时 "+(request_end_time-request_start_time)+"毫秒|";
		}
	}
	
	
	/**
	 * 获取可以使用的缓存目录,如果有内存卡，优先使用内存卡，否则使用手机自带存储空间。
	 * @param context
	 * @param uniqueName 目录名称
	 * @return
	 */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath = context.getCacheDir().getPath();
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
        	 String cacheDir = "/Android/data/" + context.getPackageName() + "/";
        	 File cacheFile =new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
        	cachePath = cacheFile.getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }
    /**
	 * 将JSON写到文件(用于记录解析JSON)
	 * @param method
	 * @param json
	 */
    public static void writeJson2Disk(String method, String json){
		if(isDevMode){
			File cacheDir = getDiskCacheDir(FastApplication.getInstance(), FOLDER_JSON_NAME);
	        if(!cacheDir.exists()){
	            cacheDir.mkdirs();
	        }
	        File cacheFile =  new File(cacheDir,method + ".txt");
	        int i=1;
	        while(cacheFile.exists()) {
	        	cacheFile =  new File(cacheDir,method +i+ ".txt");
	        	i++;
	        }
	        //IOUtils.writeFile(cacheFile.getAbsolutePath(), json);
		}
	}
    
    
	public static void write2Disk(RecordBean bean){
		File cacheDir = getDiskCacheDir(FastApplication.getInstance(), FOLDER_NAME);
        if(!cacheDir.exists()){
            cacheDir.mkdirs();
        }
        File cacheFile =  new File(cacheDir,FOLDER_NAME+".txt");
//        int i=1;
//        while(cacheFile.exists()) {
//        	cacheFile =  new File(cacheDir,bean.getMethodName() +i+ ".txt");
//        	i++;
//        }
        writeFile(cacheFile.getAbsolutePath(), bean.toString());
	}
	/**
  	 * 将信息写到固定文件
  	 * @param filePath
  	 * @param data
  	 */
  	public static void writeFile(String filePath, String data){
		File targetFile = new File(filePath);
		File dir = targetFile ;
		if( !targetFile.isDirectory() ){
			dir = targetFile.getParentFile();
		}
        if(!dir.exists()){
        	dir.mkdirs();
        }
		OutputStreamWriter output = null ;
	    BufferedReader buffer = null ;
	    String line;
	    try{
	        output = new OutputStreamWriter(new FileOutputStream(targetFile,true));
	        buffer = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(data.getBytes())));
	        while ((line = buffer.readLine()) != null) {
	            output.write(line);
	            output.write(NEWLINE);
	        }
	        output.flush();
	       
	    }catch(Exception e){
	    	LogUtils.e(e);
	    }finally{
	    	if( output != null ){
	    		try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
	    	if( buffer != null ){
		        try {
					buffer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
	    }
	}
}