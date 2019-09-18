package com.sreenivasam.service;

import org.springframework.stereotype.Service;

import com.sreenivasam.beans.ExpenseBean;
import com.sreenivasam.util.ApiResponse;

@Service
public interface ExpenseService {

	public ApiResponse saveExpense(ExpenseBean expenseBean);

	public ApiResponse getExpenses();

	public ApiResponse getExpense(Long id);

	public ApiResponse deleteExpense(Long id);
	
	public ApiResponse deleteExpenses();

}
