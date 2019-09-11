package com.sreenivasam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sreenivasam.modal.Chit;

@Repository
public interface ChitRespository extends JpaRepository<Chit, Long> {
	public Chit findByMonthAndYear(String month, Integer year);
}
