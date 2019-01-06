package com.betvictor.task.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betvictor.task.entity.StatisticsEntity;
import com.betvictor.task.repository.TextStatisticsRepository;

@Service
public class StatisticsHistoryService  {

	TextStatisticsRepository textStatisticsRepository;

	public StatisticsHistoryService(TextStatisticsRepository textStatisticsRepository) {
		this.textStatisticsRepository = textStatisticsRepository;
	}
	
	
	public List<StatisticsEntity> getStatisticsHistory(){
		List<StatisticsEntity> dbList = (List<StatisticsEntity>) textStatisticsRepository.findAll();
		dbList.sort(Comparator.comparingLong(StatisticsEntity::getId).reversed());
		return dbList.stream().limit(10).collect(Collectors.toList());
		
	}
}
