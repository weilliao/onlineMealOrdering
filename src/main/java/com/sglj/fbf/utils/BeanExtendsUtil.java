package com.sglj.fbf.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sglj.fbf.support.StringToDateSupport;


public class BeanExtendsUtil<S, T> extends BeanUtil{

	public T getObj(S xml, Class<T> cls){
		T ret = null;
		try {
			ret = cls.newInstance();
			copyProperties(xml, ret);
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return ret;
	
	}


	public List<T> getObjList(List<S> xmlList, Class<T> cls){
		List<T> retList = new ArrayList<T>();
		if (xmlList != null && xmlList.size() >0) {
			for (S xml : xmlList) {
				retList.add(getObj(xml, cls));
			}
		}
		return retList;
	 }


	/**
	 * 把传入源对象的属性值赋给目标对象
	 * 
	 * @param source
	 *            源对象
	 * @param target
	 *            目标对象
	 */
	public static void copyProperties(Object source, Object target) {
		Map<String, Object> properties = getAllNotNullProperties(source);
		Field[] fields = getAllDeclaredFields(target.getClass());
	
		for (int i = 0; i < fields.length; i++) {
			if (!fields[i].isAccessible()) {
				fields[i].setAccessible(true);
			}
	
			try {
				Object fieldContent = properties.get(fields[i].getName());
				if (fieldContent != null && !"".equals(fieldContent) && !Modifier.isFinal(fields[i].getModifiers())) {
					if ("java.util.Date".equals(fields[i].getType().getName())
							|| "java.sql.Date".equals(fields[i].getType().getName())) {
						if (fieldContent instanceof java.lang.String) {
							fields[i].set(target, StringToDateSupport.stringToDate(fieldContent.toString()));
						} else {
							fields[i].set(target, fieldContent);
						}
					} else if ("java.lang.String".equals(fields[i].getType().getName())) {
						if (fieldContent instanceof java.util.Date 
								|| fieldContent instanceof java.sql.Date
								|| fieldContent instanceof java.sql.Timestamp) {
							DateFormat sdf = new SimpleDateFormat(DateUtil.defaultTimeStampPattern);   
							fields[i].set(target, sdf.format(fieldContent));
					
						} else if (fieldContent instanceof java.lang.Boolean) {
							if (Boolean.valueOf(fieldContent.toString()) == true) {
								fields[i].set(target, "1");
							} else {
								fields[i].set(target, "0");
							}
							
						} else {
							fields[i].set(target, fieldContent);
						}
					} else {
						fields[i].set(target, fieldContent);
					}
				}
			} catch (Exception e) {
				throw new InternalError("Runtime Exception impossibility throw");
			}
	
		}
	
	}

}
