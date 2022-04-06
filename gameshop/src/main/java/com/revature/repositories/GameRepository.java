package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer>{
	public Game getByName(String name);
	public List<Game> getByPlatformId(int id);
	public List<Game> getByPlatformName(String platform);
}
