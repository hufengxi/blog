package com.app.appName.dao.impl;

import com.app.appName.core.BaseDaoImpl;
import com.app.appName.dao.TagDao;
import com.app.appName.entity.TagEntity;
import org.springframework.stereotype.Repository;
import com.app.appName.entity.TagEntity;

/**
 * @author boruonuo
 */
@Repository("tagDao")
public class TagDaoImpl extends BaseDaoImpl<TagEntity> implements TagDao{

}
