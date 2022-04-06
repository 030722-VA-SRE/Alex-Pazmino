package com.revature.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dtos.UserDto;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class UserService {
	
	private UserRepository ur;
	@Autowired
	public UserService(UserRepository ur) {
		super();
		this.ur = ur;
	}
	
	public List<UserDto> getUsers() {
		List<User> users = ur.findAll();
		
		return users.stream()
				.map(UserDto::new)
				.collect(Collectors.toList());
	}
}
