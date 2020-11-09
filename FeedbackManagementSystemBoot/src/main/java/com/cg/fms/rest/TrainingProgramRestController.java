package com.cg.fms.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.cg.fms.entity.Program;
import com.cg.fms.exception.ElementNotFoundException;
import com.cg.fms.exception.InvalidValueException;
import com.cg.fms.service.ITrainingProgramService;

@RestController
public class TrainingProgramRestController {
	
	@Autowired
	private ITrainingProgramService service;
	
	// URL : http://localhost:8854/create
	@PostMapping(value="/create", consumes = "application/json")
	public Program createProgram(@RequestBody Program pr) throws InvalidValueException
	{
		
		return service.createProgram(pr);
	}
	
	// URL : http://localhost:8854/update
	@PostMapping(value="/update", consumes = "application/json")
	public Program updateProgram(@RequestBody Program pr) throws InvalidValueException
	{
		return service.updateProgram(pr);
	}
	
	// URL : http://localhost:8854/delete
	@DeleteMapping(value="/delete", consumes= "application/json")
	public Program removeProgram(@RequestParam("id") int programid) throws ElementNotFoundException
	{
		return service.removeProgram(programid);
	}
	
	// URL : http://localhost:8854/viewid?id=1
	@GetMapping(value="/viewid", produces="application/json")
	public Program viewProgram(@RequestParam("id")int programid) throws ElementNotFoundException {
		
		return  service.viewProgram(programid);
	}
	
	// URL : http://localhost:8854/list
	@GetMapping(value="/list", produces="application/json")
	public List<Program> viewAllPrograms() throws ElementNotFoundException
	{
		return service.viewAllPrograms();
	}
	
	// URL : http://localhost:8854/listbydate?date="2016-03-11"
	@GetMapping(value="/listbydate/{date}", produces="application/json")
	public List<Program> viewAllProgramsByDate(@PathVariable("date")@DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date) throws ElementNotFoundException {
		
		return service.viewAllProgramsByDate(date);
	}

}
