/**
 * 
 */
package com.dsu.dao.api;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author nescafe Abstract class for the base implementation of CrudDao
 */
public abstract class AbstractCrudDao<I> implements CrudDao<I> {

	//private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCrudDao.class);

	@PersistenceContext
	protected EntityManager entityManager;

	private Class<I> persistentClass;

	@SuppressWarnings("unchecked")
	protected Class<I> getPersistentClass() {
		if (persistentClass == null) {
			this.persistentClass = (Class<I>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return persistentClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.twinstorm.expoedge.model.common.GenericDao#findById(java.io.
	 * Serializable)
	 */
	public I findById(Long id) {
		I instance = (I) entityManager.find(getPersistentClass(), id);
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.twinstorm.expoedge.model.common.GenericDao#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<I> findAll() {
		List<I> results = entityManager.createQuery(" SELECT o FROM " + getPersistentClass().getName() + " o ").getResultList();
		return results;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.twinstorm.expoedge.model.common.GenericDao#save(I)
	 */
	public I save(I transientInstance) {
		transientInstance = entityManager.merge(transientInstance);
		return transientInstance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.twinstorm.expoedge.model.common.GenericDao#count()
	 */
	@SuppressWarnings("unchecked")
	public int count() {
		List<Long> res = entityManager.createQuery("select count(*) from " + getPersistentClass().getName() + " as clazz").getResultList();
		return res.iterator().next().intValue();
	}

	public void flush() {
		entityManager.flush();
	}

	public void delete(Long id) {
		I instance = findById(id);
		if (instance != null) {
			entityManager.remove(instance);
		}
	}
}
