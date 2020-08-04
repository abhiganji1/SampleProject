package com.uvaan.samplepi.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uvaan.sampleapi.domain.User;
import com.uvaan.sampleapi.exception.ResourceNotFoundException;
import com.uvaan.sampleapi.param.UserParam;
import com.uvaan.sampleapi.repository.UserRepository;
import com.uvaan.sampleapi.service.UserService;

//@ Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	
	@Autowired
	UserService usersServices;

	@Override
	public Optional<User> getUserById(Long id) {
		Optional<User> a = userRepository.findById(id);
		return a;
	}

	@Override
	public User createUser(UserParam userParam) {
		

		User user = null;
		user = new User();
		user.setEmail(userParam.getEmail());
		user.setPassword(userParam.getPassword());
		user.setCreatedby(userParam.getCreatedby());
		user.setCreatedDate(userParam.getCreatedDate());
		user.setUpdatedBy(userParam.getUpdatedby());
		user.setUpdatedDate(userParam.getUpdaateddate());
		
		
		try {
			user = userRepository.save(getUserByParams(userParam, null));
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("User is already exist for selected the mobile & countryCode,Please provide different valid fields.");
		}
		return user;
	}

	@Override
	public List<User> getAllUsers(UserParam userParam) {
		List<User> result = null;
		StringBuffer sqlQuery = new StringBuffer("from User a where 1=1");

		if (0 != userParam.getId()) {
			sqlQuery.append(" AND a.id = :id");
		}
		if (null != userParam.getEmail()) {
			sqlQuery.append(" AND a.email = :email");
		}
		if (null != userParam.getPassword()) {
			sqlQuery.append(" AND a.password = :password");
		}
		if (0 != userParam.getCreatedby()) {
			sqlQuery.append(" AND a.createdby = :createdby");
		}
		if (null != userParam.getCreatedDate()) {
			sqlQuery.append(" AND a.createddate = :createddate");
		}
		if (0 != userParam.getCreatedby()) {
			sqlQuery.append(" AND a.updatedby = :updatedby");
		}
		if (null != userParam.getUpdaateddate()) {
			sqlQuery.append(" AND a.updateddate = :updateddate");
		}

		sqlQuery.append(" order by a.id");
		
		Query query = entityManager.createQuery(sqlQuery.toString());

		if (0 != userParam.getId()) {
			sqlQuery.append(" AND a.id = :id");
		}
		if (null != userParam.getEmail()) {
			sqlQuery.append(" AND a.email = :email");
		}
		if (null != userParam.getPassword()) {
			sqlQuery.append(" AND a.password = :password");
		}
		if (0 != userParam.getCreatedby()) {
			sqlQuery.append(" AND a.createdby = :createdby");
		}
		if (null != userParam.getCreatedDate()) {
			sqlQuery.append(" AND a.createddate = :createddate");
		}
		if (0 != userParam.getCreatedby()) {
			sqlQuery.append(" AND a.updatedby = :updatedby");
		}
		if (null != userParam.getUpdaateddate()) {
			sqlQuery.append(" AND a.updateddate = :updateddate");
		}

		
		
		result = query.getResultList();

		return result;
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);

	}

	@Override
	public User updateUser(UserParam userParam) {
		User user = getUserById(userParam.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userParam.getId()));
		try {
			user = getUserByParams(userParam, user);
			user = userRepository.save(user);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("User is already exist for selected the mobile & countryCode,Please provide different valid fields.");
		}
		return user;
	}

	private User getUserByParams(UserParam userParam, User user) {
		user.setId(userParam.getId());
		if (null == userParam.getEmail()) {
			userParam.setEmail(user.getEmail());

		}
		if (null == userParam.getPassword()) {
			userParam.setPassword(user.getPassword());

		}
		if (0 == userParam.getCreatedby()) {
			userParam.setCreatedby(user.getCreatedby());

		}
		if (null == userParam.getCreatedDate()) {
			userParam.setCreatedDate(user.getCreatedDate());

		}
		if (0 == userParam.getCreatedby()) {
			userParam.setUpdatedby(user.getUpdatedBy());

		}
		if (null == userParam.getUpdaateddate()) {
			userParam.setUpdaateddate(user.getUpdatedDate());

		}

		

	user.setEmail(userParam.getEmail());
	user.setPassword(userParam.getPassword());
	user.setCreatedby(userParam.getCreatedby());
	user.setCreatedDate(userParam.getCreatedDate());
	user.setUpdatedBy(userParam.getUpdatedby());
	user.setUpdatedDate(userParam.getUpdaateddate());
		return user;
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	

}
