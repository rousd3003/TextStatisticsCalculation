package com.betvictor.task.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.betvictor.task.service.external.RandomTextService;
import com.betvictor.task.service.external.RandomTextServiceImpl;

@Configuration
public class TextStatisticsConfiguration {
	
	
	public RandomTextService textService() {
		return new RandomTextServiceImpl(restTemplate(),httpEntity());
	}

	@Bean
	public HttpEntity<String> httpEntity() {
		return new HttpEntity<>("parameters", httpHeaders());
	}

	@Bean
	public HttpHeaders httpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		return headers;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	};
    
    
    
}
