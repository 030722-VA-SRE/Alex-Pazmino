package com.revature.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.revature.dtos.PlatformDto;
import com.revature.models.Platform;
import com.revature.services.PlatformService;

@RestController
@RequestMapping("/platforms")
public class PlatformController {
	
	
	private PlatformService ps;
	
	private static final Logger LOG = LoggerFactory.getLogger(PlatformController.class);
	
	@Autowired
	public PlatformController(PlatformService ps) {
		super();
		this.ps = ps;
	}
	
	@GetMapping
	public ResponseEntity<List<PlatformDto>> getAll(){
		
		LOG.info("platforms retrieved");
		return new ResponseEntity<>(ps.getAllPlatforms(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Platform> getPlatformById(@PathVariable("id") int id) {
		return new ResponseEntity<>(ps.getPlatformById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Platform> postPlatform(@RequestBody Platform platform) {
		return new ResponseEntity<>(ps.createPlatform(platform), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Platform> putPlatform(@PathVariable("id") int id, @RequestBody Platform platform) {
		platform.setId(id);
		return new ResponseEntity<>(ps.updatePlatform(id, platform), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePlatform(@PathVariable("id") int id){
		ps.deletePlatformById(id);
		return new ResponseEntity<>("Platform of id: " + id + " was deleted.", HttpStatus.OK);
	}
}
