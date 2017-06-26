/**
 * 
 */
package com.sglj.fbf.dao.sysconfig.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sglj.fbf.crud.dao.support.GenericDaoSupport;
import com.sglj.fbf.crud.query.QueryRule;
import com.sglj.fbf.dao.sysconfig.CategoryDao;
import com.sglj.fbf.entity.config.CategoryEntity;


/**
 * @author guanhongwei
 *
 */
@Repository("sysconf.categoryDao")
public class CategoryDaoImpl extends GenericDaoSupport<CategoryEntity> implements CategoryDao{

	/* (non-Javadoc)
	 * @see com.newtouch.fbf.dao.sysconf.CategoryDao#findByCode(java.lang.String)
	 */
	@Override
	public List<CategoryEntity> findByCode(String code) {
		QueryRule rule = QueryRule.getInstance();
		rule.addEqual("code", code);

		return this.find(rule);
	}

}
