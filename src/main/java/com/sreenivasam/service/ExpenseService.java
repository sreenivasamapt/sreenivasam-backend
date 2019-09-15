package com.sreenivasam.service;

import org.springframework.stereotype.Service;

import com.sreenivasam.beans.ApiResponse;
import com.sreenivasam.modal.Expense;

@Service
public interface ExpenseService {

	public ApiResponse saveExpense(Expense expense);

	public ApiResponse getExpenses();

	public ApiResponse getExpense(Long id);

	public ApiResponse deleteExpense(Long id);
	
	public ApiResponse deleteExpenses();

}
