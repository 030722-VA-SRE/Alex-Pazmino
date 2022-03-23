package shop.daos;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import shop.models.Game;
import shop.util.ConnectionUtil;

public class GamePostgres implements GameDao {
	
	private static Logger log = LogManager.getLogger(GamePostgres.class);
	
	@Override
	public List<Game> getAllGames() {
		List<Game> games = new ArrayList<>();
		String sql = "select games.gid, games.game, games.pid, platforms.platform, games.console_exclusive, games.msrp\r\n"
				+ "from games\r\n"
				+ "inner join\r\n"
				+ "platforms on games.pid = platforms.pid\r\n"
				+ "order by gid;";
		
		try(Connection c = ConnectionUtil.getConnection()){
			Statement s = c.createStatement();
			
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				Game game = new Game();
				game.setId(rs.getInt("gid"));
				game.setName(rs.getString("game"));
				game.setPlatformId(rs.getInt("pid"));
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
	
	@Override
	public int createGame(Game game) {
		int generatedGID = -1;
		String sql ="insert into games (game, pid, console_exclusive, msrp) values (?, ?, ?, ?);";
		
		try(Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, game.getName());
			ps.setInt(2, game.getPlatformId());
			ps.setBoolean(3, game.isConsoleExclusive());
			ps.setDouble(4, game.getMsrp());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				generatedGID = rs.getInt("gid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return generatedGID;
	}

	@Override
	public Game getGameById(int gid) {
		Game game = null;
		String sql = "select games.gid, games.game, games.pid, platforms.platform, games.console_exclusive, games.msrp\r\n"
				+ "from games\r\n"
				+ "inner join\r\n"
				+ "platforms on games.pid = platforms.pid\r\n"
				+ "where gid = ?;";
		
		try(Connection c = ConnectionUtil.getConnection()){
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setInt(1, gid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				game = new Game();
				game.setId(rs.getInt("gid"));
				game.setName(rs.getString("game"));
				game.setPlatformId(rs.getInt("pid"));
				game.setPlatformName(rs.getString("platform"));
				game.setConsoleExclusive(rs.getBoolean("console_exclusive"));
				game.setMsrp(rs.getDouble("msrp"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		
		return game;
	}

	@Override
	public Game getGameByName(String name) {
		Game game = null;
		String sql = "select games.gid, games.game, platforms.platform, games.console_exclusive, games.msrp\r\n"
				+ "from games\r\n"
				+ "inner join\r\n"
				+ "platforms on games.pid = platforms.pid\r\n"
				+ "where game = ?;";
		
		try(Connection c = ConnectionUtil.getConnection()){
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, name);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				game = new Game();
				game.setId(rs.getInt("gid"));
				game.setName(rs.getString("game"));
				game.setPlatformId(rs.getInt("pid"));
				game.setPlatformName(rs.getString("platform"));
				game.setConsoleExclusive(rs.getBoolean("console_exclusive"));
				game.setMsrp(rs.getDouble("msrp"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		
		return game;
	}

	@Override
	public Game getGamesByPlatformId(int pid) {
		Game game = null;
		String sql = "select games.gid, games.game, games.pid, platforms.platform, games.console_exclusive, games.msrp\r\n"
				+ "from games\r\n"
				+ "inner join\r\n"
				+ "platforms on games.pid = platforms.pid\r\n"
				+ "where games.pid = ?;";
		
		try(Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setInt(1, pid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				game = new Game();
				game.setId(rs.getInt("gid"));
				game.setName(rs.getString("game"));
				game.setPlatformId(rs.getInt("pid"));
				game.setPlatformName(rs.getString("platform"));
				game.setConsoleExclusive(rs.getBoolean("console_exclusive"));
				game.setMsrp(rs.getFloat("msrp"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		
		return game;
	}

	@Override
	public Game getGameByConsoleExclusive(boolean isExclusive) {
		Game game = null;
		String sql = "select games.gid, games.game, platforms.platform, games.console_exclusive, games.msrp\r\n"
				+ "from games\r\n"
				+ "inner join\r\n"
				+ "platforms on games.pid = platforms.pid\r\n"
				+ "where console_exclusive = ?;";
		
		try(Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setBoolean(1, isExclusive);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				game = new Game();
				game.setId(rs.getInt("gid"));
				game.setName(rs.getString("game"));
				game.setPlatformName(rs.getString("platform"));
				game.setConsoleExclusive(rs.getBoolean("console_exclusive"));
				game.setMsrp(rs.getDouble("msrp"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		
		return game;
	}

	@Override
	public Game getGameByMsrp(float msrp) {
		Game game = null;
		String sql = "select games.gid, games.game, games.pid, platforms.platform, games.console_exclusive, games.msrp\r\n"
				+ "from games\r\n"
				+ "inner join\r\n"
				+ "platforms on games.pid = platforms.pid\r\n"
				+ "where games.msrp = ?\r\n"
				+ "order by gid;";
		
		try(Connection c = ConnectionUtil.getConnection()){
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setFloat(1, msrp);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				game = new Game();
				game.setId(rs.getInt("gid"));
				game.setName(rs.getString("game"));
				game.setPlatformId(rs.getInt("pid"));
				game.setPlatformName(rs.getString("platform"));
				game.setConsoleExclusive(rs.getBoolean("console_exclusive"));
				game.setMsrp(rs.getDouble("msrp"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		
		return game;
	}



	@Override
	public boolean updateGame(Game game) {
		int rowsChanged = -1;
		String sql = "update games set\r\n"
				+ "game = ?, pid = ?, console_exclusive = ?, msrp = ?\r\n"
				+ "where gid = ?;";
		
		try(Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, game.getName());
			ps.setInt(2, game.getPlatformId());
			ps.setBoolean(3, game.isConsoleExclusive());
			ps.setDouble(4, game.getMsrp());
			ps.setInt(5, game.getId());
			
			rowsChanged = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		
		return (rowsChanged < 1 ? false : true);
	}
	
	@Override
	public boolean deleteGameById(int gid) {
		int rowsChanged = -1;
		String sql = "delete from games where gid = ?";
		
		try(Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setInt(1, gid);
			
			rowsChanged = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		
		return rowsChanged < 1 ? false : true ;
	}

	@Override
	public List<Game> getGamesByPlatformName(String platform) {
		List<Game> games = new ArrayList<>();
		String sql = "select games.gid, games.game, games.pid, platforms.platform, games.console_exclusive, games.msrp\r\n"
				+ "from games\r\n"
				+ "inner join\r\n"
				+ "platforms on games.pid = platforms.pid\r\n"
				+ "where platform = ?\r\n"
				+ "order by gid;";
		
		try(Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, platform);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Game game = new Game();
				game.setId(rs.getInt("gid"));
				game.setName(rs.getString("game"));
				game.setPlatformId(rs.getInt("pid"));
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

	@Override
	public List<Game> getGamesByParam(Map<String, List<String>> queryParamMap) {
		String sql = "select games.gid, games.game, games.pid, platforms.platform, games.console_exclusive, games.msrp\r\n"
				+ "from games\r\n"
				+ "inner join\r\n"
				+ "platforms on games.pid = platforms.pid\r\n"
				+ "where ";
		Map<Integer, String> statements = new HashMap<Integer, String>();
		int paramKey = 1;
		
		for(Map.Entry<String, List<String>> queryParam : queryParamMap.entrySet()){
			String key = queryParam.getKey();
			String value = queryParam.getValue().get(0);
			
			switch(key) {
			case "gid":
				sql += "gid = ? and ";
				break;
			case "game":
				sql += "game = ? and ";
				break;
			case "pid":
				sql += "games.pid = ? and ";
				break;
			case "platform":
				sql += "platform = ? and ";
				break;
			case "console_exclusive":
				sql += "games.console_exclusive = ? and ";
				break;
			case "msrp":
				sql += "games.msrp = ? and ";
				break;
			default:
				break;
			}
			statements.put(paramKey, value);
			paramKey++;
		}
		sql += "true;";
		
		List<Game> games = new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnection()){
			PreparedStatement ps = c.prepareStatement(sql);
			
			for(Map.Entry<Integer, String> statement: statements.entrySet()) {
				if(isInteger(statement.getValue())) {
					ps.setInt(statement.getKey(), Integer.parseInt(statement.getValue()));
				} 
				else if(isDouble(statement.getValue())) {
					ps.setDouble(statement.getKey(), Double.parseDouble(statement.getValue()));
				}
				else if(isBoolean(statement.getValue())) {
					ps.setBoolean(statement.getKey(), Boolean.parseBoolean(statement.getValue()));
				}
				else {
					ps.setString(statement.getKey(), "%" + statement.getValue() + "%");
				}
			}
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Game game = new Game();
				game.setId(rs.getInt("gid"));
				game.setName(rs.getString("game"));
				game.setPlatformId(rs.getInt("pid"));
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
	
	public static boolean isInteger(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        Integer.parseInt(strNum);
	    } catch (NumberFormatException e) {
	        return false;
	    }
	    return true;
	}
	
	public static boolean isDouble(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        Double.parseDouble(strNum);
	    } catch (NumberFormatException e) {
	        return false;
	    }
	    return true;
	}
	
	public static boolean isBoolean(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        Boolean.parseBoolean(strNum);
	    } catch (NullPointerException e) {
	        return false;
	    }
	    return true;
	}
	
}
