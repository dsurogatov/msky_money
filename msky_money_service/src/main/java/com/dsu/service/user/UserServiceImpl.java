/**
 * 
 */
package com.dsu.service.user;

import static com.dsu.dto.converter.ConverterUtils.toDTO;
import static com.dsu.dto.converter.ConverterUtils.toDTOList;
import static com.dsu.dto.converter.ConverterUtils.toEntity;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.dsu.dao.user.UserDao;
import com.dsu.dto.model.UserDTO;
import com.dsu.service.exception.ExceptionType;
import com.dsu.service.exception.MskyMoneyException;

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
	public UserDTO findById(Long id) {
		Assert.notNull(id);
		
		UserDTO userDTO = toDTO(dao.findById(id));
		if (userDTO == null) {
			throw new MskyMoneyException(ExceptionType.ENTITY_NOT_FINDED);
		}
		return userDTO;
	}

	/* (non-Javadoc)
	 * @see com.dsu.service.api.CrudService#findByFields(java.lang.String)
	 */
	@Override
	public List<UserDTO> findByFields(String findingValue) {
		if (StringUtils.isBlank(findingValue)) {
			return findAll();
		}
		return toDTOList(dao.findByFields(findingValue));
	}

	/* (non-Javadoc)
	 * @see com.dsu.service.api.CrudService#findAll()
	 */
	@Override
	public List<UserDTO> findAll() {
		return toDTOList(dao.findAll());
	}

	/* (non-Javadoc)
	 * @see com.dsu.service.api.CrudService#create(java.lang.Object)
	 */
	@Override
	public UserDTO create(UserDTO instance) {
		Assert.notNull(instance, "[Assertion failed] - 'instance' is required; it must not be null");
		Assert.isNull(instance.getId(), "[Assertion failed] - the 'instance.getId()' must be null");
		
		return toDTO(dao.save(toEntity(instance)));
	}

	/* (non-Javadoc)
	 * @see com.dsu.service.api.CrudService#update(java.lang.Object)
	 */
	@Override
	public UserDTO update(UserDTO instance) {
		Assert.notNull(instance, "[Assertion failed] - 'instance' is required; it must not be null");
		Assert.notNull(instance.getId(), "[Assertion failed] - 'instance.getId()' is required; it must not be null");
		
		return toDTO(dao.save(toEntity(instance)));
	}

	/* (non-Javadoc)
	 * @see com.dsu.service.api.CrudService#delete(java.lang.Object)
	 */
	@Override
	public void delete(UserDTO instance) {
		dao.delete(instance.getId());
	}

}
