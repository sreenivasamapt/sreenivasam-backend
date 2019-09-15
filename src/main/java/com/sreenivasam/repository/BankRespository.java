package com.sreenivasam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sreenivasam.modal.Bank;

@Repository
public interface BankRespository extends JpaRepository<Bank, Long> {
	public Bank findByName(String name);

	@Query(value = "from Bank b order by balance desc")
	List<Bank> getBanksOrderByBalDesc();

	@Query(value = "select sum(balance) from Bank")
	public Float getTotalBalance();
}
