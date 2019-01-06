package com.betvictor.task.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.betvictor.task.domain.TextStatistics;
import com.betvictor.task.entity.StatisticsEntity;
import com.betvictor.task.service.external.RandomTextService;

@Service
public class TextStatisticsService {
	private RandomTextService randomTextService;
	private TextStatistics textStatistics;

	public TextStatisticsService(RandomTextService randomTextService) {
		this.randomTextService = randomTextService;
	}
	
	public StatisticsEntity getStatistics(int startParagraphNumber,int endParagraphNumber, int minWordsPerParagraph, int maxWordsPerParagraph) throws Exception {
		textStatistics =new TextStatistics();
		long startRequestProcessingTime = System.nanoTime();
		for (int paragraphSize=startParagraphNumber;paragraphSize<=endParagraphNumber;paragraphSize++) {
			ResponseEntity<String> responseExternalApi = randomTextService.getRandomText(paragraphSize,minWordsPerParagraph,maxWordsPerParagraph);
				textStatistics.proccessTextPerRequest(responseExternalApi.getBody());

		}
		long endRequestProcessingTime = System.nanoTime();
		textStatistics.setTotalProcessingTime( Long.valueOf(endRequestProcessingTime-startRequestProcessingTime));
		return new StatisticsEntity(textStatistics.getMostFrequentWord(),textStatistics.calculateParragraphsSizeAverage(), textStatistics.calculateParragraphProcessingTime(),textStatistics.getTotalProcessingTime());
	}
	
}
