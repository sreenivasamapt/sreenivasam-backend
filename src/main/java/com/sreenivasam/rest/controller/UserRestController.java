package com.sreenivasam.rest.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sreenivasam.beans.ApiResponse;
import com.sreenivasam.beans.UserBean;
import com.sreenivasam.modal.User;
import com.sreenivasam.service.UserService;
import com.sreenivasam.util.Utility;

@RestController
@RequestMapping(path = "/api/users")
@CrossOrigin("*")
public class UserRestController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ApiResponse saveUser(@RequestBody(required = true) UserBean userBean) {

		User user = new User();
		if (userBean.getId() != null) {
			user.setId(userBean.getId());
		}
		user.setName(userBean.getName());
		user.setFlatNo(userBean.getFlatNo());
		user.setGender(userBean.getGender());
		try {
			user.setDob(Utility.yyyy_MM_dd.parse(userBean.getDobStr()));
		} catch (ParseException e) {
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),null);
		}
		
		return userService.saveUser(user);

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
