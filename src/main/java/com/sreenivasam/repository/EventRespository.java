package com.sreenivasam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sreenivasam.modal.Event;

@Repository
public interface EventRespository extends JpaRepository<Event, Long> {
}
