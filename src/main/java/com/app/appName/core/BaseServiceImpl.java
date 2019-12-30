package com.app.appName.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class BaseServiceImpl<T> implements BaseService<T> {

    /**
     * 注入BaseDao
     */
    private BaseDao<T> dao;

    public void setDao(BaseDao<T> dao) {
        this.dao = dao;
    }

	public BaseDao<T> getDao() {
		return dao;
	}

    @Override
    public Object save(T entity) {
        return dao.save(entity);
    }

    @Override
    public void delete(Object entity){
        dao.delete(entity);
    }

    @Override
    public void update(Object entity){
        dao.update(entity);
    }

    @Override
    public void saveOrUpdate(Object entity){
        dao.saveOrUpdate(entity);
    }

    @Override
    public void  saveAll(Collection<T> entities){
        dao.saveAll(entities);
    }

    @Override
    public void saveOrUpdateAll(Collection<T> entities){
        dao.saveOrUpdateAll(entities);
    }

    @Override
    public <T> T get(Class<T> entityClass, Serializable id){
        return dao.get(entityClass, id);
    }

    @Override
    public <T> T get(CharSequence hqlString, Object... params){
        return dao.get(hqlString, params);
    }

    @Override
    public <T> T getBySql(Class<T> clas, CharSequence sqlString, Object... params){
        return dao.getBySql(clas, sqlString, params);
    }

    @Override
    public <T> List<T> queryListByHql(CharSequence hql, Object... params){
        return dao.queryListByHql(hql, params);
    }

    @Override
    public <T> List<T> queryListByHql(CharSequence hql, Map<String, Object> map){
        return dao.queryListByHql(hql, map);
    }

    @Override
    public <T> List<T> queryListBySql(CharSequence sql, Object... params){
        return dao.queryListBySql(sql, params);
    }

    @Override
    public <T> List<T> queryListBySql(Class<T> beanClass, CharSequence sql, Object... params){
        return dao.queryListBySql(beanClass, sql, params);
    }

    @Override
    public List queryListBySql(String sql, Class beanClass, Object... params){
        return dao.queryListBySql(sql, beanClass, params);
    }

    @Override
    public <T> List<T> queryListBySql(Class<T> beanClass, CharSequence sql, Map<String, Object> map){
        return dao.queryListBySql(beanClass, sql, map);
    }

    @Override
    public int updateByHql(String hql){
        return dao.updateByHql(hql);
    }
}  
