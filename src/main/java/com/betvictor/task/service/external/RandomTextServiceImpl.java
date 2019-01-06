package com.betvictor.task.service.external;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RandomTextServiceImpl implements RandomTextService {

	
	private RestTemplate restTemplate ;
    private HttpEntity<String> entity;
      

	
	public RandomTextServiceImpl(RestTemplate restTemplate, HttpEntity<String> entity) {
		this.restTemplate = restTemplate;
		this.entity = entity;
	}



	public ResponseEntity<String> getRandomText(int parragraphSize,int minWordsPerParragraph,int maxWordsPerParragraph){
		return restTemplate.exchange("http://www.randomtext.me/api/gibberish/p-"+parragraphSize+"/"+minWordsPerParragraph+"-"+maxWordsPerParragraph,HttpMethod.GET,entity, String.class);
	}
}
