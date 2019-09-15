package com.sreenivasam.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sreenivasam.beans.ApiResponse;
import com.sreenivasam.modal.Bank;
import com.sreenivasam.repository.BankRespository;
import com.sreenivasam.service.BankService;
import com.sreenivasam.util.Utility;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	BankRespository bankRepository;

	private String message = "";

	@Override
	public ApiResponse getBanks() {
		
		Map<String,Object> data=new LinkedHashMap<>();
		
		List<Bank> banks=bankRepository.getBanksOrderByBalDesc();

		if (Utility.isEmpty(banks)) {
			return new ApiResponse(HttpStatus.NO_CONTENT, "No data found", null);
		}

		Float totalBalance = bankRepository.getTotalBalance();

		data.put("banks", banks);
		data.put("totalBalance", totalBalance);

		return new ApiResponse(HttpStatus.OK, null, data);
	}

	@Override
	public ApiResponse saveBank(Bank bank) {
		Bank c = bankRepository.findByName(bank.getName());

		if (c != null) {
			if ((bank.getId() == null) || (bank.getId() != c.getId())) {
				return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Bank Name Already Exists", null);
			}
		}

		if (bank.getId() == null || bank.getId() == 0) {
			message = "Bank saved successfully";
		} else {
			message = "Bank updated successfully";
		}
		bankRepository.save(bank);

		return new ApiResponse(HttpStatus.OK, message, null);
	}

	@Override
	public ApiResponse getBank(Long id) {
		if (id == null || id == 0) {
			return new ApiResponse(HttpStatus.NO_CONTENT, "No data found", null);
		} else {

			Optional<Bank> bankOp = bankRepository.findById(id);
			if (bankOp.isPresent()) {
				Bank bank = bankOp.get();
				return new ApiResponse(HttpStatus.OK, null, bank);
			} else {
				return new ApiResponse(HttpStatus.NO_CONTENT, "No data found", null);
			}
		}
	}

	@Override
	public ApiResponse deleteBank(Long id) {
		if (getBank(id).getData() == null) {
			return new ApiResponse(HttpStatus.NO_CONTENT, "No data found", null);
		} else {
			bankRepository.delete((Bank) getBank(id).getData());
			message = "Bank deleted successfully";
			return new ApiResponse(HttpStatus.OK, message, null);
		}
	}

	@Override
	public ApiResponse deleteBanks() {
		bankRepository.deleteAll();
		message = "Banks deleted successfully";
		return new ApiResponse(HttpStatus.OK, message, null);
	}

}
