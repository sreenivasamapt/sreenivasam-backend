sreenivasam com.sreenivasam.service;

import org.springframework.stereotype.Service;

import com.sreenivasam.beans.ApiResponse;
import com.sreenivasam.modal.User;

@Service
public interface UserService {

	public ApiResponse saveUser(User user);

	public ApiResponse getUsers();

	public ApiResponse getUser(Long id);

	public ApiResponse deleteUser(Long id);

	public ApiResponse deleteUsers();

}
