package com.cg.fms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.fms.entity.Feedback;
import com.cg.fms.entity.Program;
import com.cg.fms.exception.InvalidValueException;
import com.cg.fms.exception.ElementNotFoundException;
import com.cg.fms.repository.IFeedbackRepository;

@Service
public class FeedbackService implements IFeedbackService {
	
	@Autowired
	private IFeedbackRepository repo;
	
	@Override
	public Feedback addFeedback(Feedback fd) throws InvalidValueException {
		if(fd.getFeedbackId() == 0 && fd.getProgram().getProgramId() == 0 && fd.getTrainerid() == 0
				&& fd.getFeedbackCriteria1() < 0 && fd.getFeedbackCriteria1() >= 10
				&& fd.getFeedbackCriteria2() < 0 && fd.getFeedbackCriteria2() >= 10
				&& fd.getFeedbackCriteria3() < 0 && fd.getFeedbackCriteria3() >= 10
				&& fd.getFeedbackCriteria4() < 0 && fd.getFeedbackCriteria4() >= 10
				&& fd.getFeedbackCriteria5() < 0 && fd.getFeedbackCriteria5() >= 10
				&& fd.getComments() == null && fd.getSuggestions() == null) {
			throw new InvalidValueException("Check all the fields.... Feedback cannot be Added");
		} else {
			return repo.save(fd);
		}
	}

	@Override
	public Feedback updateFeedback(Feedback fd) throws ElementNotFoundException {
		if(fd == null) {
			throw new ElementNotFoundException("Feedback not Found");
		} else {
			repo.save(fd);
		}
		return fd;
	}

	@Override
	public List<Feedback> viewTrainerFeedback(int trainerid) throws ElementNotFoundException {
		List<Feedback> fd = repo.findByTrainerid(trainerid);
		if(fd == null)
			throw new ElementNotFoundException("Feedback not Found");
		return fd;
	}

	@Override
	public List<Feedback> viewProgramFeedback(int programid) throws ElementNotFoundException {
		List<Feedback> fd = repo.viewByProgramid(programid);
		if(fd == null)
			throw new ElementNotFoundException("Feedback not Found");
		return fd;
	}

}
