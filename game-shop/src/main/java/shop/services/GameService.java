package shop.services;

import shop.daos.GameDao;
import shop.daos.GamePostgres;
//import shop.daos.PlatformDao;
//import shop.daos.PlatformPostgres;
import shop.exceptions.ItemNotFoundException;
import shop.models.Game;

import java.util.*;

public class GameService {
	private GameDao gd = new GamePostgres();
	
	public List<Game> getAllGames() throws ItemNotFoundException {
		List<Game> games = gd.getAllGames();
		
		if (games == null) {
			throw new ItemNotFoundException();
		}
		return games;
	}
	
	public Game getGameById(int gid) throws ItemNotFoundException {
		Game game = gd.getGameById(gid);
		
		if(game == null) {
			throw new ItemNotFoundException();
		}
		
		return game;
	}
	
	public Game getGameByName(String name) throws ItemNotFoundException {
		Game game = gd.getGameByName(name);
		
		if(game == null) {
			throw new ItemNotFoundException();
		}
		return game;
	}
	
	public Game getGamesByPlatformId(int pid) throws ItemNotFoundException {
		Game game = gd.getGamesByPlatformId(pid);
		
		if(game == null) {
			throw new ItemNotFoundException();
		}
		
		return game;
	}
	
	public List<Game> getGamesByPlatformName(String platform) throws ItemNotFoundException{
		List<Game> games = gd.getGamesByPlatformName(platform);
		
		if(games == null) {
			throw new ItemNotFoundException();
		}
		return games;
	}
	
	public Game getGameByConsoleExclusive(boolean isExclusive) throws ItemNotFoundException{
		Game game = gd.getGameByConsoleExclusive(isExclusive);
		
		if(game == null) {
			throw new ItemNotFoundException();
		}
		return game;
	}
	
	public Game getGameByMsrp(float msrp) throws ItemNotFoundException {
		Game game = gd.getGameByMsrp(msrp);
		
		if(game == null) {
			throw new ItemNotFoundException();
		}
		return game;
	}
	
	public int createGame(Game game) {
		return gd.createGame(game);
	}
	
	public boolean updateGame(Game game) throws ItemNotFoundException{
		boolean gameUpdated = gd.updateGame(game);
		
		if(!gameUpdated) {
			throw new ItemNotFoundException();
		}
		
		return gd.updateGame(game);
	}
	
	public boolean deleteGameById(int gid) throws ItemNotFoundException{
		boolean gameDeleted = gd.deleteGameById(gid);
		
		if(!gameDeleted) {
			throw new ItemNotFoundException();
		}
		return gameDeleted;
	}
	
	public List<Game> getGamesByParam(Map<String, List<String>> queryParamMap) {
		return gd.getGamesByParam(queryParamMap);
	}
}
