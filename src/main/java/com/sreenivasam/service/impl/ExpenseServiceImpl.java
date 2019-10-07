package com.sreenivasam.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sreenivasam.beans.ExpenseBean;
import com.sreenivasam.modal.Expense;
import com.sreenivasam.repository.ExpenseRespository;
import com.sreenivasam.service.ExpenseService;
import com.sreenivasam.util.ApiResponse;
import com.sreenivasam.util.Utility;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	ExpenseRespository expenseRepository;

	private String message = "";

	@Override
	public ApiResponse getExpenses() {
		Map<String, Object> data = new LinkedHashMap<>();

		List<Expense> expenses = expenseRepository.findAll();

		if (Utility.isEmpty(expenses)) {
			return new ApiResponse(HttpStatus.NOT_FOUND, "No data found", null);
		}

		Float totalExpenses = expenseRepository.getTotalExpenseAmount();

		data.put("expenses", expenses);
		data.put("totalExpenses", totalExpenses);

		return new ApiResponse(HttpStatus.OK, null, data);
	}

	@Override
	public void saveExpense(ExpenseBean expenseBean) throws Exception {

		if (!validData(expenseBean)) {
			throw new Exception(message);
		}

		Expense expense = new Expense();
		if (expenseBean.getId() != null) {
			expense.setId(expenseBean.getId());
		}
		expense.setTitle(expenseBean.getTitle());
		expense.setAmount(expenseBean.getAmount());

		Expense c = expenseRepository.findByTitle(expense.getTitle());

		if (c != null) {
			if ((expense.getId() == null) || (expense.getId() != c.getId())) {
				message = "Expense Name Already Exists";
				throw new Exception(message);
			}
		}

		expenseRepository.save(expense);
	}

	private boolean validData(ExpenseBean bean) {

		if (Utility.isEmpty(bean.getTitle())) {
			message = "Please Enter Name";
			return false;
		}

		if (bean.getAmount() == null) {
			message = "Please Enter Amount";
			return false;
		}

		return true;
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
