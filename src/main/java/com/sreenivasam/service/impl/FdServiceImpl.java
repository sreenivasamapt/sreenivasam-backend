package com.sreenivasam.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sreenivasam.beans.ApiResponse;
import com.sreenivasam.beans.FdBean;
import com.sreenivasam.modal.Fd;
import com.sreenivasam.repository.FdRespository;
import com.sreenivasam.service.FdService;
import com.sreenivasam.util.Utility;

@Service
public class FdServiceImpl implements FdService {

	@Autowired
	FdRespository fdRepository;

	private String message = "";

	@Override
	public ApiResponse saveFd(Fd fixedDeposit) {

		if (fixedDeposit.getId() == null || fixedDeposit.getId() == 0) {
			message = "Fixed Deposit saved successfully";
		} else {
			message = "Fixed Deposit updated successfully";
		}

		fdRepository.save(fixedDeposit);

		return new ApiResponse(HttpStatus.OK, message, null);
	}

	@Override
	public ApiResponse getFds() {

		Map<String, Object> data = new LinkedHashMap<>();

		List<Fd> fds = fdRepository.findAll();

		List<FdBean> fdBeans = new ArrayList<>();
		
		if (Utility.isEmpty(fds)) {
			return new ApiResponse(HttpStatus.NOT_FOUND, "No data found", null);
		}

		for (Fd fd : fds) {
			FdBean fdBean = new FdBean();

			fdBean.setId(fd.getId());
			fdBean.setBank(fd.getBank());
			fdBean.setDepAmount(fd.getDepAmount());
			fdBean.setRoi(fd.getRoi());
			fdBean.setMaturedAmount(fd.getMaturedAmount());

			if (fd.getDepositedOn() != null) {
				fdBean.setDepositedOnStr(Utility.yyyy_MM_dd.format(fd.getDepositedOn()));
			}

			fdBean.setPeriodInMonths(fd.getPeriodInMonths());

			if (fd.getMaturedOn() != null) {
				fdBean.setMaturedOnStr(Utility.yyyy_MM_dd.format(fd.getMaturedOn()));

				LocalDate date1 = LocalDate.now();

				LocalDate date2 = fd.getMaturedOn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				fdBean.setRemainingTime(Utility.getDateDifference(date1, date2));

			}

			fdBeans.add(fdBean);
		}


		data.put("fds", fdBeans);
		
		Float totalDeposited = fdRepository.getTotalDeposited();
		
		Float totalMatured = fdRepository.getTotalMatured();
		
		data.put("fds",fdBeans);
		data.put("totalDeposited",totalDeposited);
		data.put("totalMatured",totalMatured);
		
		return new ApiResponse(HttpStatus.OK, null,data);
	}

	@Override
	public ApiResponse getFd(Long id) {
		if (id == null || id == 0) {
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "No data found", null);
		} else {

			Optional<Fd> fdOp = fdRepository.findById(id);
			if (fdOp.isPresent()) {
				Fd fd = fdOp.get();
				return new ApiResponse(HttpStatus.OK, null, fd);
			} else {
				return new ApiResponse(HttpStatus.NO_CONTENT, "No data found", null);
			}
		}
	}

	@Override
	public ApiResponse deleteFd(Long id) {
		if (getFd(id).getData() == null) {
			return new ApiResponse(HttpStatus.NO_CONTENT, "No data found", null);
		} else {
			fdRepository.delete((Fd) getFd(id).getData());
			message = "Fixed Deposit deleted successfully";
			return new ApiResponse(HttpStatus.OK, message, null);
		}
	}

	@Override
	public ApiResponse deleteFds() {
		fdRepository.deleteAll();
		message = "Fixed Deposits deleted successfully";
		return new ApiResponse(HttpStatus.OK, message, null);
	}

}
