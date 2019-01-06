package com.betvictor.task.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StatisticsEntity {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String mostFrequentWord;
	private Double averageParragraphSize;
	private Double averageParragraphProcessTime;
	private Long totalProcessingTime;
	
	
	
	public StatisticsEntity() {}
		
	public StatisticsEntity(String mostFrequentWord, Double averageParragraphSize, Double averageParragraphProcessTime,
			Long totalProcessingTime) {
		this.mostFrequentWord = mostFrequentWord;
		this.averageParragraphSize = averageParragraphSize;
		this.averageParragraphProcessTime = averageParragraphProcessTime;
		this.totalProcessingTime = totalProcessingTime;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMostFrequentWord() {
		return mostFrequentWord;
	}
	public void setMostFrequentWord(String mostFrequentWord) {
		this.mostFrequentWord = mostFrequentWord;
	}
	public Double getAverageParragraphSize() {
		return averageParragraphSize;
	}
	public void setAverageParragraphSize(Double averageParragraphSize) {
		this.averageParragraphSize = averageParragraphSize;
	}
	public Double getAverageParragraphProcessTime() {
		return averageParragraphProcessTime;
	}
	public void setAverageParragraphProcessTime(Double averageParragraphProcessTime) {
		this.averageParragraphProcessTime = averageParragraphProcessTime;
	}
	public Long getTotalProcessingTime() {
		return totalProcessingTime;
	}
	public void setTotalProcessingTime(Long totalProcessingTime) {
		this.totalProcessingTime = totalProcessingTime;
	}
	
}
