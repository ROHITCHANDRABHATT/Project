package com.cg.fms.service;

import java.util.List;

import com.cg.fms.entity.Trainer;
import com.cg.fms.exception.ElementNotFoundException;
import com.cg.fms.exception.ValueInvalidException;

public interface ITrainerManagementService {

	public Trainer addTrainer(Trainer trainer) throws ValueInvalidException, ElementNotFoundException;
	
	public Trainer updateTrainer(Trainer trainer) throws ValueInvalidException, ElementNotFoundException;
	
	public Trainer removeTrainer(int trainerId) throws ValueInvalidException, ElementNotFoundException;
	
	public Trainer viewTrainer(int trainerId) throws ValueInvalidException, ElementNotFoundException;
	
	public List<Trainer> viewAllTrainers(String skill) throws ValueInvalidException, ElementNotFoundException;
	
	public List<Trainer> viewAllTrainers() throws ElementNotFoundException;
	
}
