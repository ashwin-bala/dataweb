package com.applns.bigdata.web.data.rest;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.applns.bigdata.web.data.events.EventsSender;
import com.applns.bigdata.web.data.models.EventMessage;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;

@RestController
public class EventsController {
	
	@Autowired
	private EventsSender eventsSender;
	
	@Autowired
	private CompositeMeterRegistry meterRegistry;
	


	@PostMapping("/events")
	public ResponseEntity<String> events(@RequestBody EventMessage eventMessage) {
	    Counter eventsCounter = meterRegistry.counter("app.events");
		System.out.println(eventMessage.getFromDate());
		try {
			eventsSender.sendMessage(eventMessage);
			eventsCounter.increment();
	        return new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (JsonProcessingException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        return new ResponseEntity<>("Failed sending message", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}
