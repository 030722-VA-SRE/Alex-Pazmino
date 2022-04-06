package com.revature.services;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.revature.exceptions.PlatformExistsException;
import com.revature.exceptions.PlatformNotFoundException;
import com.revature.models.Platform;
import com.revature.repositories.PlatformRepository;

@Service
public class PlatformService {
	
	private PlatformRepository pr;
	
	@Autowired
	public PlatformService(PlatformRepository pr) {
		super();
		this.pr = pr;
	}
	
	public List<Platform> getAllPlatforms() throws PlatformNotFoundException{
		return pr.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	public Platform getPlatformById(int id) throws PlatformNotFoundException{
		return pr.findById(id).orElseThrow(PlatformNotFoundException::new);
	}
	
	public Platform getByName(String name) throws PlatformNotFoundException{
		return pr.getByName(name);
	}
	
	@Transactional
	public Platform createPlatform(Platform newPlatform) {
		Platform platform = pr.getByName(newPlatform.getName());
		
		if(platform != null) {
			throw new PlatformExistsException();
		}
		return pr.save(newPlatform);
	}
	
	@Transactional
	public Platform updatePlatform(int id, Platform updatePlatform) {
		Platform platform = pr.findById(id).orElseThrow(PlatformNotFoundException::new);
		
		updatePlatform.setId(platform.getId());
		
		return pr.save(updatePlatform);
	}
	
	@Transactional
	public void deletePlatformById(int id) throws PlatformNotFoundException{
		
		pr.findById(id).orElseThrow(PlatformNotFoundException::new);
		
		pr.deleteById(id);
	}
	
	
}
