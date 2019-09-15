package com.sreenivasam.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sreenivasam.beans.ApiResponse;
import com.sreenivasam.beans.UserBean;
import com.sreenivasam.modal.User;
import com.sreenivasam.repository.UserRespository;
import com.sreenivasam.service.UserService;
import com.sreenivasam.util.Utility;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRespository usersRepository;

	private String message = "";

	@Override
	public ApiResponse getUsers() {

		Map<String, Object> data = new LinkedHashMap<>();

		List<User> users = usersRepository.findAll();

		List<UserBean> userBeans = new ArrayList<>();

		if (Utility.isEmpty(users)) {
			return new ApiResponse(HttpStatus.NOT_FOUND, "No data found", null);
		}

		for (User user : users) {
			UserBean userBean = new UserBean();

			userBean.setId(user.getId());
			userBean.setName(user.getName());
			userBean.setFlatNo(user.getFlatNo());
			userBean.setGender(user.getGender());
			userBean.setDobStr(Utility.yyyy_MM_dd.format(user.getDob()));

			userBeans.add(userBean);
		}

		data.put("users", userBeans);

		return new ApiResponse(HttpStatus.OK, null, data);
	}

	@Override
	public ApiResponse saveUser(User user) {

		if (user.getId() == null || user.getId() == 0) {
			message = "User saved successfully";
		} else {
			message = "User updated successfully";
		}
		usersRepository.save(user);

		return new ApiResponse(HttpStatus.OK, message, null);
	}

	@Override
	public ApiResponse getUser(Long id) {
		if (id == null || id == 0) {
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "No data found", null);
		} else {

			Optional<User> userOp = usersRepository.findById(id);
			if (userOp.isPresent()) {
				User user = userOp.get();
				return new ApiResponse(HttpStatus.OK, null, user);
			} else {
				return new ApiResponse(HttpStatus.NO_CONTENT, "No data found", null);
			}
		}
	}

	@Override
	public ApiResponse deleteUser(Long id) {
		if (getUser(id).getData() == null) {
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "No data found", null);
		} else {
			usersRepository.delete((User) getUser(id).getData());
			message = "User deleted successfully";
			return new ApiResponse(HttpStatus.OK, message, null);
		}
	}

	@Override
	public ApiResponse deleteUsers() {
		usersRepository.deleteAll();
		message = "Users deleted successfully";
		return new ApiResponse(HttpStatus.OK, message, null);
	}

}
