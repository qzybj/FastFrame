package com.frame.volleypackageframe.module.product.moduleview;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.PopupWindow;
import com.frame.volleypackageframe.R;
import com.frame.volleypackageframe.common.util.FileUtils;
import com.frame.volleypackageframe.module.product.adapter.GridViewPictureAdapter;
import com.frame.volleypackageframe.module.product.moduleview.bean.BaseModuleViewBean;
import com.frame.volleypackageframe.ui.base.BaseActivity;
import com.frame.volleypackageframe.utils.StringUtil;
import com.frame.volleypackageframe.utils.TestDataBuilder;
import com.frame.volleypackageframe.utils.ToastHelper;
import com.frame.volleypackageframe.view.dialog.CustomDialog;
import com.frame.volleypackageframe.view.dynamicgridview.DynamicGridView;
import org.xutils.view.annotation.ViewInject;

import java.io.File;

/**模块化View - 图片管理View*/
public class PictureModuleView extends BaseModuleView {
	/**从图库获取图片*/
	public final int REQUEST_CODE_LOCAL = 0x005001;
	/**照相获取图片返回	 */
	public final int REQUEST_CODE_CAMERA = 0x005002;
	/**图片选择后返回*/
	public final int RESULT_CHOOSE_PICTURE = 0x005003;

	public final static String IMAGE_SAVE_PATH = "tmpImage";
	private PopupWindow mPopupWindow;
	/**上传图片最大分辨率限制*/
	public final int maxResolution= 1600;
	private File cameraFile;
	private View popwindowView;

	@ViewInject(R.id.dynamic_gv_picture)
	private DynamicGridView dgv_picture;
	private GridViewPictureAdapter mAdapter;

	public PictureModuleView(Activity context, ModuleViewType type){
		super(context,type);
	}

	@Override
	public int getLayoutResouceId() {
		return R.layout.moduleview_picture;
	}

	@Override
	public void initContentView(View mRootView) {
		//body_sv = (ScrollView)mRootView.findViewById(R.id.goodsreturned_body_sv);

	}

	@Override
	public void refreshData(BaseModuleViewBean bean) {

	}

