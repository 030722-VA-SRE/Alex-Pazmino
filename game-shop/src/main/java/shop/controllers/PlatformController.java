package shop.controllers;

import java.util.List;

import io.javalin.http.Context;
import shop.models.Game;
import shop.models.Platform;
import shop.services.GameService;
import shop.services.PlatformService;

public class PlatformController {
	
	private static PlatformService ps = new PlatformService();
	private static GameService gs = new GameService();
	
	public static void getPlatforms(Context ctx) {
		
		
		ctx.json(ps.getPlatforms());
	}
	
//	public static void getPlatformById(Context ctx) {
//		int id = Integer.parseInt(ctx.pathParam("pid"));
//		
//		Platform platform = ps.getPlatformById(id);
//		
//		if(platform == null) {
//			ctx.status(400);
//			ctx.result("Platform id: " + id + " is invalid or does not exist. please enter a valid id number.");
//		} else {
//			ctx.status(200);
//			ctx.json(platform);
//		}
//	}
	
	public static void getPlatformByName(Context ctx) {
		String name = ctx.pathParam("platform");
		
		Platform platform = ps.getPlatformByName(name);
		
		if(platform == null) {
			ctx.status(400);
			ctx.result(name + " does not exist. please search for a valid name.");
		} else {
			ctx.status(200);
			ctx.json(platform);
		}
		
	}
	
	public static void getGamesByPlatformName(Context ctx) {
		String platform = ctx.pathParam("platform");
		List<Game> games = gs.getGamesByPlatformName(platform);
		
		if(games.isEmpty()) {
			ctx.status(400);
			ctx.result("Games were not found for platform: " + platform + ". Please check for available platform names.");
		} else {
			ctx.status(200);
			ctx.json(games);
		}
		
	}
}
