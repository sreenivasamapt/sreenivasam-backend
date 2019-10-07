package com.sreenivasam.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sreenivasam.beans.EventBean;
import com.sreenivasam.modal.Event;
import com.sreenivasam.repository.EventRespository;
import com.sreenivasam.repository.UserRespository;
import com.sreenivasam.service.EventService;
import com.sreenivasam.util.ApiResponse;
import com.sreenivasam.util.Utility;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	EventRespository eventRepository;
	
	@Autowired
	UserRespository userRepository;
	
	private String message = "";

	@Override
	public ApiResponse getEvents() {

		Map<String, Object> data = new LinkedHashMap<>();

		List<Event> events = eventRepository.findAll();

		List<EventBean> eventBeans = new ArrayList<>();

		if (Utility.isEmpty(events)) {
			return new ApiResponse(HttpStatus.NOT_FOUND, "No data found", null);
		}

		for (Event event : events) {
			EventBean eventBean = new EventBean();

			eventBean.setId(event.getId());
			eventBean.setType(event.getType());
			eventBean.setUserName(event.getUser().getName());
			eventBean.setDescription(event.getDescription());
			eventBean.setEventDateStr(Utility.yyyy_MM_dd.format(event.getEventDate()));
			eventBean.setImagePath(event.getImagePath());

			eventBeans.add(eventBean);
		}

		data.put("events", eventBeans);

		return new ApiResponse(HttpStatus.OK, null, data);
	}

	@Override
	public ApiResponse saveEvent(EventBean eventBean) {
		
		if(!isValidEvent(eventBean)) {
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, null);
		}
		
		Event event = new Event();
		if (eventBean.getId() != null) {
			event.setId(eventBean.getId());
		}
		
		event.setType(eventBean.getType());
		event.setUser(userRepository.findById(eventBean.getUserId()).get());
		event.setDescription(eventBean.getDescription());
		
		event.setImagePath(eventBean.getImagePath());
		
		try {
			event.setEventDate(Utility.yyyy_MM_dd.parse(eventBean.getEventDateStr()));
		} catch (ParseException e) {
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),null);
		}

		if (event.getId() == null || event.getId() == 0) {
			message = "Event saved successfully";
		} else {
			message = "Event updated successfully";
		}
		eventRepository.save(event);

		return new ApiResponse(HttpStatus.OK, message, null);
	}
	
	public boolean isValidEvent(EventBean eventBean) {
		
		if(Utility.isEmpty(eventBean.getType())) {
			message="Please Enter Event Type";
			return false;
		}
		
		if(Utility.isEmpty(eventBean.getDescription())) {
			message="Please Enter Event Description";
			return false;
		}
		
		if(Utility.isEmpty(eventBean.getEventDateStr())) {
			message="Please Select Event Date";
			return false;
		}
		
		if(eventBean.getUserId()==null) {
			message="Please Select User";
			return false;
		}
		
		return true;
	}

	@Override
	public ApiResponse getEvent(Long id) {
		if (id == null || id == 0) {
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "No data found", null);
		} else {

			Optional<Event> eventOp = eventRepository.findById(id);
			if (eventOp.isPresent()) {
				Event event = eventOp.get();
				
				EventBean eventBean = new EventBean();

				eventBean.setId(event.getId());
				eventBean.setType(event.getType());
				eventBean.setUserName(event.getUser().getName());
				eventBean.setDescription(event.getDescription());
				eventBean.setEventDateStr(Utility.yyyy_MM_dd.format(event.getEventDate()));
				eventBean.setImagePath(event.getImagePath());
				
				return new ApiResponse(HttpStatus.OK, null, eventBean);
			} else {
				return new ApiResponse(HttpStatus.NO_CONTENT, "No data found", null);
			}
		}
	}

	@Override
	public ApiResponse deleteEvent(Long id) {
		if (getEvent(id).getData() == null) {
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "No data found", null);
		} else {
			eventRepository.delete((Event) getEvent(id).getData());
			message = "Event deleted successfully";
			return new ApiResponse(HttpStatus.OK, message, null);
		}
	}

	@Override
	public ApiResponse deleteEvents() {
		eventRepository.deleteAll();
		message = "Events deleted successfully";
		return new ApiResponse(HttpStatus.OK, message, null);
	}

}
