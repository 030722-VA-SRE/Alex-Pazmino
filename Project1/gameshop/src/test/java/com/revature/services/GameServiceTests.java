package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.revature.exceptions.GameNotFoundException;
import com.revature.models.Game;
import com.revature.models.Platform;
import com.revature.repositories.GameRepository;

@ExtendWith(MockitoExtension.class)
public class GameServiceTests {
	
	private static GameRepository gr;
	private static GameService gs;
	private static List<Game> games;
	private static Platform platform;
	
	@BeforeAll
	public static void setup() {
		gr = mock(GameRepository.class);
		gs = new GameService(gr);
		platform = new Platform(1, "test", 499.99);
		Game g1 = new Game(1,"first", platform,true, 69.99);
		Game g2 = new Game(2, "second", platform, false, 59.99);
		games = new ArrayList<>();
		games.add(g1);
		games.add(g2);
		
	}
	
	@Test
	void getAllGamesTest() {
		when(gr.findAll()).thenReturn(new ArrayList<Game>());
		assertDoesNotThrow(() ->{
			gs.getAllGames();
		});
	}
	
	@Test
	void getGameByIdTestIsNull() {
		when(gr.findById(0)).thenReturn(Optional.empty());
		assertThrows(GameNotFoundException.class, () ->{
			gs.getGameById(0);
		});
	}
	
	@Test
	void getGameByIdTestNotNull() {
		
		when(gr.findById(1)).thenReturn(Optional.of(games.get(0)));
		assertDoesNotThrow(() ->{
			assertEquals(games.get(0), gs.getGameById(1));
		});
	}
	
	@Test
	void createGameTest() {
		Game g = new Game();
		when(gr.save(g)).thenReturn(g);
		assertDoesNotThrow(() ->{
			gs.createGame(g);
		});
	}
	
	@Test
	void updateGameTestIsNull() {
		assertThrows(GameNotFoundException.class, () ->{
			gs.updateGame(0, null);
		});
	}
	
	@Test
	void deleteGameTest() {
		when(gr.findById(0)).thenReturn(Optional.empty());
		assertThrows(GameNotFoundException.class, () ->{
			gs.deleteGameById(0);
		});		
	}
	
	
	
	
}
