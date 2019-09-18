package com.sreenivasam.service;

import org.springframework.stereotype.Service;

import com.sreenivasam.beans.UserBean;
import com.sreenivasam.util.ApiResponse;

@Service
public interface UserService {

	public ApiResponse saveUser(UserBean userBean);

	public ApiResponse getUsers();

	public ApiResponse getUser(Long id);

	public ApiResponse deleteUser(Long id);

	public ApiResponse deleteUsers();

}
