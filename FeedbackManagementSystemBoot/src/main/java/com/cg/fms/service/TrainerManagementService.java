package com.cg.fms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.fms.entity.Trainer;
import com.cg.fms.entity.Role;
import com.cg.fms.exception.ElementNotFoundException;
import com.cg.fms.exception.ValueInvalidException;
import com.cg.fms.repository.ITrainerMangementRepository;
import com.cg.fms.repository.ITrainingProgramRepository;

@Service
public class TrainerManagementService implements ITrainerManagementService{

	@Autowired
	private ITrainerMangementRepository repo;
	
	@Autowired
	private ITrainingProgramRepository prepo;
	
	
	@Override
	@Transactional 
	public Trainer addTrainer(Trainer trainer) throws ValueInvalidException, ElementNotFoundException {
		
		if(!trainer.getRole().equals(Role.COORDINATOR))
			throw new ValueInvalidException("Role must be COORDINATOR");
		
		if(trainer.getProgram()!=null)
			prepo.findById(trainer.getProgram().getProgramId())
						.orElseThrow(()->new ElementNotFoundException("Program doesn't exists"));

		return repo.save(trainer);
	}

	@Override
	@Transactional
	public Trainer updateTrainer(Trainer trainer) throws ValueInvalidException, ElementNotFoundException {
		
		if(trainer.getEmployeeId()<=0)
			throw new ValueInvalidException("Trainer Id is invalid");
		
		if(trainer.getProgram()!=null)
			prepo.findById(trainer.getProgram().getProgramId())
						.orElseThrow(()->new ElementNotFoundException("Program doesn't exists"));
		
		repo.findById(trainer.getEmployeeId()).orElseThrow(()->new ElementNotFoundException("Trainer doesn't exists"));
		return repo.save(trainer);
		

	}

	@Override
	@Transactional
	public Trainer removeTrainer(int trainerId) throws ValueInvalidException, ElementNotFoundException {
		
		if(trainerId<=0)
			throw new ValueInvalidException("Trainer ID is invalid");
		
		Trainer emp = repo.findById(trainerId).orElseThrow(()->new ElementNotFoundException("Trainer doesn't exists"));
		
		repo.deleteById(trainerId);
		return emp;
	}

	@Override
	public Trainer viewTrainer(int trainerId) throws ValueInvalidException, ElementNotFoundException {
		
		if(trainerId<=0)
			throw new ValueInvalidException("Trainer ID is invalid");
		
		return repo.findById(trainerId).orElseThrow(()->new ElementNotFoundException("Trainer Doesn't exists"));
	}
 
	@Override
	public List<Trainer> viewAllTrainers(String skill) throws ValueInvalidException, ElementNotFoundException {
		
		if(skill.trim().isEmpty())
			throw new ValueInvalidException("Skill is either null or Empty");
		
		List<Trainer> trainers = repo.findBySkillIgnoreCase(skill);
		
		if(trainers.isEmpty())
			throw new ElementNotFoundException("Trainers does not exist");
		
		return trainers;
	}

	@Override
	public List<Trainer> viewAllTrainers() throws ElementNotFoundException {
		
		List<Trainer> trainers = repo.findAll();
		
		if(trainers.isEmpty())
			throw new ElementNotFoundException("Trainers does not exist");
		
		return trainers;
	}

}
