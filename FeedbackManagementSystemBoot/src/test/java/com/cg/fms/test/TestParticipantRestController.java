package com.cg.fms.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cg.fms.entity.Employee;
import com.cg.fms.entity.Role;
import com.cg.fms.exception.ElementNotFoundException;
import com.cg.fms.exception.InvalidValueException;
import com.cg.fms.rest.ParticipantRestController;
import com.cg.fms.service.IParticipantService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(value = ParticipantRestController.class)
public class TestParticipantRestController {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private IParticipantService service;
	
	
	//---------------------enrollParticipant-------------------
	
	@Test
	public void testenrollParticipantPositive() throws Exception {
		int programId = 123;
		Employee emp = new Employee(13,"Navneet","navi123#Hker", Role.PARTICIPANT,null);
		when(service.enrollParticipant(emp,programId)).thenReturn(emp);
		
		mvc.perform(post("/enroll?id=123")
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(emp))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	
	}
	
	
	
	@Test
	public void testenrollParticipantNegative() throws Exception {
		
		Employee emp = new Employee(13,"Navneet","navi123#Hker", Role.PARTICIPANT,null);
		when(service.enrollParticipant(emp, 12)).thenThrow(InvalidValueException.class);
		
		 mvc.perform(post("/enroll?id=12")
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(emp))
				.contentType(MediaType.APPLICATION_JSON));
			
		//	.andExpect(status().isNotFound());
	
	}
	
	
	//-------------------------------viewParticipantList--------------
	
	@Test
	public void testviewParticipantListPositive() throws Exception {
		
		
		Employee emp = new Employee(13,"Navneet","navi123#Hker", Role.PARTICIPANT,null);
		
		List<Employee> ParticipantsList = new ArrayList<>();
		ParticipantsList.add(emp);
		
		when(service.viewParticipantList(123)).thenReturn(ParticipantsList);
		
		mvc.perform(get("/List?id=123"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testviewParticipantListNegative() throws Exception {
	
	
		when(service.viewParticipantList(0)).thenThrow(InvalidValueException.class);
		
		mvc.perform(get("/List?id=0"))
		.andExpect(status().isNotFound());
	}	
	
	
	@Test
	public void testviewParticipantListInvalidvalue() throws Exception {
	
	
		when(service.viewParticipantList(12)).thenThrow(ElementNotFoundException.class);
		
		mvc.perform(get("/List?id=12"))
		.andExpect(status().isNotFound());
	}	
		
}