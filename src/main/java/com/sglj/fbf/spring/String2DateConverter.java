package com.sglj.fbf.spring;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import com.sglj.fbf.support.StringToDateSupport;

/**
 * spring mvc data type convert
 * @author guanhongwei
 *
 */
public final class String2DateConverter implements Converter<String, Date> {
	
	@Override
	public Date convert(String source) {
		
		return StringToDateSupport.stringToDate(source);
	}

}
