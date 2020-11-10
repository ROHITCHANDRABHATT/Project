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

import com.cg.fms.entity.Trainer;
import com.cg.fms.entity.Role;
import com.cg.fms.exception.ElementNotFoundException;
import com.cg.fms.exception.InvalidValueException;
import com.cg.fms.rest.TrainerManagementRestController;
import com.cg.fms.service.TrainerManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TrainerManagementRestController.class)
public class TestTrainerManagementRestController {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private TrainerManagementService service;
	
	
	//------------------------add----------------------------//
	@Test
	public void testAddTrainerPositive() throws Exception{
		
		Trainer trainer = new Trainer(12,"Rohit","ABcd#456gh",Role.COORDINATOR,null,"JAVA");
		
		when(service.addTrainer(trainer)).thenReturn(trainer);
		
		mvc.perform(post("/trainer/add")
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(trainer))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testAddTrainerNegative() throws Exception {
		
		Trainer trainer = new Trainer(0,"Rohit","ABcd#456gh",Role.ADMIN,null,"JAVA");
		
		when(service.addTrainer(Mockito.any(Trainer.class))).thenThrow(InvalidValueException.class);
		
		mvc.perform(post("/trainer/add")
				.accept(MediaType.APPLICATION_JSON) 
				.content(mapper.writeValueAsString(trainer))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotAcceptable());
	}
	
	//-------------------update---------------------//
	@Test
	public void testUpdateTrainerPositive() throws Exception{
		
		Trainer trainer = new Trainer(12,"Rohit","ABcd#456gh",Role.COORDINATOR,null,"JAVA");
		
		when(service.updateTrainer(trainer)).thenReturn(trainer);
		
		mvc.perform(put("/trainer/update")
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(trainer))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateTrainerInvalid() throws Exception {
		
		Trainer trainer = new Trainer(0,"Rohit","ABcd#456gh",Role.COORDINATOR,null,"JAVA");
		
		when(service.updateTrainer(Mockito.any(Trainer.class))).thenThrow(InvalidValueException.class);
		
		mvc.perform(put("/trainer/update")
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(trainer))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotAcceptable());
	}
	
	@Test
	public void testUpdateTrainerNotFound() throws ElementNotFoundException,Exception {
		
		Trainer trainer = new Trainer(14,"Rohit","ABcd#456gh",Role.COORDINATOR,null,"JAVA");
		
		when(service.updateTrainer(Mockito.any(Trainer.class))).thenThrow(ElementNotFoundException.class);
		
		mvc.perform(put("/trainer/update")
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(trainer))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()); 
	} 
	
	//---------------------delete------------------//
	@Test
	public void testDeleteTrainerPositive() throws Exception {

		Trainer trainer = new Trainer(13,"Rohit","Av3456#hgt",Role.COORDINATOR,null,"JAVA");
		
		when(service.removeTrainer(13)).thenReturn(trainer);
		
		mvc.perform(delete("/trainer/delete?id=13"))
				.andExpect(status().isOk());
	} 
	
	@Test
	public void testDeleteTrainerInvalid() throws Exception {
		
		when(service.removeTrainer(0)).thenThrow(InvalidValueException.class);
		
		mvc.perform(delete("/trainer/delete?id=0"))
				.andExpect(status().isNotAcceptable());
	} 
	
	@Test
	public void testDeleteTrainerNotFound() throws Exception {
		
		when(service.removeTrainer(14)).thenThrow(ElementNotFoundException.class);
		
		mvc.perform(delete("/trainer/delete?id=14"))
				.andExpect(status().isNotFound());
	}
	
	
	//--------------------view------------------------//
	@Test
	public void testViewTrainerPositive() throws Exception {

		Trainer trainer = new Trainer(13,"Rohit","Av3456#hgt",Role.COORDINATOR,null,"JAVA");
		
		when(service.viewTrainer(13)).thenReturn(trainer);
		
		mvc.perform(get("/trainer/view?id=13"))
				.andExpect(status().isOk());
	} 
	
	@Test
	public void testViewTrainerInvalid() throws Exception {
		
		when(service.viewTrainer(0)).thenThrow(InvalidValueException.class);
		
		mvc.perform(get("/trainer/view?id=0"))
				.andExpect(status().isNotAcceptable());
	} 
	
	@Test
	public void testViewTrainerNotFound() throws Exception {
		
		when(service.viewTrainer(14)).thenThrow(ElementNotFoundException.class);
		
		mvc.perform(get("/trainer/view?id=14"))
				.andExpect(status().isNotFound());
	}
	
	//----------------------viewBySkill-----------------------------//
	@Test
	public void testViewBySkillPositive() throws Exception {
		
		Trainer trainer = new Trainer(13,"Rohit","ABcd#456gh",Role.COORDINATOR,null,"JAVA");
		List<Trainer> trainers = new ArrayList<>();
		trainers.add(trainer);
		
		when(service.viewAllTrainers("JAVA")).thenReturn(trainers);
		
		mvc.perform(get("/trainer/viewBySkill?skill=JAVA"))
				.andExpect(status().isOk());
	} 
	
	@Test
	public void testViewBySkillNotFound() throws Exception {
		
		when(service.viewAllTrainers("PYTHON")).thenThrow(ElementNotFoundException.class);
		
		mvc.perform(get("/trainer/viewBySkill?skill=PYTHON"))
				.andExpect(status().isNotFound());
	}
	
	
	//-----------------------viewAllTrainers----------------------------//
	@Test
	public void testViewAllTrainersPositive() throws Exception {
		
		Trainer trainer = new Trainer(13,"Rohit","ABcd#456gh",Role.COORDINATOR,null,"JAVA");
		List<Trainer> trainers = new ArrayList<>();
		trainers.add(trainer);
		
		when(service.viewAllTrainers()).thenReturn(trainers);
		 
		mvc.perform(get("/trainer/viewAllTrainers"))
				.andExpect(status().isOk());
	} 
	
	@Test
	public void testViewAllTrainersNotFound() throws Exception {
		
		when(service.viewAllTrainers()).thenThrow(ElementNotFoundException.class);
		
		mvc.perform(get("/trainer/viewAllTrainers"))
				.andExpect(status().isNotFound());
	}
}
