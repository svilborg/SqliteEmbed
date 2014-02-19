package org.svilborg.sqliteembedsample;

import java.io.IOException;

import org.svilborg.sqliteembed.database.SQLiteEmbedException;
import org.svilborg.sqliteembed.database.SQLiteEmbedImporter;
import org.svilborg.sqliteembed.database.helpers.SQLiteEmbedOpenHelper;
import org.svilborg.sqliteembed.utils.Logger;
import org.svilborg.sqliteembed.utils.Utils;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static final String DATABASE_PATH = "/data/data/org.svilborg.sqliteembedsample/databases/";
	public static final String DATABASE_NAME = "teams.db";
	public static final int DATABASE_VERSION = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {
			testCreateDatabase();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLiteEmbedException e) {
			e.printStackTrace();
		}
	}

	private void testCreateDatabase() throws IOException, SQLiteEmbedException {

		SQLiteEmbedOpenHelper dbHandle = new SQLiteEmbedOpenHelper(this, DATABASE_NAME, DATABASE_PATH, null, DATABASE_VERSION);

		SQLiteEmbedImporter importer = new SQLiteEmbedImporter(this, dbHandle);

		importer.importDatabase();

		SQLiteDatabase db = dbHandle.getReadableDatabase();

		// List databases
		// ////////////////////////

		String mySql = " SELECT name FROM sqlite_master " + " WHERE type='table'";
		Cursor c = db.rawQuery(mySql, null);

		if (c.moveToFirst()) { 
			do {
				Logger.i("MainActivity", "|" + c.getString(0) + "|");

			} while (c.moveToNext());
		}

		// Check records
		// ////////////////////////
		Cursor cursor = db.rawQuery("SELECT * FROM `teams`", null);
		Logger.i("MainActivity", "Result: " + cursor.getCount());
		Logger.i("MainActivity", "Result: " + Utils.getVersionCode(this));
	}
}