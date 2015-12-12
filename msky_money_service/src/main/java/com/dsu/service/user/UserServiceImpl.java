/**
 * 
 */
package com.dsu.service.user;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsu.dao.user.UserDao;
import com.dsu.domain.model.User;

/**
 * @author nescafe
 * Service contains the main methods for working with User entity
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao dao;

	/* (non-Javadoc)
	 * @see com.dsu.service.api.CrudService#findById(java.lang.Long)
	 */
	@Override
	public User findById(Long id) {
		return dao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.dsu.service.api.CrudService#findByFields(java.lang.String)
	 */
	@Override
	public List<User> findByFields(String findingValue) {
		if (StringUtils.isBlank(findingValue)) {
			return findAll();
		}
		return dao.findByFields(findingValue);
	}

	/* (non-Javadoc)
	 * @see com.dsu.service.api.CrudService#findAll()
	 */
	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

	/* (non-Javadoc)
	 * @see com.dsu.service.api.CrudService#create(java.lang.Object)
	 */
	@Override
	public User create(User instance) {
		return dao.save(instance);
	}

	/* (non-Javadoc)
	 * @see com.dsu.service.api.CrudService#update(java.lang.Object)
	 */
	@Override
	public User update(User instance) {
		return dao.save(instance);
	}

	/* (non-Javadoc)
	 * @see com.dsu.service.api.CrudService#delete(java.lang.Object)
	 */
	@Override
	public void delete(User instance) {
		dao.delete(instance.getId());
	}

}
