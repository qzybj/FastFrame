package com.frame.fastframelibrary.utils.verify;

import com.frame.fastframelibrary.utils.dataprocess.StringUtils;

import java.util.HashMap;


/**用于处理服务端返回的错误String对应的状态码*/
public class MatchErrorUtils {

    public static final int ERROR_NONE = 120000;

    /**手机号格式不对*/
    public static final int ERROR_CODE_PHONE_FORMAT = 120001;
    /**密码错误*/
    public static final int ERROR_CODE_PASSWORD = 120002;
    /**密码格式错误*/
    public static final int ERROR_CODE_PASSWORD_FORMAT  = 120003;
    /**手机号不存在*/
    public static final int ERROR_CODE_PHONE_NO_EXIST = 120004;
    /**验证码错误*/
    public static final int ERROR_CODE_VERIFYCODE = 120005;
    /**验证码失效*/
    public static final int ERROR_CODE_VERIFYCODE_NO_VALID = 120006;
    /**手机号已注册过*/
    public static final int ERROR_CODE_PHONE_REGISTERED = 120007;
    /**1天输入密码错误两次，使用图型验证码*/
    public static final int ERROR_CODE_NEED_VERIFYCODE = 120008;

    /**
     * 定义不同跳转类型所对应的activity类，只针对跳转到页面的情况
     */
    private static HashMap<String, Integer> errorMap = null ;

    /**
     * 获取需要登录的跳转类型
     * @return
     */
    private static HashMap<String, Integer> getErrorMap(){
        if( errorMap == null ) {
            errorMap = new HashMap<String, Integer>();

            /*手机号格式不对*/
            errorMap.put("请输入正确的手机号", ERROR_CODE_PHONE_FORMAT);
            errorMap.put("请输入正确的手机号/邮箱", ERROR_CODE_PHONE_FORMAT);


            /*手机号不存在*/
            errorMap.put("银泰护照号不存在或密码错误", ERROR_CODE_PHONE_NO_EXIST);
            errorMap.put("用户不存在或密码错误", ERROR_CODE_PHONE_NO_EXIST);

            /*密码错误*/
            //errorMap.put("", ERROR_CODE_PASSWORD);

            /*密码格式错误*/
            errorMap.put("请输入6到12位英文加数字的密码", ERROR_CODE_PASSWORD_FORMAT);

            /*验证码错误*/
            errorMap.put("验证码错误", ERROR_CODE_VERIFYCODE);

            /*验证码失效*/
            errorMap.put("验证码已失效，请重新获取", ERROR_CODE_VERIFYCODE_NO_VALID);

            /*手机号已注册过*/
            errorMap.put("该手机号已注册过银泰护照，请使用忘记密码重置密码，或使用其他手机号注册", ERROR_CODE_PHONE_REGISTERED);

            /*1天输入密码错误两次，使用图型验证码*/
            errorMap.put("1天输入密码错误两次，使用图型验证码", ERROR_CODE_NEED_VERIFYCODE);

        }
        return errorMap ;
    }

    /**
     * 根据错误信息寻找对应的错误状态码
     * @param errMsg
     * @return
     */
    public static int getErrorCode(String errMsg){
        if(StringUtils.isNotEmpty(errMsg)){
            Integer integer = getErrorMap().get(errMsg.trim());
            if(integer!=null){
                return integer;
            }
        }
        return ERROR_NONE;
    }

    /**
     * 用于解析传递过来的JsonResponse
     * @return true 被消耗  false 未消耗
     */
    public boolean parserResponse(){

        return false;
    }
}