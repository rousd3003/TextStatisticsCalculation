package com.betvictor.task.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class StatisticsEntityTest {
	
	@Test
	public void testGettersFromStatisticsEntity() {
		
		StatisticsEntity entity = new StatisticsEntity("hello", Double.valueOf("55.445555"), Double.valueOf("44444.333"), Long.valueOf("22355555555"));
		assertEquals(  Double.valueOf("55.445555"),entity.getAverageParragraphSize());
		assertEquals(Double.valueOf("44444.333"), entity.getAverageParragraphProcessTime());
		assertEquals("hello",entity.getMostFrequentWord());
		assertEquals(Long.valueOf("22355555555"),entity.getTotalProcessingTime());
	}

}
