package shop.controllers;




import java.util.List;
import java.util.Map;

import io.javalin.http.Context;
import shop.models.Game;
import shop.services.GameService;

public class GameController {
	
	private static GameService gs = new GameService();
	
	public static void createGame(Context ctx) {
		Game game = ctx.bodyAsClass(Game.class);
		if(gs.createGame(game) > 0) {
			ctx.status(201);
			ctx.result("New game created in the games table!");
		} else {
			ctx.status(400);
		}
	}
	public static void getAllGames(Context ctx){
		Map<String,List<String>> queryParamMap = ctx.queryParamMap();
		
		if(queryParamMap.isEmpty()) {
			ctx.json(gs.getAllGames());
		} else {
			ctx.json(gs.getGamesByParam(queryParamMap));
		}
	}
	
	public static void getGameById(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("gid"));
		Game games = gs.getGameById(id);
		if(games == null) {
			ctx.status(400);
			ctx.result("No such game with id: " + id + " exists. please check for an available game id.");
		} else {
			ctx.status(200);
			ctx.json(games);
		}

	}
	
	public static void getGameByName(Context ctx) {
		String name = ctx.pathParam("game");
		Game game = gs.getGameByName(name);
		
		if(game == null) {
			ctx.status(400);
			ctx.result(name + "was not found. Please check for available names.");
		} else {
			ctx.status(200);
			ctx.json(game);
		}
	}
	
	public static void getGamesByPlatformId(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("pid"));
		
		if(gs.getGamesByPlatformId(id) == null) {
			ctx.status(400);
			ctx.result("Could not find games within platform id: " + id 
						+ ". Please use a valid platform id number.");
		} else {
			ctx.status(200);
			ctx.json(gs.getGamesByPlatformId(id));
		}
	}
	
	public static void getGamesByPlatformName(Context ctx) {
		String platform = ctx.pathParam("platform");
		List<Game> games = gs.getGamesByPlatformName(platform);
		
		if(games == null) {
			ctx.status(400);
			ctx.result("Games were not found for platform: " + platform + ". Please check for available platform names.");
		} else {
			ctx.status(200);
			ctx.json(games);
		}
		
	}
	
	public static void getGameByConsoleExclusive(Context ctx) {
		boolean isExclusive = ctx.attribute("console_exclusive");
		Game game = gs.getGameByConsoleExclusive(isExclusive);
		
		if(game == null) {
			ctx.status(400);
			ctx.result("Could not find games of " + isExclusive + " status. Please try again.");
		} else {
			ctx.status(200);
			ctx.json(game);
		}
	}
	
	public static void getGameByMsrp(Context ctx) {
		float msrp = ctx.attribute("msrp");
		Game game = gs.getGameByMsrp(msrp);
		
		if(game == null) {
			ctx.status(400);
			ctx.result("No available msrp of amount: " + msrp + " found. Please check for available msrp values.");
		} else {
			ctx.status(200);
			ctx.json(game);
		}
	}
	
	public static void updateGame(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("gid"));
		Game game = ctx.bodyAsClass(Game.class);
		game.setId(id);
		
		if(gs.updateGame(game)) {
			ctx.status(201);
			ctx.result("Updated game of id: " + id);
		} else {
			ctx.status(400);
			ctx.result("No such game with id: " + id + " exists. please check for an available game id.");
		}
	}
	
	public static void deleteGameById(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("gid"));
		
		if(gs.deleteGameById(id)) {
			ctx.status(200);
			ctx.result("game of id: " + id + " deleted!");
		} else {
			ctx.status(400);
			ctx.result("Game of id: " + id + " not found. please check for an available game id.");
		}
	}
	
	
	
	
}
