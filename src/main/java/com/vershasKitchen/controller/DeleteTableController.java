package com.vershasKitchen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vershasKitchen.services.ImageService;

@RestController
public class DeleteTableController {
	
	@Autowired
	private ImageService service;
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<HttpStatus> deleteAll() {
		return service.deleteAll();
	}
}
