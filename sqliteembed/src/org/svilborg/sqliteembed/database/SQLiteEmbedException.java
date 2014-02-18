package org.svilborg.sqliteembed.database;

/**
 * SQLiteEmbed Exception
 * 
 * @author svilborg
 */
public class SQLiteEmbedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7894522212871L;

	/**
	 * Constructs a new SQLiteEmbedException that includes the current stack
	 * trace.
	 */
	public SQLiteEmbedException() {
		super();
	}

	/**
	 * Constructs a new SQLiteEmbedException with the current stack trace and
	 * the specified detail message.
	 * 
	 * @param detailMessage
	 */
	public SQLiteEmbedException(String detailMessage) {
		super(detailMessage);
	}

}