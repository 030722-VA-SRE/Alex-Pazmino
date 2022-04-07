package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.dtos.PlatformDto;
import com.revature.exceptions.PlatformNotFoundException;
import com.revature.models.Platform;
import com.revature.repositories.PlatformRepository;

@ExtendWith(MockitoExtension.class)
public class PlatformServiceTests {
	
	private static PlatformRepository pr;
	private static PlatformService ps;
	
	@Test
	void getPlatformsTest() {
		when(pr.findAll()).thenReturn(new ArrayList<Platform>());
		assertThrows(PlatformNotFoundException.class,() ->{
			ps.getAllPlatforms();
		});
	}
	
	@Test
	void getPlatformByIdTest() {
		when(pr.findById(0)).thenReturn(Optional.empty());
		assertThrows(PlatformNotFoundException.class, () ->{
			ps.getPlatformById(0);
		});
	}
	
	@Test
	void createPlatformTest() {
		when(pr.save(new Platform())).thenReturn(new Platform());
		assertEquals(new PlatformDto(new Platform()), ps.createPlatform(null));
	}
	
	@Test
	void updatePlatformTest() {
		assertThrows(PlatformNotFoundException.class, () ->{
			ps.updatePlatform(0, null);
		});
	}
	
	@Test
	void deletePlatform() {
		when(pr.findById(0)).thenReturn(Optional.empty());
		assertThrows(PlatformNotFoundException.class, () ->{
			ps.deletePlatformById(0);
		});
	}
	
 }
