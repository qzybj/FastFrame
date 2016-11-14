package com.frame.fastframelibrary.utils.verify;

import android.widget.TextView;

import com.frame.fastframelibrary.utils.dataprocess.ListUtils;
import com.frame.fastframelibrary.utils.dataprocess.StringUtils;
import com.frame.fastframelibrary.utils.view.TextViewUtils;

/**
 * Created by ZhangYuanBo on 2016/10/10
 * 校验工具类:
 *   用户名的一些基本规则校验，密码的一些校验规则，返回指定的int值
 */
public class VerifyUtils {
    /**Successfull check*/
    public static final int SUCCESS_CODE = 7000000;
    /**Error: value is empty*/
    public static final int ERROR_CODE_EMPTY = 7000001;
    /**Error: value format is error*/
    public static final int ERROR_CODE_FORMAT = 7000002;
    /**Error: value length is error*/
    public static final int ERROR_CODE_LENGTH = 7000003;

    /**
     * 用于测试多个TextView是否包含有为空的
     * @param textViews
     * @return
     */
    public static boolean isContainEmpty(TextView... textViews) {
        if(ListUtils.isNotEmpty(textViews)){
            for (TextView tv:textViews) {
                if (verifyEmpty(tv)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 用于测试多个String是否包含有为空的
     * @param strings
     * @return
     */
    public static boolean isContainEmpty(String[] strings) {
        if(ListUtils.isNotEmpty(strings)){
            for (String string:strings) {
                if (verifyEmpty(string)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 检查TextView中的字符是否为空
     * @param tv
     * @return
     */
    public static boolean verifyEmpty(TextView tv) {
        return verifyEmpty(TextViewUtils.getTextViewValue(tv));
    }

    /**
     * 检查字符是否为空
     * @param string
     * @return
     */
    public static boolean verifyEmpty(String string) {
        if (StringUtils.isEmpty(string)) {
            return true;
        }
        return false;
    }

    /**
     * 检查手机号：为空校验、是否为手机号格式
     * @param tv
     * @return
     */
    public static int verifyPhone(TextView tv) {
        return verifyPhone(TextViewUtils.getTextViewValue(tv));
    }
    /**
     * 检查手机号：为空校验、是否为手机号格式
     * @param phone
     * @return
     */
    public static int verifyPhone(String phone) {
        if (verifyEmpty(phone)) {
            return ERROR_CODE_EMPTY;//"请输入手机号"
        }
        if(!StringUtils.isPhone(phone)){
            return ERROR_CODE_FORMAT;//"手机号格式错误"
        }
        return SUCCESS_CODE;
    }

    /**
     * 检查密码:为空校验、长度 6~12校验、排除汉字
     * @param password
     * @return
     */
    public static int verifyPassword(String password){
        return verifyPassword(password,6,12);
    }

    /**
     * 检查密码:为空校验、长度校验、排除汉字
     * @param password
     * @param minLength
     * @param maxLength
     * @return
     */
    public static int verifyPassword(String password,int minLength,int maxLength){
        if(verifyEmpty(password)){
            return ERROR_CODE_EMPTY;//"请输入密码";
        }
        if(minLength>password.length()||maxLength<password.length()){
            return ERROR_CODE_LENGTH;//"密码必须是"+minLength+"到"+maxLength+"位字符";
        }
        if(StringUtils.isHanzi(password)||StringUtils.isHaveBlank(password)){
            return ERROR_CODE_FORMAT;//"密码不能包含汉字、空格";
        }
        return SUCCESS_CODE;
    }
}