package com.cg.fms.service;

import java.util.List;

import com.cg.fms.entity.Trainer;
import com.cg.fms.exception.ElementNotFoundException;
import com.cg.fms.exception.InvalidValueException;

public interface ITrainerManagementService {

	public Trainer addTrainer(Trainer trainer) throws InvalidValueException, ElementNotFoundException;
	
	public Trainer updateTrainer(Trainer trainer) throws InvalidValueException, ElementNotFoundException;
	
	public Trainer removeTrainer(int trainerId) throws InvalidValueException, ElementNotFoundException;
	
	public Trainer viewTrainer(int trainerId) throws InvalidValueException, ElementNotFoundException;
	
	public List<Trainer> viewAllTrainers(String skill) throws InvalidValueException, ElementNotFoundException;
	
	public List<Trainer> viewAllTrainers() throws ElementNotFoundException;
	
}
