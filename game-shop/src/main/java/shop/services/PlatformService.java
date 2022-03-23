package shop.services;

import java.util.List;

import shop.daos.GameDao;
import shop.daos.GamePostgres;
import shop.daos.PlatformDao;
import shop.daos.PlatformPostgres;
import shop.exceptions.ItemNotFoundException;
import shop.models.Game;
import shop.models.Platform;

public class PlatformService {
	
	private PlatformDao pd = new PlatformPostgres();
	private GameDao gd = new GamePostgres();
	
	//gets all platforms
	public List<Platform> getPlatforms() throws ItemNotFoundException{
		List<Platform> platforms = pd.getPlatforms();
		
		if(platforms == null) {
			throw new ItemNotFoundException();
		}
		
		return platforms;
	}
	
	public Platform getPlatformById(int pid) throws ItemNotFoundException {
		Platform platform = pd.getPlatformById(pid);
		
		if(platform == null) {
			throw new ItemNotFoundException();
		}
		return platform;
	}
	
	public Platform getPlatformByName(String name) throws ItemNotFoundException{
		Platform platform = pd.getPlatformByName(name);
		
		if(platform == null) {
			throw new ItemNotFoundException();
		}
		
		return platform;
	}
	
	public List<Game> getGamesByPlatformName(String platform) throws ItemNotFoundException{
		List<Game> games = gd.getGamesByPlatformName(platform);
		
		if(games == null) {
			throw new ItemNotFoundException();
		}
		return games;
	}
	
}
