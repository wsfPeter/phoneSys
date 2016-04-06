package com.cienet.service.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.cienet.bean.Pager;
import com.cienet.dao.BaseDao;
import com.cienet.service.BaseService;

/**
 * Description:Service实现类基类
 * 
 * @author leo
 * @version 1.0
 * @created 2013-3-21 下午05:01:54
 */
public class BaseServiceImpl<T, PK extends Serializable> implements
        BaseService<T, PK> {

    private BaseDao<T, PK> baseDao;

    public void clear() {
        baseDao.clear();
    }

    public void clearTable() {
        baseDao.clearTable();
    }

    public void delete(PK id) {
        baseDao.delete(id);
    }

    public void delete(PK[] ids) {
        baseDao.delete(ids);
    }

    public void delete(T entity) {
        baseDao.delete(entity);
    }

    public void evict(Object object) {
        baseDao.evict(object);
    }

    public Pager findByPager(Pager pager) {
        return baseDao.findByPager(pager);
    }

    public Pager findByPager(Pager pager, DetachedCriteria detachedCriteria) {
        return baseDao.findByPager(pager, detachedCriteria);
    }

    public void flush() {
        baseDao.flush();
    }

    public T get(PK id) {
        return baseDao.get(id);
    }

    public List<T> get(PK[] ids) {
        return baseDao.get(ids);
    }

    public T get(String propertyName, Object value) {
        return baseDao.get(propertyName, value);
    }

    public List<T> getAll() {
        return baseDao.getAll();
    }

    public BaseDao<T, PK> getBaseDao() {
        return baseDao;
    }

    public List<T> getList(String propertyName, Object value) {
        return baseDao.getList(propertyName, value);
    }

    public Long getTotalCount() {
        return baseDao.getTotalCount();
    }

    public boolean isExist(String propertyName, Object value) {
        return baseDao.isExist(propertyName, value);
    }

    public boolean isUnique(String propertyName, Object oldValue,
                            Object newValue) {
        return baseDao.isUnique(propertyName, oldValue, newValue);
    }

    public T load(PK id) {
        return baseDao.load(id);
    }

    public PK save(T entity) {
        return baseDao.save(entity);
    }

    public void setBaseDao(BaseDao<T, PK> baseDao) {
        this.baseDao = baseDao;
    }

    public void update(T entity) {
        baseDao.update(entity);
    }
    
    public List<T> getQtLista(Object qValue,int maxValue) {
    	return baseDao.getQtLista(qValue,maxValue);
    }
    
    public List<T> getQtListb(Object qValue,int maxValue) {
    	return baseDao.getQtListb(qValue,maxValue);
    }
    
    public List<T> getQtListc(Object qValue,int maxValue) {
    	return baseDao.getQtListc(qValue,maxValue);
    }
}
