package org.svilborg.sqliteembed.util;

/**
 * File Utils
 * 
 * @author svilborg
 */
public class File {

	public static void copyFile(InputStream inputStream, OutputStream outputStream) {

		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, length);
		}

		outputStream.flush();
		outputStream.close();
		inputStream.close();
	}
}