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

import com.sreenivasam.beans.ApiResponse;
import com.sreenivasam.beans.ExpenseBean;
import com.sreenivasam.modal.Expense;
import com.sreenivasam.service.ExpenseService;

@RestController
@RequestMapping(path = "/api/expenses")
@CrossOrigin("*")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@PostMapping
	public ApiResponse saveExpense(@RequestBody(required = true) ExpenseBean expenseBean) {

		Expense expense = new Expense();
		if (expenseBean.getId() != null) {
			expense.setId(expenseBean.getId());
		}
		expense.setName(expenseBean.getName());
		expense.setAmount(expenseBean.getAmount());

		return expenseService.saveExpense(expense);

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
