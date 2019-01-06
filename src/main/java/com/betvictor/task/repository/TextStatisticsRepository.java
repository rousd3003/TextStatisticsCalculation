package com.betvictor.task.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.betvictor.task.entity.StatisticsEntity;

@Repository
public interface TextStatisticsRepository extends CrudRepository<StatisticsEntity, Long> {

}
