package com.sglj.fbf.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sglj.fbf.exception.SystemException;



/**
 * 文件处理处理工具类
 * 
 * @author guanhongwei
 */
public class FileUtil {

	public static final String DEFAULT_CHARSET = "UTF-8";

	public static void write(byte[] data, OutputStream os) {
		try {
			os.write(data);
		} catch (IOException e) {
			throw new SystemException(e);
		}
	}

	public static void write(String data, OutputStream os) {
		try {
			os.write(data.getBytes(DEFAULT_CHARSET));
		} catch (IOException e) {
			throw new SystemException(e);
		}
	}

	public static void write(File file, OutputStream os) {
		try {
			byte[] data = FileUtils.readFileToByteArray(file);
			os.write(data);
		} catch (IOException e) {
			throw new SystemException(e);
		}
	}
	
	/**
	 * 将图片保存到指定文件夹下
	 * @param file
	 * @param request
	 * @return
	 */
	public static String uploadFile(MultipartFile file,String fileName,String filePath, HttpServletRequest request) {
		if(file.isEmpty()) {
			return null;
		}
		if (StringUtils.isBlank(fileName)) {
			fileName = UUID.randomUUID().toString()+".jpg";
		}
		System.out.println(fileName);
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(filePath + fileName);
			outputStream.write(file.getBytes());
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

}
