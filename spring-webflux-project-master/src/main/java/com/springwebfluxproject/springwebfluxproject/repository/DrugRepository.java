package com.springwebfluxproject.springwebfluxproject.repository;


import com.springwebfluxproject.springwebfluxproject.entity.Drug;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface DrugRepository extends ReactiveCrudRepository<Drug,Integer> {
  @Query("select count(*) > 0 from Drug where dname = :dname")
    Mono<Boolean> hasDrug(String dname);
}
