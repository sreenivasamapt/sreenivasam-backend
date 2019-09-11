package com.sreenivasam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sreenivasam.modal.Expense;

@Repository
public interface ExpenseRespository extends JpaRepository<Expense, Long> {
	public Expense findByName(String name);
}
