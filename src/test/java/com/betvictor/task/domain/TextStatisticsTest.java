
  package com.betvictor.task.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.betvictor.task.domain.TextStatistics;

public class TextStatisticsTest {
	
	  TextStatistics textMetrics;
	  
	  @Before
	  public void setUp() {
		  textMetrics = new TextStatistics();
	  }
    
	  @Test
	  public void testMetricsCanBeCalculatedAfterProcessingText() throws Exception {
		  
		  String body = "{\"type\": \"gibberish\",\"amount\": 2,\"number\": \"5\",\"number_max\": \"10\",\"format\": \"p\",\"time\": \"11:02:20\",\"text_out\": \"<p>Input that until according snickered jaguar much much.</p>\\r<p>Away gazelle darn bland toward permissive flew besides on.</p>\\r\"}";
		  textMetrics.proccessTextPerRequest(body);
		  assertTrue(textMetrics.getMostFrequentWord() instanceof String);
		  assertTrue(textMetrics.getMostFrequentWord() != null);
		  assertTrue( textMetrics.calculateParragraphsSizeAverage()>0);
		  assertTrue(textMetrics.calculateParragraphProcessingTime()!=null);
	  }
	  
	  @Test(expected = Exception.class)
	  public void testNullBodyShouldThrowException() throws Exception {
		  String body =null;
		  textMetrics.proccessTextPerRequest(body);  
	  }
	  
	  @Test(expected = Exception.class)
	  public void testNullTextOutShouldThrowException() throws Exception {
		  String body = "{\"type\": \"gibberish\",\"amount\": 2,\"number\": \"5\",\"number_max\": \"10\",\"format\": \"p\",\"time\": \"11:02:20\"}";
		  textMetrics.proccessTextPerRequest(body);
	  }
  }
 
