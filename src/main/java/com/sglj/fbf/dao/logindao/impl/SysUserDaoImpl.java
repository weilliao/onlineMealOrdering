package com.sglj.fbf.dao.logindao.impl;


import org.springframework.stereotype.Repository;

import com.sglj.fbf.crud.dao.support.GenericDaoSupport;
import com.sglj.fbf.dao.logindao.SysUserDao;
import com.sglj.fbf.entity.sysuser.SysUser;

@Repository("logindao.sysUserDao")
public class SysUserDaoImpl extends GenericDaoSupport<SysUser> implements SysUserDao {
}
