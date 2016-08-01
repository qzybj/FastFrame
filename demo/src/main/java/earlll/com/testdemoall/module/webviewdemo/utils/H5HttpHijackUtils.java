package earlll.com.testdemoall.module.webviewdemo.utils;

import android.content.Context;
import android.os.Build;
import android.webkit.WebResourceResponse;
import com.frame.fastframelibrary.FastApplication;
import com.frame.fastframelibrary.utils.io.FileUtils;
import com.frame.fastframelibrary.utils.json.GsonUtils;
import com.frame.fastframelibrary.utils.dataprocess.ListUtils;
import com.frame.fastframelibrary.utils.LogUtils;
import com.frame.fastframelibrary.utils.cache.SharedPreferencesUtils;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * html页面打开时，针对http劫持逻辑的处理
 */
public class H5HttpHijackUtils {
    public static final String TAG = "HttpHijack";
    /**KEY - http劫持白名单*/
    private static final String KEY_HIJACK_WHITELIST = "hijack_whitelist";
    /**KEY - 拦截Url记录*/
    private static final String KEY_HIJACK_INTERCEPTLIST = "hijack_interceptlist";
    /**KEY - http劫持拦截开关 */
    private static final String KEY_HIJACK_INTERCEPTSWITCH = "hijack_interceptswitch";

    /**value - 允许拦截*/
    public static final String VALUE_HIJACK_INTERCEPT_ALLOW = "allow";
    /**value - 不允许拦截*/
    public static final String VALUE_HIJACK_INTERCEPT_DENY = "deny";

    private static final String CACHE_PATH = "intercepturl";
    private static final String CACHE_FILE = "intercepturlfile.txt";
    /**是否开启拦截Url开关*/
    private static boolean isAllowInterceptUrl = false;

    private static Context getContext(){
        return FastApplication.instance();
    }

    /**
     * 是否开启拦截Url开关
     * @return true 允许拦截Url false 不允许拦截Url
     */
    public static boolean isAllowInterceptUrl(){
        return isAllowInterceptUrl;
    }

    /** SET - http劫持拦截开关*/
    public static void setResponse(boolean isAllow,String flag,String[] array){
        isAllowInterceptUrl =isAllow;
        setHijackInterceptSwitch(flag);
        saveWhiteList(array);
    }

    /** SET - http劫持拦截开关*/
    public static void setHijackInterceptSwitch (String value){
        SharedPreferencesUtils.instance().setString(KEY_HIJACK_INTERCEPTSWITCH, value);
    }

    /** 获取http劫持拦截开关*/
    private static String getHijackInterceptSwitch(){
        return SharedPreferencesUtils.instance().getString(KEY_HIJACK_INTERCEPTSWITCH);
    }

    /**
     * 拦截请求URL,看是否为允许访问的白名单中，并将未知URL记录入拦截列表等等下次启动并上传
     * @param url
     * @return 返回 null 既为不拦截Url
     */
    public static WebResourceResponse checkHijackUrl(String url){
        WebResourceResponse hijackResponse = null;
        if(StringUtils.isNotEmpty(url)){
            String flagType = getHijackInterceptSwitch();
            if(VALUE_HIJACK_INTERCEPT_ALLOW.equalsIgnoreCase(flagType)){
                //标志为 allow (允许拦截)，如果 regexlist 里有值，只拦截 regexlist里面的Url;如果 regexlist 里没值，就拦截所有的Url。
                if(!matchesWhiteList(url)){
                    return null;
                }
            }else if(VALUE_HIJACK_INTERCEPT_DENY.equalsIgnoreCase(flagType)){
                //标志为 deny (不允许拦截)，如果 regexlist 里有值，只允许 regexlist里面的Url访问;如果 regexlist 里没值，就允许所有Url访问。
                if(matchesWhiteList(url)){
                    return null;
                }
            }else{
                return null;
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                hijackResponse = new WebResourceResponse("text/html", "UTF-8", new InputStream() {
                    @Override
                    public int read() throws IOException {
                        return -1;
                    }
                });
            }
            if(hijackResponse!=null){
                //将拦截的url写入本地文件
                addInterceptUrl(url);
            }
        }
        return hijackResponse;
    }

