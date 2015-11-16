package com.ssh.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Component
@Transactional(propagation = Propagation.REQUIRED)
public class BaseDao<T, PK extends Serializable> extends HibernateDaoSupport
		implements IBaseDao<T, PK> {

	private Logger log = Logger.getLogger(getClass());

	protected Class<T> persistentClass;

	@Resource(name = "transactionManager")
	public PlatformTransactionManager transactionManager;

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {

		super.setSessionFactory(sessionFactory);
	}

	public BaseDao() {

	}

	public BaseDao(Class<T> persistentClass) {
		setPersistentClass(persistentClass);
	}

	public void setPersistentClass(Class<T> persistentClass) {

		this.persistentClass = persistentClass;
	}

	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().loadAll(this.persistentClass);
	}

	@Override
	public List<T> getAllDistinct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T get(PK id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(PK id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T save(T object) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(object);
		getHibernateTemplate().flush();
		return null;
	}

	@Override
	public void saveOrUpdate(T object) {
		// TODO Auto-generated method stub
		getHibernateTemplate().saveOrUpdate(object);
		getHibernateTemplate().flush();
		log.info("Saved successfully.");
	}

	public void saveAll(Vector<T> vectors) {
		for (T entity : vectors) {
			T obj = getHibernateTemplate().merge(entity);
			getHibernateTemplate().saveOrUpdate(obj);
			getHibernateTemplate().flush();
		}
		log.info("Saved all successfully.");
	}

	@Override
	public void remove(T object) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(object);
		getHibernateTemplate().flush();
		log.info("Removed successfully.");
	}

	@Override
	public void remove(PK id) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(this.get(id));
		getHibernateTemplate().flush();
	}

	@Override
	public List<T> findByNamedQuery(String queryName,
			Map<String, Object> queryParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findByAttribute(String name, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findByAttribute(Map<String, Object> queryParams) {
		// TODO Auto-generated method stub
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createCriteria(persistentClass);
		for (String s : queryParams.keySet()) {
			criteria.add(Restrictions.eq(s, queryParams.get(s)));
		}
		return (T) criteria.uniqueResult();
	}

	@Override
	public T findByAttributeIgnoreCase(Map<String, Object> queryParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAllByAttribute(String name, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllByAttribute(Map<String, Object> queryParams) {
		// TODO Auto-generated method stub
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createCriteria(persistentClass);
		for (String s : queryParams.keySet()) {
			criteria.add(Restrictions.eq(s, queryParams.get(s)));
		}
		return criteria.list();
	}

	@Override
	public List<T> findAllByAttribute(Map<String, Object> queryParams,
			Map<String, Object> limitParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findLikelyByAttribute(Map<String, String> queryParams,
			Map<String, Object> limitParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findLikelyByAttribute(Map<String, String> queryParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findByPage(int start, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createCriteria(persistentClass);
		Integer totalResult = ((Number) criteria.setProjection(
				Projections.rowCount()).uniqueResult()).intValue();
		return totalResult;
	}

	@Override
	public int findCountByAttribute(Map<String, Object> queryParams) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T queryByPropertyCode(String propertyCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int executeBySql(String sql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteAllByName(String tableName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertByQuery() {
		// TODO Auto-generated method stub

	}

	@Override
	public <X> X findAllBySql(String hql, Object[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getEntitiesBySql(String sql, Class<T> clzz,
			String talbeAlias, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findByPage(int start, int count, PK id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countFilterByAttribute(JSONArray parameters) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<T> filterByAttribute(JSONArray parameters, int start,
			int fetchSize, boolean isOriginal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> sortColumnData(JSONArray parameters, String sortColumn,
			String direction, int start, int fetchSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getAllByOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getByPage(int start, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getStagingEntityByPK(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyFilterEntity(JSONObject parameters) {
		// TODO Auto-generated method stub

	}

	@Override
	public int countFilterByMultiAttributes(JSONArray parameters) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<T> filterByMultiAttributes(JSONArray parameters, int start,
			int fetchSize, boolean isOriginal) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAllList() {

		Query query = createQuery("from " + persistentClass.getSimpleName(),
				new Object[] {});
		return query.list();
	}

	public Query createQuery(final String queryString, final Object... values) {

		Assert.hasText(queryString, "queryString can't be null");
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}
}
