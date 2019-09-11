package com.sreenivasam.rest.controller;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

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
import com.sreenivasam.beans.FdBean;
import com.sreenivasam.modal.Fd;
import com.sreenivasam.service.FdService;
import com.sreenivasam.util.Utility;

@RestController
@RequestMapping(path = "/api/fds")
@CrossOrigin("*")
public class FdController {

	@Autowired
	private FdService fdService;

	@PostMapping
	public ApiResponse saveFd(@RequestBody(required = true) FdBean fdBean) {

		Fd fd = new Fd();
		if (fdBean.getId() != null) {
			fd.setId(fdBean.getId());
		}
		fd.setBank(fdBean.getBank());
		fd.setDepAmount(fdBean.getDepAmount());
		fd.setRoi(fdBean.getRoi());

		Float maturedAmount = 0f;
		if (fdBean.getDepAmount() != null) {
			maturedAmount = (fdBean.getDepAmount() * (fdBean.getRoi() / 100));
		}

		fd.setMaturedAmount(fdBean.getDepAmount() + maturedAmount);

		Date depositedOn = null;

		if (!Utility.isEmpty(fdBean.getDepositedOnStr())) {
			try {
				depositedOn = Utility.yyyy_MM_dd.parse(fdBean.getDepositedOnStr());
			} catch (ParseException e) {
				return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
			}
		}

		fd.setDepositedOn(depositedOn);
		fd.setPeriodInMonths(fdBean.getPeriodInMonths());

		Date maturedOn = null;

		if (!Utility.isEmpty(depositedOn) && !Utility.isEmpty(fdBean.getPeriodInMonths())) {
			Calendar c = Calendar.getInstance();
			c.setTime(depositedOn);
			c.add(Calendar.MONTH, fdBean.getPeriodInMonths());
			maturedOn = c.getTime();
		}

		fd.setMaturedOn(maturedOn);

		return fdService.saveFd(fd);

	}

	@GetMapping
	public ApiResponse fds() {
		return fdService.getFds();
	}

	@GetMapping("/{id}")
	public ApiResponse getFd(@PathVariable(value = "id") Long id) {
		return fdService.getFd(id);
	}

	@DeleteMapping("/{id}")
	public ApiResponse deleteFd(@PathVariable(value = "id") Long id) {
		return fdService.deleteFd(id);
	}

	@DeleteMapping()
	public ApiResponse deleteFds() {
		return fdService.deleteFds();
	}
}
