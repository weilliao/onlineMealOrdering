package com.sglj.fbf.support;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.sglj.fbf.utils.DateUtil;

public class StringToDateSupport {
	
	private static Logger log = Logger.getLogger(StringToDateSupport.class);
	
	// 支持转换的日期格式
	private static final String[] ACCEPT_DATE_FORMATS = {
		new String("yyyy-MM"),
		new String("yyyy-MM-dd"),
		new String("yyyy-MM-dd HH:mm"),
		new String("yyyy-MM-dd HH:mm:ss"),
		new String("yyyy/MM/dd")
	};
	
	public static Date stringToDate(String source){
		if(StringUtils.isBlank(source)){
			return null;
		}
		
		source = StringUtils.trim(source);
		
        if(source.matches("^\\d{4}-\\d{1,2}$")){
        	
            return parseDate(source, ACCEPT_DATE_FORMATS[0]);
            
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")){
        	
            return parseDate(source, ACCEPT_DATE_FORMATS[1]);
            
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")){
        	
            return parseDate(source, ACCEPT_DATE_FORMATS[2]);
            
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
        	
            return parseDate(source, ACCEPT_DATE_FORMATS[3]);
            
        }else if(source.matches("^\\d{4}/\\d{1,2}/\\d{1,2}$")){
        	
            return parseDate(source, ACCEPT_DATE_FORMATS[4]);
            
        } else if(source.matches("^[0-9]*$")) {
        	return DateUtil.long2Date(Long.parseLong(source)); 
        } else {
            throw new IllegalArgumentException("Invalid date value '" + source + "'");
        }
	}
	
	/**
     *
     * @param dateStr
     *            String 字符型日期
     * @param format
     *            String 格式
     * @return Date 日期
     */
    private static Date parseDate(String dateStr, String format) {
        Date date = null;
        
        try {
        	
            DateFormat dateFormat = new SimpleDateFormat(format);
            
            date = (Date) dateFormat.parse(dateStr);
            
        } catch (Exception e) {
        	log.info(e);
        }
        
        return date;
    }

}
