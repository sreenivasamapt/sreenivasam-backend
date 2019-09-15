package com.sreenivasam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sreenivasam.modal.Chit;

@Repository
public interface ChitRespository extends JpaRepository<Chit, Long> {

	public Chit findByMonthAndYear(Integer month, Integer year);

	@Query(value = "from Chit order by year desc")
	List<Chit> getChitsOrderByYearDesc();

	@Query(value = "select sum(actualAmount) from Chit")
	public Float getTotalDeposited();
}
