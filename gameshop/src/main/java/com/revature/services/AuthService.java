package com.revature.services;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.revature.dtos.UserDto;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.AuthorizationException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class AuthService {
	
	private UserRepository ur;
	private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);
	
	
	public AuthService(UserRepository ur) {
		super();
		this.ur = ur;
	}
	
	public UserDto login(String username, String password) {
		User user = ur.findUserByUsername(username);
		
		if(user == null || !user.getPassword().equals(password)) {
			throw new AuthenticationException("Attempted to login with username: " + username);
		}
		
		LOG.info("User " + user.getUsername() + "'s credentials validated.");
		
		return new UserDto(user);
	}
	
	public String generateToken(UserDto principal) {
		User user = ur.getById(principal.getId());
		
		return user.getId()+":"+user.getRole().toString();
	}
	public void verify(String token) {
		
		
		if(token == null) {
			throw new AuthorizationException("null token");
		}
		
		String[] splitToken = token.split(":");
		
		User principal = ur.findById(Integer.valueOf(splitToken[0])).orElse(null);
		
		if(principal == null 
				|| !principal.getRole().toString().equals(splitToken[1])
				|| !principal.getRole().toString().equals("ADMIN")) {
			throw new AuthorizationException("Unable to verify token of value: "
					+splitToken[0]+", "+splitToken[1]);
		}
		
		LOG.info("token verified successfully");
		
		MDC.put("userId", principal.getId());
	}
}
