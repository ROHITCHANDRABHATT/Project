package com.cg.fms.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cg.fms.entity.Course;
import com.cg.fms.exception.ElementNotFoundException;
import com.cg.fms.exception.InvalidValueException;
import com.cg.fms.rest.CourseRestController;
import com.cg.fms.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CourseRestController.class)
public class TestCourseController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CourseService service;

	@Autowired
	private ObjectMapper mapper;
	
	@Test
	public void testAddCourseValidInput() throws Exception{//Test for addCourse() with valid inputs
		Course course = new Course(10,"Java","Full Stack",45);
		when(service.addCourse(course)).thenReturn(course);
		mockMvc.perform(post("/course/add").accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(course))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testAddCourseInValidInput() throws Exception{//Test for addCourse() with invalid inputs
		Course course = new Course(11,null,"Full Stack",0);
		when(service.addCourse(Mockito.any(Course.class))).thenThrow(InvalidValueException.class);
		mockMvc.perform(post("/course/add").accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(course))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotAcceptable());
	}
	
	@Test
	public void testUpdateCourseValidInput() throws Exception{//Test for updateCourse() with valid inputs
		Course course = new Course(10,"Java","Full Stack",45);
		when(service.updateCourse(course)).thenReturn(course);
		mockMvc.perform(put("/course/update").accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(course))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateCourseInValidInput() throws Exception{//Test for updateCourse() with invalid inputs
		Course course = new Course(11,null,"Full Stack",0);
		when(service.updateCourse(Mockito.any(Course.class))).thenThrow(InvalidValueException.class);
		mockMvc.perform(put("/course/update").accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(course))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotAcceptable()); 
	}
	
	@Test
	public void testUpdateCourseInValidInput2() throws Exception{//Test for updateCourse() with invalid inputs
		Course course = new Course(20,"Java2","Full Stack",45);
		when(service.updateCourse(Mockito.any(Course.class))).thenThrow(ElementNotFoundException.class);
		mockMvc.perform(put("/course/update").accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(course))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void testRemoveCourseValidInput() throws Exception{//Test for removeCourse() with valid inputs
		Course course = new Course(20,"Java2","Full Stack",45);
		when(service.removeCourse(20)).thenReturn(course);
		mockMvc.perform(delete("/course/remove?id=20")).andExpect(status().isOk());
	
	}
	
	@Test
	public void testRemoveCourseInValidInput() throws Exception{//Test for removeCourse() with invalid inputs
		when(service.removeCourse(0)).thenThrow(InvalidValueException.class);
		mockMvc.perform(delete("/course/remove?id=0")).andExpect(status().isNotAcceptable());
	
	}
	
	@Test
	public void testViewCourseValidInput() throws Exception{//Test for viewCourse() with valid inputs
		Course course = new Course(20,"Java2","Full Stack",45);
		when(service.viewCourse(20)).thenReturn(course);
		mockMvc.perform(get("/course/view?id=20")).andExpect(status().isOk());
	
	}
	
	@Test
	public void testViewCourseInValidInput() throws Exception{//Test for viewCourse() with invalid inputs
		when(service.viewCourse(0)).thenThrow(InvalidValueException.class);
		mockMvc.perform(get("/course/view?id=0")).andExpect(status().isNotAcceptable());
	
	}
	
	@Test
	public void testViewAllCoursesValidInput() throws Exception{//Test for viewAllCourse() with valid inputs
		Course course = new Course(20,"Java2","Full Stack",45);
		Course course1 = new Course(21,"Java2","Full Stack",45);
		List<Course> list = new ArrayList<>();
		list.add(course);
		list.add(course1);
		when(service.viewAllCourses()).thenReturn(list);
		mockMvc.perform(get("/course/list").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	
	}
	
	@Test
	public void testViewAllCoursesInValidInput() throws Exception{//Test for viewAllCourse() with invalid inputs
		when(service.viewAllCourses()).thenThrow(ElementNotFoundException.class);
		mockMvc.perform(get("/course/list").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	
	}

}
