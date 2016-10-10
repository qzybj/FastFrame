package com.frame.fastframelibrary.utils.other;

import com.frame.fastframelibrary.utils.dataprocess.ListUtils;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;

/**
 * Created by ZhangYuanBo on 2016/10/10
 * 校验工具类:
 *   用户名的一些基本规则校验，密码的一些校验规则，返回指定的int值
 */
public class VerifyUtils {

    /**
     * 用于测试多个String是否包含有为空的
     * @param strings
     * @return
     */
    public static boolean isEmptyStrings(String... strings) {
        if(ListUtils.isNotEmpty(strings)){
            for (String string:strings) {
                if (StringUtils.isEmpty(string)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 检查输入的手机号
     * @return  返回 null 代表通过校验
     */
    private static String verifyPhone( String phone) {
        if (StringUtils.isEmpty(phone)) {
            return "请输入手机号";
        }
        if(!phone.startsWith("1")||11!=phone.length()||!StringUtils.isNumeric(phone)){
            return "手机号格式错误";
        }
        return null;
    }

    /**
     * 检查密码
     * @return 返回 null 代表通过校验
     */
    public static String verifyPassword(String password,int minLength,int maxLength){
        if(StringUtils.isEmpty(password)){
            return "请输入密码";
        }
        if(minLength>password.length()||maxLength<password.length()){
            return "密码必须是"+minLength+"到"+maxLength+"位字符";
        }
        if(StringUtils.isHanzi(password)||StringUtils.isHaveBlank(password)){
            return "密码必须是英文字母、特殊字符、数字";
        }
        return null;
    }

}