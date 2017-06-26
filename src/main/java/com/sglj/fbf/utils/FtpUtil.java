package com.sglj.fbf.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * FTP文件上传下载工具类
 * 
 * @author guanhongwei
 * 
 */
public class FtpUtil {

	private static final Log logger = LogFactory.getLog(FtpUtil.class);

	/**
	 * 目录文件下载
	 * 
	 * @param host
	 *            FTP服务器IP地址
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param remotePath
	 *            FTP服务器上的相对路径
	 * @param localPath
	 *            本地服务器上的相对路径
	 * @return
	 */
	public static boolean download(String host, int port, String username, String password, String remotePath, String localPath, boolean isBackup) {
		boolean result = false;
		FTPClient client = new FTPClient();
		// client.setControlEncoding("UTF-8");
		client.setConnectTimeout(5000);

		try {
			client.connect(host, port);

			int replyCode = client.getReplyCode(); // 获取Ftp登录应答代码
			// 验证是否连接成功
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				client.disconnect();
				logger.error("FTP server refused connection.");
				return result;
			}

			client.login(username, password);
			client.setFileType(FTP.BINARY_FILE_TYPE); // 设置文件传输类型为二进制

			// 转移到FTP服务器目录至指定的目录下
			if (StringUtils.isNotEmpty(remotePath)) {
				client.changeWorkingDirectory(remotePath);
			}

			FTPFile[] files = client.listFiles();
			if (files.length == 0) {
				return result;
			}

			for (FTPFile ftpFile : files) {
				if (ftpFile.isFile()) {
					FileOutputStream fos = new FileOutputStream(localPath + "/" + ftpFile.getName());
					result = client.retrieveFile(ftpFile.getName(), fos);
					fos.close();

					if (result && isBackup) {
						client.rename(remotePath + "/" + ftpFile.getName(), remotePath + "/backup/" + ftpFile.getName());
					}
				}
			}

			result = true;

			client.logout();

		} catch (IOException e) {
			logger.error(e);
		} finally {
			if (client.isConnected()) {
				try {
					client.disconnect();
				} catch (IOException ioe) {
				}
			}
		}

		return result;
	}

	/**
	 * 文件下载
	 * 
	 * @param host
	 *            FTP服务器IP地址
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param remotePath
	 *            FTP服务器上的相对路径
	 * @param fileName
	 *            要下载的文件名
	 * @param os
	 *            本地文件输出流
	 * @return
	 */
	public static boolean download(String host, int port, String username, String password, String remotePath, String fileName, OutputStream os) {
		boolean result = false;
		FTPClient client = new FTPClient();

		try {
			client.connect(host, port);

			int replyCode = client.getReplyCode(); // 获取Ftp登录应答代码
			// 验证是否连接成功
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				client.disconnect();
				logger.error("FTP server refused connection.");
				return result;
			}

			client.login(username, password);
			client.setFileType(FTP.BINARY_FILE_TYPE); // 设置文件传输类型为二进制

			// 转移到FTP服务器目录至指定的目录下
			if (StringUtils.isNotEmpty(remotePath)) {
				client.changeWorkingDirectory(remotePath);
			}

			FTPFile[] files = client.listFiles();
			for (FTPFile ftpFile : files) {
				if (ftpFile.getName().equals(fileName)) {
					result = client.retrieveFile(fileName, os);
					break;
				}
			}

			os.close();
			client.logout();

		} catch (IOException e) {
			logger.error(e);
		} finally {
			if (client.isConnected()) {
				try {
					client.disconnect();
				} catch (IOException ioe) {
				}
			}
		}

		return result;
	}

	/**
	 * 文件上传
	 * 
	 * @param host
	 *            FTP服务器IP地址
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param remotePath
	 *            FTP服务器保存目录,如果是根目录则为“/”
	 * @param fileName
	 *            上传到FTP服务器上的文件的名称
	 * @param is
	 *            本地文件输入流
	 * @return
	 */
	public static boolean upload(String host, int port, String username, String password, String remotePath, String fileName, InputStream is) {
		boolean result = false;
		FTPClient client = new FTPClient();

		try {
			client.connect(host, port);

			int replyCode = client.getReplyCode(); // 获取Ftp登录应答代码
			// 验证是否连接成功
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				client.disconnect();
				logger.error("FTP server refused connection.");
				return result;
			}

			client.login(username, password);
			client.setFileType(FTP.BINARY_FILE_TYPE); // 设置传送模式

			// 转移到FTP服务器目录至指定的目录下
			if (StringUtils.isNotEmpty(remotePath)) {
				client.changeWorkingDirectory(remotePath);
			}

			result = client.storeFile(fileName, is);

			client.logout();

		} catch (IOException e) {
			logger.error(e);
		} finally {
			if (client.isConnected()) {
				try {
					client.disconnect();
				} catch (IOException ioe) {
				}
			}
		}

		return result;
	}

	public static void main(String[] args) throws SocketException, IOException {
		String fileName = "test.txt";
		File localFile = new File("/opt/B2B/" + fileName);
		OutputStream os = new FileOutputStream(localFile);

		FtpUtil.download("192.168.255.100", 21, "ebftp", "test@123", "/var/ftp/pub", fileName, os);
	}
}
