package org.svilborg.sqliteembed.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.svilborg.sqliteembed.utils.FileUtils;
import org.svilborg.sqliteembed.utils.Logger;

import android.content.Context;

/**
 * Imports a prepared Sqlite db file situated in /assets folder
 * 
 * 
 */
public class SQLiteEmbedImporter {

	private Context context;
	private static final String TAG = "SQLiteEmbedImporter";

	private String databaseName;
	private String databaseFullPath;

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param dbHandle
	 */
	public SQLiteEmbedImporter(Context context, String databaseName, String databaseFullPath) {
		this.context = context;
		this.databaseName = databaseName;
		this.databaseFullPath = databaseFullPath;
	}

	/**
	 * Create a database and import the data
	 * 
	 * @throws SQLiteEmbedException
	 * */
	public void importDatabase() throws SQLiteEmbedException {

		Logger.i(TAG, "Import Database");

		deleteDatabase();

		try {
			copyDatabase();
		} catch (IOException e) {
			Logger.e(TAG, "Db Creation Error  - " + e.getMessage());

			throw new SQLiteEmbedException("Db Creation Error - Error copying database");
		}

	}

	/**
	 * Create a database and import the data
	 * 
	 * @throws SQLiteEmbedException
	 * */
	public void importDatabaseIfMissing() throws IOException, SQLiteEmbedException {

		Logger.i(TAG, "Import Database");

		File file = new File(databaseFullPath);

		boolean existsFile = file.exists();

		if (existsFile) {
			Logger.i(TAG, "Import Database - Database File Exists");
		} else {
			Logger.i(TAG, "Import Database - Database File Does NOT Exist");

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

		Logger.i(TAG, "Open assets file " + databaseName);
		Logger.i(TAG, "Open output file " + databaseFullPath);

		InputStream inputStream = context.getAssets().open(databaseName);
		OutputStream outputStream = new FileOutputStream(databaseFullPath);

		Logger.i(TAG, "Opened output file");

		FileUtils.copyFile(inputStream, outputStream);

		Logger.i(TAG, "Copied Database");
	}

	/**
	 * Deletes existing Database File
	 */
	private void deleteDatabase() {

		Logger.i(TAG, "Delete DB file");
		try {
			File file = new File(databaseFullPath);

			if (file.exists()) {
				Logger.i(TAG, "Delete DB file - File Exists");
				file.delete();
			}
		} catch (Exception ex) {
			Logger.e(TAG, "Delete DB file Error " + ex.getMessage());
		}
	}
}