package org.svilborg.sqliteembed.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * File Utils
 * 
 * @author svilborg
 */
public class File {

	public static void copyFile(InputStream inputStream, OutputStream outputStream) throws IOException {

		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, length);
		}

		outputStream.flush();
		outputStream.close();
		inputStream.close();
	}

	public static void copyFile(String inputFile, String outputFile) throws IOException {
		copyFile(new FileInputStream(inputFile), new FileOutputStream(outputFile));
	}
}