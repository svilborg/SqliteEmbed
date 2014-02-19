package org.svilborg.sqliteembed.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class Utils {

	/**
	 * Gets Application Version
	 * 
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context) {
		String version = "";

		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			version = info.versionName;
		} catch (Exception e) {
			Logger.e("Utils", "Error - cant get Application Version Name");
			version = "0";
		}

		return version;
	}

	/**
	 * Gets Application Version Code
	 * 
	 * @param context
	 * @return
	 */
	public static int getVersionCode(Context context) {
		int version = 0;

		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			version = info.versionCode;
		} catch (Exception e) {
			Logger.e("Utils", "Error - cant get Application Version Code");
		}

		return version;
	}

	/**
	 * Extracts digits from a string
	 * 
	 * @param str
	 * @return
	 */
	public static String getNumerics(String str) {
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i)))
				sb.append(str.charAt(i));
		}

		return sb.toString();
	}
}
