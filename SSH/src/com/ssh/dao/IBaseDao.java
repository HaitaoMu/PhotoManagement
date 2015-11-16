package com.ssh.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IBaseDao<T, PK extends Serializable> {

	/**
	 * Generic method used to get all objects of a particular type. This is the
	 * same as lookup up all rows in a table.
	 * 
	 * @return List of populated objects
	 */
	List<T> getAll();

	/**
	 * Gets all records without duplicates.
	 * <p>
	 * Note that if you use this method, it is imperative that your model
	 * classes correctly implement the hashcode/equals methods
	 * </p>
	 * 
	 * @return List of populated objects
	 */
	List<T> getAllDistinct();

	/**
	 * Generic method to get an object based on class and identifier. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the identifier (primary key) of the object to get
	 * @return a populated object
	 * @see org.springframework.orm.ObjectRetrievalFailureException
	 */
	T get(PK id);

	/**
	 * Checks for existence of an object of type T using the id arg.
	 * 
	 * @param id
	 *            the id of the entity
	 * @return - true if it exists, false if it doesn't
	 */
	boolean exists(PK id);

	/**
	 * Generic method to save an object - handles both update and insert.
	 * 
	 * @param object
	 *            the object to save
	 * @return the persisted object
	 */
	T save(T object);

	/**
	 * Generic method to save an object - handles both update and insert.
	 * 
	 * @param object
	 */
	public void saveOrUpdate(T object);

	/**
	 * Generic method to delete an object based on class
	 * 
	 * @param object
	 *            the object to remove
	 */
	void remove(T object);

	/**
	 * Generic method to delete an object based on class and id
	 * 
	 * @param id
	 *            the identifier (primary key) of the object to remove
	 */
	void remove(PK id);

	/**
	 * Find a list of records by using a named query
	 * 
	 * @param queryName
	 *            query name of the named query
	 * @param queryParams
	 *            a map of the query names and the values
	 * @return a list of the records found
	 */
	List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams);

	/**
	 * Generic method to get an object based on class and attribute
	 * 
	 * @param name
	 *            query name of class attribute
	 * @param value
	 *            of attribute
	 * @return the persisted object
	 */
	T findByAttribute(String name, Object value);

	/**
	 * Generic method to get an object based on class and more attributes
	 * 
	 * @param queryParams
	 *            a map of the query attribute names and the values
	 * @return the persisted object
	 */
	T findByAttribute(Map<String, Object> queryParams);

	/**
	 * IgnoreCase query
	 * 
	 * @param queryParams
	 * @return
	 */
	T findByAttributeIgnoreCase(Map<String, Object> queryParams);

	/**
	 * Generic method to get an object based on class and attribute
	 * 
	 * @param name
	 *            query name of class attribute
	 * @param value
	 *            of attribute
	 * @return a list of the records found
	 */
	List<T> findAllByAttribute(String name, Object value);

	/**
	 * Generic method to get an object based on class and more attributes
	 * 
	 * @param queryParams
	 *            a map of the query attribute names and the values
	 * @return a list of the records found
	 */
	List<T> findAllByAttribute(Map<String, Object> queryParams);

	List<T> findAllByAttribute(Map<String, Object> queryParams,
			Map<String, Object> limitParams);

	/**
	 * fuzzyQuery
	 * 
	 * @param queryParams
	 * @return
	 */
	List<T> findLikelyByAttribute(Map<String, String> queryParams,
			Map<String, Object> limitParams);

	/**
	 * fuzzyQuery
	 * 
	 * @param queryParams
	 * @return
	 */
	List<T> findLikelyByAttribute(Map<String, String> queryParams);

	/**
	 * ∑÷“≥≤È—Ø
	 * 
	 * @param start
	 * @param count
	 * @return
	 */
	List<T> findByPage(int start, int count);

	void update(T entity);

	int getTotalCount();

	int findCountByAttribute(Map<String, Object> queryParams);

	T queryByPropertyCode(String propertyCode);

	int executeBySql(String sql);

	void deleteAllByName(String tableName);

	void insertByQuery();

	public <X> X findAllBySql(final String hql, final Object[] values);

	public List<T> getEntitiesBySql(String sql, Class<T> clzz,
			String talbeAlias, Object... params);

	/**
	 * @param start
	 * @param count
	 * @param id
	 * @return
	 */
	List<T> findByPage(int start, int count, PK id);

	int countFilterByAttribute(JSONArray parameters);

	List<T> filterByAttribute(JSONArray parameters, int start, int fetchSize,
			boolean isOriginal);

	List<T> sortColumnData(JSONArray parameters, String sortColumn,
			String direction, int start, int fetchSize);

	List<T> getAllByOrder();

	List<T> getByPage(int start, int count);

	T getStagingEntityByPK(Object entity);

	/**
	 * @param parameters
	 */
	void modifyFilterEntity(JSONObject parameters);

	int countFilterByMultiAttributes(JSONArray parameters);

	List<T> filterByMultiAttributes(JSONArray parameters, int start,
			int fetchSize, boolean isOriginal);

}
