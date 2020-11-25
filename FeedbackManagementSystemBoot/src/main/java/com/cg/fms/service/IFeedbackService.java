package com.cg.fms.service;

import java.util.List;

import com.cg.fms.entity.Feedback;
import com.cg.fms.entity.Program;
import com.cg.fms.exception.ElementNotFoundException;
import com.cg.fms.exception.InvalidValueException;

public interface IFeedbackService {

public Feedback addFeedback(Feedback fd) throws InvalidValueException;

public List<Feedback> viewTrainerFeedback(int trainerid) throws ElementNotFoundException;

public List<Feedback> viewProgramFeedback(int programid) throws ElementNotFoundException;

}
