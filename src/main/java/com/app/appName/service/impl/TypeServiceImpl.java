package com.app.appName.service.impl;

import com.app.appName.core.BaseDao;
import com.app.appName.core.BaseServiceImpl;
import com.app.appName.dao.TypeDao;
import com.app.appName.entity.TypeEntity;
import com.app.appName.service.TypeService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.app.appName.entity.TypeEntity;

/**
 * @author boruonuo
 */
@Service("typeService")
public class TypeServiceImpl extends BaseServiceImpl<TypeEntity> implements TypeService{
    /**
    * 注入DAO
    */
    @Resource(name = "typeDao")
    public void setDao(BaseDao<TypeEntity> dao) {
        super.setDao(dao);
    }
    @Resource
    public TypeDao typeDao;
}
