package com.app.appName.service.impl;

import com.app.appName.core.BaseDao;
import com.app.appName.core.BaseServiceImpl;
import com.app.appName.dao.BowenDao;
import com.app.appName.entity.BowenEntity;
import com.app.appName.service.BowenService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.app.appName.entity.BowenEntity;

/**
 * @author boruonuo
 */
@Service("bowenService")
public class BowenServiceImpl extends BaseServiceImpl<BowenEntity> implements BowenService{
    /**
    * 注入DAO
    */
    @Resource(name = "bowenDao")
    public void setDao(BaseDao<BowenEntity> dao) {
        super.setDao(dao);
    }
    @Resource
    public BowenDao bowenDao;
}
