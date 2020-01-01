package com.app.appName.service.impl;

import com.app.appName.core.BaseDao;
import com.app.appName.core.BaseServiceImpl;
import com.app.appName.dao.BowenTagDao;
import com.app.appName.entity.BowenTagEntity;
import com.app.appName.service.BowenTagService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.app.appName.entity.BowenTagEntity;

/**
 * @author boruonuo
 */
@Service("bowenTagService")
public class BowenTagServiceImpl extends BaseServiceImpl<BowenTagEntity> implements BowenTagService{
    /**
    * 注入DAO
    */
    @Resource(name = "bowenTagDao")
    public void setDao(BaseDao<BowenTagEntity> dao) {
        super.setDao(dao);
    }
    @Resource
    public BowenTagDao bowenTagDao;
}
