package org.svilborg.sqliteembed.test;

import java.io.IOException;
import java.util.List;

import org.svilborg.sqliteembed.utils.FileUtils;
import org.svilborg.sqliteembed.utils.FileUtils.SqlFile;

import junit.framework.TestCase;

public class FUtilsTest extends TestCase {

	public FUtilsTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testListEmpty() {

		String path = "./assets/";
		String ext = "txt";

		String[] files = new String[2];
		files[0] = "test";
		files[1] = "test2";

		try {
			List<SqlFile> listFiles = FileUtils.list(files, path, ext);

			assertTrue(listFiles.isEmpty());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	}

	public void testListNonEmpty() {

		String path = "./assets/";
		String ext = "txt";

		String[] files = new String[2];
		files[0] = "test.txt";
		files[1] = "test2.sql";

		try {
			List<SqlFile> listFiles = FileUtils.list(files, path, ext);

			assertEquals(1, listFiles.size());
			assertEquals("test.txt", listFiles.get(0).name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	}
}
