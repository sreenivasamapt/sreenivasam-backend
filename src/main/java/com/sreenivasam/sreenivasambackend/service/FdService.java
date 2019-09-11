sreenivasam com.sreenivasam.service;

import org.springframework.stereotype.Service;

import com.sreenivasam.beans.ApiResponse;
import com.sreenivasam.modal.Fd;

@Service
public interface FdService {

	public ApiResponse saveFd(Fd fd);

	public ApiResponse getFds();

	public ApiResponse getFd(Long id);

	public ApiResponse deleteFd(Long id);

	public ApiResponse deleteFds();

}
