package org.svilborg.sqliteembed.utils;

import android.util.Log;

/**
 * Custom Android Logger
 * 
 * 
 */
public class Logger {

	public static void d(String tag, String msg) {
		if (Log.isLoggable(tag, Log.DEBUG)) {
			Log.d(tag, msg);
		}
	}

	public static void d(String tag, String msg, Throwable tr) {
		if (Log.isLoggable(tag, Log.DEBUG)) {
			Log.d(tag, msg, tr);
		}
	}

	public static void i(String tag, String msg) {
		if (Log.isLoggable(tag, Log.INFO)) {
			Log.i(tag, msg);
		}
	}

	public static void i(String tag, String msg, Throwable tr) {
		if (Log.isLoggable(tag, Log.INFO)) {
			Log.i(tag, msg, tr);
		}
	}

	public static void e(String tag, String msg) {
		if (Log.isLoggable(tag, Log.ERROR)) {
			Log.e(tag, msg);
		}
	}

	public static void e(String tag, String msg, Throwable tr) {
		if (Log.isLoggable(tag, Log.ERROR)) {
			Log.e(tag, msg, tr);
		}
	}

	public static void v(String tag, String msg) {
		if (Log.isLoggable(tag, Log.VERBOSE)) {
			Log.v(tag, msg);
		}
	}

	public static void v(String tag, String msg, Throwable tr) {
		if (Log.isLoggable(tag, Log.VERBOSE)) {
			Log.v(tag, msg, tr);
		}
	}

	public static void w(String tag, String msg) {
		if (Log.isLoggable(tag, Log.WARN)) {
			Log.w(tag, msg);
		}
	}

	public static void w(String tag, String msg, Throwable tr) {
		if (Log.isLoggable(tag, Log.WARN)) {
			Log.w(tag, msg, tr);
		}
	}
}
