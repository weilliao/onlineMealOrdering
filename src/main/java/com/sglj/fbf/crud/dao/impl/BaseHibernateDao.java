package com.sglj.fbf.crud.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.impl.CriteriaImpl.OrderEntry;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.sglj.fbf.crud.dao.Dao;
import com.sglj.fbf.crud.dao.support.QueryRuleSupport;
import com.sglj.fbf.crud.query.Page;
import com.sglj.fbf.crud.query.QueryRule;
import com.sglj.fbf.utils.BeanUtil;

/**
 * 
 * @author guanhongwei
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class BaseHibernateDao extends HibernateDaoSupport implements Dao {
	
	/**
	 */
	@Override
	public void save(Object entity) {
		getHibernateTemplate().save(entity);
	}

	/**
	 */
	@Override
	public void save(Object[] aentity) {
		if (aentity != null && aentity.length > 0) {
			for (int i = 0; i < aentity.length; i++) {
				save(aentity[i]);
			}
		}
	}

	/**
	 */
	@Override
	public void save(Collection<?> entities) {
		if (entities != null && !entities.isEmpty()) {
			for (Object entity : entities) {
				save(entity);
			}
		}
	}

	/**
	 */
	@Override
	public void update(Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	/**
	 */
	@Override
	public void update(Object[] aentity) {
		if (aentity != null && aentity.length > 0) {
			for (int i = 0; i < aentity.length; i++) {
				update(aentity[i]);
			}
		}
	}

	/**
	 */
	@Override
	public void update(Collection<?> entities) {
		if (entities != null && !entities.isEmpty()) {
			for (Object entity : entities) {
				update(entity);
			}
		}
	}

	/**
	 */
	@Override
	public void delete(Object entity) {
		getHibernateTemplate().delete(entity);
	}

	/**
	 */
	@Override
	public void delete(Collection<?> entities) {
		getHibernateTemplate().deleteAll(entities);
	}

	/**
	 */
	@Override
	public void deleteById(Class<?> clazz, Serializable id) {
		getHibernateTemplate().delete(get(clazz, id));
	}

	/**
	 */
	@Override
	public <T> T get(Class<T> clazz, Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}

	/**
	 */
	@Override
	public <T> List<T> getAll(Class<T> clazz) {
		return getHibernateTemplate().loadAll(clazz);
	}

	/**
	 * @param statement hql
	 */
	@Override
	public Long getCount(String statement, Map<String, Object> params) {
		statement = " select count (*) " + removeSelect(removeOrders(statement));
		List<?> list = this.find(statement, params);

		return (Long) list.get(0);
	}

	protected String removeSelect(String statement) {
		statement = StringUtils.remove(statement, " fetch");
		int beginPos = statement.toLowerCase(Locale.US).indexOf("from");

		Assert.isTrue(beginPos != -1, " the statement : " + statement + " must has a keyword 'from'");
		return statement.substring(beginPos);
	}

	protected String removeOrders(String statement) {
		Pattern pattern = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(statement);

		StringBuffer buffer = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(buffer, "");
		}
		matcher.appendTail(buffer);
		return buffer.toString();
	}

	protected String removeGroup(String statement) {
		Pattern pattern = Pattern.compile("group\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(statement);

		StringBuffer buffer = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(buffer, "");
		}
		matcher.appendTail(buffer);
		return buffer.toString();
	}

	/**
	 * @param statement hql
	 */
	@Override
	public List<?> find(final String statement, final Map<String, Object> params) {
		
		return this.getHibernateTemplate().execute(new HibernateCallback<List<?>>() {

			@Override
			public List<?> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(statement);

				if (params != null) {
					for (Map.Entry<String, Object> entry : params.entrySet()) {
						String name = entry.getKey();
						Object value = entry.getValue();

						if (value instanceof Collection)
							query.setParameterList(name, (Collection<?>) value);
						else if (value instanceof Object[])
							query.setParameterList(name, (Object[]) value);
						else
							query.setParameter(name, value);

					}
				}

				return query.list();
			}
		});
	}

	/**
	 * @param sql 
	 */
	@Override
	public List<Object[]> findByNativeSQL(final String sql, final Object... values) {
		return this.findByNativeSQL(sql, null, values);
	}

	/**
	 * 根据sql语句进行单值查询
	 * @param sql 
	 */
	@Override
	public Object findObjectByNativeSQL(final String sql, final Object... values) {
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery(sql);

				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}

				return query.uniqueResult();
			}

		});
	}
	
	/**
	 * 根据hql语句进行单值查询
	 * @param hql
	 * @param values
	 * @return
	 */
	public Object findUniqueResult(final String hql, final Object... values){
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				
				if(values != null){
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				
				return query.uniqueResult();
			}
			
		});
	}
	
	/**
	 * 根据 QueryRule 进行单值查询
	 * @param <T>
	 * @param queryRule
	 * @param clazz
	 * @return
	 */
	public <T> T findUniqueResult(final QueryRule queryRule, final Class<T> clazz){
		return (T) getHibernateTemplate().execute(new HibernateCallback<T>() {
			
			@Override
			public T doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(clazz);
				QueryRuleSupport.createCriteriaWithQueryRule(criteria, queryRule);

				CriteriaImpl impl = (CriteriaImpl) criteria;
				Projection projection = impl.getProjection();
				
				criteria.setProjection(projection);
				if (projection == null){
					criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
				}
				
				return (T) criteria.uniqueResult();
			}
		});
	}

	/**
	 */
	@Override
	public void flush() {
		getHibernateTemplate().flush();
	}

	/**
	 */
	@Override
	public void clear() {
		getHibernateTemplate().clear();
	}

	/**
	 */
	@Override
	public void evict(Object entity) {
		getHibernateTemplate().evict(entity);
	}

	/**
	 */
	@Override
	public boolean contains(Object entity) {
		return getHibernateTemplate().contains(entity);
	}

	/**
	 * @param statement hql
	 */
	@Override
	public Page find(final String statement, final Map<String, Object> params, final int pageIndex, final int pageSize) {
		
		return (Page) getHibernateTemplate().execute(new HibernateCallback<Page>() {
			@Override
			public Page doInHibernate(Session session) throws HibernateException {
				Page page = new Page(pageSize, pageIndex);

				Long totalCount = getCount(statement, params);
				if (totalCount < 1)
					return page;

				Query query = session.createQuery(statement);
				if (params != null) {
					for (Map.Entry<String, Object> entry : params.entrySet()) {
						String name = entry.getKey();
						Object value = entry.getValue();

						if (value instanceof Collection)
							query.setParameterList(name, (Collection) value);
						else if (value instanceof Object[])
							query.setParameterList(name, (Object[]) value);
						else
							query.setParameter(name, value);

					}
				}

				List result = query.setFirstResult(page.getRecordIndex()).setMaxResults(pageSize).list();
				page.setTotalCount(totalCount.intValue());
				page.setResult(result);

				return page;
			}
		});
	}

	/**
	 * @param sql
	 */
	@Override
	public Long getCountByNativeSQL(String sql, Object... values) {
		sql = " select count (1) " + removeSelect(removeGroup(removeOrders(sql)));
		BigDecimal count = (BigDecimal) this.findObjectByNativeSQL(sql, values);

		return count.longValue();
	}

	/**
	 * @param sql
	 */
	@Override
	public Page findByNativeSQL(final String sql, final Class<?> clazz, final int pageIndex, final int pageSize, final Object... values) {
		return (Page) getHibernateTemplate().execute(new HibernateCallback<Page>() {
			@Override
			public Page doInHibernate(Session session) throws HibernateException {
				Page page = new Page(pageSize, pageIndex);

				Long totalCount = getCountByNativeSQL(sql, values);
				if (totalCount < 1)
					return page;

				SQLQuery query = session.createSQLQuery(sql);

				if (clazz != null)
					query.addEntity(clazz);

				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}

				List result = query.setFirstResult(page.getRecordIndex()).setMaxResults(pageSize).list();
				page.setTotalCount(totalCount.intValue());
				page.setResult(result);

				return page;
			}

		});
	}

	/**
	 * @param sql
	 */
	@Override
	public Page findByNativeSQL(String sql, int pageIndex, int pageSize, Object... values) {
		return this.findByNativeSQL(sql, null, pageIndex, pageSize, values);
	}

	/**
	 * @param sql
	 */
	@Override
	public Page findMapByNativeSQL(final String sql, final int pageIndex, final int pageSize, final Object... values) {
		return getHibernateTemplate().execute(new HibernateCallback<Page>() {
			@Override
			public Page doInHibernate(Session session) throws HibernateException {
				Page page = new Page(pageSize, pageIndex);

				Long totalCount = getCountByNativeSQL(sql, values);
				if (totalCount < 1)
					return page;

				SQLQuery query = session.createSQLQuery(sql);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}

				List result = query.setFirstResult(page.getRecordIndex()).setMaxResults(pageSize).list();
				page.setTotalCount(totalCount.intValue());
				page.setResult(result);

				return page;
			}

		});
	}

	/**
	 * @param queryRule
	 */
	@Override
	public Page find(final QueryRule queryRule, final Class<?> clazz, final int pageIndex, final int pageSize) {
		return (Page) getHibernateTemplate().execute(new HibernateCallback<Page>() {
			@Override
			public Page doInHibernate(Session session) throws HibernateException {
				Page page = new Page(pageSize, pageIndex);

				Criteria criteria = session.createCriteria(clazz);
				QueryRuleSupport.createCriteriaWithQueryRule(criteria, queryRule);

				CriteriaImpl impl = (CriteriaImpl) criteria;
				Projection projection = impl.getProjection();

				List<OrderEntry> orderEntries = getOrders(impl);

				BeanUtil.setProperty(impl, "orderEntries", new ArrayList());

				int totalCount = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
				if (totalCount < 1)
					return page;

				criteria.setProjection(projection);
				if (projection == null)
					criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
				BeanUtil.setProperty(impl, "orderEntries", orderEntries);

				List result = criteria.setFirstResult(page.getRecordIndex()).setMaxResults(pageSize).list();
				page.setTotalCount(totalCount);
				page.setResult(result);

				return page;
			}

		});
	}

	protected List<OrderEntry> getOrders(Criteria criteria) {
		CriteriaImpl impl = (CriteriaImpl) criteria;
		try {
			Field field = criteria.getClass().getDeclaredField("orderEntries");
			field.setAccessible(true);
			return (List<OrderEntry>) field.get(impl);
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility can't throw ");
		}
	}

	/**
	 * @param hql
	 */
	@Override
	public int executeUpdate(String hql, Object... values) {
		return getHibernateTemplate().bulkUpdate(hql, values);
	}

	/**
	 * @param sql
	 */
	@Override
	public int executeUpdateByNativeSQL(final String sql, final Object... values) {
		Integer updateCount = (Integer) getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			public Integer doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);

				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}

				return query.executeUpdate();
			}
		});

		return updateCount;
	}

	/**
	 * @param sql
	 */
	@Override
	public <T> List<T> findByNativeSQL(final String sql, final Class<T> clazz, final Object... values) {
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
			@Override
			public List<T> doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				if (clazz != null) {
					query.addEntity(clazz);
				}
				
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				return query.list();
			}
		});
	}

	/**
	 * @param sql
	 */
	@Override
	public List<Map<String, Object>> findMapByNativeSQL(final String sql, final Object... values) {
		return getHibernateTemplate().execute(new HibernateCallback<List<Map<String, Object>>>() {
			@Override
			public List<Map<String, Object>> doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				return query.list();
			}
		});
	}

	/**
	 * @param queryRule
	 */
	@Override
	public <T> List<T> find(final QueryRule queryRule, final Class<T> clazz) {
		return getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
			@Override
			public List<T> doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(clazz);
				QueryRuleSupport.createCriteriaWithQueryRule(criteria, queryRule);

				CriteriaImpl impl = (CriteriaImpl) criteria;
				Projection projection = impl.getProjection();
				
				criteria.setProjection(projection);
				if (projection == null){
					criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
				}
				
				return criteria.list();
			}
		});
	}
	/**
	 * 特殊的自定义sql查询
	 */
	@Override
	public Page findMapByNativeSpecialSQL(final String sql, final int pageIndex, final int pageSize, final Object... values) {
		return getHibernateTemplate().execute(new HibernateCallback<Page>() {
			@Override
			public Page doInHibernate(Session session) throws HibernateException {
				Page page = new Page(pageSize, pageIndex);
				String sqlcount = " select count (*) " +"from (" +sql+")";
				BigDecimal count = (BigDecimal) findObjectByNativeSQL(sqlcount, values);
				Long totalCount =count.longValue();
				if (totalCount < 1)
					return page;

				SQLQuery query = session.createSQLQuery(sql);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}

				List result = query.setFirstResult(page.getRecordIndex()).setMaxResults(pageSize).list();
				page.setTotalCount(totalCount.intValue());
				page.setResult(result);

				return page;
			}

		});
	}

}