    /**
     * 获取http劫持白名单列表
     * @return
     */
    private static ArrayList<String> getWhiteList(){
        if (getContext()!=null) {
            String jsonStr = SharedPreferencesUtils.instance().getString(KEY_HIJACK_WHITELIST);
            if (StringUtils.isNotEmpty(jsonStr)) {
                try {
                    return GsonUtils.toObjectList(jsonStr);
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
        }
        return null;
    }

    /**记录拦截的Url*/
    private synchronized static void addInterceptUrl(String url) {
        String jsonStr = SharedPreferencesUtils.instance().getString(KEY_HIJACK_INTERCEPTLIST);
        if (StringUtils.isNotEmpty(url)) {
            ArrayList<String> list = null;
            if (StringUtils.isNotEmpty(jsonStr)) {
                try {
                    list = GsonUtils.toObjectList(jsonStr);
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
            if (ListUtils.isEmpty(list)) {
                list = new ArrayList<String>();
            }
            list.add(url);
            SharedPreferencesUtils.instance().setString(KEY_HIJACK_INTERCEPTLIST, GsonUtils.toJson(list));
            LogUtils.d(TAG,"addInterceptUrl(记录拦截的Url) ="+jsonStr);
        }
    }

    /**
     * 获取拦截列表存储文件
     * @return
     */
    public static File getInterceptUrlFile() {
        File cacheDir = FileUtils.getDiskCacheDir(getContext(), CACHE_PATH);
        if(!cacheDir.exists()){
            cacheDir.mkdirs();
        }
        File cacheFile =  new File(cacheDir,CACHE_FILE);
        String jsonStr = SharedPreferencesUtils.instance().getString(KEY_HIJACK_INTERCEPTLIST);
        if(StringUtils.isEmpty(jsonStr)){
            return null;
        }
        FileUtils.writeFile(cacheFile.getAbsolutePath(),jsonStr,true);
        return cacheFile;
    }

    /**
     * 保存http劫持白名单列表
     * @param bean
     */
    public static void saveWhiteList(ArrayList<String> bean) {
        if (getContext()!=null&&bean!=null) {
            try {
                SharedPreferencesUtils.instance().setString(KEY_HIJACK_WHITELIST, GsonUtils.toJson(bean));
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }
    /**
     * 保存http劫持白名单列表
     * @param array
     */
    public static void saveWhiteList(String[] array) {
        saveWhiteList(ListUtils.toList(String.class,array));
    }

    /** 清空http劫持白名单列表*/
    public static void clearWhiteList(){
        SharedPreferencesUtils.instance().setString(KEY_HIJACK_WHITELIST, "");
    }

    /** 清空拦截的Url列表*/
    public static void clearInterceptList(){
        SharedPreferencesUtils.instance().setString(KEY_HIJACK_INTERCEPTLIST, "");
    }

    /**
     * 目标字符匹配http劫持白名单列表
     * @param str 目标字符
     * @return
     */
    private static boolean matchesWhiteList(String str){
        //return matchesWhiteListByRegEx(str);//正则匹配
        return matchesWhiteListByContain(str);//字符串包含匹配
    }

    /**
     * 目标字符匹配http劫持白名单列表(正则匹配)
     * @param str 目标字符
     * @return true 匹配http劫持白名单 false 不匹配
     */
    private static boolean matchesWhiteListByRegEx(String str){
        if(getContext()!=null&&StringUtils.isNotEmpty(str)){
            ArrayList<String> whiteListRules = getWhiteList();
            if(!ListUtils.isEmpty(whiteListRules)){//匹配扫描白名单
                for (int i = 0; i <whiteListRules.size() ; i++) {
                    String rule = whiteListRules.get(i);
                    if(StringUtils.isNotEmpty(rule)){
                        Matcher matcher = Pattern.compile(rule).matcher(str);
                        //matcher.find();//匹配到子串
                        //matcher.matches();//匹配到整串
                        if(matcher.find()){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     *
     * 目标字符匹配http劫持白名单列表(字符串包含匹配)
     * @param str 目标字符
     * @return true 匹配http劫持白名单 false 不匹配
     */
    private static boolean matchesWhiteListByContain(String str){
        if(ListUtils.isNotEmpty(getWhiteList())){//二维码扫描内部指定白名单
            ArrayList<String> ruleList = getWhiteList();
            int size = ruleList.size();
            for (int i = 0; i < size; i++) {
                String rule = ruleList.get(i);
                if(StringUtils.isNotEmpty(rule)&&str.contains(rule)){
                    return true;
                }
            }
        }
        return false;
    }
}
