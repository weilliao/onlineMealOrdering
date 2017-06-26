package com.sglj.fbf.spring;

import java.io.IOException;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

public class JSONHttpMessageConverter extends FastJsonHttpMessageConverter{
	
	@Override
    protected void writeInternal(Object obj, HttpOutputMessage opt) throws IOException,HttpMessageNotWritableException {
		if(opt != null && opt.getHeaders() != null){
			opt.getHeaders().setContentType(MediaType.APPLICATION_JSON);
			opt.getHeaders().set("Content-Disposition", MediaType.APPLICATION_JSON_VALUE);
			if(opt.getHeaders().getContentLength() == -1L){
				String str = JSON.toJSONString(
						obj,
						SerializerFeature.WriteNonStringKeyAsString, 
						SerializerFeature.SortField,
						SerializerFeature.WriteNullStringAsEmpty, 
						SerializerFeature.QuoteFieldNames,
						SerializerFeature.WriteMapNullValue
					);
				
		        long contentLength = str.getBytes(this.getCharset().name()).length;
		        opt.getHeaders().setContentLength(contentLength);
		        StreamUtils.copy(str, this.getCharset(), opt.getBody());
			}
		}
    }
}
