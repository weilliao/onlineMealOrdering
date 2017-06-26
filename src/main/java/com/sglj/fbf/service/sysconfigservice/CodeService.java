/**
 * 
 */
package com.sglj.fbf.service.sysconfigservice;

import java.util.List;

import com.sglj.fbf.crud.query.Page;
import com.sglj.fbf.crud.query.QueryRule;
import com.sglj.fbf.entity.config.CodeEntity;
import com.sglj.fbf.exception.ServiceException;


/**
 * @author guanhongwei
 *
 */
public interface CodeService {

	/**
	 * 根据查询规则分页查询小类代码信息
	 * 
	 * @param queryRule
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Page queryCode(QueryRule queryRule, int pageIndex, int pageSize);

	/**
	 * 根据小类ID获取字典表信息
	 * 
	 * @param id
	 * @return
	 */
	public CodeEntity getCodeById(String id);

	/**
	 * 创建小类代码信息
	 * 
	 * @param code
	 */
	public void createCode(CodeEntity code);

	/**
	 * 更新小类代码信息
	 * 
	 * @param code
	 */
	public void updateCode(CodeEntity code);

	/**
	 * 根据小类ID删除字典表信息
	 * 
	 * @param codeId
	 */
	public void removeCode(String codeId);

	/**
	 * 根据小类ID逻辑删除字典表信息
	 * 
	 * @param codeId
	 * @param delReason
	 */
	public void removeCode(String codeId, String delReason);

	/**
	 * 小类代码新增或修改数据校验
	 * 
	 * @param code
	 * @throws ServiceException
	 */
	public void validateCodeWhenSaveOrUpdate(CodeEntity code) throws ServiceException;
	
	/**
	 * 根据条件查询Code
	 * @param queryRule
	 * @return
	 */
	public List<CodeEntity> queryCodeList(QueryRule queryRule);

}
