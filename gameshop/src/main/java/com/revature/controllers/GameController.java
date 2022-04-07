package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Game;
import com.revature.services.GameService;

@RestController
@RequestMapping("/games")
public class GameController {
	
	private GameService gs;
	
	@Autowired
	public GameController(GameService gs) {
		super();
		this.gs = gs;
	}
	
	@GetMapping
	public ResponseEntity<List<Game>> getAllGames() {
		return new ResponseEntity<>(gs.getAllGames(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Game> getGameById(@PathVariable("id") int id) {
		return new ResponseEntity<>(gs.getGameById(id), HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<Game> postGame(@RequestBody Game game) {
		return new ResponseEntity<>(gs.createGame(game), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Game> putGame(@PathVariable("id") int id, @RequestBody Game game) {
		game.setId(id);
		return new ResponseEntity<>(gs.updateGame(id, game), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteGame(@PathVariable("id") int id) {
		gs.deleteGameById(id);
		return new ResponseEntity<>("Game of id: " + id + " was deleted.", HttpStatus.OK);
	}
	
}
