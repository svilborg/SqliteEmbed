package org.svilborg.sqliteembed.database.helpers;

import org.svilborg.sqliteembed.database.SQLiteEmbedException;
import org.svilborg.sqliteembed.database.SQLiteEmbedImporter;
import org.svilborg.sqliteembed.utils.Logger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * SQLite DB Handler
 * 
 */
public class SQLiteEmbedImportHelper extends SQLiteEmbedHelper {

	// private SQLiteDatabase db;
	static final String TAG = "SQLiteEmbedImportHelper";
	private boolean isOnCreate = false;
	private boolean isOnUpdate = false;

	public SQLiteEmbedImportHelper(Context context, String name, String path, CursorFactory factory, int version) {
		super(context, name, path, factory, version);

		// init();
	}

	@Override
	public synchronized void close() {

		Logger.i(TAG, "Database close");

		super.close();
	}

	public void init() {
		Logger.i(TAG, "ON INIT");
		
		// create emty db
		getWritableDatabase();

		if (isOnCreate) {
			close();

			SQLiteEmbedImporter importer = new SQLiteEmbedImporter(currentContext, this.getDatabaseName(), this.getDatabaseFullPath());

			try {
				importer.importDatabase();
			} catch (SQLiteEmbedException e) {
				Logger.i(TAG, "ON CREATE EXCEPTION " + e.getMessage());
				e.printStackTrace();
			}

			getWritableDatabase().close();
		}
		else if (isOnUpdate) {
			// TODO
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Logger.i(TAG, "ON CREATE");

		isOnCreate = true;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Logger.i(TAG, "ON UPDATE");

		isOnUpdate = true;
	}

	@Override
	public final void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// suppressed
	}

	@Override
	public final void onConfigure(SQLiteDatabase db) {
		// suppressed
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