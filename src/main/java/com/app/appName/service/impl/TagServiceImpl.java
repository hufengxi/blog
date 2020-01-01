package com.app.appName.service.impl;

import com.app.appName.core.BaseDao;
import com.app.appName.core.BaseServiceImpl;
import com.app.appName.dao.TagDao;
import com.app.appName.entity.TagEntity;
import com.app.appName.service.TagService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.app.appName.entity.TagEntity;

/**
 * @author boruonuo
 */
@Service("tagService")
public class TagServiceImpl extends BaseServiceImpl<TagEntity> implements TagService{
    /**
    * 注入DAO
    */
    @Resource(name = "tagDao")
    public void setDao(BaseDao<TagEntity> dao) {
        super.setDao(dao);
    }
    @Resource
    public TagDao tagDao;
}
