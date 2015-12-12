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
	public List<User> findByFields(String findingValue) {
		if (findingValue == null) {
			throw new IllegalArgumentException("findingValue is null");
		}
		
		@SuppressWarnings("unchecked")
		List<User> results = entityManager.createQuery(" SELECT o FROM User o WHERE concat(o.id, o.name, o.login) LIKE :value ")
				.setParameter("value", "%" + findingValue + "%").getResultList();
		return results;
	}

}
