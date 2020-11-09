package com.cg.fms.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.entity.Program;
import com.cg.fms.exception.ElementNotFoundException;
import com.cg.fms.exception.InvalidValueException;
import com.cg.fms.repository.ITrainingProgramRepository;

@Service
public class TrainingProgramService implements ITrainingProgramService{
   
	@Autowired
	private ITrainingProgramRepository repo;
	
	
	public boolean programCheck(Program pr) throws InvalidValueException {

		if (pr.getStartDate() == null)
			throw new InvalidValueException("The Startdate is null :" + pr.getStartDate());

		else if (pr.getEndDate() == null)
			throw new InvalidValueException("The Enddate is null :" + pr.getEndDate());
		
		else if(pr.getEndDate().equals(pr.getStartDate()))
			throw new InvalidValueException("The StartDate or EndDate is Invalid");
		
		else if(pr.getEndDate().isBefore(pr.getStartDate()))
			throw new InvalidValueException("EndDate cannot be before StartDate");
		
		else
			
			return true;
	}

	
	/* -------------------------------------Implementation -------------------------------------------*/
	
	@Override
	public Program createProgram(Program pr) throws InvalidValueException {
		programCheck(pr);
		return repo.save(pr);
	}

	@Override
	@Transactional
	public Program updateProgram(Program pr) throws InvalidValueException {
		programCheck(pr);
		return repo.save(pr);
	}

	@Override
	public Program removeProgram(int programid) throws ElementNotFoundException {
		Program program=repo.findById(programid).orElseThrow(() -> new ElementNotFoundException("Program Id doesn't exist : "+programid));
		repo.deleteById(programid);
	   return program;
	}

	@Override
	public Program viewProgram(int programid) throws ElementNotFoundException {
		Program program=repo.findById(programid).orElseThrow(() -> new ElementNotFoundException("Program Id doesn't exist : "+programid));;
		 return program;
	}

	@Override
	public List<Program> viewAllPrograms() throws ElementNotFoundException {
		List<Program> list=repo.findAll();
		
		if(list.isEmpty())
			throw new ElementNotFoundException("Program List is Empty");
		
		return list;
	}

	@Override
	public List<Program> viewAllProgramsByDate(LocalDate date) throws ElementNotFoundException {
         List<Program> list=repo.findByLocalDate(date);
         
         if(list.isEmpty())
 			throw new ElementNotFoundException("Program Not Found on Certain Date");
 		
 		return list;
	}

	@Override
	public List<Program> viewAllProgramsByFaculty(int facultyid) throws ElementNotFoundException {
		
		return repo.findByFacultyId(facultyid);
	}

}
