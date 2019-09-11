sreenivasam com.sreenivasam.service;

import org.springframework.stereotype.Service;

import com.sreenivasam.beans.ApiResponse;
import com.sreenivasam.modal.Bank;

@Service
public interface BankService {

	public ApiResponse saveBank(Bank bank);

	public ApiResponse getBanks();

	public ApiResponse getBank(Long id);

	public ApiResponse deleteBank(Long id);

	public ApiResponse deleteBanks();

}
