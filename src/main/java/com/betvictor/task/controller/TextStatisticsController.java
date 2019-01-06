package com.betvictor.task.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betvictor.task.entity.StatisticsEntity;
import com.betvictor.task.repository.TextStatisticsRepository;
import com.betvictor.task.service.StatisticsHistoryService;
import com.betvictor.task.service.TextStatisticsService;

@RestController
public class TextStatisticsController {
	
	private TextStatisticsService textStatisticsService;
	private StatisticsHistoryService statisticsHistoryService;
	private TextStatisticsRepository textStatisticsRepository;


	
	
	public TextStatisticsController(TextStatisticsService textStatisticsService,
			StatisticsHistoryService statisticsHistoryService,
			TextStatisticsRepository textStatisticsRepository) {
		this.textStatisticsService = textStatisticsService;
		this.statisticsHistoryService = statisticsHistoryService;
		this.textStatisticsRepository = textStatisticsRepository;

	}

	@GetMapping(value="/betvictor/text")
	public ResponseEntity<StatisticsEntity> getStatistics(@RequestParam(value="p_start",required=true) int startParragraphNumber,
			@RequestParam(value="p_end",required=true) int endParragraphNumber,
			@RequestParam(value="w_count_min",required=true)int minWordsPerParragraph,
			@RequestParam(value="w_count_max",required=true) int maxWordsPerParragraph)
	{

		StatisticsEntity  statisticsEntity;
		try {
			statisticsEntity = textStatisticsService.getStatistics(startParragraphNumber, endParragraphNumber, minWordsPerParragraph, maxWordsPerParragraph);			
			textStatisticsRepository.save(statisticsEntity);
			return new ResponseEntity<StatisticsEntity>(statisticsEntity,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<StatisticsEntity>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	

	}
	
	@GetMapping(value="/betvictor/history")
	public ResponseEntity<List<StatisticsEntity>> getHistory(){
		
		return new ResponseEntity<List<StatisticsEntity>>(statisticsHistoryService.getStatisticsHistory(), HttpStatus.OK);
		
	}
}
