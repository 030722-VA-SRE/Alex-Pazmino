package shop.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import shop.models.Game;
import shop.models.Platform;
import shop.util.ConnectionUtil;

public class PlatformPostgres implements PlatformDao{
	
	private static Logger log = LogManager.getLogger(PlatformPostgres.class);
	
	@Override
	public List<Platform> getPlatforms() {
		String sql = "select * from platforms;";
		List<Platform> platforms = new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnection()){
			Statement s = c.createStatement();
			
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				Platform platform = new Platform();
				platform.setId(rs.getInt("pid"));
				platform.setName(rs.getString("platform"));
				platform.setMsrp(rs.getFloat("msrp"));
				
				platforms.add(platform);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return platforms;
	}
	
	@Override
	public Platform getPlatformById(int id) {
		Platform platform = null;
		String sql = "select * from platforms where pid = ?;";
		
		try(Connection c = ConnectionUtil.getConnection()){
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				platform = new Platform();
				platform.setId(rs.getInt("pid"));
				platform.setName(rs.getString("platform"));
				platform.setMsrp(rs.getFloat("msrp"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return platform;
	}
	
	@Override
	public Platform getPlatformByName(String name) {
		Platform platform = null;
		String sql = "select * from platforms where platform = ?;";
		
		try(Connection c = ConnectionUtil.getConnection()){
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, name);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				platform = new Platform();
				platform.setId(rs.getInt("pid"));
				platform.setName(rs.getString("platform"));
				platform.setMsrp(rs.getFloat("msrp"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return platform;
	}

	@Override
	public Platform getPlatformByMsrp(float msrp) {
		Platform platform = null;
		String sql = "select * from platforms where msrp = ?;";
		
		try(Connection c = ConnectionUtil.getConnection()){
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setFloat(1, msrp);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				platform = new Platform();
				platform.setId(rs.getInt("pid"));
				platform.setName(rs.getString("platform"));
				platform.setMsrp(rs.getFloat("msrp"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return platform;
	}

	@Override
	public List<Game> getGamesByPlatformName(String platform) {
		List<Game> games = new ArrayList<>();
		String sql = "select games.gid, games.game, platforms.platform, games.console_exclusive, games.msrp\r\n"
				+ "from games\r\n"
				+ "inner join\r\n"
				+ "platforms on games.pid = platforms.pid\r\n"
				+ "where platform = ?\r\n"
				+ "order by games.gid;";
		
		try(Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, platform);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Game game = new Game();
				game.setId(rs.getInt("gid"));
				game.setName(rs.getString("game"));
				game.setPlatformName(rs.getString("platform"));
				game.setConsoleExclusive(rs.getBoolean("console_exclusive"));
				game.setMsrp(rs.getDouble("msrp"));
				
				games.add(game);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		
		return games;
	}
	
}
