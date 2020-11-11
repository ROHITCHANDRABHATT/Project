package com.cg.fms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.entity.Employee;
import com.cg.fms.entity.Program;
import com.cg.fms.exception.ElementNotFoundException;
import com.cg.fms.exception.InvalidValueException;
import com.cg.fms.repository.IParticipantRepository;
import com.cg.fms.repository.ITrainingProgramRepository;

@Service
public class ParticipantService implements IParticipantService {


	
	@Autowired
	private IParticipantRepository repo;
	
	@Autowired
	private ITrainingProgramRepository pro;
	
	
	// validity check for Employee 
		public boolean validityCheck(Employee emp) throws InvalidValueException {
			if (emp == null)
				throw new InvalidValueException("Empty Employee Object");
			else if (emp.getEmpName().trim().isEmpty() || emp.getEmpName() == null )
				throw new InvalidValueException("Employee name is empty or null");
			else if (!((emp.getPassword().length() >= 6) &&  (emp.getPassword().length() <= 12)))
				throw new InvalidValueException("Invalid Password!!!(length must be in range[6,12]");
			else
			return true;
		}
	
	@Override
	public Employee enrollParticipant(Employee emp , int programId) throws InvalidValueException {
		
		if (validityCheck(emp)) 
			throw new InvalidValueException("Employee not found");
		
		Program p = pro.findById(programId).orElseThrow(() -> new InvalidValueException("Program Id not found"+programId));
		if(p!=null) {	
			emp.setProgram(p);		
	}
		return repo.save(emp);
	}
	
	
	@Override
	public List<Employee> viewParticipantList(int programId) throws ElementNotFoundException, InvalidValueException {
		// TODO Auto-generated method stub
		
		if(programId<=0)
			throw new InvalidValueException("Invalid Program ID");
		
		List<Employee> ParticipantsList = repo.findByProgram(programId);
		
		if(ParticipantsList.isEmpty())
			throw new ElementNotFoundException("Participants not found");
		
		return ParticipantsList;
		
		}

	}
