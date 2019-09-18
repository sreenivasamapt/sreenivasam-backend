package com.sreenivasam.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(path = "/api/expenses")
@CrossOrigin("*")
public class ExpenseRestController {

	@Autowired
	private ExpenseService expenseService;

	@PostMapping
	public ApiResponse saveExpense(@RequestBody(required = true) ExpenseBean expenseBean) {
		return expenseService.saveExpense(expenseBean);
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
