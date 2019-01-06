package com.betvictor.task.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.betvictor.task.domain.TextStatistics;
import com.betvictor.task.service.TextStatisticsService;
import com.betvictor.task.service.external.RandomTextService;


public class TextStatisticsServiceTest {

	TextStatisticsService service;
	@Mock
	RandomTextService randomTextService;
	@Mock
	TextStatistics textStatistics;
	@Mock
	ResponseEntity<String> response;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		service = new TextStatisticsService(randomTextService);
		
	}
	
	@Test
	public void testGivenRandomTestParametersReturnAllStatistics() throws Exception{
		String body = "{\"type\": \"gibberish\",\"amount\": 2,\"number\": \"5\",\"number_max\": \"10\",\"format\": \"p\",\"time\": \"11:02:20\",\"text_out\": \"<p>Input that until according snickered jaguar much much.</p>\\r<p>Away gazelle darn bland toward permissive flew besides on.</p>\\r\"}";

		Mockito.when(randomTextService.getRandomText(Matchers.anyInt(), Matchers.anyInt(), Matchers.anyInt())).thenReturn(response);
		Mockito.when(response.getBody()).thenReturn(body);
		assertNotNull( service.getStatistics(1, 4, 5, 10));
		
		
	}
	
	


}
