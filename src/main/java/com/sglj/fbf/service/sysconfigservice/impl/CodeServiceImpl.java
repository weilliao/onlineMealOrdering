/**
 * 
 */
package com.sglj.fbf.service.sysconfigservice.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sglj.fbf.crud.query.Page;
import com.sglj.fbf.crud.query.QueryRule;
import com.sglj.fbf.dao.sysconfig.CodeDao;
import com.sglj.fbf.entity.config.CodeEntity;
import com.sglj.fbf.exception.ServiceException;
import com.sglj.fbf.service.sysconfigservice.CodeService;


/**
 * @author guanhongwei
 *
 */
@Service("sysconf.codeService")
public class CodeServiceImpl implements CodeService{
	
	@Resource(name = "sysconf.codeDao")
	private CodeDao codeDao;
	/**
	 */
	@Override
	public Page queryCode(QueryRule queryRule, int pageIndex, int pageSize) {
		queryRule.addIsNotNull("category");
		return codeDao.find(queryRule, pageIndex, pageSize);
	}

	/**
	 */
	@Override
	public CodeEntity getCodeById(String id) {
		return codeDao.get(id);
	}

	/**
	 */
	@Override
	public void createCode(CodeEntity code) {
		codeDao.save(code);
	}

	/**
	 */
	@Override
	public void updateCode(CodeEntity code) {
		codeDao.update(code);
	}

	/**
	 */
	@Override
	public void removeCode(String codeId) {
		codeDao.deleteById(codeId);
	}

	/**
	 */
	@Override
	public void removeCode(String codeId, String delReason) {
		CodeEntity code = codeDao.get(codeId);
		code.setIsDeleted(true);
		code.setDelReason(delReason);
		codeDao.update(code);
	}

	/**
	 */
	@Override
	public void validateCodeWhenSaveOrUpdate(CodeEntity code) throws ServiceException {
		QueryRule rule = QueryRule.getInstance();
		rule.addEqual("value", code.getValue());
		rule.addEqual("category", code.getCategory());

		if (code.getId() != null) {
			rule.addNotEqual("id", code.getId());
		}

		List<CodeEntity> codes = codeDao.find(rule);
		if (!codes.isEmpty()) {
			throw new ServiceException("大类 [" + code.getCategory().getName() + "] 下的小类代码 [" + code.getValue() + "] 已经被使用，不能重复申请！");
		}

	}

	@Override
	public List<CodeEntity> queryCodeList(QueryRule queryRule) {
		return codeDao.find(queryRule);
	}

}
