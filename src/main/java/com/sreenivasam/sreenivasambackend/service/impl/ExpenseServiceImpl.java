package com.sreenivasam.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sreenivasam.beans.ApiResponse;
import com.sreenivasam.modal.Expense;
import com.sreenivasam.repository.ExpenseRespository;
import com.sreenivasam.service.ExpenseService;
import com.sreenivasam.util.Utility;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	ExpenseRespository expenseRepository;

	private String message = "";

	@Override
	public ApiResponse getExpenses() {
		Map<String,Object> data=new LinkedHashMap<>();
		
		List<Expense> expenses=expenseRepository.findAll();
		
		if (Utility.isEmpty(expenses)) {
			return new ApiResponse(HttpStatus.NOT_FOUND, "No data found", null);
		}
		
		Double totalExpenses=expenses.stream().mapToDouble(x->x.getAmount()).sum();
		
		data.put("expenses",expenses);
		data.put("totalExpenses",totalExpenses);
		
		return new ApiResponse(HttpStatus.OK, null,data);
	}

	@Override
	public ApiResponse saveExpense(Expense expense) {
		Expense c = expenseRepository.findByName(expense.getName());

		if (c != null) {
			if ((expense.getId() == null) || (expense.getId() != c.getId())) {
				return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Expense Name Already Exists", null);
			}
		}

		if (expense.getId() == null || expense.getId() == 0) {
			message = "Expense saved successfully";
		} else {
			message = "Expense updated successfully";
		}
		expenseRepository.save(expense);

		return new ApiResponse(HttpStatus.OK, message, null);
	}

	@Override
	public ApiResponse getExpense(Long id) {
		if (id == null || id == 0) {
			return new ApiResponse(HttpStatus.NOT_FOUND, "No data found", null);
		} else {

			Optional<Expense> expenseOp = expenseRepository.findById(id);
			if (expenseOp.isPresent()) {
				Expense expense = expenseOp.get();
				return new ApiResponse(HttpStatus.OK, null, expense);
			} else {
				return new ApiResponse(HttpStatus.NOT_FOUND, "No data found", null);
			}
		}
	}

	@Override
	public ApiResponse deleteExpense(Long id) {
		if (getExpense(id).getData() == null) {
			return new ApiResponse(HttpStatus.NOT_FOUND, "No data found", null);
		} else {
			expenseRepository.delete((Expense) getExpense(id).getData());
			message = "Expense deleted successfully";
			return new ApiResponse(HttpStatus.OK, message, null);
		}
	}

	@Override
	public ApiResponse deleteExpenses() {
		expenseRepository.deleteAll();
		message = "Expenses deleted successfully";
		return new ApiResponse(HttpStatus.OK, message, null);
	}

}
