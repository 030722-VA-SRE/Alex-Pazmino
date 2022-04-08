package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.dtos.UserDto;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
	
	private static UserRepository ur;
	private static UserService us;
	private static List<User> users = new ArrayList<>();
	private static List<UserDto> usersDto = new ArrayList<>();
	private static User user;
	
	@BeforeAll
	public static void setup() {
		ur = mock(UserRepository.class);
		us = new UserService(ur);
		
		User u1 = new User(1, "first", "axse1", UserRole.ADMIN);
		User u2 = new User(2, "second", "pscme2", UserRole.USER);
		users.add(u1);
		users.add(u2);
		usersDto.add(new UserDto(u1));
		usersDto.add(new UserDto(u2));
	}
	
	@Test
	public void getUsersTest() {
		when(ur.findAll()).thenReturn(users);
		assertThrows((UserNotFoundException.class), () -> {
			assertEquals(usersDto, us.getUsers());
		});
	}
	
	
	@Test
	public void getUserByIdTest() {
		when(ur.findById(1)).thenReturn(Optional.of(users.get(0)));
		assertDoesNotThrow(() ->{
			assertEquals(usersDto.get(0), us.getUserById(1));
		});
	}
	
	@Test
	public void createUserTest() {
		when(ur.save(any(User.class))).thenReturn(user);
		assertEquals(user, us.createUser(user));
	}
	
	@Test
	public void updateUserTest() {
		User u3 = new User(3, "test3", "asci1", UserRole.ADMIN);
		User u4 = new User(4,"test4","cool4", UserRole.USER);
		
		when(ur.findById(anyInt())).thenReturn(Optional.of(u4));
		when(ur.save(any(User.class))).thenReturn(u3);
		assertEquals(u3, us.updateUser(3, u3));
		
	}
	
	@Test
	public void deleteUserTest() {
		when(ur.findById(1)).thenReturn(Optional.of(users.get(0)));
		assertDoesNotThrow(() -> {
			assertEquals(usersDto.get(0),us.getUserById(1));
		});
	}
	
}
