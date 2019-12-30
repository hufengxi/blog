package com.app.appName.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BaseService<T> {
    Object save(T entity);
    void delete(Object entity);
    void update(Object entity);
    void saveOrUpdate(Object entity);
    void  saveAll(Collection<T> entities);
    void saveOrUpdateAll(Collection<T> entities);
    public <T> T get(Class<T> entityClass, Serializable id);
    public <T> T get(CharSequence hqlString, Object... params);
    public <T> T getBySql(Class<T> clas, CharSequence sqlString, Object... params);
    public <T> List<T> queryListByHql(CharSequence hql, Object... params);
    public <T> List<T> queryListByHql(CharSequence hql, Map<String, Object> map);
    public <T> List<T> queryListBySql(CharSequence sql, Object... params);
    public <T> List<T> queryListBySql(Class<T> beanClass, CharSequence sql, Object... params);
    public List queryListBySql(String sql, Class beanClass, Object... params);
    public <T> List<T> queryListBySql(Class<T> beanClass, CharSequence sql, Map<String, Object> map);
    public int updateByHql(String hql);
}
