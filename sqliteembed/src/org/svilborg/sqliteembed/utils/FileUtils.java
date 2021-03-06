package org.svilborg.sqliteembed.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.res.AssetManager;

/**
 * File Utils
 * 
 */
public class FileUtils {

	/**
	 * Copy File
	 * 
	 * @param inputStream
	 * @param outputStream
	 * @throws IOException
	 */
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

	/**
	 * Copy File
	 * 
	 * @param inputFile
	 * @param outputFile
	 * @throws IOException
	 */
	public static void copyFile(String inputFile, String outputFile) throws IOException {
		copyFile(new FileInputStream(inputFile), new FileOutputStream(outputFile));
	}

	/**
	 * Copy File with Channels
	 * 
	 * @param inputFile
	 * @param outputFile
	 * @throws IOException
	 */
	public void copyFileChannel(String inputFile, String outputFile) throws IOException {
		FileInputStream inputStream = new FileInputStream(inputFile);
		FileOutputStream outputStream = new FileOutputStream(outputFile);

		FileChannel inChannel = inputStream.getChannel();
		FileChannel outChannel = outputStream.getChannel();

		inChannel.transferTo(0, inChannel.size(), outChannel);

		inputStream.close();
		outputStream.close();
	}

	/**
	 * List asset files
	 * 
	 * @param files
	 * @param path
	 *            Folder
	 * @param ext
	 *            File Extension
	 * @return
	 * @throws IOException
	 */
	public static List<SqlFile> list(String[] files, String path, String ext) throws IOException {
		// String[] files = assetManager.list(path);
		Arrays.sort(files);

		List<FileUtils.SqlFile> sqlFiles = new ArrayList<FileUtils.SqlFile>();
		for (String file : files) {
			if (file.endsWith(ext)) {
				String versionNumber = Utils.getNumerics(file);
				int version = 0;

				try {
					version = Integer.parseInt(versionNumber);
				} catch (Exception e) {
				}

				FileUtils.SqlFile x = new FileUtils().new SqlFile(file, version, "");

				sqlFiles.add(x);
			}
		}
		return sqlFiles;
	}

	/**
	 * Sql File
	 */
	public class SqlFile {
		public String name;
		public int version;
		public String size;

		/**
		 * @param name
		 *            File name
		 * @param version
		 *            File Version
		 * @param size
		 */
		public SqlFile(String name, int version, String size) {
			super();
			this.name = name;
			this.version = version;
			this.size = size;
		}
	}
}