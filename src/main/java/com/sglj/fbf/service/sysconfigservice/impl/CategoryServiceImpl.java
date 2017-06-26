/**
 * 
 */
package com.sglj.fbf.service.sysconfigservice.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sglj.fbf.crud.query.Page;
import com.sglj.fbf.crud.query.QueryRule;
import com.sglj.fbf.dao.sysconfig.CategoryDao;
import com.sglj.fbf.dao.sysconfig.CodeDao;
import com.sglj.fbf.entity.config.CategoryEntity;
import com.sglj.fbf.entity.config.CodeEntity;
import com.sglj.fbf.exception.ServiceException;
import com.sglj.fbf.service.sysconfigservice.CategoryService;


/**
 * @author guanhongwei
 *
 */
@Service("sysconf.categoryService")
public class CategoryServiceImpl implements CategoryService{
	
	@Resource(name = "sysconf.categoryDao")
	private CategoryDao categoryDao;
	
	@Resource(name = "sysconf.codeDao")
	private CodeDao codeDao;

	/**
	 */
	@Override
	public List<CategoryEntity> getCategorys() {
		List<CategoryEntity> categorys = categoryDao.getAll();
		List<CategoryEntity> result = new ArrayList<CategoryEntity>();

		for (CategoryEntity category : categorys) {
			if (!category.getIsDeleted()) {
				result.add(category);
			}
		}

		return result;
	}

	/**
	 */
	@Override
	public Page queryCategory(QueryRule queryRule, int pageIndex, int pageSize) {
		return categoryDao.find(queryRule, pageIndex, pageSize);
	}

	/**
	 */
	@Override
	public CategoryEntity getCategoryById(String id) {
		return categoryDao.get(id);
	}

	/**
	 */
	@Override
	public void createCategory(CategoryEntity category) {
		categoryDao.save(category);

	}

	/**
	 */
	@Override
	public void updateCategory(CategoryEntity category) {
		categoryDao.update(category);
	}

	/**
	 */
	@Override
	public void removeCategory(String categoryId) {
		categoryDao.deleteById(categoryId);
	}

	/**
	 */
	@Override
	public void removeCategory(String categoryId, String delReason) {
		CategoryEntity category = categoryDao.get(categoryId);
		category.setIsDeleted(true);
		category.setDelReason(delReason);
		categoryDao.update(category);

	}

	/**
	 */
	@Override
	public CategoryEntity getCategoryByCode(String code) {
		if (StringUtils.isEmpty(code))
			return null;

		List<CategoryEntity> categorys = categoryDao.findByCode(code);
		if (categorys.isEmpty())
			return null;

		CategoryEntity category = categorys.get(0);

		QueryRule rule = QueryRule.getInstance();
		rule.addEqual("category", category);
		rule.addAscOrder("seq");

		List<CodeEntity> codes = codeDao.find(rule);
		category.setCodes(codes);

		return category;
	}

	/**
	 */
	@Override
	public void validateCategoryWhenSaveOrUpdate(CategoryEntity category) throws ServiceException {
		QueryRule rule = QueryRule.getInstance();
		rule.addEqual("code", category.getCode());

		if (category.getId() != null) {
			rule.addNotEqual("id", category.getId());
		}

		List<CategoryEntity> categories = categoryDao.find(rule);
		if (!categories.isEmpty()) {
			throw new ServiceException("大类代码：" + category.getCode() + " 已经被使用，不能重复申请！");
		}

	}

	/**
	 */
	@Override
	public void validateCategoryWhenRemove(String categoryId) throws ServiceException {
		QueryRule rule = QueryRule.getInstance();
		QueryRule subRule = rule.addSubQueryRule("category");
		subRule.addEqual("id", categoryId);

		List<CodeEntity> codes = codeDao.find(rule);
		if (!codes.isEmpty()) {
			throw new ServiceException("请先删除此大类对应的小类代码，然后再进行删除操作！");
		}
	}

	/* (non-Javadoc)
	 * @see com.newtouch.fbf.service.sysconf.CategoryService#queryCategory(com.ghw.org.crud.entity.QueryRule)
	 */
	@Override
	public List<CategoryEntity> queryCategory(QueryRule queryRule) {
		return categoryDao.find(queryRule);
	}
}
