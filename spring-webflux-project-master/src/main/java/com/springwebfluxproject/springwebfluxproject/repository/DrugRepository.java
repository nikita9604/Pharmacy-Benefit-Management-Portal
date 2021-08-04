package com.springwebfluxproject.springwebfluxproject.repository;


import com.springwebfluxproject.springwebfluxproject.entity.Drug;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface DrugRepository extends ReactiveCrudRepository<Drug,Integer>,DrugRepositoryExtended{

//    @Query("select count(*) > 0 from InsuranceDrug where did = :did and inid:inid")
//    Mono<Boolean> checkIfDrugCoveredByInsurance(int did, int inid);
}
