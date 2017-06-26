package com.sglj.fbf.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author guanhongwei
 */
public class ZipUtil {

	private static Log logger = LogFactory.getLog(ZipUtil.class);

	private static final int BUFFER = 1024 * 8;

	public static void compress(File inputFile, File zipFile) throws IOException {
		List<File> fileList = getSubFiles(inputFile);
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));

		for (File file : fileList) {
			ZipEntry ze = new ZipEntry(getAbsFilePath(inputFile, file));
			ze.setSize(file.length());
			ze.setTime(file.lastModified());

			zos.putNextEntry(ze);

			InputStream is = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[BUFFER];
			int readLen = 0;
			while ((readLen = is.read(buffer, 0, BUFFER)) != -1) {
				zos.write(buffer, 0, readLen);
			}
			is.close();
		}
		zos.close();
	}

	private static List<File> getSubFiles(File baseFile) {
		List<File> files = new ArrayList<File>();

		if (baseFile.isFile()) {
			files.add(baseFile);
		} else if (baseFile.isDirectory()) {
			File[] tmp = baseFile.listFiles();
			for (File file : tmp) {
				files.addAll(getSubFiles(file));
			}
		}

		return files;
	}

	private static String getAbsFilePath(File inputFile, File file) {
		File real = file;

		String absFileName = real.getName();
		while (true) {
			real = real.getParentFile();
			if (real == null)
				break;
			if (real.equals(inputFile.getParentFile()))
				break;
			else
				absFileName = real.getName() + "/" + absFileName;
		}
		return absFileName;
	}

	public static void decompress(File outputFileDir, File zipFile) throws IOException {
		ZipFile zfile = new ZipFile(zipFile);
		Enumeration<? extends ZipEntry> zList = zfile.entries();

		while (zList.hasMoreElements()) {
			ZipEntry ze = zList.nextElement();
			logger.info(ze.getName());

			if (ze.isDirectory()) {
				File f = new File(outputFileDir.getPath()+"/" + ze.getName());
				f.mkdirs();
				continue;
			} else {
				if (!outputFileDir.exists()) {
					outputFileDir.mkdirs();
				}

				OutputStream os = new BufferedOutputStream(new FileOutputStream(new File(outputFileDir.getPath(), ze.getName())));
				InputStream is = new BufferedInputStream(zfile.getInputStream(ze));

				byte[] buffer = new byte[BUFFER];
				int readLen = 0;
				while ((readLen = is.read(buffer, 0, BUFFER)) != -1) {
					os.write(buffer, 0, readLen);
				}
				is.close();
				os.close();
			}
		}
		zfile.close();

	}

	public static void main(String[] args) throws IOException {
		String localPath = "/opt/bill99/reconciliation";

		File zipFile = new File(localPath, "2014-07-03.zip");
		File outputFileDir = new File(localPath);
		ZipUtil.decompress(outputFileDir, zipFile);

		File inputFile = new File(localPath, "2014-07-08.csv");
		zipFile = new File(localPath, "2014-07-08.zip");
		ZipUtil.compress(inputFile, zipFile);

	}

}
