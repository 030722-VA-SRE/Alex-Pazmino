package shop.daos;

import java.util.List;
import java.util.Map;

import shop.models.Game;

public interface GameDao {
	public List<Game> getAllGames();
	public Game getGameById(int gid);
	public Game getGameByName(String game);
	public Game getGamesByPlatformId(int pid);
	public List<Game> getGamesByPlatformName(String platform);
	public Game getGameByConsoleExclusive(boolean isExclusive);
	public Game getGameByMsrp(float msrp);
	public int createGame(Game game);
	public boolean updateGame(Game game);
	public boolean deleteGameById(int gid);
	public List<Game> getGamesByParam(Map<String,List<String>> queryParamMap);
}

//ADD CONSTRAINT TO EXCLUSIVES TO PREVENT DUPLICATES WITH OTHER PLATFORMS
