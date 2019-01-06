package com.betvictor.task.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.betvictor.task.controller.TextStatisticsController;
import com.betvictor.task.repository.TextStatisticsRepository;
import com.betvictor.task.service.StatisticsHistoryService;
import com.betvictor.task.service.TextStatisticsService;


@RunWith(SpringRunner.class)
@WebMvcTest(TextStatisticsController.class)
public class TextStatisticsControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	TextStatisticsService service;
	
	@MockBean
	StatisticsHistoryService statisticsHistoryService;
	
	@MockBean
	TextStatisticsRepository repository;
	
	@Test
	public void testCallPassingParameterWillReturnStatusOk() throws Exception {
		
		mockMvc.perform(get("/betvictor/text").param("p_start", "1").param("p_end", "3").param("w_count_min", "5").param("w_count_max", "7")).andExpect(status().isOk());
	}
	
	@Test
	public void testCallWithoutParrameterWillReturnBadRequestStatus() throws Exception {
		mockMvc.perform(get("/betvictor/text")).andExpect(status().isBadRequest());

	}
	
	@Test
	public void testThrowingExceptionFormServiceWillReturnInternalServerErrorStatus() throws Exception {
		Mockito.when(service.getStatistics(Matchers.anyInt(), Matchers.anyInt(), Matchers.anyInt(), Matchers.anyInt())).thenThrow(new Exception());
		mockMvc.perform(get("/betvictor/text").param("p_start", "1").param("p_end", "3").param("w_count_min", "5").param("w_count_max", "7")).andExpect(status().isInternalServerError());
		
	}
	
	@Test
	public void testGetHistoryWillReturnStatusOk() throws Exception {
		mockMvc.perform(get("/betvictor/history")).andExpect(status().isOk());
		
	}

}
