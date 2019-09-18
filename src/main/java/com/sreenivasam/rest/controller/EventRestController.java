package com.sreenivasam.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sreenivasam.beans.EventBean;
import com.sreenivasam.service.EventService;
import com.sreenivasam.util.ApiResponse;

@RestController
@RequestMapping(path = "/api/event")
@CrossOrigin("*")
public class EventRestController {

	@Autowired
	private EventService eventService;

	@PostMapping
	public ApiResponse saveEvent(@RequestBody(required = true) EventBean eventBean) {
		return eventService.saveEvent(eventBean);
	}

	@GetMapping
	public ApiResponse events() {
		return eventService.getEvents();
	}

	@GetMapping("/{id}")
	public ApiResponse getEvent(@PathVariable(value = "id") Long id) {
		return eventService.getEvent(id);
	}

	@DeleteMapping("/{id}")
	public ApiResponse deleteEvent(@PathVariable(value = "id") Long id) {
		return eventService.deleteEvent(id);
	}

	@DeleteMapping()
	public ApiResponse deleteEvents() {
		return eventService.deleteEvents();
	}
}
