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

import com.sreenivasam.beans.UserBean;
import com.sreenivasam.service.UserService;
import com.sreenivasam.util.ApiResponse;

@RestController
@RequestMapping(path = "/api/users")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ApiResponse saveUser(@RequestBody(required = true) UserBean userBean) {
		return userService.saveUser(userBean);
	}

	@GetMapping
	public ApiResponse users() {
		return userService.getUsers();
	}

	@GetMapping("/{id}")
	public ApiResponse getUser(@PathVariable(value = "id") Long id) {
		return userService.getUser(id);
	}

	@DeleteMapping("/{id}")
	public ApiResponse deleteUser(@PathVariable(value = "id") Long id) {
		return userService.deleteUser(id);
	}
	
	@DeleteMapping()
	public ApiResponse deleteUsers() {
		return userService.deleteUsers();
	}
}
