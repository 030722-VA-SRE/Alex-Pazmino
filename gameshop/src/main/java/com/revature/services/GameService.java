package com.revature.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.revature.exceptions.GameNotFoundException;
import com.revature.models.Game;
import com.revature.repositories.GameRepository;

@Service
public class GameService {
	
	private GameRepository gr;
	
	@Autowired
	public GameService(GameRepository gr) {
		super();
		this.gr = gr;
	}
	
	public List<Game> getAllGames() throws GameNotFoundException {
		return gr.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	public Game getGameById(int id) throws GameNotFoundException {
		return gr.findById(id).orElseThrow(GameNotFoundException::new);
	}
	
	

	@Transactional
	public Game createGame(Game newGame){
		return gr.save(newGame);
	}
	
	@Transactional
	public Game updateGame(int id, Game updateGame) throws GameNotFoundException {
		Game game = gr.findById(id).orElseThrow(GameNotFoundException::new);
		
		updateGame.setId(game.getId());
		
		return gr.save(updateGame);
	}
	
	@Transactional
	public void deleteGameById(int id) throws GameNotFoundException {
		gr.findById(id).orElseThrow(GameNotFoundException::new);
		
		gr.deleteById(id);
	}
	
}
