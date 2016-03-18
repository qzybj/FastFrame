package com.frame.fastframe.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.frame.fastframe.R;
import com.frame.fastframe.module.common.util.DensityUtil;
import com.frame.fastframe.utils.DeviceUtils;
import com.frame.fastframe.utils.StringUtil;

public class CustomDialog extends Dialog {
	protected CustomDialog(Context context) {
		super(context);
	}

	public CustomDialog(Context context, int theme) {
		super(context, theme);
	}


	public static class Builder implements android.view.View.OnClickListener {
		private Context context;
		private CustomDialog dialog;
		/** 中间内容 **/
		private String message;
		/** 标题 **/
		private String title;
		/** 回调接口，兼容BaseActivity里的公共方法**/
		private DialogCallBack dialogCallBack;
		/** 带回调builder的接口，为了兼容BaseActivity里的公共方法**/
		private MyDialogCallBack myDialogCallBack;
		/** 对话框的配置封装  兼容以前 **/
		private DialogConfig dialogConfig;
		private String positiveButtonText, negativeButtonText;
		private DialogInterface.OnClickListener positiveButtonClickListener, negativeButtonClickListener;
		private View divideView,buttonDivideView;
		private View buttonView;
		/** 确定，取消  button **/
		private Button sureTv,cancelTv;
		private TextView titleTv,contentTv;
		/**默认倒计时时间，只有没有按钮的情况下 才会倒计时对话框自动消失**/
		private volatile int countDownTime=3;
		private  boolean isAutoDismiss=false;
		/**倒计时**/
		private final int DIALOG_DISMISS=10001;
		private OnKeyListener onKeyListener;

		/** 单选 **/
		private CharSequence[] items;
		private int checkedItem=0;
		private DialogInterface.OnClickListener onClickListener;
		private ListView itemLv;
		private ItemAdapter itemAdapter;

		private final int MESSAGE_HORIZONTAL_PADDING=33;
		private final int WORD_SIZE=15;
		private final int LINE_SPACE_EXTRA=2;
		private boolean isCancelable=true;

		public Builder(Context context) {
			this.context = context;
		}
		/**
		 * Set the Dialog message from resource
		 *
		 * @param message
		 * @return
		 */
		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		/**
		 * Set the Dialog message from resource
		 *
		 * @param message
		 * @return
		 */
		public Builder setMessage(int message) {
			this.message = (String) context.getText(message);
			return this;
		}

		/**
		 * Set the Dialog title from String
		 *
		 * @param title
		 * @return
		 */
		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		/**
		 * Set the Dialog title from resource
		 *
		 * @param title
		 * @return
		 */
		public Builder setTitle(int title) {
			this.title = (String) context.getText(title);
			return this;
		}

		public Builder setDialogConfig(DialogConfig dialogConfig){
			this.dialogConfig=dialogConfig;
			return this;
		}

		public Builder setMyDialogCallBack(MyDialogCallBack myDialogCallBack,String positive){
			this.myDialogCallBack=myDialogCallBack;
			this.positiveButtonText=positive;
			return this;
		}

		public Builder setMyDialogCallBack(MyDialogCallBack myDialogCallBack, String positive, String negative){
			this.myDialogCallBack=myDialogCallBack;
			this.negativeButtonText=negative;
			this.positiveButtonText=positive;
			return this;
		}

		public Builder setMyDialogCallBack(MyDialogCallBack myDialogCallBack){
			this.myDialogCallBack=myDialogCallBack;
			return this;
		}

		public Builder setDialogCallBack(DialogCallBack dialogCallBack){
			this.dialogCallBack=dialogCallBack;
			return this;
		}

		public Builder setDialogCallBack(DialogCallBack dialogCallBack,String positive){
			this.dialogCallBack=dialogCallBack;
			this.positiveButtonText=positive;
			return this;
		}

		public Builder setDialogCallBack(DialogCallBack dialogCallBack, String positive, String negative){
			this.dialogCallBack=dialogCallBack;
			this.negativeButtonText=negative;
			this.positiveButtonText=positive;
			return this;
		}


		public Builder setPositiveButton(int positiveButtonText, DialogInterface.OnClickListener listener) {
			this.positiveButtonText = (String) context.getText(positiveButtonText);
			this.positiveButtonClickListener = listener;
			return this;
		}

		public Builder setPositiveButton(String positiveButtonText, DialogInterface.OnClickListener listener) {
			this.positiveButtonText = positiveButtonText;
			this.positiveButtonClickListener = listener;
			return this;
		}


		public Builder setNegativeButton(int negativeButtonText, DialogInterface.OnClickListener listener) {
			this.negativeButtonText = (String) context.getText(negativeButtonText);
			this.negativeButtonClickListener = listener;
			return this;
		}

		public Builder setCancelable(boolean isCancelable){
			this.isCancelable=isCancelable;
			return this;
		}

