package org.svilborg.sqliteembed.database;

import java.io.File;

import org.svilborg.sqliteembed.util.Logger;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLite DB Handler
 * 
 * @author svilborg
 */
public class SQLiteEmbedOpenHelper extends SQLiteOpenHelper {

	private SQLiteDatabase db;
	private String dbPath;
	private static final String TAG = "SQLiteEmbedOpenHelper";
	private Context currentContext;

	public SQLiteEmbedOpenHelper(Context context, String name, String path, CursorFactory factory, int version) {
		super(context, name, factory, version);

		currentContext = context;

		String absPath = context.getDatabasePath(name).getAbsolutePath();

		Logger.i(TAG, "Database ABS PATH - " + absPath);

		this.setDatabasePath(path);
	}

	public String getDatabasePath() {
		return dbPath;
	}

	public void setDatabasePath(String dbPath) {
		this.dbPath = dbPath;
	}

	public String getDatabaseFullPath() {
		Logger.i(TAG, "Database FULL PATH - " + this.getDatabasePath() + this.getDatabaseName());
		return this.getDatabasePath() + this.getDatabaseName();
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
		
		File dbFile = currentContext.getDatabasePath(getDatabaseName());

		if (dbFile.exists()) {
			Logger.i(TAG, "Database File does exist");
			return true;
		}
		else { 
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
		// TODO: Write routines to remove and overwrite the database on an
		// upgrade
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
	
	public Cursor showAllTables(){
        String mySql = " SELECT name FROM sqlite_master " + " WHERE type='table'";
       // Cursor cursor = db.rawQuery(mySql, null);
        return db.rawQuery(mySql, null);
    }

}
