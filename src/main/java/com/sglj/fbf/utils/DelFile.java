package com.sglj.fbf.utils;

import java.io.File;

/** * @author  wei * @date 创建时间：2016年12月2日 下午9:47:55 * @version 1.0 * @parameter  * @since  * @return  */
/**
 * 文件的删除
 * <p>Title:  </p>
 * <p>Description:  </p>
 * @author
 * @date
 */
public class DelFile {
	/**
	 * 删除指定位置的文件
	 * @param folderPath
	 */
	public static void delFile(String folderPath){
		File f = new File(folderPath);  // 输入要删除的文件位置
		if(f.exists()){
			if (f.isFile()) {
	             boolean result = f.delete();
	             if(!result)
	             {
	            	 //删除进程正在使用的文件
	            	 System.gc();
	            	 f.delete();
	             }
	        }
		}
	}
	/**
	 * 删除文件夹
	 * @param folderPath 文件夹完整绝对路径
	 */
	public static void delFolder(String folderPath) {
	     try {
	        delAllFile(folderPath); //删除完里面所有内容
	        String filePath = folderPath;
	        filePath = filePath.toString();
	        java.io.File myFilePath = new java.io.File(filePath);
	        myFilePath.delete(); //删除空文件夹
	     } catch (Exception e) {
	       e.printStackTrace(); 
	     }
	}

	//删除指定文件夹下所有文件
	//param path 文件夹完整绝对路径
	/**
	 * 删除指定文件夹下所有文件
	 * @param path 文件夹完整绝对路径
	 * @return
	 */
	public static boolean delAllFile(String path) {
		
	    boolean flag = false;
	    File file = new File(path);
	    if (!file.exists()) {
	         return flag;
	    }
	    if (!file.isDirectory()) {
	         return flag;
	    }
	    String[] tempList = file.list();
	    File temp = null;
	    for (int i = 0; i < tempList.length; i++) {
	        if (path.endsWith(File.separator)) {
	             temp = new File(path + tempList[i]);
	        } else {
	              temp = new File(path + File.separator + tempList[i]);
	        }
	        if (temp.isFile()) {
	             boolean result = temp.delete();
	             if(!result)
	             {
	            	 //删除进程正在使用的文件
	            	 System.gc();
	            	 temp.delete();
	             }
	        }
	        if (temp.isDirectory()) {
	             delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
	             delFolder(path + "/" + tempList[i]);//再删除空文件夹
	             flag = true;
	        }
	     }
	     return flag;
	   }
}
