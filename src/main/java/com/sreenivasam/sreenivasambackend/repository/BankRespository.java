package com.sreenivasam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sreenivasam.modal.Bank;

@Repository
public interface BankRespository extends JpaRepository<Bank, Long> {
	public Bank findByName(String name);
}
