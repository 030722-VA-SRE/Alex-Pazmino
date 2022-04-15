package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.dtos.UserDto;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTests {
	
	private static UserRepository ur;
	private static AuthService as;
	private static User principal;
	private static UserDto principalDto;
	
	@BeforeAll
	public static void setup() {
		
		ur = mock(UserRepository.class);
		as = new AuthService(ur);
		principal = new User(1, "test", "pass1", UserRole.ADMIN);
		principalDto = new UserDto(principal);
	}
	
	@Test
	public void loginTest() {
		when(ur.findUserByUsername(anyString())).thenReturn(principal);
		assertEquals(principalDto, as.login("test","pass1"));
	}
	
	@Test
	public void generateTokenTest() {
		when(ur.getById(anyInt())).thenReturn(principal);
		assertEquals("1:ADMIN", as.generateToken(principalDto));
	}
}
