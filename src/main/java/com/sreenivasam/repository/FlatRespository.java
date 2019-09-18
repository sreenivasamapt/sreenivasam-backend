package com.sreenivasam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sreenivasam.modal.Flat;

@Repository
public interface FlatRespository extends JpaRepository<Flat, Long> {
}
