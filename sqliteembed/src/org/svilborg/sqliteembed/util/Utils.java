package org.svilborg.sqliteembed.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class Utils {
	public static String getVersion(Context context) {
		String version = "";

		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			version = info.versionName;
		} catch (Exception e) {
			Logger.e("Utils", "Error - cant get Application Version");
			version = "0";
		}

		return version;
	}
}
