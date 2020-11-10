package com.cg.fms.service;

import java.util.List;

import com.cg.fms.entity.Employee;
import com.cg.fms.exception.ElementNotFoundException;
import com.cg.fms.exception.InvalidValueException;

public interface IParticipantService {

	public Employee enrollParticipant(Employee emp, int programId) throws InvalidValueException;
	
	public List<Employee> viewParticipantList(int programId) throws ElementNotFoundException, InvalidValueException;


}
