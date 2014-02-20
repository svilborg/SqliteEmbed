package org.svilborg.sqliteembed.database.helpers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.svilborg.sqliteembed.utils.Logger;
import org.svilborg.sqliteembed.utils.SqlParser;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class SQLiteEmbedHelper extends SQLiteOpenHelper {

	static final String TAG = "SQLiteEmbedHelper";
	protected Context currentContext;
	protected String dbName;
	protected String dbPath;

	/**
	 * Create a helper object to create, open, and/or manage a database.
	 * 
	 * @param context
	 * @param name
	 * @param path
	 * @param factory
	 * @param version
	 */
	public SQLiteEmbedHelper(Context context, String name, String path, CursorFactory factory, int version) {
		super(context, name, factory, version);

		init(context, name, path);
	}

	/**
	 * Create a helper object to create, open, and/or manage a database.
	 * 
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 * @param errorHandler
	 */
	// public SQLiteEmbedHelper(Context context, String name, String path,
	// CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
	// super(context, name, factory, version, errorHandler);
	// init(context, name, path);
	// }

	/**
	 * Post Construction Inits
	 * 
	 * @param context
	 * @param name
	 * @param path
	 */
	private void init(Context context, String name, String path) {
		currentContext = context;
		dbName = name;

		if (path != null) {
			dbPath = path;
		} else {
			dbPath = context.getDatabasePath(name).getAbsolutePath();
		}

		Logger.i(TAG, "Database DB PATH - " + dbPath);

		this.setDatabasePath(dbPath);
	}

	public String getDatabasePath() {
		return dbPath;
	}

	/**
	 * For older apis
	 */
	public String getDatabaseName() {
		return dbName;
	}

	public void setDatabasePath(String dbPath) {
		this.dbPath = dbPath;
	}

	public String getDatabaseFullPath() {
		Logger.i(TAG, "Database FULL PATH - " + this.getDatabasePath() + dbName);
		return this.getDatabasePath() + dbName;
	}

	/**
	 * Check if the database already exists
	 * 
	 * @return true if it exists, false otherwise
	 */
	public boolean existsDatabase() {

		Logger.i(TAG, "Check if Database exists");

		SQLiteDatabase checkDbHandle = null;

		try {
			checkDbHandle = SQLiteDatabase.openDatabase(this.getDatabaseFullPath(), null, SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {
			Logger.i(TAG, "Database does not exist");
		}

		if (checkDbHandle != null) {
			Logger.i(TAG, "Database does exist");
			checkDbHandle.close();
		}

		return checkDbHandle != null ? true : false;
	}

	/**
	 * Check if DB File exists
	 * 
	 * @return
	 */
	public boolean existsDatabaseFile() {
		Logger.i(TAG, "Check if Database File exists");

		File dbFile = currentContext.getDatabasePath(dbName);

		if (dbFile.exists()) {
			Logger.i(TAG, "Database File does exist - " + dbName);
			return true;
		} else {
			Logger.i(TAG, "Database File does not exist");
			return false;
		}
	}
	
	
	public void parseSqlFile(String file) {

		try {
			InputStream is = currentContext.getAssets().open(file);

			SqlParser parser = new SqlParser(is);
			String[] sql = parser.getSql();

			for (String string : sql) {
				Logger.i("SQL Line", string);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void execSqlFile(SQLiteDatabase db, String file) {
		// SQLiteDatabase db = this.getReadableDatabase();

		try {
			InputStream is = currentContext.getAssets().open(file);

			SqlParser parser = new SqlParser(is);
			String[] sql = parser.getSql();

			for (String sqlStmt : sql) {
				Logger.i("SQL Line", sqlStmt);

				db.execSQL(sqlStmt);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}