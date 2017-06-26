package com.sglj.fbf.crud.dao.generator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Service;

import com.sglj.fbf.crud.dao.CustomIDGenerator;
import com.sglj.fbf.exception.ServiceException;

@Service
public class CustomUUIDGenerator implements CustomIDGenerator, IdentifierGenerator {

	private String idPrefix;
	
	/**
	 * 自定义生成主键
	 */
	@Override
	public String generate() {
		return generate(idPrefix, StringUtils.EMPTY);
	}

	@Override
	public List<String> generate(int count) {
		
		if(count < 1 ||  count > Integer.MAX_VALUE ) throw new ServiceException("参数错误");
		
		List<String> rst = new ArrayList<String>();
		
		for (int i = 0; i < count; i++) {
			generate(idPrefix, StringUtils.EMPTY);
		}
		
		return rst;
	}

	/**
	 * Hibernate 自定义主键生成
	 */
	@Override
	public Serializable generate(SessionImplementor sessionimplementor, Object obj) throws HibernateException {
		
		return generate("WEI" + StringUtils.EMPTY, StringUtils.EMPTY);
	}
	
	/**
	 * 生成ID
	 * @param idPrefix 生成结果的前缀
	 * @param idSuffix 生成结果的后缀
	 * @return
	 */
	public static String generate(String idPrefix, String idSuffix){
		String temp = UUID.randomUUID().toString();
		
		idPrefix = StringUtils.trim(idPrefix);
		idSuffix = StringUtils.trim(idSuffix);
		
		temp = idPrefix + temp.replaceAll("-", StringUtils.EMPTY) + idSuffix;
		
		return temp;
	}

	/**
	 * @return the idPrefix
	 */
	public String getIdPrefix() {
		return idPrefix;
	}

	/**
	 * @param idPrefix the idPrefix to set
	 */
	public void setIdPrefix(String idPrefix) {
		this.idPrefix = idPrefix;
	}

}
