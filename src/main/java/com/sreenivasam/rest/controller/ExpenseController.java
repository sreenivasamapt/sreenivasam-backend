package com.sreenivasam.rest.controller;

import javax.transaction.Transactional;

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

import com.sreenivasam.beans.ExpenseBean;
import com.sreenivasam.service.ExpenseService;
import com.sreenivasam.util.ApiResponse;
import com.sreenivasam.util.Utility;

@RestController
@RequestMapping(path = "/api/expenses")
@CrossOrigin("*")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;
	private String message;

	@PostMapping
	@Transactional(rollbackOn = Exception.class)
	public ApiResponse saveExpense(@RequestBody(required = true) ExpenseBean expenseBean) {

		try {
			expenseService.saveExpense(expenseBean);

			if (Utility.isEmpty(expenseBean.getId())) {
				message = "Expense saved successfully";
			} else {
				message = "Expense updated successfully";
			}

			return new ApiResponse(HttpStatus.OK, message, null);
		} catch (Exception e) {
			message = "Error-" + e.getMessage();
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, null);
		}

	}

	@GetMapping
	public ApiResponse expenses() {
		return expenseService.getExpenses();
	}

	@GetMapping("/{id}")
	public ApiResponse getExpense(@PathVariable(value = "id") Long id) {
		return expenseService.getExpense(id);
	}

	@DeleteMapping("/{id}")
	public ApiResponse deleteExpense(@PathVariable(value = "id") Long id) {
		return expenseService.deleteExpense(id);
	}

	@DeleteMapping()
	public ApiResponse deleteExpenses() {
		return expenseService.deleteExpenses();
	}
}
