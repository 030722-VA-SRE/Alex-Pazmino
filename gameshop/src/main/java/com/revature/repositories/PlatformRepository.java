package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Platform;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Integer> {
	public Platform getByName(String name);
}
