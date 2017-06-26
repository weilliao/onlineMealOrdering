package com.sglj.fbf.dao.user.impl;


import org.springframework.stereotype.Repository;

import com.sglj.fbf.crud.dao.support.GenericDaoSupport;
import com.sglj.fbf.dao.user.UserDao;
import com.sglj.fbf.entity.user.User;

@Repository
public class UserDaoImpl extends GenericDaoSupport<User> implements UserDao {
	
}
