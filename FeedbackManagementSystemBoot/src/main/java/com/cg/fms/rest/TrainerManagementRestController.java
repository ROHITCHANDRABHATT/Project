package com.cg.fms.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.entity.Trainer;
import com.cg.fms.exception.ElementNotFoundException;
import com.cg.fms.exception.ValueInvalidException;
import com.cg.fms.service.ITrainerManagementService;

@RestController
@RequestMapping("/trainer")
public class TrainerManagementRestController {
	
	@Autowired
	ITrainerManagementService service;
	
	//URL: http://localhost:8880/trainer/add
	@PostMapping(value = "/add", consumes = "application/json")
	public Trainer add(@Valid @RequestBody Trainer trainer) throws ValueInvalidException,ElementNotFoundException {	
		
		return service.addTrainer(trainer);
	}
	
	//URL: http://localhost:8880/trainer/update
	@PutMapping(value = "/update", consumes = "application/json")
	public Trainer update(@Valid @RequestBody Trainer trainer) throws ValueInvalidException,ElementNotFoundException {
		
		return service.updateTrainer(trainer);
	}
	 
	//URL: http://localhost:8880/trainer/delete
	@DeleteMapping(value = "/delete", produces = "application/json")
	public Trainer delete(@RequestParam("id") int trainerId) throws ValueInvalidException,ElementNotFoundException {
		
		return service.removeTrainer(trainerId);
	}
	
	//URL: http://localhost:8880/trainer/view
	@GetMapping(value = "/view", produces = "application/json")
	public Trainer view(@RequestParam("id") int trainerId) throws ValueInvalidException, ElementNotFoundException {
		
		return service.viewTrainer(trainerId);
	}
	
	//URL: http://localhost:8880/trainer/viewBySkill
	@GetMapping(value = "/viewBySkill", produces = "application/json")
	public List<Trainer> viewBySkill(@RequestParam("skill") String skill) throws ValueInvalidException,ElementNotFoundException {
		
		return service.viewAllTrainers(skill);
	}
	
	//URL: http://localhost:8880/trainer/viewAllTrainers
	@GetMapping(value = "/viewAllTrainers", produces = "application/json")
	public List<Trainer> viewTrainerList() throws ElementNotFoundException {
		
		return service.viewAllTrainers();
	}
	
	
}
