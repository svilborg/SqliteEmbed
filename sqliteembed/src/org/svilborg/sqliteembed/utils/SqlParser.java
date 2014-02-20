package org.svilborg.sqliteembed.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Sql Parser
 * 
 */
public class SqlParser {
	private String[] sql;

	/**
	 * Constructor
	 * 
	 * @param is
	 */
	public SqlParser(InputStream is) {

		try {
			sql = parseSqlInputStream(is);
		} catch (IOException e) {
			Logger.e("SqlParser", "Error reading input" + e.getMessage());
		}
	}

	/**
	 * Returns sql statemnts
	 * 
	 * @return
	 */
	public String[] getSql() {
		return sql;
	}

	private String[] parseSqlInputStream(InputStream is) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(is));

		String[] str = parseSqlFile(in);
		
		is.close();

		return str;
	}

	private String[] parseSqlFile(BufferedReader file) throws IOException {
		String line;
		StringBuilder sql = new StringBuilder();
		String multiLineComment = null;

		while ((line = file.readLine()) != null) {
			line = line.trim();

			// Check for start of multi-line comment
			if (multiLineComment == null) {
				// Check for first multi-line comment type
				if (line.startsWith("/*")) {
					if (!line.endsWith("}")) {
						multiLineComment = "/*";
					}
					// Check for second multi-line comment type
				} else if (line.startsWith("{")) {
					if (!line.endsWith("}")) {
						multiLineComment = "{";
					}
					// Append line if line is not empty or a single line comment
				} else if (!line.startsWith("--") && !line.equals("")) {
					sql.append(line);
				} // Check for matching end comment
			} else if (multiLineComment.equals("/*")) {
				if (line.endsWith("*/")) {
					multiLineComment = null;
				}
				// Check for matching end comment
			} else if (multiLineComment.equals("{")) {
				if (line.endsWith("}")) {
					multiLineComment = null;
				}
			}

		}

		file.close();

		return sql.toString().split(";");
	}
}