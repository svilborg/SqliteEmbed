package org.svilborg.sqliteembed.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.svilborg.sqliteembed.utils.SqlParser;

import junit.framework.TestCase;

public class SqlParserTest extends TestCase {

	private FileInputStream getFileIs(String path) throws FileNotFoundException {
		File file = new File(path);
		FileInputStream fileInputStream = new FileInputStream(file);

		return fileInputStream;
	}

	public void testSqlParserNull() throws FileNotFoundException {
		try {
			SqlParser parser = new SqlParser(null);
			fail("No Exception thrown");
		} catch (NullPointerException e) {

		}
	}

	public void testGetSql1() throws FileNotFoundException {
		FileInputStream fileInputStream = getFileIs("./assets/update_0.1.sql");

		SqlParser parser = new SqlParser(fileInputStream);
		String[] sql = parser.getSql();

		assertNotNull("Not Empty SQLs", sql);
		assertTrue("Count 3", sql.length == 3);

		assertEquals("INSERT INTO 'teams' VALUES(6,'Poland', 5)", sql[0]);
		assertEquals("INSERT INTO 'teams' VALUES(7,'Estonia', 2)", sql[1]);
		assertEquals("INSERT INTO 'teams' VALUES(8,'Latvia', 4)", sql[2]);
	}

	public void testGetSql2() throws FileNotFoundException {
		FileInputStream fileInputStream = getFileIs("./assets/update_0.2.sql");

		SqlParser parser = new SqlParser(fileInputStream);
		String[] sql = parser.getSql();

		assertNotNull("Not Empty SQLs", sql);
		assertTrue("Count 2", sql.length == 2);

		assertEquals("INSERT INTO 'teams' VALUES(6,'Poland', 5)", sql[0]);
		assertEquals("CREATE TABLE players (\"_id\" INTEGER NOT NULL,\"name\" TEXT,\"goals\" INTEGER NOT NULL DEFAULT (0))", sql[1]);
	}
	
	public void testGetSql3() throws FileNotFoundException {
		FileInputStream fileInputStream = getFileIs("./assets/update_0.3.sql");

		SqlParser parser = new SqlParser(fileInputStream);
		String[] sql = parser.getSql();

		assertNotNull("Not Empty SQLs", sql);
		assertTrue("Count 2", sql.length == 2);

		assertEquals("INSERT INTO 'teams' VALUES(6,'Poland', 5)", sql[0]);
		assertEquals("CREATE TABLE coaches (\"_id\" INTEGER NOT NULL,\"name\" TEXT,\"years\" INTEGER NOT NULL DEFAULT (0))", sql[1]);
	}
}
