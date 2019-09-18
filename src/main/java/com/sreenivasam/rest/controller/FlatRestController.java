package com.sreenivasam.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sreenivasam.beans.FlatBean;
import com.sreenivasam.service.FlatService;
import com.sreenivasam.util.ApiResponse;

@RestController
@RequestMapping(path = "/api/flat")
@CrossOrigin("*")
public class FlatRestController {

	@Autowired
	private FlatService flatService;
	
	@PostMapping
	public ApiResponse saveFlat(@RequestBody(required = true) FlatBean flatBean) {
		return flatService.saveFlat(flatBean);
	}

	@GetMapping
	public ApiResponse flats() {
		return flatService.getFlats();
	}

	@GetMapping("/{id}")
	public ApiResponse getFlat(@PathVariable(value = "id") Long id) {
		return flatService.getFlat(id);
	}

	@DeleteMapping("/{id}")
	public ApiResponse deleteFlat(@PathVariable(value = "id") Long id) {
		return flatService.deleteFlat(id);
	}
	
	@DeleteMapping()
	public ApiResponse deleteFlats() {
		return flatService.deleteFlats();
	}
}
