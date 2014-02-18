package org.svilborg.sqliteembed.database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.svilborg.sqliteembed.util.Logger;

import android.content.Context;

/**
 * Imports a prepared Sqlite db file situated in /assets folder
 * 
 * @author svilborg
 */
public class SQLiteEmbedImporter {

	private Context context;
	private static final String TAG = "SQLiteEmbedImporter";

	private SQLiteEmbedOpenHelper dbHandle;

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param dbHandle
	 */
	public SQLiteEmbedImporter(Context context, SQLiteEmbedOpenHelper dbHandle) {
		this.context = context;
		this.dbHandle = dbHandle;
	}

	/**
	 * Gets Application Context
	 * 
	 * @return Context
	 */
	public Context getContext() {
		return context;
	}

	/**
	 * Sets Application Context
	 * 
	 * @param context
	 */
	public void setContext(Context context) {
		this.context = context;
	}

	/**
	 * Create a database and import the data
	 * 
	 * @throws SQLiteEmbedException
	 * */
	public void importDatabase() throws IOException, SQLiteEmbedException {

		Logger.i(TAG, "Import Database");

		boolean existsFile = dbHandle.existsDatabaseFile();

		if (existsFile) {
			Logger.i(TAG, "Import Database - Database File Exists");
		} else {
			Logger.i(TAG, "Import Database - Database File Does NOT Exist");

			dbHandle.getReadableDatabase();

			try {
				copyDatabase();
			} catch (IOException e) {
				Logger.e(TAG, "Db Creation Error  - " + e.getMessage());

				throw new SQLiteEmbedException("Db Creation Error - Error copying database");
			}
		}
	}

	/**
	 * 
	 * Copies the database from assets folder to the default database folder
	 * 
	 **/
	private void copyDatabase() throws IOException {

		Logger.i(TAG, "Copy Database");

		Logger.i(TAG, "Open assets file " + dbHandle.getDatabaseName());
		Logger.i(TAG, "Open output file " + dbHandle.getDatabaseFullPath());

		InputStream inputStream = context.getAssets().open(dbHandle.getDatabaseName());
		OutputStream outputStream = new FileOutputStream(dbHandle.getDatabaseFullPath());

		Logger.i(TAG, "Opened output file");

		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, length);
		}

		outputStream.flush();
		outputStream.close();
		inputStream.close();

		boolean existsFile = dbHandle.existsDatabaseFile();

		if (existsFile) {
			Logger.i(TAG, "Post-Import Database - Database File Exists");
		} else {
			Logger.i(TAG, "Post-Import Database - Database File Does NOT Exist");
		}

		Logger.i(TAG, "Copied Database");
	}
}