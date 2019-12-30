package com.app.appName.dao.impl;

import com.app.appName.core.BaseDaoImpl;
import com.app.appName.dao.UsersDao;
import com.app.appName.entity.UsersEntity;

import org.springframework.stereotype.Repository;

@Repository(value = "usersDao")
public class UsersDaoImpl extends BaseDaoImpl<UsersEntity> implements UsersDao{

}
