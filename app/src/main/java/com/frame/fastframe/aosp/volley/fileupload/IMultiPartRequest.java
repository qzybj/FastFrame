package com.frame.fastframe.aosp.volley.fileupload;

import java.io.File;
import java.util.Map;

public interface IMultiPartRequest {

     void addFileUpload(String param, File file);

     void addStringUpload(String param, String content);

     Map<String,File> getFileUploads();
    
     Map<String,String> getStringUploads();
}