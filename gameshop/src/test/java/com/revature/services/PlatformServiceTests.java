package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
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
	private static List<Platform> platforms;
	private static List<PlatformDto> platformsDto;
	
	@BeforeAll
	public static void setup() {
		pr = mock(PlatformRepository.class);
		ps = new PlatformService(pr);
		
		Platform p1 = new Platform(1, "first", 20.00);
		Platform p2 = new Platform(2, "second", 30.00);
		platforms = new ArrayList<>();
		platforms.add(p1);
		platforms.add(p2);
		
		platformsDto = new ArrayList<>();
		platformsDto.add(new PlatformDto(p1));
		platformsDto.add(new PlatformDto(p2));
	}
	
	
	@Test
	void getPlatformsTest() {
		when(pr.findAll()).thenReturn(new ArrayList<Platform>());
		assertDoesNotThrow(() ->{
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
		Platform p = new Platform();
		when(pr.save(p)).thenReturn(p);
		assertDoesNotThrow(() ->{
			ps.createPlatform(p);
		});
	}
	
	
	
	@Test
	void updatePlatformTest() {
		assertThrows(PlatformNotFoundException.class, () ->{
			ps.updatePlatform(0, null);
		});
	}
	
	@Test
	void deletePlatformTest() {
		when(pr.findById(0)).thenReturn(Optional.empty());
		assertThrows(PlatformNotFoundException.class, () ->{
			ps.deletePlatformById(0);
		});
	}
	
 }
