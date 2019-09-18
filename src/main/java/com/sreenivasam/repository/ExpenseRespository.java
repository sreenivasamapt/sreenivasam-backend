package com.sreenivasam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sreenivasam.modal.Expense;

@Repository
public interface ExpenseRespository extends JpaRepository<Expense, Long> {
	public Expense findByTitle(String name);
	
	@Query(value="from Expense order by createdOn desc")
	public List<Expense> getExpensesOrderByDateDesc();

	@Query(value = "select sum(amount) from Expense")
	public Float getTotalExpenseAmount();
}
