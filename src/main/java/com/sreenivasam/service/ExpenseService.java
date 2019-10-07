package com.sreenivasam.service;

import org.springframework.stereotype.Service;

import com.sreenivasam.beans.ExpenseBean;
import com.sreenivasam.util.ApiResponse;

@Service
public interface ExpenseService {

	public void saveExpense(ExpenseBean expenseBean) throws Exception;

	public ApiResponse getExpenses();

	public ApiResponse getExpense(Long id);

	public ApiResponse deleteExpense(Long id);
	
	public ApiResponse deleteExpenses();

}
