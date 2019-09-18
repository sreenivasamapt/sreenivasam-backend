package com.sreenivasam.service;

import org.springframework.stereotype.Service;

import com.sreenivasam.beans.EventBean;
import com.sreenivasam.util.ApiResponse;

@Service
public interface EventService {

	public ApiResponse saveEvent(EventBean eventBean);

	public ApiResponse getEvents();

	public ApiResponse getEvent(Long id);

	public ApiResponse deleteEvent(Long id);

	public ApiResponse deleteEvents();

}
