/**
 * 
 */
package com.sglj.fbf.dao.sysconfig.impl;

import org.springframework.stereotype.Repository;

import com.sglj.fbf.crud.dao.support.GenericDaoSupport;
import com.sglj.fbf.dao.sysconfig.CodeDao;
import com.sglj.fbf.entity.config.CodeEntity;


/**
 * @author guanhongwei
 *
 */
@Repository("sysconf.codeDao")
public class CodeDaoImpl extends GenericDaoSupport<CodeEntity> implements CodeDao{

}
