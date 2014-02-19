package org.svilborg.sqliteembed.database.helpers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.svilborg.sqliteembed.utils.Logger;
import org.svilborg.sqliteembed.utils.SqlParser;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLite DB Handler
 * 
 * 
 */
public class SQLiteEmbedOpenHelper extends SQLiteOpenHelper {

	private SQLiteDatabase db;
	private String dbPath;
	private String dbName;
	private static final String TAG = "SQLiteEmbedOpenHelper";
	private Context currentContext;

	public SQLiteEmbedOpenHelper(Context context, String name, String path, CursorFactory factory, int version) {
		super(context, name, factory, version);

		currentContext = context;
		dbName = name;

		if (path != null) {
			dbPath = path;
		} else {
			dbPath = context.getDatabasePath(name).getAbsolutePath();
		}

		Logger.i(TAG, "Database ABS PATH - " + dbPath);

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

	/**
	 * Open Database
	 * 
	 * @throws SQLException
	 */
	public void openDatabase() throws SQLException {

		Logger.i(TAG, "Open the database");

		String path = this.getDatabaseFullPath();
		db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);

		this.getReadableDatabase();
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

	public boolean existsDatabaseFile() {
		Logger.i(TAG, "Check if Database File exists");

		File dbFile = currentContext.getDatabasePath(dbName);

		if (dbFile.exists()) {
			Logger.i(TAG, "Database File does exist");
			return true;
		} else {
			Logger.i(TAG, "Database File does not exist");
			return false;
		}
	}

	@Override
	public synchronized void close() {

		Logger.i(TAG, "Database close");

		if (db != null) {
			db.close();
		}

		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	/**
	 * Called everytime the database is opened by getReadableDatabase /
	 * getWritableDatabase.
	 */
	@Override
	public void onOpen(SQLiteDatabase db) {
		Logger.i(TAG, "Database open");

		super.onOpen(db);
	}
}