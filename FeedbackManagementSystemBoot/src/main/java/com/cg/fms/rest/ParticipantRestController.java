package com.cg.fms.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.entity.Employee;
import com.cg.fms.exception.ElementNotFoundException;
import com.cg.fms.exception.InvalidValueException;
import com.cg.fms.service.IParticipantService;


@RestController
public class ParticipantRestController {

	@Autowired
	private IParticipantService service;
	
		//URL: http://localhost:8800/enroll?id=123
		@PostMapping(value="/enroll", consumes ="application/json")
		public Employee enrollParticipant (@RequestBody  Employee emp,  @RequestParam("id") int programId) throws InvalidValueException {
			
			return service.enrollParticipant(emp, programId);
			
		}
		
		//URL: http://localhost:8800/List/get?id=123
		@GetMapping(value="/List", produces="application/json")
		public List<Employee> viewParticipantList(@RequestParam("id") int programId) throws  ElementNotFoundException, InvalidValueException {
			
			return service.viewParticipantList(programId);
			
		}
	
}
