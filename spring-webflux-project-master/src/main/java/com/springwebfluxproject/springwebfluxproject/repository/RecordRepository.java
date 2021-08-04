package com.springwebfluxproject.springwebfluxproject.repository;

import com.springwebfluxproject.springwebfluxproject.entity.Record;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RecordRepository extends ReactiveCrudRepository<Record,Integer> {
}
