package shop.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.javalin.Javalin;
import shop.controllers.GameController;
import shop.controllers.PlatformController;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;

public class Driver {
	
	private static Logger log = LogManager.getRootLogger();

	public static void main(String[] args) {

		Javalin app = Javalin.create((config) -> {
			config.defaultContentType = "application/json";
		});
		
		app.start(8080);
		log.info("Started Javalin app");
		
		app.routes(()-> {
			path("platforms", () -> {
				get(PlatformController::getPlatforms);
				
				path("{platform}", () -> {
					get(PlatformController::getPlatformByName);
					
					path("games", () -> {
						get(GameController::getGamesByPlatformName);
						post(GameController::createGame);
						path("{gid}", () -> {
							get(GameController::getGameById);
							put(GameController::updateGame);
							delete(GameController::deleteGameById);
						});
					});
				});
			});
		});
		app.routes(()->{
			path("games",() -> {
				get(GameController::getAllGames);
				post(GameController::createGame);
				

				path("{gid}",() -> {
					get(GameController::getGameById);
					put(GameController::updateGame);
					delete(GameController::deleteGameById);
				});
				
			});
		});
		
		
	}
	
}
