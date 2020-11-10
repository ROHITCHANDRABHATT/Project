package com.cg.fms.service;

import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.entity.Course;
import com.cg.fms.exception.ElementNotFoundException;
import com.cg.fms.exception.InvalidValueException;
import com.cg.fms.repository.ICourseRepository;

@Service
public class CourseService implements ICourseService {
	
	@Autowired
	private ICourseRepository repo;
	
	@Override
	public Course addCourse(Course course) throws InvalidValueException {// method to add course
		validInputCheck(course,1);
		return repo.save(course);
	}

	@Override
	public Course removeCourse(int courseId) throws InvalidValueException {// method to remove course
		Course course = repo.findById(courseId).orElseThrow(() -> new InvalidValueException("Enter Valid Course id. Course with id" + courseId + "does not exist"));
		repo.delete(course);
		return course;
		
	}

	@Override
	public Course updateCourse(Course course) throws ElementNotFoundException, InvalidValueException{// method to update course
		validInputCheck(course, 2);
		return repo.saveAndFlush(course);
		
	}

	@Override
	public Course viewCourse(int courseId) throws InvalidValueException{//method to view course
		Course course = repo.findById(courseId).orElseThrow(() -> new InvalidValueException("Course with id" + courseId + "does not exist"));
		return course;
	}

	@Override
	public List<Course> viewAllCourses() throws ElementNotFoundException {//method to view all course
		List<Course> course = repo.findAll();
		checkList(course);
		return course;
	}

	
	
	public List<Course> checkList(List<Course> course) throws ElementNotFoundException {// check list is empty or not
		
		if((course == null) || course.size() == 0)
			throw new ElementNotFoundException("No courses available right now.");
		
			return course;
	}
	
	public static String validInputCheck(Course course) throws InvalidValueException { // check course name, description and no. of days 
		StringJoiner message = new StringJoiner(" & ");
		int count = 0;
		if (course == null) {
			message.add("Cousre does not exist");
			return message.toString();
		}

		else {
			if (isNullOrEmpty(course.getCourseName())) {
				message.add("Course name should have a valid input");
				count++;
			}
			if (isNullOrEmpty(course.getCourseDescription())) {
				message.add("Course description should have a valid input");
				count++;
			}
			if (course.getNoOfDays() < 1 || course.getNoOfDays() > 999) {
				message.add("Number of days should be between 1 to 999");
				count++;
			}
		}
		if (count >= 1) {
			return message.toString();
		}
		return "Input is correct";

	}
	
	public static void validInputCheck(Course course, int method) throws InvalidValueException{ // checks for valid id 

		String message = validInputCheck(course);
		switch (method) {
		case 1:

			if (message != "Input is correct")
				throw new InvalidValueException(message);
			break;
		case 2:
			if (message.equals("Input is correct") ) {
				if (course.getCourseId() < 1) {
					throw new InvalidValueException("Enter valid Course Id" );
				} 
			}else {
				if (course.getCourseId() < 1) {
					throw new InvalidValueException("Enter valid Course Id" + message);
				}
				throw new InvalidValueException(message);
			} 
			break; 
			
		}
	}
	

		public static boolean isNullOrEmpty(String string) {// Check is string is null or empty
			if (string != null && !string.isEmpty())
				return false;
			return true;
		}

}
