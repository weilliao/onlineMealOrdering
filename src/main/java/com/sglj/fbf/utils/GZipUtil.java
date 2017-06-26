package com.sglj.fbf.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * GZip压缩与解压缩处理工具类
 * 
 * GZip是GNUzip的缩写，它是一个GNU自由软件的文件压缩程序。我们在Linux中经常会用到后缀为.gz的文件，它们就是GZIP格式的。
 * 
 * @author guanhongwei
 * 
 */
public class GZipUtil {

	private static final int BUFFER = 1024 * 8;

	public static String compress(String str) throws IOException {
		if (str == null || str.length() == 0) {
			return str;
		}

		ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		compress(bais, baos);

		// ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// GZIPOutputStream gos = new GZIPOutputStream(baos);
		// gos.write(str.getBytes());
		// gos.close();

		return baos.toString("ISO-8859-1");
	}

	public static String decompress(String str) throws IOException {
		if (str == null || str.length() == 0) {
			return str;
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));

		decompress(bais, baos);

		return baos.toString();
	}

	public static void compress(InputStream is, OutputStream os) throws IOException {
		GZIPOutputStream gos = new GZIPOutputStream(os);

		int readLen;
		byte buffer[] = new byte[BUFFER];
		while ((readLen = is.read(buffer, 0, BUFFER)) != -1) {
			gos.write(buffer, 0, readLen);
		}

		gos.finish();
		gos.flush();

		gos.close();
	}

	public static void decompress(InputStream is, OutputStream os) throws IOException {
		GZIPInputStream gis = new GZIPInputStream(is);

		byte buffer[] = new byte[BUFFER];
		int readLen;
		while ((readLen = gis.read(buffer, 0, BUFFER)) != -1) {
			os.write(buffer, 0, readLen);
		}

		gis.close();
	}

	public static void main(String[] args) throws IOException {
		String str = GZipUtil.compress("sdfaef3439g到都是re9gerwg");

		System.out.println(GZipUtil.decompress(str));
		
		String localPath = "/opt/bill99/reconciliation";
		String prefix = "2014-07-03";

		FileInputStream fis = new FileInputStream(new File(localPath, prefix + ".csv"));
		FileOutputStream fos = new FileOutputStream(new File(localPath, prefix + ".zip"));
		GZipUtil.compress(fis, fos);
	}
}
