/**
 * 
 */
package com.dsu.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dsu.dao.api.AbstractCrudDao;
import com.dsu.domain.model.User;

/**
 * @author nescafe
 *
 */
@Repository
public class UserDaoImpl extends AbstractCrudDao<User> implements UserDao {

	/* (non-Javadoc)
	 * @see com.dsu.dao.user.UserDao#findByName(java.lang.String)
	 */
	@Override
	public List<User> findByCols(String findedValue) {
		@SuppressWarnings("unchecked")
//		List<String[]> results = entityManager.createQuery(" SELECT o.name, o.id || o.login || o.name || 'sss' FROM User o ")
//				.getResultList();
		List<User> results = entityManager.createQuery(" SELECT o FROM User o WHERE concat(o.id, o.name, o.login) LIKE :value ")
				.setParameter("value", "%" + findedValue + "%").getResultList();
		return results;
	}

}
