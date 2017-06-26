package com.sglj.fbf.spring;

import java.beans.Introspector;
import java.io.Serializable;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.ContextLoader;

@Service("springBeanService")
public class SpringBeanService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2825300841701922725L;

	@SuppressWarnings("unchecked")
	public <T> T getBean(Class<T> clazz, String beanName) {
		ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		return (T) context.getBean(beanName);
	}

	@SuppressWarnings("unchecked")
	public <T> T getBean(Class<T> clazz) {
		ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		String beanName = ClassUtils.getShortName(clazz.getName());
		beanName = Introspector.decapitalize(beanName);
		return (T) context.getBean(beanName);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBeans(Class<T> clazz) {
		ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		String beanName = ClassUtils.getShortName(clazz.getName());
		beanName = Introspector.decapitalize(beanName);
		return (T) context.getBean(beanName);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBeans(Class<T> clazz, String beanName) {
		ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		return (T) context.getBean(beanName);
	}
}