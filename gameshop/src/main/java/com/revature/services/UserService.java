package com.revature.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.revature.dtos.UserDto;
import com.revature.exceptions.GameNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class UserService {
	
	private UserRepository ur;
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	public UserService(UserRepository ur) {
		super();
		this.ur = ur;
	}
	
	public List<UserDto> getUsers() {
		List<User> users = ur.findAll(Sort.by(Sort.Direction.ASC, "id"));
		
		if(users.isEmpty()) {
			throw new UserNotFoundException("Users list is null or not found.");
		}
		
		return users.stream()
				.map(UserDto::new)
				.collect(Collectors.toList());
	}
	
	public UserDto getUserById(int id) {
		return new UserDto(ur.findById(id).orElseThrow(UserNotFoundException::new));
	}
	
	@Transactional
	public User createUser(User user) {
		LOG.info("User created", user);
		return ur.save(user);
	}
	
	@Transactional
	public User updateUser(int id,User updateUser) {
		User user = ur.findById(id).orElseThrow(GameNotFoundException::new);
		
		updateUser.setId(user.getId());
		LOG.info("User updated", updateUser);
		return ur.save(updateUser);
	}
	
	@Transactional
	public void deleteUser(int id) {
		User user = ur.findById(id).orElseThrow(UserNotFoundException::new);
		LOG.info("User deleted", user);
		ur.delete(user);
	}
}
