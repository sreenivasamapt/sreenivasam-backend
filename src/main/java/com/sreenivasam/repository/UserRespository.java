package com.sreenivasam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sreenivasam.modal.User;

@Repository
public interface UserRespository extends JpaRepository<User, Long> {
}
