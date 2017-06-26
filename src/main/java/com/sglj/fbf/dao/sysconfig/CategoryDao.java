/**
 * 
 */
package com.sglj.fbf.dao.sysconfig;

import java.util.List;

import com.sglj.fbf.crud.dao.GenericDao;
import com.sglj.fbf.entity.config.CategoryEntity;


/**
 * @author guanhongwei
 *
 */
public interface CategoryDao extends GenericDao<CategoryEntity>{

	/**
	 * 根据大类代码查询字典表
	 * @param code
	 * @return
	 */
	List<CategoryEntity> findByCode(String code);

}
