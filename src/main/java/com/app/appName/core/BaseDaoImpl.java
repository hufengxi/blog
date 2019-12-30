package com.app.appName.core;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.*;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
public class BaseDaoImpl<T> implements BaseDao<T>{

    protected Class<T> entityClass;

    @Resource
    protected SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl(){
        Type type = getClass().getGenericSuperclass();

        if(type instanceof ParameterizedType){
            this.entityClass = (Class<T>)((ParameterizedType)type).getActualTypeArguments()[0];
        }else{
            this.entityClass = null;
        }
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public Object save(Object entity){
        Object re = null;
        Session session = getSession();
        try{
            Transaction ts = session.beginTransaction();
            re = session.save(entity);
            ts.commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return re;
    }

    public void delete(Object entity){
        Session session = getSession();
        try {
            Transaction ts = session.beginTransaction();
            session.delete(entity);
            ts.commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void update(Object entity){
        Session session = getSession();
        try{
            Transaction ts = session.beginTransaction();
            session.update(entity);
            ts.commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void saveOrUpdate(Object entity){
        Session session = getSession();
        try {
            Transaction ts = session.beginTransaction();
            session.saveOrUpdate(entity);
            ts.commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void  saveAll(Collection<T> entities){
        Session session = getSession();
        try{
            Transaction tx = session.beginTransaction();
            int i = 0;

            for(@SuppressWarnings("rawtypes")
                Iterator iterator = entities.iterator(); iterator.hasNext();){
                Object entity = iterator.next();
                session.save(entity);
                i++;
                if(i%1000 == 0){
                    session.flush();
                    session.clear();
                }

            }
            tx.commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void saveOrUpdateAll(Collection<?> entities){
        Session session = getSession();
        try {
            Transaction tx = session.beginTransaction();
            int i = 0;
            for(@SuppressWarnings("rawtypes")
                    Iterator iterator = entities.iterator(); iterator.hasNext();){
                Object object = iterator.next();
                session.saveOrUpdate(object);
                i++;
                if(i%1000 == 0){
                    session.flush();
                    session.clear();
                }
            }
            tx.commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @SuppressWarnings({"unchecked", "hiding"})
    public <T> T get(Class<T> entityClass, Serializable id){
        Session session = getSession();
        Object reEntity = new Object();
        try {
            Transaction tx = session.beginTransaction();
            reEntity = session.get(entityClass, id);
            tx.commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return (T) reEntity;
    }

    @SuppressWarnings({"unchecked", "rawtypes", "hiding"})
    public <T> T get(CharSequence hqlString, Object... params){
        Session session = getSession();
        Object reEntity = new Object();
        try {
            Transaction tx = session.beginTransaction();

            Query query = session.createQuery(hqlString.toString());
            for(int i = 0; i < params.length; i++){
                query.setParameter(i, params[i]);
            }
            List list = query.setMaxResults(1).list();
            if(list.isEmpty()){
                reEntity = null;
            }
            reEntity = list.get(0);
            tx.commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return (T) reEntity;
    }

    @SuppressWarnings({"unchecked", "rawtypes", "hiding"})
    public <T> T getBySql(Class<T> clas, CharSequence sqlString, Object... params){
        Session session = getSession();
        Object object = new Object();
        try{
            Transaction tx = session.beginTransaction();
            Query query = session.createSQLQuery(sqlString.toString()).addEntity(clas);
            for(int i = 0; i < params.length; i++){
                query.setParameter(i, params[i]);
            }
            List list = query.setMaxResults(1).list();
            if(list.isEmpty()){
                object = null;
            }else{
                object = list.get(0);
            }
            tx.commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return (T) object;
    }

    @SuppressWarnings({"unchecked", "hiding"})
    public <T> List<T> queryListByHql(CharSequence hql, Object... params){
        Session session = getSession();
        List reList = null;
        try{
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery(hql.toString());
            for(int i = 0; i < params.length; i++){
                query.setParameter(i, params[i]);
            }
            reList = query.list();
            tx.commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return reList;
    }

    @SuppressWarnings({"unchecked", "hiding"})
    public <T> List<T> queryListByHql(CharSequence hql, Map<String, Object> map){
        Session session = getSession();
        List reList = null;
        try{
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery(hql.toString());
            setParameterWithMap(query, map);
            reList = query.list();
            tx.commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return reList;
    }

    @SuppressWarnings({"unchecked", "hiding"})
    public <T> List<T> queryListBySql(CharSequence sql, Object... params){
        Session session = getSession();
        List reList = null;
        try{
            Transaction tx = session.beginTransaction();
            Query query = session.createSQLQuery(sql.toString());
            for(int i = 0; i < params.length; i++){
                query.setParameter(i, params[i]);
            }
            reList = query.list();
            tx.commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return reList;
    }

    public <T> List<T> queryListBySql(Class<T> beanClass, CharSequence sql, Object... params){
        Session session = getSession();
        List reList = null;
        try{
            Transaction tx = session.beginTransaction();
            Query query = getSession().createSQLQuery(sql.toString()).addEntity(beanClass);
            for(int i = 0; i < params.length; i++){
                query.setParameter(i, params[i]);
            }
            reList = query.list();
            tx.commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return reList;
    }

    public List queryListBySql(String sql, Class beanClass, Object... params){

        Session session = getSession();
        List reList = null;
        try{
            Transaction tx = session.beginTransaction();
            Query query = getSession().createSQLQuery(sql);
            for(int i = 0; i < params.length; i++){
                query.setParameter(i, params[i]);
            }
            query.setResultTransformer(Transformers.aliasToBean(beanClass));
            reList = query.list();
            tx.commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return reList;
    }

    public <T> List<T> queryListBySql(Class<T> beanClass, CharSequence sql, Map<String, Object> map){
        Session session = getSession();
        List reList = null;
        try{
            Transaction tx = session.beginTransaction();
            Query query = session.createSQLQuery(sql.toString()).addEntity(beanClass);
            setParameterWithMap(query, map);
            reList = query.list();
            tx.commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return reList;
    }

    public int updateByHql(String hql){
        Session session = getSession();
        int re = -1;
        try{
            Transaction ts = session.beginTransaction();
            Query query = session.createQuery(hql);
            re = query.executeUpdate();
            ts.commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return re;
    }

    protected Query setParameterWithMap(Query query, Map<String, Object> map){
        for(@SuppressWarnings("rawtypes")
                Iterator iterator = map.keySet().iterator(); iterator.hasNext();){
            String key = (String) iterator.next();
            query.setParameter(key, map.get(key));
        }
        return query;
    }

}