		public Builder setNegativeButton(String negativeButtonText,
										 DialogInterface.OnClickListener listener) {
			this.negativeButtonText = negativeButtonText;
			this.negativeButtonClickListener = listener;
			return this;
		}
		public Builder setCountDownTime(int countDownTime){
			if(countDownTime<=3 && countDownTime>0){
				this.countDownTime=countDownTime;
			}
			return this;
		}
		public Builder setOnKeyListener(OnKeyListener onkeyListener){
			this.onKeyListener=onkeyListener;
			return  this;
		}

		public Builder  setSingleChoiceItems(CharSequence[] items, int checkedItem, OnClickListener listener){
			this.items=items;
			this.checkedItem=checkedItem;
			this.onClickListener=listener;
			return this;
		}

		public CustomDialog getDialog(){
			return dialog;
		}

		private CustomDialog create(){
			CustomDialog mDialog=new CustomDialog(context, R.style.style_dialog_common);
			View rootView= LayoutInflater.from(context).inflate(R.layout.common_alertdialog, null);
			initView(rootView);
			configView(mDialog);

			/** 把dialog的键盘监控通过回调交给调用的页面去处理 **/
			if(myDialogCallBack!=null){
				myDialogCallBack.doCallBack(this);
			}
			if(onKeyListener!=null){
				mDialog.setOnKeyListener(onKeyListener);
			}
			mDialog.setContentView(rootView);
			mDialog.setCancelable(isCancelable);
			return mDialog;
		}

