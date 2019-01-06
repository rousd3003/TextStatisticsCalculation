package com.betvictor.task.service.external;

import org.springframework.http.ResponseEntity;

public interface RandomTextService {

	public ResponseEntity<String> getRandomText(int paragraphSize,int minWordsPerParagraph,int maxWordsPerParagraph);
	
}
