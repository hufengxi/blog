package com.app.appName.dao.impl;

import com.app.appName.core.BaseDaoImpl;
import com.app.appName.dao.UsersDao;
import com.app.appName.entity.UsersEntity;
import org.springframework.stereotype.Repository;
import com.app.appName.entity.UsersEntity;

/**
 * @author boruonuo
 */
@Repository("usersDao")
public class UsersDaoImpl extends BaseDaoImpl<UsersEntity> implements UsersDao{

}
