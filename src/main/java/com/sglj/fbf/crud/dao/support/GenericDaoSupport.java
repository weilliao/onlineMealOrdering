package com.sglj.fbf.crud.dao.support;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.sglj.fbf.base.BaseEntity;
import com.sglj.fbf.crud.dao.Dao;
import com.sglj.fbf.crud.dao.GenericDao;
import com.sglj.fbf.crud.query.Page;
import com.sglj.fbf.crud.query.QueryRule;
import com.sglj.fbf.utils.SessionUtils;

/**
 * 泛型对象数据访问操作，是框架的基本DAO，
 * 用于支持商业逻辑数据访问的抽象委托类，
 * 通过范型方式定义了访问基本Dao操作的缺省实现，
 * 可用于支持直接的HQL和SQL语句查询。
 * @author guanhongwei
 *
 * @param <T>
 */
@SuppressWarnings("unchecked")
public abstract class GenericDaoSupport<T extends BaseEntity> implements GenericDao<T> {
	
	@Resource(name = "baseHibernateDao")
	private Dao dao;

	private Class<T> clazz;
	
	public GenericDaoSupport(){
		clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	/**
	 * @return the dao
	 */
	public Dao getDao() {
		return dao;
	}

	/**
	 * 用于子类覆盖，设置自定义DAO实现类
	 * 
	 * @param dao
	 *            the dao to set
	 */
	protected void setDao(Dao dao) {
		this.dao = dao;
	}
	
	/**
	 * 用于子类覆盖,在save,update之前调用
	 * 
	 * @param entity
	 */
	protected void prepareObjectBeforeSaveOrUpdate(T entity) {
		if (StringUtils.isEmpty(entity.getId())) {
			entity.setFirstInsert(new Date());
			
			if (SessionUtils.getUser() != null) {
				entity.setCreateUser(SessionUtils.getUser().getUserId());
				entity.setDataOwner(SessionUtils.getUser().getUserId());
			}
		}

		entity.setLastModified(new Date());
		if (SessionUtils.getUser() != null) {
			if(StringUtils.isNotBlank(SessionUtils.getUser().getUserId())){
				entity.setUpdateUser(SessionUtils.getUser().getUserId());
			}
		}

	}

	@Override
	public void save(T entity) {
		
		prepareObjectBeforeSaveOrUpdate(entity);
		
		dao.save(entity);
	}

	@Override
	public void save(T[] aentity) {
		
		for (T t : aentity) {
			prepareObjectBeforeSaveOrUpdate(t);
		}
		
		dao.save(aentity);
	}

	@Override
	public void save(Collection<T> entities) {
		
		for (T t : entities) {
			prepareObjectBeforeSaveOrUpdate(t);
		}
		
		dao.save(entities);
		
	}

	@Override
	public void update(T entity) {
		
		prepareObjectBeforeSaveOrUpdate(entity);
		
		dao.update(entity);
		
	}

	@Override
	public void update(T[] aentity) {
		
		for (T t : aentity) {
			prepareObjectBeforeSaveOrUpdate(t);
		}
		
		dao.update(aentity);
		
	}

	@Override
	public void update(Collection<T> entities) {
		
		for (T t : entities) {
			prepareObjectBeforeSaveOrUpdate(t);
		}
		
		dao.update(entities);
		
	}

	@Override
	public void delete(T entity) {
		dao.delete(entity);
		
	}

	@Override
	public void delete(Collection<T> entities) {
		dao.delete(entities);
	}

	@Override
	public void deleteById(String id) {
		dao.deleteById(clazz, id);
		
	}

	@Override
	public T get(String id) {
		return dao.get(clazz, id);
	}

	@Override
	public List<T> getAll() {
		return dao.getAll(clazz);
	}

	@Override
	public List<T> find(QueryRule queryRule) {
		return dao.find(queryRule, clazz);
	}

	/**
	 * 根据传入的查询语句，以及过滤参数与值绑定的Map对象，进行数据访问。
	 * @param statement hql
	 */
	@Override
	public List<T> find(String statement, Map<String, Object> params) {
		return (List<T>) dao.find(statement, params);
	}

	/**
	 * 根据传入的查询语句，过滤参数与值绑定的Map对象，以及页码，每页记录数，获取分页对象。
	 * 
	 */
	@Override
	public Page find(QueryRule queryRule, int pageIndex, int pageSize) {
		return dao.find(queryRule, clazz, pageIndex, pageSize);
	}

	/**
	 * @param statement hql
	 */
	@Override
	public Page find(String statement, Map<String, Object> params, int pageIndex, int pageSize) {
		return dao.find(statement, params, pageIndex, pageSize);
	}

	@Override
	public void deleteById(String id, String delReason) {
		T t = this.get(id);
		t.setIsDeleted(true);
		t.setDelReason(delReason);
		this.update(t);
	}

	@Override
	public void evict(Object entity) {
		dao.evict(entity);
	}
	
	/**
	 * 获取总记录数
	 * 
	 * @param statement hql
	 * @param params
	 * @return
	 */
	protected Long getCount(String statement, Map<String, Object> params) {
		return dao.getCount(statement, params);
	}
	
	/**
	 * 获取总记录数
	 * 
	 * @param sql
	 * @param values
	 * @return
	 */
	protected Long getCountByNativeSQL(String sql, Object... values) {
		return dao.getCountByNativeSQL(sql, values);
	}
	
	/**
	 * 根据传入的数据库原生SQL语句，以及查询变量所对应的参数值，进行数据访问。
	 * 
	 * @param sql
	 * @param values
	 * @return
	 */
	protected List<Object[]> findByNativeSQL(String sql, Object... values) {
		return dao.findByNativeSQL(sql, values);
	}
	
	/**
	 * 根据传入的数据库原生SQL语句，以及查询变量所对应的参数值，进行数据访问，原生查询通过一个SQL语句和隐式的映像到一个实体。
	 * 
	 * 映射元数据为基础的一个实体，它认为原生查询的结果集中的栏将完全匹配实体的O/R映射。 原生SQL查询的映射的实体通过clazz参数来确定。
	 * 
	 * @param sql
	 * @param clazz
	 * @param values
	 * @return
	 */
	protected List<T> findByNativeSQL(String sql, Class<T> clazz, Object... values) {
		return dao.findByNativeSQL(sql, clazz, values);
	}
	
	/**
	 * 根据传入的数据库原生SQL语句，查询变量所对应的参数值，以及页码，每页记录数，及获取分页对象。
	 * 
	 * @param sql
	 * @param clazz
	 * @param pageIndex
	 * @param pageSize
	 * @param values
	 * @return
	 */
	public Page findByNativeSQL(String sql, Class<?> clazz, int pageIndex, int pageSize, Object... values) {
		return dao.findByNativeSQL(sql, clazz, pageIndex, pageSize, values);
	}
	
	/**
	 * @param sql
	 * @param pageIndex
	 * @param pageSize
	 * @param values
	 * @return
	 */
	protected Page findMapByNativeSQL(String sql, int pageIndex, int pageSize, Object... values) {
		return dao.findMapByNativeSQL(sql, pageIndex, pageSize, values);
	}

	/**
	 * 根据原生sql语句查询List<Map<String, Object>
	 * 
	 * @param sql
	 * @param values
	 * @return
	 */
	protected List<Map<String, Object>> findMapByNativeSQL(String sql, Object... values) {
		return dao.findMapByNativeSQL(sql, values);
	}
	
	/**
	 * @param hql
	 * @param values
	 * @return
	 */
	public int executeUpdate(String hql, Object... values) {
		return dao.executeUpdate(hql, values);
	}

	/**
	 * 
	 * @param sql
	 * @param values
	 * @return
	 */
	public int executeUpdateByNativeSQL(String sql, Object... values) {
		return dao.executeUpdateByNativeSQL(sql, values);
	}
	
	/**
	 * 根据 sql和参数 values 查询唯一的结果, 注意捕获处理查询的结果不为一条的异常
	 * NonUniqueResultException,HibernateException
	 * @param sql
	 * @param values
	 * @exception NonUniqueResultException
	 */
	public Object findUniqueResultBySql(String sql, Object... values) {
		return dao.findObjectByNativeSQL(sql, values);
	}
	
	/**
	 * 根据hql语句进行单值查询
	 * NonUniqueResultException,HibernateException
	 * @param hql
	 * @param values
	 * @return
	 */
	public Object findUniqueResult(String hql, Object... values) {
		return dao.findUniqueResult(hql, values);
	}
	
	/**
	 * 根据 QueryRule 进行单值查询
	 * NonUniqueResultException,HibernateException
	 * @param <T>
	 * @param queryRule
	 * @param clazz
	 * @return
	 */
	public T findUniqueResult(QueryRule queryRule, Class<T> clazz) {
		return dao.findUniqueResult(queryRule, clazz);
	}
	/**
	 * 特殊的sql分页查询，慎用
	 * @param sql
	 * @param pageIndex
	 * @param pageSize
	 * @param values
	 * @return
	 */
	public Page findMapByNativeSpecialSQL(final String sql, final int pageIndex, final int pageSize, final Object... values) {
		return dao.findMapByNativeSpecialSQL(sql, pageIndex, pageSize, values);
	}
}
