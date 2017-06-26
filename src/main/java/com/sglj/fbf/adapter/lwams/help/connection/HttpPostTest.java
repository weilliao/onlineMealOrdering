package com.sglj.fbf.adapter.lwams.help.connection;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.sglj.fbf.constant.BusiConstant;

public class HttpPostTest {

	public static void main(String[] args) throws Exception {
		HttpPostEmulator httpPost=new HttpPostEmulator();
		String url="http://120.26.238.139:8080/LWAMS/jobnet/export/interfaceImport_data.json";
		//其他参数
		ArrayList<FormFieldKeyValuePair> form=new ArrayList<FormFieldKeyValuePair>();
		//所要上传的文件
		ArrayList<UploadFileItem> file=new ArrayList<UploadFileItem>();
//		form.add(new FormFieldKeyValuePair("node_uuid", "db81208b-83f1-41bd-8d48-6553bc84aab9"));
//		form.add(new FormFieldKeyValuePair("type", "job_net"));
//		form.add(new FormFieldKeyValuePair("account", "admin"));
			url+="?";
				url+="node_uuid=985300a9-51d0-4647-bf4d-680f9e202bc1";
				url+="&type="+ BusiConstant.JOBNET;
				url+="&account=admin";
		
		file.add(new UploadFileItem("CTP-冒烟测试.dat", "D://CTP-冒烟测试.dat"));
		
		String response = httpPost.sendHttpPostRequest(url, form, file);
	        System.out.println("Responsefrom server is: " + response);  
	          
	        //对 imageUrl、thumbnailUrl、shortUrl进行获取，不能返回空  
//	        HttpClient httpClient = new HttpClient();  
//	            GetMethod getMethod = new GetMethod(imageUrl);  
//	            if (httpClient.executeMethod(getMethod) != HttpStatus.SC_OK) {  
//	                Assert.fail("imageUrl 内容不存在.");  
//	            }  
	        
//	         }  
		
	}
	
	
}
