package com.app.appName.service.impl;

import com.app.appName.core.BaseDao;
import com.app.appName.core.BaseServiceImpl;
import com.app.appName.dao.UsersDao;
import com.app.appName.entity.UsersEntity;
import com.app.appName.service.UsersService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("usersService")
public class UsersServiceImpl extends BaseServiceImpl<UsersEntity> implements UsersService {

    @Resource(name="usersDao")
    public void setDao(BaseDao<UsersEntity> dao) {
        super.setDao(dao);
    }

    @Resource
    UsersDao usersDao;

}
