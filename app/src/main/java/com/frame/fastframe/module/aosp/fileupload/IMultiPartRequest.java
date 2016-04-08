package com.frame.fastframelibrary.aosp.fileupload;

import java.io.File;
import java.util.Map;

public interface IMultiPartRequest {

    public void addFileUpload(String param, File file);
    
    public void addStringUpload(String param, String content);
    
    public Map<String,File> getFileUploads();
    
    public Map<String,String> getStringUploads(); 
}