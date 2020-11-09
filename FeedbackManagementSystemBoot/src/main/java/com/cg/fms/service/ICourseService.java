package com.cg.fms.service;

import java.util.List;

import com.cg.fms.entity.Course;
import com.cg.fms.exception.ElementNotFoundException;

import com.cg.fms.exception.InvalidValueException;

public interface ICourseService {
	
	public Course addCourse(Course course) throws InvalidValueException;
	public void removeCourse(int courseId) throws InvalidValueException;
	public Course updateCourse(Course course) throws ElementNotFoundException,InvalidValueException;
	public Course viewCourse(int courseId) throws InvalidValueException;
	public List<Course> viewAllCourses() throws ElementNotFoundException; 


}
