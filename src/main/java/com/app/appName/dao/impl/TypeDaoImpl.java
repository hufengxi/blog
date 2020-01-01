package com.app.appName.dao.impl;

import com.app.appName.core.BaseDaoImpl;
import com.app.appName.dao.TypeDao;
import com.app.appName.entity.TypeEntity;
import org.springframework.stereotype.Repository;
import com.app.appName.entity.TypeEntity;

/**
 * @author boruonuo
 */
@Repository("typeDao")
public class TypeDaoImpl extends BaseDaoImpl<TypeEntity> implements TypeDao{

}
