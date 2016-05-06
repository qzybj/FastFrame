package com.frame.fastframelibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import com.frame.fastframelibrary.FastApplication;
import java.io.File;

/** App相关工具类*/
public class AppUtils {
    /**
     * 获取包名
     * @return
     */
    public static String getPackageName(){
            return FastApplication.getInstance().getPackageName();
    }
    /**
     * 获取当前应用版本号名称
     * @return 版本名称
     */
    public static String getVersionName() {
        return getVersionName(FastApplication.getInstance(),getPackageName());
    }

    /**
	 * 获取软件的版本名称
	 * 
	 * @param context
	 * @param packageName
	 * @return
	 */
	public static String getVersionName(Context context, String packageName) {
		String versionName = "";
		try {
			versionName =  context.getPackageManager().getPackageInfo(packageName, 0).versionName;
			if (StringUtils.isEmpty(versionName)) {
				return "";
			}
		} catch (NameNotFoundException e) {
			 LogUtils.e("获取软件的版本名称："+e.getMessage());
		}
		return versionName;
	}
	
	
	 /**
     * 获取当前应用版本号
     * @return 版本名称
     */
    public static int getVersionCode() {
        return getVersionCode(FastApplication.getInstance(),getPackageName());
    }
    
	/**
	 * 获取软件的版本号
	 * 
	 * @param context
	 * @param packageName
	 * @return
	 */
	public static int getVersionCode(Context context, String packageName) {
		try {
			return context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
		} catch (NameNotFoundException e) {
			LogUtils.e("获取软件的版本号："+e.getMessage());
		}
		return 1;
	}
	
    
    /**
     * 获取应用名称
     * @return 应用名称
     */
    public  static String getApplicationName() {
    	String applicationName = "";
		try {
			PackageManager pm = FastApplication.getInstance().getPackageManager();
			PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
			applicationName = pi.applicationInfo.loadLabel(pm).toString(); 
		} catch (NameNotFoundException e) {
			LogUtils.e("获取应用名称: "+e.getMessage());
		}
		return applicationName;
    }

    /**
     * 获取应用缓存路径
     * @return
     */
	public static File getExternalCacheDir() {
		if (Build.VERSION.SDK_INT > 7) {
			File dir = FastApplication.getInstance().getExternalCacheDir();
			return dir;
		} else {
			File esd = Environment.getExternalStorageDirectory();
			if (esd != null) {
				return new File(esd, "/Android/data/" + getPackageName()+ "/cache");
			} else {
				return null;
			}
		}
	}

    /**
     * 
     * 判断桌面是否已添加快捷方式
     * @return
     */
    public static boolean shortcut4Exist() {
        boolean result = false;
        String title = null;
        try {
            PackageManager pm = FastApplication.getInstance().getPackageManager();
            title = pm.getApplicationLabel(pm.getApplicationInfo(getPackageName(),PackageManager.GET_META_DATA)).toString();
        } catch (Exception e) {
            LogUtils.e(e);
        }

        final String uriStr;
        if (Build.VERSION.SDK_INT < 8) {
            uriStr = "content://com.android.launcher.settings/favorites?notify=true";
        } else {
            uriStr = "content://com.android.launcher2.settings/favorites?notify=true";
        }
        final Uri CONTENT_URI = Uri.parse(uriStr);
        final Cursor c = FastApplication.getInstance().getContentResolver().query(CONTENT_URI, null,"title=?", new String[] { title }, null);
        if (c != null && c.getCount() > 0) {
            result = true;
        }
        return result;
    }
    /**
     *
     * 为当前应用添加桌面快捷方式
     * @param iconResId 图片资源id
     */
    public static void shortcut4Add(int iconResId) {
        if (iconResId>0){
            try {
                Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
                Intent shortcutIntent =
                        FastApplication.getInstance().getPackageManager().getLaunchIntentForPackage(getPackageName());
                shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
                String title = null;
                try {
                    PackageManager pm = FastApplication.getInstance().getPackageManager();
                    title = pm.getApplicationLabel(pm.getApplicationInfo(getPackageName(),PackageManager.GET_META_DATA)).toString();
                } catch (Exception e) {
                    LogUtils.e(e);
                }
                // 快捷方式名称
                shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
                // 不允许重复创建（不一定有效）
                shortcut.putExtra("duplicate", false);
                // 快捷方式的图标
                Parcelable iconResource = Intent.ShortcutIconResource.fromContext(FastApplication.getInstance(), iconResId);
                shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource);
                FastApplication.getInstance().sendBroadcast(shortcut);
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }
    
    /**
     * 删除当前应用的桌面快捷方式
     */
    public static void shortcut4Del() {
        Intent shortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
        // 获取当前应用名称
        String title = null;
        try {
            PackageManager pm = FastApplication.getInstance().getPackageManager();
            title = pm.getApplicationLabel(pm.getApplicationInfo(getPackageName(),PackageManager.GET_META_DATA)).toString();
        } catch (Exception e) {
            LogUtils.e(e);
        }
        // 快捷方式名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
        Intent shortcutIntent = FastApplication.getInstance().getPackageManager().getLaunchIntentForPackage(getPackageName());
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        FastApplication.getInstance().sendBroadcast(shortcut);
    }
}