	/**
	 * 初始化图片 GridView
	 */
	private void initGridViewDatas()
	{
		mAdapter = new GridViewPictureAdapter(getModuleViewContext(), TestDataBuilder.getImageBeanList(),4);
//		mAdapter.showIndeterminateProgress(true);
		dgv_picture.setAdapter(mAdapter);// 设置适配器
		dgv_picture.setOnItemClickListener(
				new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View v, int position, long id){
						hideleSoftInput();
						//第一张为默认图片
//						if(position == 0) { //点击图片位置为+ 0对应0张图片，(添加图片改为第一个显示)
						if(position == (parent.getCount()-1)) { //点击图片位置为+ 0对应0张图片(添加图片改为最后一个显示)
							if (parent.getCount()<6) {
								if (getModuleViewContext() instanceof BaseActivity) {
									//选择图片
									showBottomPopwindow(position);
								}
							}else {
								//ToastHelper.showToast(getModuleViewContext(),"");
							}
						}else {
							delDialog(position);
						}
					}
				});
	}
	//隐藏软键盘
	protected void hideleSoftInput() {
		InputMethodManager imm = (InputMethodManager)getModuleViewContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(getRootView().getWindowToken(), 0); //强制隐藏键盘
	}

	public void setOnActivityResult(int requestCode, int resultCode, Intent data){
		if(resultCode== Activity.RESULT_OK) {//打开图片
			if (requestCode==REQUEST_CODE_LOCAL) {
				if (data == null) {
					return;
				}
				Uri uri = data.getData();
				if (!TextUtils.isEmpty(uri.getAuthority())) {
					//查询选择图片
					Cursor cursor =getModuleViewContext().getContentResolver().
							query(uri,new String[] { MediaStore.Images.Media.DATA },null,null,null);
					//返回 没找到选择图片
					if (null == cursor) {
						return;
					}
					//光标移动至开头 获取图片路径
					cursor.moveToFirst();
					String pathImage = cursor.getString(cursor
							.getColumnIndex(MediaStore.Images.Media.DATA));
					cursor.close();
					//refreshPictureUI(pathImage);
					uploadImgData(pathImage);
				}
			}else if (requestCode == REQUEST_CODE_CAMERA) { // 发送照片
				if (cameraFile != null && cameraFile.exists()){
					String pathImage = cameraFile.getAbsolutePath();
					//refreshPictureUI(pathImage);
					uploadImgData(pathImage);
				}
			} else {

			}
		}
	}

	/**
	 * Dialog对话框提示用户删除操作
	 * @param position 为删除图片位置
	 */
	protected void delDialog(final int position) {
		new CustomDialog.Builder(getModuleViewContext()).setMessage(R.string.sure_del_photo)
				.setTitle(R.string.reminder)
				.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						//delUpdateImgUrl(position);
						mAdapter.remove(position);
						mAdapter.notifyDataSetChanged();
					}
				})
				.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).show();
	}
	/**显示底部弹出窗口
	 * @param position */
	private void showBottomPopwindow(final int position) {
		if (getModuleViewContext() instanceof BaseActivity) {
			BaseActivity activity = (BaseActivity)getModuleViewContext();
			if (mPopupWindow == null) {
				LayoutInflater mLayoutInflater = LayoutInflater.from(getModuleViewContext());
				popwindowView = mLayoutInflater.inflate(R.layout.popwindow_menu_bottom, null);
				Button btn_camera_video = (Button) popwindowView.findViewById(R.id.bottom_popmenu_btn_1);
				btn_camera_video.setText(R.string.take_photos);
				btn_camera_video.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						mPopupWindow.dismiss();
						selectPicFromCamera();
					}
				});
				Button btn_select_video = (Button) popwindowView.findViewById(R.id.bottom_popmenu_btn_2);
				btn_select_video.setText(R.string.from_gallery);
				btn_select_video.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						mPopupWindow.dismiss();
						selectPicFromLocal();
					}
				});
				popwindowView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						if (mPopupWindow != null) {
							//当点击popupwindow的背景时，关闭
							mPopupWindow.dismiss();
						}
					}
				});
				Button btn_cancel = (Button) popwindowView.findViewById(R.id.bottom_popmenu_btn_cancel);
				btn_cancel.setText(R.string.cancel);
				btn_cancel.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						mPopupWindow.dismiss();
					}
				});
				mPopupWindow = new PopupWindow(popwindowView,
						activity.getWindowManager().getDefaultDisplay().getWidth(),
						activity.getWindowManager().getDefaultDisplay().getHeight());
			}
			mPopupWindow.setAnimationStyle(R.style.style_popwindow_animation);
			//实例化一个ColorDrawable颜色为半透明
			ColorDrawable dw = new ColorDrawable(0x88000000);
			//设置SelectPicPopupWindow弹出窗体的背景
			mPopupWindow.setBackgroundDrawable(dw);
			mPopupWindow.setOutsideTouchable(true);
			mPopupWindow.setFocusable(true);
			mPopupWindow.showAtLocation(popwindowView, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
		}
	}

	/**照相获取图片*/
	public void selectPicFromCamera() {
		cameraFile = new File(FileUtils.getDiskCacheDir(getModuleViewContext(), IMAGE_SAVE_PATH),
				"camera"+ System.currentTimeMillis() + ".jpg");
		cameraFile.getParentFile().mkdirs();
		((BaseActivity)getModuleViewContext()).startActivityForResult(
				new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraFile)),
				REQUEST_CODE_CAMERA);
	}

	/*** 从图库获取图片*/
	public void selectPicFromLocal() {
		Intent intent;
		if (Build.VERSION.SDK_INT < 19) {
			intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("image/*");
		} else {
			intent = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		}
		((BaseActivity)getModuleViewContext()).startActivityForResult(intent, REQUEST_CODE_LOCAL);
	}

	/**上传图片请求*/
	private void uploadImgData(String imgPath){
		if (!StringUtil.isBlank(imgPath)) {
//			String  compressBitmapPath = compressBitmap(imgPath);
//			if (!StringUtil.isBlank(compressBitmapPath)) {
//				byte[] bytes = SDCardUtils.File2byte(compressBitmapPath);
//				if (bytes!=null) {
//					tmpPathImage = null;
//					UploadImageRequest request = new UploadImageRequest();
//					request.imgbyte = Base64.encodeToString(bytes, 0, bytes.length,Base64.DEFAULT);
//					YTLog.d("" + request.imgbyte);
//					//SDCardUtils.write2Disk(mBaseContext, "imagestore", request.imgbyte);
//					//request.imgbyte = bytes;
//					request.setShowLoading(true);
//					DataServer.asyncGetData(request,UploadImageResponse.class, basicHandler);
//					deleteTempImg(compressBitmapPath);
//					tmpPathImage = imgPath;
//				}
//			}
		}
	}

}