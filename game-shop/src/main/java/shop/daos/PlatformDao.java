package shop.daos;

import java.util.List;

import shop.models.Game;
import shop.models.Platform;

public interface PlatformDao {
	public List<Platform> getPlatforms();
	public Platform getPlatformById(int pid);
	public Platform getPlatformByName(String name);
	public Platform getPlatformByMsrp(float msrp);
	public List<Game> getGamesByPlatformName(String platform);
}
