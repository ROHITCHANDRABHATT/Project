package com.cg.fms.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;
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

import com.cg.fms.entity.Program;
import com.cg.fms.exception.ElementNotFoundException;
import com.cg.fms.exception.InvalidValueException;
import com.cg.fms.rest.TrainingProgramRestController;
import com.cg.fms.service.TrainingProgramService;
import com.fasterxml.jackson.databind.ObjectMapper;

	@RunWith(SpringRunner.class)
	@WebMvcTest(value = TrainingProgramRestController.class)
	public class TestTrainingProgramRestController {
		
		@Autowired
		private MockMvc mvc;
		
		@Autowired
		private ObjectMapper mapper;
		
		@MockBean
		private TrainingProgramService service;
		

		//------------------------add----------------------------//
		@Test
		public void testCreateProgramPositive() throws Exception{
			
			Program program = new Program(1,LocalDate.of(2020, Month.AUGUST, 11),LocalDate.of(2020, Month.NOVEMBER, 11),null,null);
			
			when(service.createProgram(program)).thenReturn(program);
			
			mvc.perform(post("/create")
					.accept(MediaType.APPLICATION_JSON)
					.content(mapper.writeValueAsString(program))
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		}
		
		@Test
		public void testCreateProgramNegative() throws Exception {
			
			Program program = new Program(1,null,LocalDate.of(2020, Month.NOVEMBER, 11),null,null);
			
			when(service.createProgram(Mockito.any(Program.class))).thenThrow(InvalidValueException.class);
			
			mvc.perform(post("/create")
					.accept(MediaType.APPLICATION_JSON) 
					.content(mapper.writeValueAsString(program))
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isNotAcceptable());
		}
		
		@Test
		public void testUpdateProgramPositive() throws Exception{
			
			Program program = new Program(2,LocalDate.of(2020, Month.APRIL, 11),LocalDate.of(2020, Month.JUNE, 11),null,null);
			
			when(service.updateProgram(program)).thenReturn(program);
			
			mvc.perform(put("/update")
					.accept(MediaType.APPLICATION_JSON)
					.content(mapper.writeValueAsString(program))
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		}
		
		@Test
		public void testUpdateProgramInvalid() throws Exception {
			
			Program program = new Program(2,LocalDate.of(2020, Month.APRIL, 11),null,null,null);
			
			when(service.updateProgram(Mockito.any(Program.class))).thenThrow(InvalidValueException.class);
			
			mvc.perform(put("/update")
					.accept(MediaType.APPLICATION_JSON)
					.content(mapper.writeValueAsString(program))
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isNotAcceptable());
		}
		
		@Test
		public void testRemoveProgramPositive() throws Exception {
			Program program = new Program(2,LocalDate.of(2020, Month.APRIL, 11),LocalDate.of(2020, Month.JUNE, 11),null,null);
			
			when(service.removeProgram(2)).thenReturn(program);
			
			mvc.perform(delete("/delete?id=2")).andExpect(status().isOk());
		} 
		
		@Test
		public void testRemoveProgramNegative() throws Exception {
			
			when(service.removeProgram(0)).thenThrow(ElementNotFoundException.class);
			
			mvc.perform(delete("/delete?id=0"))
					.andExpect(status().isNotFound());
		} 
		@Test
		public void testViewProgramPositive() throws Exception {

			Program program = new Program(2,LocalDate.of(2020, Month.APRIL, 11),LocalDate.of(2020, Month.JUNE, 11),null,null);
			
			when(service.viewProgram(2)).thenReturn(program);
			
			mvc.perform(get("/viewid?id=2"))
					.andExpect(status().isOk());
		} 
		
		@Test
		public void testViewProgramNegative() throws Exception {
			
			when(service.viewProgram(0)).thenThrow(ElementNotFoundException.class);
			
			mvc.perform(get("/viewid?id=0"))
					.andExpect(status().isNotFound());
		} 
		
		@Test
		public void testViewAllProgramPositive() throws Exception{
			
		 Program program = new Program(2,LocalDate.of(2020, Month.APRIL, 11),LocalDate.of(2020, Month.JUNE, 11),null,null);
		 Program program1 = new Program(2,LocalDate.of(2020, Month.APRIL, 11),LocalDate.of(2020, Month.JUNE, 11),null,null);
		 
		 List<Program> list = new ArrayList<>();
		 list.add(program);
		 list.add(program1);
		 
		 when(service.viewAllPrograms()).thenReturn(list);
		 
		 mvc.perform(get("/list")).andExpect(status().isOk());
		 
		}
		
		@Test
		public void testViewAllProgramNegative() throws Exception{
			
			when(service.viewAllPrograms()).thenThrow(ElementNotFoundException.class);
			
			mvc.perform(get("/list")).andExpect(status().isNotFound());
		}
		
		@Test
		public void testViewAllProgramByDatePositive() throws Exception{
			
		 Program program = new Program(2,LocalDate.of(2020, Month.APRIL, 11),LocalDate.of(2020, Month.JUNE, 11),null,null);
		 List<Program> list = new ArrayList<>();
		 list.add(program);
		 
		 when(service.viewAllProgramsByDate(LocalDate.of(2020, Month.APRIL, 11))).thenReturn(list);
		 
		 mvc.perform(get("/listbydate/2016-04-11")).andExpect(status().isOk());
		 
		}
		
		@Test
		public void testViewAllProgramByDateNegative() throws Exception {
			
			when(service.viewAllProgramsByDate(LocalDate.of(2020, Month.AUGUST, 11))).thenThrow(ElementNotFoundException.class);
			
			mvc.perform(get("/listbydate"))
					.andExpect(status().isNotFound());
		}
		
		
	}
