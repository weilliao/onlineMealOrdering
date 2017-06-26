package com.sglj.fbf.adapter.lwams.help.connection;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;


public class ConnectionInfoUtils {
	/**
	 * httpclient
	 */
	HttpParams params = new BasicHttpParams();
	//设置连接超时时间
	static Integer CONNECTION_TIMEOUT = 6 * 1000; //设置请求超时2秒钟 根据业务调整
	static Integer SO_TIMEOUT = 5 * 1000; //设置等待数据超时时间2秒钟 根据业务调整
	//定义了当从ClientConnectionManager中检索ManagedClientConnection实例时使用的毫秒级的超时时间
	//这个参数期望得到一个java.lang.Long类型的值。如果这个参数没有被设置，默认等于CONNECTION_TIMEOUT，因此一定要设置
	static Long CONN_MANAGER_TIMEOUT = 500L; //该值就是连接不够用的时候等待超时时间，一定要设置，而且不能太大 ()
	
	 public static HttpClient getHttpClient(){
    	HttpParams mHttpParams=new BasicHttpParams();
    	//设置网络链接超时
    	//即:Set the timeout in milliseconds until a connection is established.
    	mHttpParams.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECTION_TIMEOUT);
    	
    	mHttpParams.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);
    	mHttpParams.setLongParameter(ClientPNames.CONN_MANAGER_TIMEOUT, CONN_MANAGER_TIMEOUT);
    	//在提交请求之前 测试连接是否可用
    	mHttpParams.setBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, true);
    	//待配置
    	HttpClient httpClient=new HttpClient();
    	return httpClient;
	    }
	
	/**
	 * get调用接口
	 * @param Url
	 * @return
	 */
	public static String getHttp(String Url){
		String responseMsg="";
		HttpClient httpClient =getHttpClient();
		GetMethod getMethod=new GetMethod(Url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		try {
			httpClient.executeMethod(getMethod);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream in = getMethod.getResponseBodyAsStream();
			int len = 0;
			byte[] buf = new byte[1024];
			while((len=in.read(buf))!=-1){
				out.write(buf, 0, len);
			}
			responseMsg = out.toString("UTF-8");
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//释放连接
			getMethod.releaseConnection();
		}
		return responseMsg;
	}
	
	
	/**
	 * post调用
	 * @param Url
	 * @param map
	 * @return
	 */
	public static Map<String, Object> postHttp(String Url,Map<String, Object> map){
		String responseMsg = "";
		HttpClient httpClient = getHttpClient();
		httpClient.getParams().setContentCharset("UTF-8");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		PostMethod postMethod = new PostMethod(Url);
		if(null != map && map.size() > 0){
			//获取map的key
			Set<String> keys=map.keySet();
			if(!keys.isEmpty()){
//			
				if(!keys.isEmpty()){
					for (String key : keys) {
						//添加参数
						postMethod.addParameter(key, map.get(key)!=null?map.get(key).toString():"");
					}
				}
			}
		}
		try {
			// HttpClient发出Post请求
			int h= httpClient.executeMethod(postMethod);
			System.out.println(h+"\n");
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream in = postMethod.getResponseBodyAsStream();
			int len = 0;
			byte[] buf = new byte[1024];
			while((len=in.read(buf))!=-1){
				out.write(buf, 0, len);
			}
			responseMsg = out.toString("UTF-8");
			
			responseMap.put("status", h);
			responseMap.put("responseMsg", responseMsg);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return responseMap;
		
	}

	/**
	 * post调用 文件上传
	 * @param Url
	 * @param map
	 * @param filePath
	 * @return
	 */
	public static Map<String, Object> postHttpFileIO(String Url,Map<String, Object> map,String filePath){
		org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
		Map<String, Object> responseMap = new HashMap<String, Object>();
		if (null != map && map.size() > 0) {
			// 获取map的key
			Set<String> keys = map.keySet();
			if (!keys.isEmpty()) {
				int i = 0;
				for (String key : keys) {
					if (i == 0) {
						Url += "?" + key + "=" + map.get(key).toString();
					} else {
						Url += "&" + key + "=" + map.get(key).toString();
					}
					i++;
				}
			}
		}
		// 请求处理页面
		HttpPost httppost = new HttpPost(Url);
		// 创建待处理的文件
		FileBody file = new FileBody(new File(filePath));

		// 对请求的表单域进行填充
		MultipartEntity reqEntity = new MultipartEntity();
		reqEntity.addPart("file", file);
		// 设置请求
		httppost.setEntity(reqEntity);
		// 执行
		HttpResponse response = null;
		try {
			response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			responseMap.put("status", response.getStatusLine().getStatusCode());
			// 显示内容
			responseMap.put("responseMsg", EntityUtils.toString(entity));
			return responseMap;
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return responseMap;
	}

}
