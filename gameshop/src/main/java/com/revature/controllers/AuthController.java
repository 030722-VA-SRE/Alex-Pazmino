package com.revature.controllers;

import java.util.UUID;

import javax.transaction.Transactional;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dtos.UserDto;
import com.revature.services.AuthService;

@RestController
@RequestMapping("/auth")
@Transactional
public class AuthController {
	
	private AuthService as;
	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	public AuthController(AuthService as) {
		super();
		this.as = as;
	}
	
	@PostMapping
	public ResponseEntity<UserDto> login(@RequestParam(name = "username")String username, @RequestParam(name="password")String password){
		UserDto principal = as.login(username, password);
		MDC.put("requestId", UUID.randomUUID().toString());
		LOG.debug("starting login");
		
		String token = as.generateToken(principal);
		
		HttpHeaders hh = new HttpHeaders();
		
		hh.set("Authorization", token);
		
		LOG.debug("Login terminated successfully");
		LOG.info("login successful");
		
		return new ResponseEntity<>(principal, hh, HttpStatus.OK);
	}
}
