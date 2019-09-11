package com.sreenivasam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sreenivasam.modal.Fd;

@Repository
public interface FdRespository extends JpaRepository<Fd, Long> {
}
