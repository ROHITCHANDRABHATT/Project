package com.cg.fms.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.entity.Feedback;
import com.cg.fms.entity.Program;
import com.cg.fms.exception.InvalidValueException;
import com.cg.fms.exception.ElementNotFoundException;
import com.cg.fms.service.IFeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackRestController {
	
	@Autowired
	private IFeedbackService service;
	
	// URL: http://localhost:8880/feedback/addFeedback
	@PostMapping(value = "/addFeedback", consumes = "application/json")
	public String addFeedback(@RequestBody Feedback fd) throws InvalidValueException{
		service.addFeedback(fd);
		return "Feedback Added Successfully";
	}
	
	// URL: http://localhost:8880/feedback/trainerid?trainerid=20
	@GetMapping(value = "/trainerid", produces = "application/json")
	public List<Feedback> viewTrainerFeedback(@RequestParam("trainerid") int trainerid) throws ElementNotFoundException {
		 return service.viewTrainerFeedback(trainerid);
	}
	// URL: http://localhost:8880/feedback/programid?programid=31
	@GetMapping(value = "/programid", produces = "application/json")
	public List<Feedback> viewProgramFeedback(@RequestParam("trainingId") int programid) throws ElementNotFoundException {
		return service.viewProgramFeedback(programid);
	}
}
