package com.cienet.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.cienet.bean.Pager;
import com.cienet.bean.Pager.OrderType;
import com.cienet.dao.BaseDao;

/**
 * @ClassName: BaseDaoImpl
 * @Description: Dao实现基类
 * @author zhujiang
 * @date 2014年7月1日 下午11:34:45
 * 
 * @param <T>
 * @param <PK>
 */
@Repository
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

    protected SessionFactory sessionFactory;
    private Class<T> entityClass;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public BaseDaoImpl() {
        this.entityClass = null;
        Class c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type)
                    .getActualTypeArguments();
            this.entityClass = (Class<T>) parameterizedType[0];
        }
    }

    public void clear() {
        getSession().clear();
    }

    public void clearTable() {
        String hql = "delete from " + entityClass.getName();
        getSession().createQuery(hql).executeUpdate();
    }

    public void delete(PK id) {
        Assert.notNull(id, "id is required");
        T entity = load(id);
        getSession().delete(entity);
    }

    public void delete(PK[] ids) {
        Assert.notEmpty(ids, "ids must not be empty");
        for (PK id : ids) {
            T entity = load(id);
            getSession().delete(entity);
        }
    }

    public void delete(T entity) {
        Assert.notNull(entity, "entity is required");
        getSession().delete(entity);
    }

    public void evict(Object object) {
        Assert.notNull(object, "object is required");
        getSession().evict(object);
    }

    public Pager findByPager(Pager pager) {
        if (pager == null) {
            pager = new Pager();
        }
        DetachedCriteria detachedCriteria = DetachedCriteria
                .forClass(entityClass);
        return findByPager(pager, detachedCriteria);
    }

    public Pager findByPager(Pager pager, DetachedCriteria detachedCriteria) {
        if (pager == null) {
            pager = new Pager();
        }
        Integer pageNumber = pager.getPageNumber();
        Integer pageSize = pager.getPageSize();
        String property = pager.getSearchProperty();
        String keyword = pager.getSearchValue();
        String orderBy = pager.getOrderProperty();
        OrderType orderType = pager.getOrderType();

        Criteria criteria = detachedCriteria
                .getExecutableCriteria(getSession());
        if (StringUtils.isNotEmpty(property) && StringUtils.isNotEmpty(keyword)) {
            String propertyString = "";
            if (property.contains(".")) {
                String propertyPrefix = StringUtils.substringBefore(property,
                        ".");
                String propertySuffix = StringUtils.substringAfter(property,
                        ".");
                criteria.createAlias(propertyPrefix, "model");
                propertyString = "model." + propertySuffix;
            } else {
                propertyString = property;
            }
            criteria.add(Restrictions.like(propertyString, "%" + keyword + "%"));
        }

        long totalCount = (Long) criteria.setProjection(Projections.rowCount())
                .uniqueResult();

        criteria.setProjection(null);

        criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        if (StringUtils.isNotEmpty(orderBy) && orderType != null) {
            if (orderType == OrderType.asc) {
                criteria.addOrder(Order.asc(orderBy));
            } else {
                criteria.addOrder(Order.desc(orderBy));
            }
        }
        pager.setTotalCount(totalCount);
        pager.setResult(criteria.list());
        return pager;
    }

    public void flush() {
        getSession().flush();
    }

    @SuppressWarnings("unchecked")
    public T get(PK id) {
        Assert.notNull(id, "id is required");
        return (T) getSession().get(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> get(PK[] ids) {
        Assert.notEmpty(ids, "ids must not be empty");
        String hql = "from " + entityClass.getName()
                + " as model where model.id in(:ids)";
        return getSession().createQuery(hql).setParameterList("ids", ids)
                .list();
    }

    @SuppressWarnings("unchecked")
    public T get(String propertyName, Object value) {
        Assert.hasText(propertyName, "propertyName must not be empty");
        Assert.notNull(value, "value is required");
        String hql = "from " + entityClass.getName() + " as model where model."
                + propertyName + " = ?";
        return (T) getSession().createQuery(hql).setParameter(0, value)
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        String hql = "from " + entityClass.getName();
        return getSession().createQuery(hql).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll(String orderBy) {
        String hql = "from " + entityClass.getName() + " order By " + orderBy;
        return getSession().createQuery(hql).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> getQtLista( Object qValue,int maxValue) {
        Assert.notNull(qValue, "qValue is required");
        Assert.notNull(maxValue, "maxValue is required");
        String hql = "from " + entityClass.getName() + " as model where model.qtype = 1" +
        		"and model.qlevel=?";
        return getSession().createQuery(hql).setParameter(0, qValue).setFirstResult(0).setMaxResults(maxValue).list();
    }
    
    @SuppressWarnings("unchecked")
    public List<T> getQtListb( Object qValue,int maxValue) {
        Assert.notNull(qValue, "qValue is required");
        Assert.notNull(maxValue, "maxValue is required");
        String hql = "from " + entityClass.getName() + " as model where model.qtype = 2" +
        		"and model.qlevel=?";
        return getSession().createQuery(hql).setParameter(0, qValue).setFirstResult(0).setMaxResults(maxValue).list();
    }
    
    @SuppressWarnings("unchecked")
    public List<T> getQtListc( Object qValue,int maxValue) {
        Assert.notNull(qValue, "qValue is required");
        Assert.notNull(maxValue, "maxValue is required");
        String hql = "from " + entityClass.getName() + " as model where model.qtype = 3" +
        		"and model.qlevel=?";
        return getSession().createQuery(hql).setParameter(0, qValue).setFirstResult(0).setMaxResults(maxValue).list();
    }
    
    @SuppressWarnings("unchecked")
    public List<T> getList(String propertyName, Object value) {
        Assert.hasText(propertyName, "propertyName must not be empty");
        Assert.notNull(value, "value is required");
        String hql = "from " + entityClass.getName() + " as model where model."
                + propertyName + " = ?";
        return getSession().createQuery(hql).setParameter(0, value).list();
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Long getTotalCount() {
        String hql = "select count(*) from " + entityClass.getName();
        return (Long) getSession().createQuery(hql).uniqueResult();
    }

    public boolean isExist(String propertyName, Object value) {
        Assert.hasText(propertyName, "propertyName must not be empty");
        Assert.notNull(value, "value is required");
        T object = get(propertyName, value);
        return (object != null);
    }

    public boolean isUnique(String propertyName, Object oldValue,
            Object newValue) {
        Assert.hasText(propertyName, "propertyName must not be empty");
        Assert.notNull(newValue, "newValue is required");
        if (newValue == oldValue || newValue.equals(oldValue)) {
            return true;
        }
        if (newValue instanceof String) {
            if (oldValue != null
                    && StringUtils.equalsIgnoreCase((String) oldValue,
                            (String) newValue)) {
                return true;
            }
        }
        T object = get(propertyName, newValue);
        return (object == null);
    }

    @SuppressWarnings("unchecked")
    public T load(PK id) {
        Assert.notNull(id, "id is required");
        return (T) getSession().load(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public PK save(T entity) {
        Assert.notNull(entity, "entity is required");
        return (PK) getSession().save(entity);
    }

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void update(T entity) {
        Assert.notNull(entity, "entity is required");
        getSession().update(entity);
    }
}