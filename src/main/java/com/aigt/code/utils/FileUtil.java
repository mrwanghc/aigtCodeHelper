package com.aigt.code.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.MessageFormat;

/**
 * 
 * @author whc
 *
 */
public class FileUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * 增加文件是否存在判断的删除
	 * 
	 * @param file
	 */
	public static boolean delete(File file) {
		if (file.exists()) {
			return file.delete();
		}
		return true;
	}

	/**
	 * 转换文件大小
	 * 
	 * @param fileSize
	 * @return
	 */
	public static String formatFileSize(long fileSize) {
		DecimalFormat df = new DecimalFormat("#.00");
		String strFileSize = "";
		char unit = ' ';
		if (fileSize < 1024) {
			strFileSize = String.valueOf(fileSize);
		} else if (fileSize < 1048576) {
			strFileSize = df.format((double) fileSize / 1024);
			unit = 'K';
		} else if (fileSize < 1073741824) {
			strFileSize = df.format((double) fileSize / 1048576);
			unit = 'M';
		} else {
			strFileSize = df.format((double) fileSize / 1073741824);
			unit = 'G';
		}
		return MessageFormat.format("{0} {1}B", strFileSize, unit);
	}

	/**
	 * 递归求取目录文件个数
	 * 
	 * @param fileOrDirectory
	 * @return
	 */
	public static long getFileCount(File fileOrDirectory) {
		long size = 0;
		File fileList[] = fileOrDirectory.listFiles();
		size = fileList.length;
		for (File file : fileList) {
			if (file.isDirectory()) {
				size = size + getFileCount(file);
				size--;
			}
		}
		return size;
	}

	/**
	 * 取得文件夹大小
	 * 
	 * @param fileOrDirectory
	 * @return
	 * @throws Exception
	 */
	public static long getFileSize(File fileOrDirectory) {
		long size = 0;
		File fileList[] = fileOrDirectory.listFiles();
		for (File file : fileList) {
			if (file.isDirectory()) {
				size = size + getFileSize(file);
			} else {
				size = size + file.length();
			}
		}
		return size;
	}

	/**
	 * 递归求取目录文件个数和文件夹大小
	 * 
	 * @param fileOrDirectory
	 * @return [0]为文件个数；[1]为文件大小
	 */
	public static long[] getFileCountAndSize(File fileOrDirectory,
			long[] countAndSize) {
		File fileList[] = fileOrDirectory.listFiles();
		for (File file : fileList) {
			if (file.isDirectory()) {
				getFileCountAndSize(file, countAndSize);
			} else {
				countAndSize[0] += 1;
				countAndSize[1] += file.length();
			}
		}
		return countAndSize;
	}

	public static void main(String args[]) {
		String dir = "D:\\svn\\cybershop20150130\\cybershop\\cybershop-web\\src\\main\\webapp\\assets\\upload";
		long[] countAndSize = new long[2];
		FileUtil.getFileCountAndSize(new File(dir), countAndSize);
		System.out.println("fileNum:" + countAndSize[0] + "fileSize:"
				+ countAndSize[1]);
	}

	/**
	 * 使用Stream和Byte数组拷贝文件<br>
	 * 步骤1 新建文件输入流并对它进行缓冲;<br>
	 * 步骤2 新建文件输出流并对它进行缓冲;<br>
	 * 步骤3 从输入流读入缓冲数组,然后写入输出流;<br>
	 * 步骤4 刷新输出流;<br>
	 * 步骤5 关闭流
	 * 
	 * @param sourceFile
	 * @param targetFile
	 * @throws IOException
	 */
	public static void copyFile(File sourceFile, File targetFile) {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

			byte[] b = new byte[2048];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			outBuff.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inBuff != null)
					inBuff.close();
				if (outBuff != null)
					outBuff.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
