package com.cg.fms.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.cg.fms.entity.Feedback;
import com.cg.fms.entity.Program;
import com.cg.fms.exception.InvalidValueException;
import com.cg.fms.exception.ElementNotFoundException;
import com.cg.fms.rest.FeedbackRestController;
import com.cg.fms.service.FeedbackService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FeedbackRestController.class)
public class TestFeedbackRestController {
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private FeedbackService service;
	
	/* -------------------------Adding Feedback--------------------------*/
	@Test
	public void testAddFeedbackPositive() throws Exception{
		Program program = new Program(2,LocalDate.of(2020, Month.APRIL, 11),LocalDate.of(2020, Month.JUNE, 11),null,null);
		
		Feedback fd = new Feedback(4,22,program,9,9,9,9,9,"good","no suggestion");
		
		when(service.addFeedback(fd)).thenReturn(fd);
		
		mvc.perform(post("/feedback/addFeedback")
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(fd))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testAddFeedbackNegative() throws Exception{
		
		Program program = new Program(2,LocalDate.of(2020, Month.APRIL, 11),LocalDate.of(2020, Month.JUNE, 11),null,null);
		
		Feedback fd = new Feedback(4,22,program,9,9,9,9,9,null,"no suggestion");
		
		when(service.addFeedback(Mockito.any(Feedback.class))).thenThrow(InvalidValueException.class);
		
		mvc.perform(post("/feedback/addFeedback")
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(fd))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotAcceptable());
	}
	
	/* -------------------------View Feedback By Trainer Id--------------------------*/
	
	@Test
	public void testViewTrainerPositive() throws Exception {
		
		Program program = new Program(2,LocalDate.of(2020, Month.APRIL, 11),LocalDate.of(2020, Month.JUNE, 11),null,null);

		Feedback fd = new Feedback(4,22,program,9,9,9,9,9,"good","no suggestion");
		
		List<Feedback> fds = new ArrayList<>();
		fds.add(fd);
		
		when(service.viewTrainerFeedback(22)).thenReturn(fds);
		
		mvc.perform(get("/feedback/trainerid?trainerid=22"))
				.andExpect(status().isOk());
	} 
	
	@Test
	public void testViewTrainerNegative() throws Exception {
		
		Program program = new Program(2,LocalDate.of(2020, Month.APRIL, 11),LocalDate.of(2020, Month.JUNE, 11),null,null);

		Feedback fd = new Feedback(4,0,program,9,9,9,9,9,"good","no suggestion");
		
		List<Feedback> fds = new ArrayList<>();
		fds.add(fd);
		
		when(service.viewTrainerFeedback(0)).thenThrow(ElementNotFoundException.class);
		
		mvc.perform(get("/feedback/trainerid?trainerid=0"))
				.andExpect(status().isNotFound());
	}

	/* -------------------------View Feedback By Program Id--------------------------*/
	
	@Test
	public void testViewProgramPositive() throws Exception {
		
		Program program = new Program(2,LocalDate.of(2020, Month.APRIL, 11),LocalDate.of(2020, Month.JUNE, 11),null,null);

		Feedback fd = new Feedback(4,2,program,9,9,9,9,9,"good","no suggestion");
		
		List<Feedback> fds = new ArrayList<>();
		fds.add(fd);
		
		when(service.viewProgramFeedback(2)).thenReturn(fds);
		
		mvc.perform(get("/feedback/programid?programid=2"))
				.andExpect(status().isOk());
	} 
	
	@Test
	public void testViewProgramNegative() throws Exception {
		Program program = new Program(2,LocalDate.of(2020, Month.APRIL, 11),LocalDate.of(2020, Month.JUNE, 11),null,null);

		Feedback fd = new Feedback(4,0,program,9,9,9,9,9,"good","no suggestion");
		
		List<Feedback> fds = new ArrayList<>();
		fds.add(fd);
		
		when(service.viewProgramFeedback(0)).thenThrow(ElementNotFoundException.class);
		
		mvc.perform(get("/feedback/programid?programid=0"))
				.andExpect(status().isNotFound());
	}
}
