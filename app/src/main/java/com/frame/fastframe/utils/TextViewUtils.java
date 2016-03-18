package com.frame.fastframe.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;




public class TextViewUtils {

	
	public void setTextViewTextColor(Context con,TextView tv,int start,int end) {
		SpannableStringBuilder builder = new SpannableStringBuilder(tv.getText().toString());
		//ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
		//con.getResources().getColor(R.color.color_000000);
		ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.RED);
		builder.setSpan(redSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tv.setText(builder);
	}
	
	public static void setTextViewSpannableStr(Context context,int colorResId ,TextView tv,String str,int start,int end,View.OnClickListener listener){
		//setTextViewSpannableStr(context, Paint.UNDERLINE_TEXT_FLAG, colorResId, tv, str, start, end, listener);
	}
	
//	/**
//	 *
//	 *
//	 * 设置TextView的字符和一部分点击事件
//	 * @param context
//	 * @param paintFlags 设置TextPaint flag
//	 * @param colorResId 设置字体颜色
//	 * @param tv 	待设置的TextView
//	 * @param str  	待处理的字符
//	 * @param start 待处理的字符开始
//	 * @param end	待处理的字符结束
//	 * @param listener 点击事件监听  (!!!不要传空，因为会有问题)
//	 */
//	public static void setTextViewSpannableStr(Context context,int paintFlags,int colorResId ,TextView tv,String str,int start,int end,View.OnClickListener listener){
//		if (!StringUtil.isBlank(str)) {
//			try {
//				SpannableString spanableInfo = new SpannableString(str);
//				spanableInfo.setSpan(new Clickable(context,paintFlags,context.getResources().getColor(colorResId),str,listener), start, end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//				tv.setText(spanableInfo);
//				tv.setMovementMethod(LinkMovementMethod.getInstance());
//			} catch (Exception e) {
//				Log.e("TextViewUtils",e.getLocalizedMessage());
//			}
//		}
//	}
	
	public static void setTextViewValueAndVisiable(TextView tv,String text){
		if (tv!=null) {
			if (StringUtil.isBlank(text)) {
				tv.setVisibility(View.GONE);
			}else {
				tv.setVisibility(View.VISIBLE);
				tv.setText(text);
			}
		}
	}
	public static void setTextViewValue(TextView tv,String text){
		if (tv!=null) {
			if (!StringUtil.isBlank(text)) {
				tv.setText(text);
			}
		}
	}
	
	public static String getTextViewValue(TextView tv){
		return getTextViewValue(tv,"");
	}
	
	public static String getTextViewValue(TextView tv,String text){
		if (tv!=null) {
			String returnStr  = tv.getText().toString().trim();
			if (!StringUtil.isBlank(returnStr)) {
				return returnStr;
			}
		}
		return text;
	}
	
	public static String getEditTextValue(EditText et){
		if (et!=null) {
			String returnStr  = et.getText().toString().trim();
			if (!StringUtil.isBlank(returnStr)) {
				return returnStr;
			}
		}
		return "";
	}

}