		private void initView(View rootView) {
			titleTv=(TextView) rootView.findViewById(R.id.title);
			contentTv=(TextView) rootView.findViewById(R.id.content);
			sureTv=(Button) rootView.findViewById(R.id.sure);
			cancelTv=(Button) rootView.findViewById(R.id.cancel);
			buttonView=rootView.findViewById(R.id.buttonView);
			buttonDivideView=rootView.findViewById(R.id.btnDivide);
			divideView=rootView.findViewById(R.id.divideLine);
			itemLv=(ListView) rootView.findViewById(R.id.itemLv);
		}
		/**
		 * 显示控制 赋值
		 *
		 */
		private void configView(final CustomDialog mDialog) {
			sureTv.setOnClickListener(this);
			cancelTv.setOnClickListener(this);
			itemLv.setVisibility(View.GONE);
			if(items!=null){
				itemLv.setVisibility(View.VISIBLE);
				if(!StringUtil.isEmpty(title)){
					titleTv.setText(title);
				}
				contentTv.setVisibility(View.GONE);
				itemAdapter=mDialog.new ItemAdapter(items, checkedItem, context);
				itemLv.setAdapter(itemAdapter);
				itemLv.setVerticalScrollBarEnabled(false);
				itemLv.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
											int position, long id) {
						itemAdapter.setCheckedItem(position);
						onClickListener.onClick(mDialog, position);
					}
				});
				if(StringUtil.isEmpty(positiveButtonText) && StringUtil.isEmpty(negativeButtonText)){
					buttonView.setVisibility(View.GONE);
					divideView.setVisibility(View.GONE);
				}else{
					if(!StringUtil.isEmpty(positiveButtonText)){
						buttonDivideView.setVisibility(View.GONE);
						cancelTv.setVisibility(View.GONE);
						sureTv.setText(positiveButtonText);
					}else if(!StringUtil.isEmpty(negativeButtonText)){
						buttonDivideView.setVisibility(View.GONE);
						sureTv.setVisibility(View.GONE);
						cancelTv.setText(negativeButtonText);
					}
				}

			} else if(dialogConfig!=null){
				titleTv.setText(dialogConfig.title);
				contentTv.setText(dialogConfig.message);
				mDialog.setCancelable(dialogConfig.isCancleDialog);
				sureTv.setText(dialogConfig.positive);
				if(dialogConfig.isOneButton){
					buttonDivideView.setVisibility(View.GONE);
					cancelTv.setVisibility(View.GONE);
				}else{
					cancelTv.setText(dialogConfig.negative);
				}
			}else{
				if(!StringUtil.isEmpty(title)){
					titleTv.setText(title);
				}
				if(!StringUtil.isEmpty(message)){
					contentTv.setText(message);
				}
				if(StringUtil.isEmpty(positiveButtonText)){
					buttonView.setVisibility(View.GONE);
					divideView.setVisibility(View.GONE);
					isAutoDismiss=true;
				}else if(StringUtil.isEmpty(negativeButtonText)){
					buttonDivideView.setVisibility(View.GONE);
					cancelTv.setVisibility(View.GONE);
					sureTv.setText(positiveButtonText);
				}else{
					sureTv.setText(positiveButtonText);
					cancelTv.setText(negativeButtonText);
				}
			}
		}

		public void onClick(View v) {
			switch(v.getId()){
				case  R.id.sure:
					if(dialog!=null){
						dialog.dismiss();
					}
					if(dialogCallBack!=null){
						dialogCallBack.positive();
					}else if(myDialogCallBack!=null){
						myDialogCallBack.positive();
					}else if(positiveButtonClickListener!=null){
						positiveButtonClickListener.onClick(dialog,
								DialogInterface.BUTTON_POSITIVE);
					}
					break;
				case R.id.cancel:
					if(dialog!=null){
						dialog.dismiss();
					}
					if(dialogCallBack!=null){
						dialogCallBack.negative();
					}else if(myDialogCallBack!=null){
						myDialogCallBack.negative();
					}else if(negativeButtonClickListener!=null){
						negativeButtonClickListener.onClick(dialog,
								DialogInterface.BUTTON_NEGATIVE);
					}
					break;
			}
		}
		public CustomDialog show() {
			dialog = create();
			dialog.show();
			Window dialogWindow = dialog.getWindow();
			WindowManager.LayoutParams lp = dialogWindow.getAttributes();
			dialogWindow.setGravity(Gravity.CENTER);
			lp.width = DeviceUtils.getScreenWidth(context)*3/4;
			if(items==null){
				int messageHeight=0;
				if(!StringUtil.isEmpty(message)){
					int wordCount= (lp.width- DensityUtil.dip2px(context, MESSAGE_HORIZONTAL_PADDING)*2)/DensityUtil.dip2px(context,WORD_SIZE);
					int extraCount=StringUtil.countString(message)-wordCount;
					if(extraCount>=0){
						messageHeight=(extraCount/wordCount+1)*DensityUtil.dip2px(context, WORD_SIZE+LINE_SPACE_EXTRA);
					}
				}
				lp.height =  DeviceUtils.getScreenHeight(context)/4+messageHeight;
				if(lp.height>DeviceUtils.getScreenHeight(context)*2/3){
					lp.height=DeviceUtils.getScreenHeight(context)*2/3;
				}

			}else{
				lp.height =  DeviceUtils.getScreenHeight(context)/2;
			}
			dialogWindow.setAttributes(lp);
			if(isAutoDismiss){//开启倒计时
				countDownTime();
			}
			return dialog;
		}
		private Handler myHandler=new Handler(){
			public void handleMessage(android.os.Message msg) {
				switch(msg.what){
					case DIALOG_DISMISS:
						if(dialog!=null && dialog.isShowing()){
							dialog.dismiss();
						}
						break;
				}
			};
		};
		/**
		 * 开启倒计时
		 */
		private void countDownTime() {
			if(countDownTime<=0){
				return;
			}
			new Thread(new Runnable() {

				@Override
				public void run() {
					while(countDownTime>0){
						SystemClock.sleep(1000);
						countDownTime--;
					}
					myHandler.sendEmptyMessage(DIALOG_DISMISS);
				}
			}).start();

		}
	}
	/**
	 * 对话框回调接口
	 */
	public interface DialogCallBack {
		public void positive();
		public void negative();
	}

	/**
	 * 对话框回调接口
	 */
	public interface MyDialogCallBack {

		public void positive();

		public void negative();

		public void doCallBack(CustomDialog.Builder builder);
	}


	public class ItemAdapter extends BaseAdapter {

		private CharSequence[] items;
		private int checkedItem=0;
		private Context context;
		private LayoutInflater inflate;
		public ItemAdapter(CharSequence[] items, int checkedItem, Context context) {
			super();
			this.items = items;
			this.checkedItem = checkedItem;
			this.context = context;
			inflate= LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return items==null?0:items.length;
		}

		public void setCheckedItem(int position){
			this.checkedItem=position;
			this.notifyDataSetChanged();
		}

		@Override
		public Object getItem(int position) {
			return items[position];
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder vh;
			if(convertView==null){
				vh=new ViewHolder();
				convertView=inflate.inflate(R.layout.common_dialog_choice_item, null);
				vh.choiceRbIv=(ImageView) convertView.findViewById(R.id.choiceRb);
				vh.itemTv=(TextView) convertView.findViewById(R.id.itemTxt);
				convertView.setTag(vh);
			}else{
				vh=(ViewHolder) convertView.getTag();
			}
			if(position==checkedItem){
				vh.choiceRbIv.setImageResource(R.mipmap.dialog_choice_item_selected);
			}else{
				vh.choiceRbIv.setImageResource(R.mipmap.dialog_choice_item_normal);
			}
			vh.itemTv.setText(items[position]);
			return convertView;
		}

	}
	class ViewHolder {
		/** 复选按钮 */
		ImageView choiceRbIv;
		TextView itemTv;
	}
	public class DialogConfig {
		/** 对话框标题*/
		public String title = "温馨提示";
		/** 对话框提示信息*/
		public String message = "暂无信息";
		/** 对话框确定按钮文案*/
		public String positive = "确定";
		/** 对话框取消按钮文案*/
		public String negative = "取消";
		/** 是否只有一个确定按钮,默认为1个按钮*/
		public boolean isOneButton = true;
		/** back按键是否可取消对话框 ，默认可以取消对话框*/
		public boolean isCancleDialog = true;
	}
}