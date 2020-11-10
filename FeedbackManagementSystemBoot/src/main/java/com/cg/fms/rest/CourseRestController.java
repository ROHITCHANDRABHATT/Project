package com.cg.fms.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.entity.Course;
import com.cg.fms.exception.ElementNotFoundException;
import com.cg.fms.exception.InvalidValueException;
import com.cg.fms.service.ICourseService;

@RestController//RepresentationalStateTransferController 
public class CourseRestController {

	@Autowired
	private ICourseService service;
	
	// URL: http://localhost:8880/add
	@PostMapping(value = "/add", consumes = "application/json")
	public Course addCourse(@RequestBody Course course) throws InvalidValueException {
		service.addCourse(course);
		return course;
		
	}
	
	// URL: http://localhost:8880/remove?id=
	@DeleteMapping(value = "/remove")
	public Course removeCourse(@RequestParam("id") int courseId) throws InvalidValueException{
		return service.removeCourse(courseId);
	}
	
	//// URL: http://localhost:8880/view?id=
	@GetMapping(value = "/view", produces = "application/json")//It will handle get requests
	public Course viewCourse(@RequestParam("id") int courseId) throws InvalidValueException {
		return service.viewCourse(courseId);
	}
	
	//URL: http://localhost:8880/update
	@PutMapping(value = "/update", consumes = "application/json")
	public Course updateCourse(@RequestBody Course course) throws ElementNotFoundException,InvalidValueException {
		return service.updateCourse(course);
	}
	
	//URL: http://localhost:8880/list
	@GetMapping(value = "/list", consumes = "application/json")
		public List<Course> viewAllCourses() throws ElementNotFoundException{
			return service.viewAllCourses();
		
	}
	
	
}
