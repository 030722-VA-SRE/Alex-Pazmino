package com.revature.controllers;

import java.util.List;
import java.util.UUID;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dtos.UserDto;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService us;
	private AuthService as;
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	public UserController(UserService us, AuthService as) {
		super();
		this.us = us;
		this.as = as;
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getUsers(@RequestHeader(value = "Authorization", required = false) String token) {
		
		MDC.put("requestId", UUID.randomUUID().toString());
		
		as.verify(token);
		
		LOG.info("users retrieved");
		return new ResponseEntity<>(us.getUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@RequestHeader(value = "Authorization", required = false)String token, @PathVariable("id")int id){
		return new ResponseEntity<>(us.getUserById(id),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<User> postUser(@RequestHeader(value = "Authorization", required = false)String token, @RequestBody User user) {
		MDC.put("requestId", UUID.randomUUID().toString());
		
		as.verify(token);
		User createUser = us.createUser(user);
		return new ResponseEntity<>(createUser,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> putUser(@RequestHeader(value = "Authorization", required = false)String token, @PathVariable("id")int id, @RequestBody User user) {
		MDC.put("requestId", UUID.randomUUID().toString());
		
		as.verify(token);
		user.setId(id);
		return new ResponseEntity<>(us.updateUser(id, user), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@RequestHeader(value = "Authorization", required = false)String token, @PathVariable("id")int id){
		MDC.put("requestId", UUID.randomUUID().toString());
		
		as.verify(token);
		us.deleteUser(id);
		return new ResponseEntity<>("User of id: "+id+" was deleted.", HttpStatus.OK);
	}

}
