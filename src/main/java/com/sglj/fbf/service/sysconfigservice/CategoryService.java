/**
 * 
 */
package com.sglj.fbf.service.sysconfigservice;

import java.util.List;

import com.sglj.fbf.crud.query.Page;
import com.sglj.fbf.crud.query.QueryRule;
import com.sglj.fbf.entity.config.CategoryEntity;
import com.sglj.fbf.exception.ServiceException;


/**
 * @author guanhongwei
 *
 */
public interface CategoryService {

	/**
	 * 获取大类集合
	 * 
	 * @return
	 */
	public List<CategoryEntity> getCategorys();
	
	/**
	 * 查询大类代码信息
	 * @param queryRule
	 * @return
	 */
	public List<CategoryEntity> queryCategory(QueryRule queryRule);

	/**
	 * 根据查询规则分页查询大类代码信息
	 * 
	 * @param queryRule
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Page queryCategory(QueryRule queryRule, int pageIndex, int pageSize);

	/**
	 * 根据大类ID获取字典表信息
	 * 
	 * @param id
	 * @return
	 */
	public CategoryEntity getCategoryById(String id);

	/**
	 * 创建大类代码信息
	 * 
	 * @param category
	 */
	public void createCategory(CategoryEntity category);

	/**
	 * 更新大类代码信息
	 * 
	 * @param category
	 */
	public void updateCategory(CategoryEntity category);

	/**
	 * 根据大类ID删除字典表信息
	 * 
	 * @param categoryId
	 */
	public void removeCategory(String categoryId);

	/**
	 * 根据大类ID逻辑删除字典表信息
	 * 
	 * @param categoryId
	 * @param delReason
	 */
	public void removeCategory(String categoryId, String delReason);

	/**
	 * 根据大类代码获取字典表信息
	 * 
	 * @param code
	 * @return
	 */
	public CategoryEntity getCategoryByCode(String code);

	/**
	 * 大类代码新增或修改数据校验
	 * 
	 * @param category
	 * @throws ServiceException
	 */
	public void validateCategoryWhenSaveOrUpdate(CategoryEntity category) throws ServiceException;

	/**
	 * 大类代码删除数据校验
	 * 
	 * @param categoryId
	 * @throws ServiceException
	 */
	public void validateCategoryWhenRemove(String categoryId) throws ServiceException;

}
